<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Danh mục ${category.categoryname}</title>
    <style>
        .category-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 30px; padding-bottom: 15px; border-bottom: 2px solid #e2e8f0; }
        .category-title { font-size: 28px; font-weight: 700; color: var(--text-main); }
        .video-count { background: var(--primary); color: white; padding: 4px 12px; border-radius: 20px; font-size: 14px; font-weight: 600; }
        
        .video-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(320px, 1fr)); gap: 30px; }
        .video-card { background: white; border-radius: 16px; overflow: hidden; box-shadow: 0 4px 6px -1px rgba(0,0,0,0.05); transition: all 0.3s ease; border: 1px solid #e2e8f0; }
        .video-card:hover { transform: translateY(-8px); box-shadow: 0 20px 25px -5px rgba(0,0,0,0.1); }
        
        .video-thumbnail { position: relative; width: 100%; padding-top: 56.25%; /* 16:9 Aspect Ratio */ overflow: hidden; background: #0f172a; }
        .video-thumbnail img { position: absolute; top: 0; left: 0; width: 100%; height: 100%; object-fit: cover; transition: transform 0.5s ease; }
        .video-card:hover .video-thumbnail img { transform: scale(1.05); }
        .play-overlay { position: absolute; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.3); display: flex; align-items: center; justify-content: center; opacity: 0; transition: opacity 0.3s ease; }
        .video-card:hover .play-overlay { opacity: 1; }
        .play-overlay svg { width: 48px; height: 48px; color: white; fill: white; }
        
        .video-info { padding: 20px; }
        .video-title { font-size: 18px; font-weight: 600; color: var(--text-main); margin-bottom: 10px; line-height: 1.4; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; overflow: hidden; }
        .video-meta { font-size: 14px; color: var(--text-muted); display: flex; flex-direction: column; gap: 6px; margin-bottom: 15px; }
        .video-meta span { display: flex; align-items: center; gap: 6px; }
        
        .video-actions { display: flex; justify-content: space-between; border-top: 1px solid #e2e8f0; padding-top: 15px; }
        .action-btn { display: flex; align-items: center; gap: 6px; color: var(--text-muted); font-size: 14px; font-weight: 500; cursor: pointer; transition: color 0.2s; }
        .action-btn:hover { color: var(--primary); }
        
        /* Pagination */
        .pagination { display: flex; justify-content: center; gap: 10px; margin-top: 40px; }
        .page-link { width: 40px; height: 40px; display: flex; align-items: center; justify-content: center; border-radius: 8px; font-weight: 600; color: var(--text-main); background: white; border: 1px solid #e2e8f0; text-decoration: none; transition: all 0.2s; }
        .page-link:hover { background: #f1f5f9; border-color: #cbd5e1; }
        .page-link.active { background: var(--primary); color: white; border-color: var(--primary); }
    </style>
</head>
<body>
    <div class="category-header">
        <h2 class="category-title">${category.categoryname}</h2>
        <span class="video-count">${totalVideos} Videos</span>
    </div>
    
    <div class="video-grid">
        <c:forEach var="v" items="${videos}">
            <div class="video-card">
                <a href="${pageContext.request.contextPath}/video-detail?id=${v.videoId}">
                    <div class="video-thumbnail">
                        <c:choose>
                            <c:when test="${not empty v.poster}">
                                <img src="${pageContext.request.contextPath}/uploads/${v.poster}" onerror="this.src='https://placehold.co/600x400/1e293b/ffffff?text=Video+Poster'" alt="${v.title}">
                            </c:when>
                            <c:otherwise>
                                <img src="https://placehold.co/600x400/1e293b/ffffff?text=No+Poster" alt="No Poster">
                            </c:otherwise>
                        </c:choose>
                        <div class="play-overlay">
                            <svg viewBox="0 0 24 24"><polygon points="5 3 19 12 5 21 5 3"></polygon></svg>
                        </div>
                    </div>
                </a>
                <div class="video-info">
                    <a href="${pageContext.request.contextPath}/video-detail?id=${v.videoId}" style="text-decoration:none;">
                        <h3 class="video-title">${v.title}</h3>
                    </a>
                    <div class="video-meta">
                        <span><svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="2" y="7" width="20" height="15" rx="2" ry="2"></rect><polyline points="17 2 12 7 7 2"></polyline></svg> Mã: ${v.videoId}</span>
                        <span><svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg> Lượt xem: ${v.views}</span>
                    </div>
                    <div class="video-actions">
                        <div class="action-btn">
                            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M14 9V5a3 3 0 0 0-3-3l-4 9v11h11.28a2 2 0 0 0 2-1.7l1.38-9a2 2 0 0 0-2-2.3zM7 22H4a2 2 0 0 1-2-2v-7a2 2 0 0 1 2-2h3"></path></svg>
                            Like (10)
                        </div>
                        <div class="action-btn">
                            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="18" cy="5" r="3"></circle><circle cx="6" cy="12" r="3"></circle><circle cx="18" cy="19" r="3"></circle><line x1="8.59" y1="13.51" x2="15.42" y2="17.49"></line><line x1="15.41" y1="6.51" x2="8.59" y2="10.49"></line></svg>
                            Share (10)
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    
    <div class="pagination">
        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${currentPage == i}">
                    <span class="page-link active">${i}</span>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/category?id=${category.categoryId}&page=${i}" class="page-link">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
</body>
