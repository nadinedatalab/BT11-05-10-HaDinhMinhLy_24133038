<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Trang Chủ Admin</title>
    <style>
        .admin-dashboard { padding: 20px; text-align: center; }
        .dashboard-cards { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; margin-top: 30px; }
        .dash-card { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); transition: transform 0.2s; text-decoration: none; color: var(--text-main); border: 1px solid #e2e8f0; }
        .dash-card:hover { transform: translateY(-5px); border-color: var(--primary); color: var(--primary); box-shadow: 0 10px 15px -3px rgba(0,0,0,0.1); }
        .dash-icon { width: 48px; height: 48px; background: #fee2e2; color: var(--primary); border-radius: 12px; display: flex; align-items: center; justify-content: center; margin: 0 auto 15px; }
        .dash-title { font-size: 18px; font-weight: 600; }
    </style>
</head>
<body>
    <div class="admin-dashboard">
        <h2>Chào mừng quay trở lại, ${sessionScope.user.username}!</h2>
        <p style="color: var(--text-muted); margin-top: 10px;">Bạn đang ở trong khu vực Quản trị hệ thống.</p>
        
        <div class="dashboard-cards">
            <a href="${pageContext.request.contextPath}/admin/videos/insert" class="dash-card">
                <div class="dash-icon">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path><polyline points="17 8 12 3 7 8"></polyline><line x1="12" y1="3" x2="12" y2="15"></line></svg>
                </div>
                <div class="dash-title">Thêm Video Mới</div>
            </a>
            <a href="${pageContext.request.contextPath}/admin/users" class="dash-card">
                <div class="dash-icon">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                </div>
                <div class="dash-title">Quản lý Người dùng</div>
            </a>
        </div>
    </div>
</body>
