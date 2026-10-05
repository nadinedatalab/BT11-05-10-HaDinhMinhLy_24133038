<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Thanh toán COD</title>
</head>
<body>
<div class="container mt-4">
    <h2>Thanh toán COD</h2>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/checkout" method="post">
        <div class="form-group mb-3">
            <label>Họ tên:</label>
            <input type="text" class="form-control" value="${sessionScope.user.fullname}" readonly>
        </div>
        <div class="form-group mb-3">
            <label>Số điện thoại:</label>
            <input type="text" class="form-control" name="phone" value="${sessionScope.user.phone}" required>
        </div>
        <div class="form-group mb-3">
            <label>Địa chỉ giao hàng:</label>
            <textarea class="form-control" name="address" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Xác nhận đặt hàng</button>
    </form>
</div>
</body>
