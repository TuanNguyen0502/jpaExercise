package vn.loh.services;

import vn.loh.entity.Video;

import java.util.List;

public interface IVideoService {
    void insert(Video video);
    void update(Video video);
    void delete(String id) throws Exception;
    Video findById(String id);
    List<Video> findAll();
    List<Video> findByCategoryID(int categoryID);
}
