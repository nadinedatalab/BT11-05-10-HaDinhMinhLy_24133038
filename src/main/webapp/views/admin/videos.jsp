<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Quản lý Video</title>
    <style>
        .admin-table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        .admin-table th, .admin-table td { padding: 12px 15px; text-align: left; border-bottom: 1px solid #e2e8f0; }
        .admin-table th { background: #f8fafc; font-weight: 600; color: #475569; }
        .admin-table tr:hover { background: #f1f5f9; }
        .btn { padding: 8px 12px; border-radius: 6px; text-decoration: none; font-size: 14px; font-weight: 500; display: inline-block; }
        .btn-primary { background: var(--primary); color: white; }
        .btn-warning { background: #eab308; color: white; }
        .btn-danger { background: #ef4444; color: white; }
        .top-action { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
    </style>
</head>
<body>
    <div style="padding: 20px;">
        <div class="top-action">
            <h2>Danh sách Video</h2>
            <a href="${pageContext.request.contextPath}/admin/videos/insert" class="btn btn-primary">+ Thêm Video Mới</a>
        </div>
        
        <table class="admin-table">
            <thead>
                <tr>
                    <th>Video ID</th>
                    <th>Hình ảnh</th>
                    <th>Tiêu đề</th>
                    <th>Lượt xem</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="v" items="${videos}">
                    <tr>
                        <td>${v.videoId}</td>
                        <td>
                            <c:if test="${not empty v.poster}">
                                <img src="${pageContext.request.contextPath}/${v.poster}" width="80" alt="Poster" style="border-radius:4px;">
                            </c:if>
                        </td>
                        <td>${v.title}</td>
                        <td>${v.views}</td>
                        <td>
                            <span style="color: ${v.active ? '#16a34a' : '#ef4444'}; font-weight: 600;">
                                ${v.active ? 'Hoạt động' : 'Tạm khóa'}
                            </span>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/videos/update?id=${v.videoId}" class="btn btn-warning">Sửa</a>
                            <a href="${pageContext.request.contextPath}/admin/videos/delete?id=${v.videoId}" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa video này?');">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
