<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>Giỏ hàng</title>
</head>
<body>
<div class="container mt-4">
    <h2>Giỏ hàng của bạn</h2>
    <c:if test="${empty sessionScope.cart}">
        <p>Giỏ hàng trống.</p>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Tiếp tục mua sắm</a>
    </c:if>
    <c:if test="${not empty sessionScope.cart}">
        <table class="table">
            <thead>
                <tr>
                    <th>Sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Tổng</th>
                    <th>Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:set var="totalSum" value="0" />
                <c:forEach items="${sessionScope.cart}" var="item">
                    <tr>
                        <td>${item.video.title}</td>
                        <td><fmt:formatNumber value="${item.video.price}" type="currency" currencySymbol="VNĐ"/></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/cart/update" method="post" class="d-inline">
                                <input type="hidden" name="id" value="${item.video.videoId}">
                                <input type="number" name="quantity" value="${item.quantity}" min="1" max="${item.video.quantity}" style="width: 60px;">
                                <button type="submit" class="btn btn-sm btn-info">Cập nhật</button>
                            </form>
                        </td>
                        <td><fmt:formatNumber value="${item.totalPrice}" type="currency" currencySymbol="VNĐ"/></td>
                        <td>
                            <a href="${pageContext.request.contextPath}/cart/remove?id=${item.video.videoId}" class="btn btn-sm btn-danger">Xóa</a>
                        </td>
                    </tr>
                    <c:set var="totalSum" value="${totalSum + item.totalPrice}" />
                </c:forEach>
            </tbody>
        </table>
        <h4>Tổng cộng: <fmt:formatNumber value="${totalSum}" type="currency" currencySymbol="VNĐ"/></h4>
        <a href="${pageContext.request.contextPath}/checkout" class="btn btn-success">Thanh toán COD</a>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Tiếp tục mua sắm</a>
    </c:if>
</div>
</body>
