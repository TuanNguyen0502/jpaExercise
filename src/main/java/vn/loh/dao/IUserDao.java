package vn.loh.dao;

import vn.loh.entity.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    User findById(int id);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByPhone(String phone);
    void insert(User user);
    boolean updatePassword(String username, String password);
}
