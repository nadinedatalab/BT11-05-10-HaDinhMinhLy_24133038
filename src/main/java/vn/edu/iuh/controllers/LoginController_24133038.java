package vn.edu.iuh.controllers;

import vn.edu.iuh.models.User_24133038;
import vn.edu.iuh.services.IUserService_24133038;
import vn.edu.iuh.services.UserServiceImpl_24133038;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController_24133038 extends HttpServlet {
    private IUserService_24133038 userService = new UserServiceImpl_24133038();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/login")) {
            req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
        } else if (path.equals("/logout")) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/login")) {
            String username = req.getParameter("username");
            String pass = req.getParameter("password");
            
            User_24133038 user = userService.login(username, pass);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                if (user.isAdmin()) {
                    resp.sendRedirect(req.getContextPath() + "/admin/home");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/home");
                }
            } else {
                req.setAttribute("error", "Sai thông tin đăng nhập");
                req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
            }
        }
    }
}
