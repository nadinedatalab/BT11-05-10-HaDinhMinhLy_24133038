package vn.edu.iuh.dao;

import vn.edu.iuh.models.Order_24133038;
import vn.edu.iuh.models.OrderDetail_24133038;

import java.util.List;

public interface IOrderDao_24133038 {
    int insertOrder(Order_24133038 order, List<OrderDetail_24133038> details);
    List<Order_24133038> findByUsername(String username);
    List<Order_24133038> findByUsernameAndStatus(String username, String status);
    List<OrderDetail_24133038> findOrderDetails(int orderId);
}
