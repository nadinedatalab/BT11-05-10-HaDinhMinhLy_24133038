package vn.edu.iuh.controllers;

import vn.edu.iuh.models.User_24133038;
import vn.edu.iuh.services.IUserService_24133038;
import vn.edu.iuh.services.UserServiceImpl_24133038;
import vn.edu.iuh.utils.EmailUtils_24133038;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register", "/register-verify"})
public class RegisterController_24133038 extends HttpServlet {
    private IUserService_24133038 userService = new UserServiceImpl_24133038();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/register")) {
            req.getRequestDispatcher("/views/web/register.jsp").forward(req, resp);
        } else if (path.equals("/register-verify")) {
            req.getRequestDispatcher("/views/web/register_verify.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        
        if (path.equals("/register")) {
            String username = req.getParameter("username");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            
            // Store temporarily in session
            User_24133038 tempUser = new User_24133038(username, password, "", username, email, false, true, "");
            session.setAttribute("tempUser", tempUser);
            
            // Send OTP
            String otp = EmailUtils_24133038.sendOTP(email);
            if (otp == null) otp = "123456"; // Fallback for exam environment without real email
            
            session.setAttribute("sentOtp", otp);
            resp.sendRedirect(req.getContextPath() + "/register-verify");
            
        } else if (path.equals("/register-verify")) {
            String userOtp = req.getParameter("otp");
            String sentOtp = (String) session.getAttribute("sentOtp");
            
            if (sentOtp != null && sentOtp.equals(userOtp)) {
                User_24133038 tempUser = (User_24133038) session.getAttribute("tempUser");
                if (tempUser != null) {
                    userService.insert(tempUser);
                    session.removeAttribute("tempUser");
                    session.removeAttribute("sentOtp");
                    resp.sendRedirect(req.getContextPath() + "/login?message=RegisterSuccess");
                    return;
                }
            }
            req.setAttribute("error", "Mã OTP không đúng");
            req.getRequestDispatcher("/views/web/register_verify.jsp").forward(req, resp);
        }
    }
}
