package vn.edu.iuh.dao;

import vn.edu.iuh.models.Category_24133038;
import vn.edu.iuh.utils.ConnectDB_24133038;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl_24133038 implements ICategoryDao_24133038 {
    @Override
    public List<Category_24133038> findAll() {
        List<Category_24133038> list = new ArrayList<>();
        String sql = "SELECT * FROM Category";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Category_24133038(
                    rs.getInt("CategoryId"), rs.getString("Categoryname"),
                    rs.getString("Categorycode"), rs.getString("Images"),
                    rs.getBoolean("Status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category_24133038 findById(int categoryId) {
        String sql = "SELECT * FROM Category WHERE CategoryId=?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Category_24133038(
                    rs.getInt("CategoryId"), rs.getString("Categoryname"),
                    rs.getString("Categorycode"), rs.getString("Images"),
                    rs.getBoolean("Status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
