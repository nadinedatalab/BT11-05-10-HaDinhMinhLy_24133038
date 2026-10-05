package vn.edu.iuh.controllers;

import vn.edu.iuh.models.User_24133038;
import vn.edu.iuh.services.IUserService_24133038;
import vn.edu.iuh.services.UserServiceImpl_24133038;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/users", "/admin/users/insert", "/admin/users/update", "/admin/users/delete"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AdminUserController_24133038 extends HttpServlet {
    private IUserService_24133038 userService = new UserServiceImpl_24133038();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/admin/users")) {
            int page = 1;
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }
            int limit = 6;
            int offset = (page - 1) * limit;
            List<User_24133038> list = userService.findAll(offset, limit);
            int totalItems = userService.countAll();
            int totalPages = (int) Math.ceil((double) totalItems / limit);

            req.setAttribute("users", list);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.getRequestDispatcher("/views/admin/users.jsp").forward(req, resp);
            
        } else if (path.equals("/admin/users/delete")) {
            String username = req.getParameter("username");
            userService.delete(username);
            resp.sendRedirect(req.getContextPath() + "/admin/users");
            
        } else if (path.equals("/admin/users/update")) {
            String username = req.getParameter("username");
            User_24133038 user = userService.findByUsername(username);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/views/admin/user_edit.jsp").forward(req, resp);
            
        } else if (path.equals("/admin/users/insert")) {
            req.getRequestDispatcher("/views/admin/user_add.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        
        String username = req.getParameter("username");
        String pass = req.getParameter("password");
        String phone = req.getParameter("phone");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        boolean admin = req.getParameter("admin") != null;
        boolean active = req.getParameter("active") != null;
        String images = req.getParameter("images"); // Giữ nguyên giá trị cũ
        
        // Xử lý upload ảnh User
        Part part = req.getPart("imageFile");
        if (part != null && part.getSize() > 0) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            
            String fileName = getFileName(part);
            images = "uploads/" + fileName;
            part.write(uploadPath + File.separator + fileName);
        }
        
        User_24133038 user = new User_24133038(username, pass, phone, fullname, email, admin, active, images);
        
        if (path.equals("/admin/users/insert")) {
            userService.insert(user);
        } else if (path.equals("/admin/users/update")) {
            userService.update(user);
        }
        
        
        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }
    
    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "default.jpg";
    }
}
