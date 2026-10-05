package vn.edu.iuh.controllers;

import vn.edu.iuh.dao.IOrderDao_24133038;
import vn.edu.iuh.dao.OrderDaoImpl_24133038;
import vn.edu.iuh.models.CartItem_24133038;
import vn.edu.iuh.models.Order_24133038;
import vn.edu.iuh.models.OrderDetail_24133038;
import vn.edu.iuh.models.User_24133038;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/checkout", "/orders", "/order/details"})
public class OrderController_24133038 extends HttpServlet {
    private IOrderDao_24133038 orderDao = new OrderDaoImpl_24133038();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        User_24133038 user = (User_24133038) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (path.equals("/checkout")) {
            req.getRequestDispatcher("/views/web/checkout.jsp").forward(req, resp);
        } else if (path.equals("/orders")) {
            String status = req.getParameter("status");
            List<Order_24133038> orders;
            if (status != null && !status.trim().isEmpty()) {
                orders = orderDao.findByUsernameAndStatus(user.getUsername(), status);
            } else {
                orders = orderDao.findByUsername(user.getUsername());
            }
            req.setAttribute("orders", orders);
            req.setAttribute("currentStatus", status);
            req.getRequestDispatcher("/views/web/orders.jsp").forward(req, resp);
        } else if (path.equals("/order/details")) {
            int orderId = Integer.parseInt(req.getParameter("id"));
            List<OrderDetail_24133038> details = orderDao.findOrderDetails(orderId);
            req.setAttribute("details", details);
            req.getRequestDispatcher("/views/web/order_details.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getServletPath();
        HttpSession session = req.getSession();
        User_24133038 user = (User_24133038) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        if (path.equals("/checkout")) {
            List<CartItem_24133038> cart = (List<CartItem_24133038>) session.getAttribute("cart");
            if (cart == null || cart.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/cart");
                return;
            }

            String address = req.getParameter("address");
            String phone = req.getParameter("phone");

            double total = 0;
            List<OrderDetail_24133038> details = new ArrayList<>();
            for (CartItem_24133038 item : cart) {
                total += item.getTotalPrice();
                OrderDetail_24133038 d = new OrderDetail_24133038();
                d.setVideoId(item.getVideo().getVideoId());
                d.setQuantity(item.getQuantity());
                d.setPrice(item.getVideo().getPrice());
                details.add(d);
            }

            Order_24133038 order = new Order_24133038();
            order.setUsername(user.getUsername());
            order.setOrderDate(new Date());
            order.setTotalAmount(total);
            order.setPaymentMethod("COD");
            order.setStatus("Đơn hàng mới");
            order.setAddress(address);
            order.setPhone(phone);

            int orderId = orderDao.insertOrder(order, details);
            if (orderId > 0) {
                session.removeAttribute("cart");
                resp.sendRedirect(req.getContextPath() + "/orders?success=1");
            } else {
                req.setAttribute("error", "Lỗi khi đặt hàng!");
                req.getRequestDispatcher("/views/web/checkout.jsp").forward(req, resp);
            }
        }
    }
}
