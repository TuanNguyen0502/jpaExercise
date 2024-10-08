package vn.loh.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.loh.configs.JPAConfig;
import vn.loh.dao.IRoleDao;
import vn.loh.dao.IUserDao;
import vn.loh.entity.User;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        User user = enma.find(User.class, id);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "SELECT u FROM User u WHERE u.username like :username";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("username", "%" + username + "%");
        List<User> users = query.getResultList();
        if (!users.isEmpty()) {
            return users.getFirst();
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        EntityManager enma = JPAConfig.getEntityManager();
        User user = enma.find(User.class, email);
        return user;
    }

    @Override
    public User findByPhone(String phone) {
        EntityManager enma = JPAConfig.getEntityManager();
        User user = enma.find(User.class, phone);
        return user;
    }

    @Override
    public void insert(User user) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            // Method to insert, update, delete a category
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean checkExistUsername(String username) {
        boolean duplicate = false;
        User user = findByUsername(username);
        if (user != null) {
            duplicate = true;
        }
        return duplicate;
    }

    @Override
    public boolean checkExistEmail(String email) {
        boolean duplicate = false;
        User user = findByEmail(email);
        if (user != null) {
            duplicate = true;
        }
        return duplicate;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        boolean duplicate = false;
        User user = findByPhone(phone);
        if (user != null) {
            duplicate = true;
        }
        return duplicate;
    }

    @Override
    public boolean updatePassword(String username, String password) {
        boolean isSuccess = false;
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            // Get user by username
            User user = enma.find(User.class, username);
            if (user != null) {
                user.setPassword(password);
                // Call method to insert, update, delete
                enma.merge(user);
                isSuccess = true;
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
        return isSuccess;
    }
}
