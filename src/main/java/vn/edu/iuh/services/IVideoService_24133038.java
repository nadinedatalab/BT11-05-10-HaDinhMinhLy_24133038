package vn.edu.iuh.services;

import vn.edu.iuh.models.Video_24133038;
import java.util.List;

public interface IVideoService_24133038 {
    boolean insert(Video_24133038 video);
    Video_24133038 findById(String videoId);
    List<Video_24133038> findByCategoryId(int categoryId, int offset, int limit);
    int countByCategoryId(int categoryId);
    List<Video_24133038> findAll();
    boolean update(Video_24133038 video);
    boolean delete(String videoId);
}
