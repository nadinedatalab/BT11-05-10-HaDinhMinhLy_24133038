<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Users</title>
</head>
<body>
    <h2>Danh sách Users</h2>
    <a href="${pageContext.request.contextPath}/admin/users/insert">Thêm User mới</a>
    <br><br>
    <table border="1" cellpadding="5" cellspacing="0" width="100%">
        <tr>
            <th>Username</th>
            <th>Fullname</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Admin</th>
            <th>Active</th>
            <th>Hành động</th>
        </tr>
        <c:forEach var="u" items="${users}">
            <tr>
                <td>${u.username}</td>
                <td>${u.fullname}</td>
                <td>${u.email}</td>
                <td>${u.phone}</td>
                <td>${u.admin}</td>
                <td>${u.active}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/users/update?username=${u.username}">Sửa</a> | 
                    <a href="${pageContext.request.contextPath}/admin/users/delete?username=${u.username}" onclick="return confirm('Bạn có chắc muốn xoá?');">Xoá</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <!-- Phân trang -->
    <div style="text-align:center; margin-top: 20px;">
        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${currentPage == i}">
                    <strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/admin/users?page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</body>
</html>
