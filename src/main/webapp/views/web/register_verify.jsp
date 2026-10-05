<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Xác thực OTP</title>
</head>
<body>
    <h2>Xác thực OTP</h2>
    <p>Mã OTP đã được gửi về email của bạn (Nếu dùng bản test không gửi email thực tế, mã là 123456)</p>
    <h3 style="color:red">${error}</h3>
    <form action="${pageContext.request.contextPath}/register-verify" method="post">
        Mã OTP: <input type="text" name="otp" required><br><br>
        <button type="submit">Xác nhận OTP</button>
    </form>
</body>
</html>
