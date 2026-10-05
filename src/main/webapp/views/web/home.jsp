<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Trang Chủ</title>
    <style>
        .hero { background: linear-gradient(135deg, var(--primary) 0%, #3b82f6 100%); border-radius: 20px; padding: 60px 40px; color: white; text-align: center; margin-bottom: 40px; box-shadow: 0 10px 25px -5px rgba(37, 99, 235, 0.4); }
        .hero h1 { font-size: 42px; font-weight: 700; margin-bottom: 15px; }
        .hero p { font-size: 18px; opacity: 0.9; max-width: 600px; margin: 0 auto; line-height: 1.6; }
        .section-title { font-size: 24px; font-weight: 700; margin-bottom: 25px; color: var(--text-main); display: flex; align-items: center; gap: 10px; }
        .section-title::before { content: ""; display: block; width: 4px; height: 24px; background: var(--primary); border-radius: 2px; }
        .cat-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); gap: 24px; }
        .cat-card { background: white; border-radius: 16px; padding: 24px; text-align: center; text-decoration: none; color: var(--text-main); transition: all 0.3s ease; box-shadow: 0 4px 6px -1px rgba(0,0,0,0.05); border: 1px solid #e2e8f0; display: flex; flex-direction: column; align-items: center; justify-content: center; min-height: 160px; }
        .cat-card:hover { transform: translateY(-8px); box-shadow: 0 20px 25px -5px rgba(0,0,0,0.1), 0 10px 10px -5px rgba(0,0,0,0.04); border-color: var(--primary); color: var(--primary); }
        .cat-icon { width: 64px; height: 64px; background: #eff6ff; color: var(--primary); border-radius: 50%; display: flex; align-items: center; justify-content: center; margin-bottom: 15px; transition: all 0.3s ease; }
        .cat-card:hover .cat-icon { background: var(--primary); color: white; transform: scale(1.1); }
        .cat-name { font-size: 18px; font-weight: 600; }
    </style>
</head>
<body>
    <div class="hero">
        <h1>Chào mừng đến với 24133038-Videoterest</h1>
        <p>Nền tảng chia sẻ và trải nghiệm những video chất lượng nhất. Khám phá hàng ngàn video thuộc nhiều thể loại khác nhau ngay hôm nay!</p>
    </div>

    <h2 class="section-title">Khám Phá Danh Mục</h2>
    <div class="cat-grid">
        <c:forEach var="cat" items="${categories}">
            <a href="${pageContext.request.contextPath}/category?id=${cat.categoryId}" class="cat-card">
                <div class="cat-icon">
                    <svg width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polygon points="5 3 19 12 5 21 5 3"></polygon></svg>
                </div>
                <div class="cat-name">${cat.categoryname}</div>
            </a>
        </c:forEach>
    </div>
</body>
