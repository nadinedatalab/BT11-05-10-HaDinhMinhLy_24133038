package vn.edu.iuh.dao;

import vn.edu.iuh.models.User_24133038;
import vn.edu.iuh.utils.ConnectDB_24133038;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl_24133038 implements IUserDao_24133038 {
    @Override
    public boolean insert(User_24133038 user) {
        String sql = "INSERT INTO Users (Username, Password, Phone, Fullname, Email, Admin, Active, Images) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getFullname());
            ps.setString(5, user.getEmail());
            ps.setBoolean(6, user.isAdmin());
            ps.setBoolean(7, user.isActive());
            ps.setString(8, user.getImages());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User_24133038 user) {
        String sql = "UPDATE Users SET Password=?, Phone=?, Fullname=?, Email=?, Admin=?, Active=?, Images=? WHERE Username=?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getFullname());
            ps.setString(4, user.getEmail());
            ps.setBoolean(5, user.isAdmin());
            ps.setBoolean(6, user.isActive());
            ps.setString(7, user.getImages());
            ps.setString(8, user.getUsername());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String username) {
        String sql = "DELETE FROM Users WHERE Username=?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User_24133038 findByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE Username=?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User_24133038(
                    rs.getString("Username"), rs.getString("Password"),
                    rs.getString("Phone"), rs.getString("Fullname"),
                    rs.getString("Email"), rs.getBoolean("Admin"),
                    rs.getBoolean("Active"), rs.getString("Images")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User_24133038> findAll(int offset, int limit) {
        List<User_24133038> list = new ArrayList<>();
        String sql = "SELECT * FROM Users ORDER BY Username OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User_24133038(
                    rs.getString("Username"), rs.getString("Password"),
                    rs.getString("Phone"), rs.getString("Fullname"),
                    rs.getString("Email"), rs.getBoolean("Admin"),
                    rs.getBoolean("Active"), rs.getString("Images")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM Users";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User_24133038 login(String username, String password) {
        String sql = "SELECT * FROM Users WHERE Username=? AND Password=?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User_24133038(
                    rs.getString("Username"), rs.getString("Password"),
                    rs.getString("Phone"), rs.getString("Fullname"),
                    rs.getString("Email"), rs.getBoolean("Admin"),
                    rs.getBoolean("Active"), rs.getString("Images")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
