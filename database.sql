CREATE DATABASE TestQT_60;
GO
USE TestQT_60;
GO

CREATE TABLE Users (
    Username nvarchar(50) PRIMARY KEY,
    Password nvarchar(50) NOT NULL,
    Phone nvarchar(15),
    Fullname nvarchar(50),
    Email nvarchar(150),
    Admin bit,
    Active bit,
    Images nvarchar(500)
);

CREATE TABLE Category (
    CategoryId int IDENTITY(1,1) PRIMARY KEY,
    Categoryname nvarchar(100),
    Categorycode nvarchar(100),
    Images nvarchar(500),
    Status bit
);

CREATE TABLE Videos (
    VideoId nvarchar(50) PRIMARY KEY,
    Title nvarchar(200),
    Poster nvarchar(50),
    Views int,
    Description nvarchar(500),
    Active bit,
    CategoryId int FOREIGN KEY REFERENCES Category(CategoryId),
    Price decimal(18,2) DEFAULT 0,
    Quantity int DEFAULT 0
);

CREATE TABLE Shares (
    ShareId int IDENTITY(1,1) PRIMARY KEY,
    Emails nvarchar(50),
    SharedDate date,
    Username nvarchar(50) FOREIGN KEY REFERENCES Users(Username),
    VideoId nvarchar(50) FOREIGN KEY REFERENCES Videos(VideoId)
);

CREATE TABLE Favorites (
    FavoriteId int IDENTITY(1,1) PRIMARY KEY,
    LikedDate date,
    VideoId nvarchar(50) FOREIGN KEY REFERENCES Videos(VideoId),
    Username nvarchar(50) FOREIGN KEY REFERENCES Users(Username)
);

-- Insert dummy data
INSERT INTO Users VALUES ('admin', '123', '0123456789', 'Administrator', 'admin@gmail.com', 1, 1, '');
INSERT INTO Users VALUES ('user1', '123', '0987654321', 'User 1', 'user1@gmail.com', 0, 1, '');
INSERT INTO Users VALUES ('user2', '123', '0987654321', 'User 2', 'user2@gmail.com', 0, 1, '');
INSERT INTO Users VALUES ('user3', '123', '0987654321', 'User 3', 'user3@gmail.com', 0, 1, '');
INSERT INTO Users VALUES ('user4', '123', '0987654321', 'User 4', 'user4@gmail.com', 0, 1, '');
INSERT INTO Users VALUES ('user5', '123', '0987654321', 'User 5', 'user5@gmail.com', 0, 1, '');
INSERT INTO Users VALUES ('user6', '123', '0987654321', 'User 6', 'user6@gmail.com', 0, 1, '');
INSERT INTO Users VALUES ('user7', '123', '0987654321', 'User 7', 'user7@gmail.com', 0, 1, '');

INSERT INTO Category VALUES (N'Phim Hàn', 'PHIMHAN', '', 1);
INSERT INTO Category VALUES (N'Phim Kiếm Hiệp', 'KIEMHIEP', '', 1);

INSERT INTO Videos VALUES ('V01', N'Video Phim Hàn 1', 'poster1.jpg', 100, N'Mô tả video 1', 1, 1, 50000, 100);
INSERT INTO Videos VALUES ('V02', N'Video Phim Hàn 2', 'poster2.jpg', 150, N'Mô tả video 2', 1, 1, 60000, 50);
INSERT INTO Videos VALUES ('V03', N'Video Phim Hàn 3', 'poster3.jpg', 200, N'Mô tả video 3', 1, 1, 70000, 20);
INSERT INTO Videos VALUES ('V04', N'Video Phim Hàn 4', 'poster4.jpg', 50, N'Mô tả video 4', 1, 1, 40000, 200);
INSERT INTO Videos VALUES ('V05', N'Video Kiếm Hiệp 1', 'poster5.jpg', 300, N'Mô tả video 5', 1, 2, 80000, 10);

-- E-commerce tables
CREATE TABLE Orders (
    OrderId int IDENTITY(1,1) PRIMARY KEY,
    Username nvarchar(50) FOREIGN KEY REFERENCES Users(Username),
    OrderDate datetime,
    TotalAmount decimal(18,2),
    PaymentMethod nvarchar(50),
    Status nvarchar(100),
    Address nvarchar(500),
    Phone nvarchar(15)
);

CREATE TABLE OrderDetails (
    OrderId int FOREIGN KEY REFERENCES Orders(OrderId),
    VideoId nvarchar(50) FOREIGN KEY REFERENCES Videos(VideoId),
    Quantity int,
    Price decimal(18,2),
    PRIMARY KEY (OrderId, VideoId)
);
