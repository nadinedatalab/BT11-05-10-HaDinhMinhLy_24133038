<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>Chi tiết đơn hàng</title>
</head>
<body>
<div class="container mt-4">
    <h2>Chi tiết đơn hàng #${param.id}</h2>
    <table class="table table-bordered mt-3">
        <thead>
            <tr>
                <th>Tên sản phẩm</th>
                <th>Số lượng</th>
                <th>Đơn giá</th>
                <th>Thành tiền</th>
            </tr>
        </thead>
        <tbody>
            <c:set var="sum" value="0" />
            <c:forEach items="${details}" var="d">
                <tr>
                    <td>${d.productName}</td>
                    <td>${d.quantity}</td>
                    <td><fmt:formatNumber value="${d.price}" type="currency" currencySymbol="VNĐ"/></td>
                    <td><fmt:formatNumber value="${d.price * d.quantity}" type="currency" currencySymbol="VNĐ"/></td>
                </tr>
                <c:set var="sum" value="${sum + d.price * d.quantity}" />
            </c:forEach>
        </tbody>
    </table>
    <h4>Tổng giá trị: <fmt:formatNumber value="${sum}" type="currency" currencySymbol="VNĐ"/></h4>
    <a href="${pageContext.request.contextPath}/orders" class="btn btn-secondary">Quay lại</a>
</div>
</body>
