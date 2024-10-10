package vn.loh.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.loh.configs.JPAConfig;
import vn.loh.dao.IVideoDao;
import vn.loh.dao.implement.VideoDaoImpl;
import vn.loh.entity.Video;
import vn.loh.services.IVideoService;

import java.util.List;

public class VideoServiceImpl implements IVideoService {
    private IVideoDao videoDao = new VideoDaoImpl();

    @Override
    public void insert(Video video) {
        if (videoDao.checkVideoExist(video.getId())) {
            throw new RuntimeException("Video đã tồn tại");
        }
        videoDao.insert(video);
    }

    @Override
    public void update(Video video) {
        if (!videoDao.checkVideoExist(video.getId())) {
            throw new RuntimeException("Video không tồn tại");
        }
        videoDao.update(video);
    }

    @Override
    public void delete(String id) throws Exception {
        if (!videoDao.checkVideoExist(id)) {
            throw new Exception("Video không tồn tại");
        }
        videoDao.delete(id);
    }

    @Override
    public Video findById(String id) {
        return videoDao.findById(id);
    }

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public List<Video> findByCategoryID(int categoryID) {
        return videoDao.findByCategoryID(categoryID);
    }
}
