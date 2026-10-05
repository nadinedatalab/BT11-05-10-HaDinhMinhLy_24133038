package vn.edu.iuh.services;

import vn.edu.iuh.dao.IVideoDao_24133038;
import vn.edu.iuh.dao.VideoDaoImpl_24133038;
import vn.edu.iuh.models.Video_24133038;
import java.util.List;

public class VideoServiceImpl_24133038 implements IVideoService_24133038 {
    private IVideoDao_24133038 videoDao = new VideoDaoImpl_24133038();

    @Override
    public boolean insert(Video_24133038 video) { return videoDao.insert(video); }
    @Override
    public Video_24133038 findById(String videoId) { return videoDao.findById(videoId); }
    @Override
    public List<Video_24133038> findByCategoryId(int categoryId, int offset, int limit) { return videoDao.findByCategoryId(categoryId, offset, limit); }
    @Override
    public int countByCategoryId(int categoryId) { return videoDao.countByCategoryId(categoryId); }
    @Override
    public List<Video_24133038> findAll() { return videoDao.findAll(); }
    @Override
    public boolean update(Video_24133038 video) { return videoDao.update(video); }
    @Override
    public boolean delete(String videoId) { return videoDao.delete(videoId); }
}
