<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm User</title>
</head>
<body>
    <h2>Thêm User Mới</h2>
    <form action="${pageContext.request.contextPath}/admin/users/insert" method="post" enctype="multipart/form-data">
        Username: <input type="text" name="username" required><br><br>
        Password: <input type="password" name="password" required><br><br>
        Fullname: <input type="text" name="fullname" required><br><br>
        Email: <input type="email" name="email" required><br><br>
        Phone: <input type="text" name="phone"><br><br>
        Images (Upload): <input type="file" name="imageFile" accept="image/*"><br><br>
        Admin: <input type="checkbox" name="admin" value="true"><br><br>
        Active: <input type="checkbox" name="active" value="true" checked><br><br>
        
        <button type="submit">Lưu</button>
        <a href="${pageContext.request.contextPath}/admin/users">Huỷ</a>
    </form>
</body>
</html>
