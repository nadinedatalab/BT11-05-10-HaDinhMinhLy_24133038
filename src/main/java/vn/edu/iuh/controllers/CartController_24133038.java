package vn.edu.iuh.controllers;

import vn.edu.iuh.dao.IVideoDao_24133038;
import vn.edu.iuh.dao.VideoDaoImpl_24133038;
import vn.edu.iuh.models.CartItem_24133038;
import vn.edu.iuh.models.Video_24133038;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/cart", "/cart/add", "/cart/update", "/cart/remove"})
public class CartController_24133038 extends HttpServlet {
    private IVideoDao_24133038 videoDao = new VideoDaoImpl_24133038();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/cart")) {
            req.getRequestDispatcher("/views/web/cart.jsp").forward(req, resp);
        } else if (path.equals("/cart/remove")) {
            String videoId = req.getParameter("id");
            HttpSession session = req.getSession();
            List<CartItem_24133038> cart = (List<CartItem_24133038>) session.getAttribute("cart");
            if (cart != null) {
                cart.removeIf(item -> item.getVideo().getVideoId().equals(videoId));
                session.setAttribute("cart", cart);
            }
            resp.sendRedirect(req.getContextPath() + "/cart");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        List<CartItem_24133038> cart = (List<CartItem_24133038>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        if (path.equals("/cart/add")) {
            String videoId = req.getParameter("id");
            Video_24133038 video = videoDao.findById(videoId);
            if (video != null) {
                boolean exists = false;
                for (CartItem_24133038 item : cart) {
                    if (item.getVideo().getVideoId().equals(videoId)) {
                        int newQty = item.getQuantity() + 1;
                        if (newQty > video.getQuantity()) {
                            newQty = video.getQuantity();
                        }
                        item.setQuantity(newQty);
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    if (video.getQuantity() > 0) {
                        cart.add(new CartItem_24133038(video, 1));
                    }
                }
            }
            session.setAttribute("cart", cart);
            resp.sendRedirect(req.getContextPath() + "/cart");

        } else if (path.equals("/cart/update")) {
            String videoId = req.getParameter("id");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            Video_24133038 video = videoDao.findById(videoId);

            if (video != null) {
                if (quantity > video.getQuantity()) {
                    quantity = video.getQuantity(); // Limit to stock
                }
                if (quantity < 1) {
                    quantity = 1;
                }
                for (CartItem_24133038 item : cart) {
                    if (item.getVideo().getVideoId().equals(videoId)) {
                        item.setQuantity(quantity);
                        break;
                    }
                }
            }
            session.setAttribute("cart", cart);
            resp.sendRedirect(req.getContextPath() + "/cart");
        }
    }
}
