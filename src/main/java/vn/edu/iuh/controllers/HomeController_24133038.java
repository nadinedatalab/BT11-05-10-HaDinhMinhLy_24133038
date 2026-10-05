package vn.edu.iuh.controllers;

import vn.edu.iuh.models.Category_24133038;
import vn.edu.iuh.models.Video_24133038;
import vn.edu.iuh.services.CategoryServiceImpl_24133038;
import vn.edu.iuh.services.ICategoryService_24133038;
import vn.edu.iuh.services.IVideoService_24133038;
import vn.edu.iuh.services.VideoServiceImpl_24133038;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/home", "/video-detail", "/category"})
public class HomeController_24133038 extends HttpServlet {
    private ICategoryService_24133038 categoryService = new CategoryServiceImpl_24133038();
    private IVideoService_24133038 videoService = new VideoServiceImpl_24133038();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        
        if (path.equals("/home")) {
            List<Category_24133038> categories = categoryService.findAll();
            req.setAttribute("categories", categories);
            
            req.getRequestDispatcher("/views/web/home.jsp").forward(req, resp);
            
        } else if (path.equals("/video-detail")) {
            String videoId = req.getParameter("id");
            Video_24133038 video = videoService.findById(videoId);
            if (video != null) {
                Category_24133038 cat = categoryService.findById(video.getCategoryId());
                req.setAttribute("video", video);
                req.setAttribute("category", cat);
            }
            req.getRequestDispatcher("/views/web/video_detail.jsp").forward(req, resp);
            
        } else if (path.equals("/category")) {
            int categoryId = Integer.parseInt(req.getParameter("id"));
            int page = 1;
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            int limit = 3;
            int offset = (page - 1) * limit;
            
            List<Video_24133038> videos = videoService.findByCategoryId(categoryId, offset, limit);
            int totalItems = videoService.countByCategoryId(categoryId);
            int totalPages = (int) Math.ceil((double) totalItems / limit);
            
            Category_24133038 cat = categoryService.findById(categoryId);
            req.setAttribute("category", cat);
            req.setAttribute("videos", videos);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("totalVideos", totalItems); // Count for Q6
            
            req.getRequestDispatcher("/views/web/category.jsp").forward(req, resp);
        }
    }
}
