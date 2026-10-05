package vn.edu.iuh.dao;

import vn.edu.iuh.models.Video_24133038;
import vn.edu.iuh.utils.ConnectDB_24133038;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VideoDaoImpl_24133038 implements IVideoDao_24133038 {
    @Override
    public boolean insert(Video_24133038 video) {
        String sql = "INSERT INTO Videos (VideoId, Title, Poster, Views, Description, Active, CategoryId, Price, Quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, video.getVideoId());
            ps.setString(2, video.getTitle());
            ps.setString(3, video.getPoster());
            ps.setInt(4, video.getViews());
            ps.setString(5, video.getDescription());
            ps.setBoolean(6, video.isActive());
            ps.setInt(7, video.getCategoryId());
            ps.setDouble(8, video.getPrice());
            ps.setInt(9, video.getQuantity());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Video_24133038 findById(String videoId) {
        String sql = "SELECT * FROM Videos WHERE VideoId=?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, videoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Video_24133038(
                    rs.getString("VideoId"), rs.getString("Title"),
                    rs.getString("Poster"), rs.getInt("Views"),
                    rs.getString("Description"), rs.getBoolean("Active"),
                    rs.getInt("CategoryId"), rs.getDouble("Price"), rs.getInt("Quantity")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Video_24133038> findByCategoryId(int categoryId, int offset, int limit) {
        List<Video_24133038> list = new ArrayList<>();
        String sql = "SELECT * FROM Videos WHERE CategoryId=? ORDER BY VideoId OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, offset);
            ps.setInt(3, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Video_24133038(
                    rs.getString("VideoId"), rs.getString("Title"),
                    rs.getString("Poster"), rs.getInt("Views"),
                    rs.getString("Description"), rs.getBoolean("Active"),
                    rs.getInt("CategoryId"), rs.getDouble("Price"), rs.getInt("Quantity")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int countByCategoryId(int categoryId) {
        String sql = "SELECT COUNT(*) FROM Videos WHERE CategoryId=?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<Video_24133038> findAll() {
        List<Video_24133038> list = new ArrayList<>();
        String sql = "SELECT * FROM Videos";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Video_24133038(
                    rs.getString("VideoId"), rs.getString("Title"),
                    rs.getString("Poster"), rs.getInt("Views"),
                    rs.getString("Description"), rs.getBoolean("Active"),
                    rs.getInt("CategoryId"), rs.getDouble("Price"), rs.getInt("Quantity")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean update(Video_24133038 video) {
        String sql = "UPDATE Videos SET Title=?, Poster=?, Views=?, Description=?, Active=?, CategoryId=?, Price=?, Quantity=? WHERE VideoId=?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, video.getTitle());
            ps.setString(2, video.getPoster());
            ps.setInt(3, video.getViews());
            ps.setString(4, video.getDescription());
            ps.setBoolean(5, video.isActive());
            ps.setInt(6, video.getCategoryId());
            ps.setDouble(7, video.getPrice());
            ps.setInt(8, video.getQuantity());
            ps.setString(9, video.getVideoId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String videoId) {
        String sql = "DELETE FROM Videos WHERE VideoId=?";
        try (Connection conn = ConnectDB_24133038.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, videoId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
