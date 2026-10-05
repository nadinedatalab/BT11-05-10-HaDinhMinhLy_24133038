<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>${video.title}</title>
    <style>
        .video-container { display: flex; flex-direction: column; gap: 30px; background: white; border-radius: 20px; box-shadow: 0 10px 25px -5px rgba(0,0,0,0.05); padding: 30px; margin-top: 20px; }
        @media (min-width: 768px) { .video-container { flex-direction: row; } }
        
        .video-player-section { flex: 2; }
        .video-player { width: 100%; border-radius: 12px; overflow: hidden; background: #0f172a; position: relative; padding-top: 56.25%; box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1); }
        .video-player img { position: absolute; top: 0; left: 0; width: 100%; height: 100%; object-fit: contain; }
        
        .video-details-section { flex: 1; display: flex; flex-direction: column; }
        .vd-category { display: inline-block; background: #eff6ff; color: var(--primary); padding: 6px 16px; border-radius: 20px; font-size: 14px; font-weight: 600; text-decoration: none; margin-bottom: 15px; width: max-content; }
        .vd-title { font-size: 28px; font-weight: 700; color: var(--text-main); margin-bottom: 15px; line-height: 1.3; }
        
        .vd-stats { display: flex; align-items: center; gap: 20px; margin-bottom: 25px; padding-bottom: 20px; border-bottom: 1px solid #e2e8f0; color: var(--text-muted); font-size: 15px; }
        .vd-stat-item { display: flex; align-items: center; gap: 8px; }
        
        .vd-actions { display: flex; gap: 15px; margin-bottom: 25px; }
        .vd-btn { flex: 1; display: flex; align-items: center; justify-content: center; gap: 8px; padding: 12px; border-radius: 10px; font-weight: 600; cursor: pointer; transition: all 0.2s; border: none; font-family: inherit; font-size: 15px; }
        .btn-like { background: #eff6ff; color: var(--primary); }
        .btn-like:hover { background: var(--primary); color: white; transform: translateY(-2px); }
        .btn-share { background: #f1f5f9; color: var(--text-main); }
        .btn-share:hover { background: #e2e8f0; transform: translateY(-2px); }
        
        .vd-description { background: #f8fafc; padding: 20px; border-radius: 12px; line-height: 1.7; color: var(--text-main); font-size: 15px; }
        .vd-description h4 { font-size: 16px; margin-bottom: 10px; color: var(--text-main); }
    </style>
</head>
<body>
    <div class="video-container">
        <!-- Player Section -->
        <div class="video-player-section">
            <div class="video-player">
                <c:choose>
                    <c:when test="${not empty video.poster}">
                        <img src="${pageContext.request.contextPath}/uploads/${video.poster}" onerror="this.src='https://placehold.co/800x450/1e293b/ffffff?text=Video+Player'" alt="${video.title}">
                    </c:when>
                    <c:otherwise>
                        <img src="https://placehold.co/800x450/1e293b/ffffff?text=No+Video" alt="No Video">
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <!-- Details Section -->
        <div class="video-details-section">
            <a href="${pageContext.request.contextPath}/category?id=${category.categoryId}" class="vd-category">${category.categoryname}</a>
            <h1 class="vd-title">${video.title}</h1>
            
            <div class="vd-stats">
                <div class="vd-stat-item">
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg>
                    ${video.views} lượt xem
                </div>
                <div class="vd-stat-item">
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="2" y="7" width="20" height="15" rx="2" ry="2"></rect><polyline points="17 2 12 7 7 2"></polyline></svg>
                    Mã: ${video.videoId}
                </div>
            </div>
            
            <div class="vd-stats">
                <div class="vd-stat-item" style="color: var(--primary); font-size: 20px; font-weight: 700;">
                    Giá: <fmt:formatNumber value="${video.price}" type="currency" currencySymbol="VNĐ"/>
                </div>
                <div class="vd-stat-item">
                    Kho: ${video.quantity} bản
                </div>
            </div>
            
            <div class="vd-actions">
                <button class="vd-btn btn-like">
                    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M14 9V5a3 3 0 0 0-3-3l-4 9v11h11.28a2 2 0 0 0 2-1.7l1.38-9a2 2 0 0 0-2-2.3zM7 22H4a2 2 0 0 1-2-2v-7a2 2 0 0 1 2-2h3"></path></svg>
                    Thích (10)
                </button>
                <button class="vd-btn btn-share">
                    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="18" cy="5" r="3"></circle><circle cx="6" cy="12" r="3"></circle><circle cx="18" cy="19" r="3"></circle><line x1="8.59" y1="13.51" x2="15.42" y2="17.49"></line><line x1="15.41" y1="6.51" x2="8.59" y2="10.49"></line></svg>
                    Chia sẻ
                </button>
                <form action="${pageContext.request.contextPath}/cart/add" method="post" style="flex: 1;">
                    <input type="hidden" name="id" value="${video.videoId}">
                    <button type="submit" class="vd-btn" style="background: #22c55e; color: white; width: 100%;">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="9" cy="21" r="1"></circle><circle cx="20" cy="21" r="1"></circle><path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path></svg>
                        Thêm vào giỏ
                    </button>
                </form>
            </div>
            
            <div class="vd-description">
                <h4>Mô tả video</h4>
                <c:choose>
                    <c:when test="${not empty video.description}">
                        ${video.description}
                    </c:when>
                    <c:otherwise>
                        <em>Không có mô tả cho video này.</em>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
