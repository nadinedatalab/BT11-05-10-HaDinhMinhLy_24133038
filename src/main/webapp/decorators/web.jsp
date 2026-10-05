<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><decorator:title default="Video Hub"/></title>
    <link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        :root {
            --primary: #3b0764; /* Deep Purple */
            --primary-hover: #2e1065;
            --bg-color: #f8fafc;
            --text-main: #1e293b;
            --text-muted: #64748b;
            --card-bg: #ffffff;
        }
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: 'Outfit', sans-serif; background-color: var(--bg-color); color: var(--text-main); display: flex; flex-direction: column; min-height: 100vh; }
        
        /* Modern Header */
        header {
            background: rgba(255, 255, 255, 0.8);
            backdrop-filter: blur(10px);
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
            position: sticky; top: 0; z-index: 50;
            padding: 15px 40px; display: flex; justify-content: space-between; align-items: center;
        }
        .logo { font-size: 24px; font-weight: 700; color: var(--primary); text-decoration: none; display: flex; align-items: center; gap: 8px;}
        .nav-links { display: flex; gap: 20px; align-items: center; }
        .nav-links a { color: var(--text-main); text-decoration: none; font-weight: 600; padding: 8px 16px; border-radius: 8px; transition: all 0.3s ease; }
        .nav-links a:hover { background: #f1f5f9; color: var(--primary); }
        .nav-links .btn-login { background: var(--primary); color: white; }
        .nav-links .btn-login:hover { background: var(--primary-hover); color: white; transform: translateY(-2px); }

        main { flex: 1; padding: 40px; max-width: 1200px; margin: 0 auto; width: 100%; }
        
        /* Footer */
        footer { background: #0f172a; color: #94a3b8; text-align: center; padding: 20px; margin-top: auto; font-size: 14px; }
        footer strong { color: white; }
        
        /* Global Card Style */
        .card { background: var(--card-bg); border-radius: 12px; box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1); padding: 20px; transition: transform 0.3s; }
        .card:hover { transform: translateY(-5px); box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1); }
    </style>
    <decorator:head/>
</head>
<body>
    <header>
        <a href="${pageContext.request.contextPath}/home" class="logo">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polygon points="5 3 19 12 5 21 5 3"></polygon></svg>
            24133038-Videoterest
        </a>
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/home">Trang Chủ</a>
            <a href="${pageContext.request.contextPath}/cart">Giỏ hàng</a>
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <c:if test="${sessionScope.user.admin}">
                        <a href="${pageContext.request.contextPath}/admin/home">Quản trị Admin</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/orders">Lịch sử ĐH</a>
                    <a href="${pageContext.request.contextPath}/logout" class="btn-login">Đăng xuất (${sessionScope.user.username})</a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login" class="btn-login" style="background: transparent; color: var(--primary); border: 2px solid var(--primary);">Đăng nhập</a>
                    <a href="${pageContext.request.contextPath}/register" class="btn-login">Đăng ký</a>
                </c:otherwise>
            </c:choose>
        </div>
    </header>

    <main>
        <decorator:body/>
    </main>

    <footer>
        Họ tên: Hà Đinh Minh Lý - MSSV: 24133038 - Mã đề: 04
    </footer>
</body>
</html>
