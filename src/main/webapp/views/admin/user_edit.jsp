<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa User</title>
</head>
<body>
    <h2>Cập nhật User</h2>
    <form action="${pageContext.request.contextPath}/admin/users/update" method="post" enctype="multipart/form-data">
        Username: <input type="text" name="username" value="${user.username}" readonly><br><br>
        Password: <input type="password" name="password" value="${user.password}" required><br><br>
        Fullname: <input type="text" name="fullname" value="${user.fullname}" required><br><br>
        Email: <input type="email" name="email" value="${user.email}" required><br><br>
        Phone: <input type="text" name="phone" value="${user.phone}"><br><br>
        Images URL hiện tại: <input type="text" name="images" value="${user.images}" readonly><br>
        Upload ảnh mới: <input type="file" name="imageFile" accept="image/*"><br><br>
        Admin: <input type="checkbox" name="admin" value="true" ${user.admin ? 'checked' : ''}><br><br>
        Active: <input type="checkbox" name="active" value="true" ${user.active ? 'checked' : ''}><br><br>
        
        <button type="submit">Cập nhật</button>
        <a href="${pageContext.request.contextPath}/admin/users">Huỷ</a>
    </form>
</body>
</html>
