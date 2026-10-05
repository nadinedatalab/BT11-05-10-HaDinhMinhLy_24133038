package vn.edu.iuh.controllers;

import vn.edu.iuh.models.Video_24133038;
import vn.edu.iuh.services.CategoryServiceImpl_24133038;
import vn.edu.iuh.services.ICategoryService_24133038;
import vn.edu.iuh.services.IVideoService_24133038;
import vn.edu.iuh.services.VideoServiceImpl_24133038;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/videos", "/admin/videos/insert", "/admin/videos/update", "/admin/videos/delete"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50)
public class AdminVideoController_24133038 extends HttpServlet {
    private IVideoService_24133038 videoService = new VideoServiceImpl_24133038();
    private ICategoryService_24133038 categoryService = new CategoryServiceImpl_24133038();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/admin/videos/insert")) {
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/views/admin/video_add.jsp").forward(req, resp);
        } else if (path.equals("/admin/videos")) {
            req.setAttribute("videos", videoService.findAll());
            req.getRequestDispatcher("/views/admin/videos.jsp").forward(req, resp);
        } else if (path.equals("/admin/videos/update")) {
            String id = req.getParameter("id");
            req.setAttribute("video", videoService.findById(id));
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/views/admin/video_edit.jsp").forward(req, resp);
        } else if (path.equals("/admin/videos/delete")) {
            String id = req.getParameter("id");
            videoService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        
        try {
            if (path.equals("/admin/videos/insert")) {
                String videoId = req.getParameter("videoId");
                String title = req.getParameter("title");
                int views = 0;
                try { views = Integer.parseInt(req.getParameter("views")); } catch (Exception ignored) {}
                String description = req.getParameter("description");
                boolean active = req.getParameter("active") != null;
                int categoryId = Integer.parseInt(req.getParameter("categoryId"));
                
                // Xử lý upload ảnh poster
                String poster = "";
                Part part = req.getPart("posterFile");
                if (part != null && part.getSize() > 0 && part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                    String fileName = part.getSubmittedFileName();
                    
                    String realPath = getServletContext().getRealPath("");
                    if (realPath == null) realPath = System.getProperty("java.io.tmpdir"); // fallback
                    String uploadPath = realPath + File.separator + "uploads";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdir();
                    
                    poster = "uploads/" + fileName;
                    part.write(uploadPath + File.separator + fileName);
                }
                
                Video_24133038 video = new Video_24133038(videoId, title, poster, views, description, active, categoryId);
                videoService.insert(video);
                
                resp.sendRedirect(req.getContextPath() + "/admin/videos");
            } else if (path.equals("/admin/videos/update")) {
                String videoId = req.getParameter("videoId");
                String title = req.getParameter("title");
                int views = 0;
                try { views = Integer.parseInt(req.getParameter("views")); } catch (Exception ignored) {}
                String description = req.getParameter("description");
                boolean active = req.getParameter("active") != null;
                int categoryId = Integer.parseInt(req.getParameter("categoryId"));
                
                Video_24133038 video = videoService.findById(videoId);
                if (video != null) {
                    video.setTitle(title);
                    video.setViews(views);
                    video.setDescription(description);
                    video.setActive(active);
                    video.setCategoryId(categoryId);
                    
                    Part part = req.getPart("posterFile");
                    if (part != null && part.getSize() > 0 && part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                        String fileName = part.getSubmittedFileName();
                        
                        String realPath = getServletContext().getRealPath("");
                        if (realPath == null) realPath = System.getProperty("java.io.tmpdir"); // fallback
                        String uploadPath = realPath + File.separator + "uploads";
                        File uploadDir = new File(uploadPath);
                        if (!uploadDir.exists()) uploadDir.mkdir();
                        
                        video.setPoster("uploads/" + fileName);
                        part.write(uploadPath + File.separator + fileName);
                    }
                    
                    videoService.update(video);
                }
                resp.sendRedirect(req.getContextPath() + "/admin/videos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println("<h3>Có lỗi xảy ra: " + e.getMessage() + "</h3><pre>");
            e.printStackTrace(resp.getWriter());
            resp.getWriter().println("</pre>");
        }
    }
}
