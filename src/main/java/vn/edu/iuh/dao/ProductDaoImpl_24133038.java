package vn.edu.iuh.dao;

import vn.edu.iuh.models.Product_24133038;
import vn.edu.iuh.utils.ConnectDB_24133038;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl_24133038 implements IProductDao_24133038 {
    @Override
    public List<Product_24133038> findAll() {
        List<Product_24133038> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE Active = 1";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product_24133038 p = new Product_24133038();
                p.setProductId(rs.getInt("ProductId"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setImages(rs.getString("Images"));
                p.setDescription(rs.getString("Description"));
                p.setActive(rs.getBoolean("Active"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product_24133038 findById(int id) {
        String sql = "SELECT * FROM Products WHERE ProductId = ?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product_24133038 p = new Product_24133038();
                p.setProductId(rs.getInt("ProductId"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setImages(rs.getString("Images"));
                p.setDescription(rs.getString("Description"));
                p.setActive(rs.getBoolean("Active"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
