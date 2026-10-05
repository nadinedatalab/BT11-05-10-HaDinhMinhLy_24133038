<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Đăng ký tài khoản</title>
    <style>
        .auth-container { max-width: 450px; margin: 60px auto; background: white; padding: 40px; border-radius: 16px; box-shadow: 0 10px 25px -5px rgba(0,0,0,0.1); }
        .auth-title { text-align: center; font-size: 28px; font-weight: 700; color: var(--text-main); margin-bottom: 30px; }
        .auth-error { background: #fee2e2; color: #ef4444; padding: 12px; border-radius: 8px; margin-bottom: 20px; font-size: 14px; text-align: center; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; font-weight: 600; font-size: 14px; color: var(--text-main); }
        .form-group input { width: 100%; padding: 12px 16px; border: 1px solid #cbd5e1; border-radius: 8px; font-size: 15px; font-family: inherit; transition: all 0.2s; outline: none; }
        .form-group input:focus { border-color: var(--primary); box-shadow: 0 0 0 3px rgba(59, 7, 100, 0.1); }
        .btn-submit { width: 100%; padding: 14px; background: var(--primary); color: white; border: none; border-radius: 8px; font-size: 16px; font-weight: 600; cursor: pointer; transition: all 0.2s; font-family: inherit; margin-bottom: 15px; }
        .btn-submit:hover { background: var(--primary-hover); transform: translateY(-2px); }
        .btn-outline { display: block; width: 100%; padding: 14px; background: transparent; color: var(--primary); border: 2px solid var(--primary); border-radius: 8px; font-size: 16px; font-weight: 600; cursor: pointer; transition: all 0.2s; text-align: center; text-decoration: none; font-family: inherit; }
        .btn-outline:hover { background: #f8fafc; transform: translateY(-2px); }
        .auth-divider { display: flex; align-items: center; text-align: center; margin: 20px 0; color: var(--text-muted); font-size: 14px; }
        .auth-divider::before, .auth-divider::after { content: ''; flex: 1; border-bottom: 1px solid #cbd5e1; }
        .auth-divider::not(:empty)::before { margin-right: .25em; }
        .auth-divider::not(:empty)::after { margin-left: .25em; }
        .auth-info { text-align: center; font-size: 13px; color: var(--text-muted); margin-bottom: 20px; }
    </style>
</head>
<body>
    <div class="auth-container">
        <h2 class="auth-title">Đăng Ký Tài Khoản</h2>
        
        <div class="auth-info">Mã OTP kích hoạt sẽ được gửi tới Email của bạn.</div>
        
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" placeholder="Nhập tên đăng nhập" required>
            </div>
            
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" placeholder="Tạo mật khẩu" required>
            </div>
            
            <div class="form-group">
                <label for="email">Địa chỉ Email</label>
                <input type="email" id="email" name="email" placeholder="Nhập địa chỉ Email" required>
            </div>
            
            <button type="submit" class="btn-submit">Đăng ký (Nhận OTP)</button>
        </form>
        
        <div class="auth-divider">đã có tài khoản?</div>
        
        <a href="${pageContext.request.contextPath}/login" class="btn-outline">Quay lại Đăng nhập</a>
    </div>
</body>
