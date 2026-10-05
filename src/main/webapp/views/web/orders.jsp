<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>Lịch sử đơn hàng</title>
</head>
<body>
<div class="container mt-4">
    <h2>Lịch sử đặt hàng</h2>
    <c:if test="${param.success == 1}">
        <div class="alert alert-success">Đặt hàng thành công!</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/orders" method="get" class="mb-4">
        <label>Lọc theo trạng thái:</label>
        <select name="status" class="form-control d-inline w-auto" onchange="this.form.submit()">
            <option value="">-- Tất cả --</option>
            <option value="Đơn hàng mới" ${currentStatus == 'Đơn hàng mới' ? 'selected' : ''}>Đơn hàng mới</option>
            <option value="Đã xác nhận" ${currentStatus == 'Đã xác nhận' ? 'selected' : ''}>Đã xác nhận</option>
            <option value="Chuẩn bị hàng" ${currentStatus == 'Chuẩn bị hàng' ? 'selected' : ''}>Chuẩn bị hàng</option>
            <option value="Vận chuyển" ${currentStatus == 'Vận chuyển' ? 'selected' : ''}>Vận chuyển</option>
            <option value="Giao hàng" ${currentStatus == 'Giao hàng' ? 'selected' : ''}>Giao hàng</option>
            <option value="Đã giao" ${currentStatus == 'Đã giao' ? 'selected' : ''}>Đã giao</option>
            <option value="Đơn hàng hủy" ${currentStatus == 'Đơn hàng hủy' ? 'selected' : ''}>Đơn hàng hủy</option>
            <option value="Đơn hàng hoàn" ${currentStatus == 'Đơn hàng hoàn' ? 'selected' : ''}>Đơn hàng hoàn</option>
        </select>
    </form>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Mã ĐH</th>
                <th>Ngày đặt</th>
                <th>Tổng tiền</th>
                <th>Phương thức</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${orders}" var="o">
                <tr>
                    <td>${o.orderId}</td>
                    <td><fmt:formatDate value="${o.orderDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                    <td><fmt:formatNumber value="${o.totalAmount}" type="currency" currencySymbol="VNĐ"/></td>
                    <td>${o.paymentMethod}</td>
                    <td><span class="badge bg-info text-dark">${o.status}</span></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/order/details?id=${o.orderId}" class="btn btn-sm btn-primary">Chi tiết</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
