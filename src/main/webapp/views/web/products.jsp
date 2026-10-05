<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container mt-4">
    <h2>Danh sách sản phẩm</h2>
    <div class="row">
        <c:forEach items="${products}" var="p">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${p.productName}</h5>
                        <p class="card-text">Giá: <fmt:formatNumber value="${p.price}" type="currency" currencySymbol="VNĐ"/></p>
                        <p class="card-text">Kho: ${p.quantity}</p>
                        <p class="card-text">${p.description}</p>
                        <form action="${pageContext.request.contextPath}/cart/add" method="post">
                            <input type="hidden" name="id" value="${p.productId}">
                            <button type="submit" class="btn btn-primary">Thêm vào giỏ</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
