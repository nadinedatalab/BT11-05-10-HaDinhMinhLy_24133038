<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Video Mới</title>
</head>
<body>
    <h2>Upload/Thêm Video</h2>
    <form action="${pageContext.request.contextPath}/admin/videos/insert" method="post" enctype="multipart/form-data">
        Mã Video (ID): <input type="text" name="videoId" required><br><br>
        Tiêu đề: <input type="text" name="title" required><br><br>
        Poster (Upload): <input type="file" name="posterFile" accept="image/*"><br><br>
        View mặc định: <input type="number" name="views" value="0"><br><br>
        Mô tả: <textarea name="description"></textarea><br><br>
        Kích hoạt: <input type="checkbox" name="active" value="true" checked><br><br>
        Category: 
        <select name="categoryId">
            <c:forEach var="cat" items="${categories}">
                <option value="${cat.categoryId}">${cat.categoryname}</option>
            </c:forEach>
        </select><br><br>
        
        <button type="submit">Lưu Video</button>
    </form>
</body>
</html>
