<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Cập nhật Video</title>
    <style>
        .form-container { background: white; padding: 30px; border-radius: 12px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); max-width: 600px; margin: 20px auto; }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 8px; font-weight: 600; color: #475569; }
        .form-group input, .form-group select, .form-group textarea { width: 100%; padding: 10px; border: 1px solid #cbd5e1; border-radius: 6px; font-family: inherit; }
        .btn-submit { background: var(--primary); color: white; padding: 12px 20px; border: none; border-radius: 6px; font-weight: 600; cursor: pointer; font-size: 16px; width: 100%; }
        .btn-submit:hover { opacity: 0.9; }
    </style>
</head>
<body>
    <div class="form-container">
        <h2 style="margin-bottom: 25px; text-align: center;">Cập nhật Video: ${video.title}</h2>
        
        <form action="${pageContext.request.contextPath}/admin/videos/update" method="post" enctype="multipart/form-data">
            <input type="hidden" name="videoId" value="${video.videoId}">
            
            <div class="form-group">
                <label>Tiêu đề Video</label>
                <input type="text" name="title" value="${video.title}" required>
            </div>
            
            <div class="form-group">
                <label>Ảnh Poster mới (để trống nếu không đổi)</label>
                <c:if test="${not empty video.poster}">
                    <div style="margin-bottom:10px;"><img src="${pageContext.request.contextPath}/${video.poster}" width="100"></div>
                </c:if>
                <input type="file" name="posterFile" accept="image/*">
            </div>
            
            <div class="form-group">
                <label>Lượt xem</label>
                <input type="number" name="views" value="${video.views}" required>
            </div>
            
            <div class="form-group">
                <label>Mô tả</label>
                <textarea name="description" rows="4">${video.description}</textarea>
            </div>
            
            <div class="form-group">
                <label>Danh mục</label>
                <select name="categoryId" required>
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.categoryId}" ${cat.categoryId == video.categoryId ? 'selected' : ''}>${cat.categoryname}</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="form-group">
                <label>
                    <input type="checkbox" name="active" value="true" ${video.active ? 'checked' : ''}>
                    Kích hoạt (Hiển thị cho người dùng)
                </label>
            </div>
            
            <button type="submit" class="btn-submit">Lưu Thay Đổi</button>
        </form>
    </div>
</body>
