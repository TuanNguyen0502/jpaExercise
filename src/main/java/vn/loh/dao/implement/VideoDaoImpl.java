package vn.loh.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.loh.configs.JPAConfig;
import vn.loh.dao.IVideoDao;
import vn.loh.entity.Video;

import java.util.List;

public class VideoDaoImpl implements IVideoDao {
    @Override
    public void insert(Video video) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // Method to insert, update, delete a category
            entityManager.persist(video);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            // gọi phuong thức để insert, update, delete
            enma.merge(video);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(String id) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            // gọi phuong thức để insert, update, delete
            Video video = enma.find(Video.class, id);
            if (video != null) {
                enma.remove(video);
            } else {
                throw new Exception("Không tìm thấy");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public Video findById(String id) {
        EntityManager enma = JPAConfig.getEntityManager();
        return enma.find(Video.class, id);
    }

    @Override
    public List<Video> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findByCategoryID(int categoryID) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.category.id = :categoryID";
        TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
        query.setParameter("categoryID", categoryID);
        return query.getResultList();
    }

    @Override
    public boolean checkVideoExist(String id) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        Video video = entityManager.find(Video.class, id);
        return video != null;
    }
}
