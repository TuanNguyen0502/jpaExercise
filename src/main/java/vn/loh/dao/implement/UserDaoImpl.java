package vn.loh.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import vn.loh.configs.JPAConfig;
import vn.loh.dao.IRoleDao;
import vn.loh.dao.IUserDao;
import vn.loh.entity.User;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> findAll() {
        EntityManager entityManager = JPAConfig.getEntityManager();
        TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
        List<User> users = query.getResultList();
        entityManager.close();
        return users;
    }

    @Override
    public User findById(int id) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        User user = entityManager.find(User.class, id);
        entityManager.close();
        return user;
    }

    @Override
    public User findByUsername(String username) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String jpql = "SELECT u FROM User u WHERE u.username = :username";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("username", username);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            // Handle no result found
            System.out.println("No user found with username: " + username);
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String spql = "SELECT u FROM User u WHERE u.email = :email";
        TypedQuery<User> query = entityManager.createQuery(spql, User.class);
        query.setParameter("email", email);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            // Handle no result found
            System.out.println("No user found with email: " + email);
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public User findByPhone(String phone) {
        EntityManager entityManager = JPAConfig.getEntityManager();
        String spql = "SELECT u FROM User u WHERE u.phone = :phone";
        TypedQuery<User> query = entityManager.createQuery(spql, User.class);
        query.setParameter("phone", phone);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            // Handle no result found
            System.out.println("No user found with email: " + phone);
        } finally {
            entityManager.close();
        }
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
    public boolean updatePassword(String username, String password) {
        boolean isSuccess = false;
        EntityManager entityManager = JPAConfig.getEntityManager();
        EntityTransaction trans = entityManager.getTransaction();
        try {
            trans.begin();
            // Get user by username
            User user = entityManager.find(User.class, username);
            if (user != null) {
                user.setPassword(password);
                // Call method to insert, update, delete
                entityManager.merge(user);
                isSuccess = true;
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
        return isSuccess;
    }
}
