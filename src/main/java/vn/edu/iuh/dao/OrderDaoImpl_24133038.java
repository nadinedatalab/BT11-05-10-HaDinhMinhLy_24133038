package vn.edu.iuh.dao;

import vn.edu.iuh.models.Order_24133038;
import vn.edu.iuh.models.OrderDetail_24133038;
import vn.edu.iuh.utils.ConnectDB_24133038;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl_24133038 implements IOrderDao_24133038 {
    @Override
    public int insertOrder(Order_24133038 order, List<OrderDetail_24133038> details) {
        String sqlOrder = "INSERT INTO Orders (Username, OrderDate, TotalAmount, PaymentMethod, Status, Address, Phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlDetail = "INSERT INTO OrderDetails (OrderId, VideoId, Quantity, Price) VALUES (?, ?, ?, ?)";
        int orderId = -1;
        Connection conn = null;
        try {
            conn = ConnectDB_24133038.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement psOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setString(1, order.getUsername());
            psOrder.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            psOrder.setDouble(3, order.getTotalAmount());
            psOrder.setString(4, order.getPaymentMethod());
            psOrder.setString(5, order.getStatus());
            psOrder.setString(6, order.getAddress());
            psOrder.setString(7, order.getPhone());
            psOrder.executeUpdate();

            ResultSet rs = psOrder.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            if (orderId != -1) {
                PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
                for (OrderDetail_24133038 d : details) {
                    psDetail.setInt(1, orderId);
                    psDetail.setString(2, d.getVideoId());
                    psDetail.setInt(3, d.getQuantity());
                    psDetail.setDouble(4, d.getPrice());
                    psDetail.addBatch();
                }
                psDetail.executeBatch();
            }

            conn.commit();
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderId;
    }

    @Override
    public List<Order_24133038> findByUsername(String username) {
        List<Order_24133038> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE Username = ? ORDER BY OrderDate DESC";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order_24133038 o = new Order_24133038();
                o.setOrderId(rs.getInt("OrderId"));
                o.setUsername(rs.getString("Username"));
                o.setOrderDate(rs.getDate("OrderDate"));
                o.setTotalAmount(rs.getDouble("TotalAmount"));
                o.setPaymentMethod(rs.getString("PaymentMethod"));
                o.setStatus(rs.getString("Status"));
                o.setAddress(rs.getString("Address"));
                o.setPhone(rs.getString("Phone"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Order_24133038> findByUsernameAndStatus(String username, String status) {
        List<Order_24133038> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE Username = ? AND Status = ? ORDER BY OrderDate DESC";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order_24133038 o = new Order_24133038();
                o.setOrderId(rs.getInt("OrderId"));
                o.setUsername(rs.getString("Username"));
                o.setOrderDate(rs.getDate("OrderDate"));
                o.setTotalAmount(rs.getDouble("TotalAmount"));
                o.setPaymentMethod(rs.getString("PaymentMethod"));
                o.setStatus(rs.getString("Status"));
                o.setAddress(rs.getString("Address"));
                o.setPhone(rs.getString("Phone"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<OrderDetail_24133038> findOrderDetails(int orderId) {
        List<OrderDetail_24133038> list = new ArrayList<>();
        String sql = "SELECT od.*, p.Title FROM OrderDetails od JOIN Videos p ON od.VideoId = p.VideoId WHERE od.OrderId = ?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail_24133038 d = new OrderDetail_24133038();
                d.setOrderId(rs.getInt("OrderId"));
                d.setVideoId(rs.getString("VideoId"));
                d.setQuantity(rs.getInt("Quantity"));
                d.setPrice(rs.getDouble("Price"));
                d.setVideoTitle(rs.getString("Title"));
                list.add(d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
