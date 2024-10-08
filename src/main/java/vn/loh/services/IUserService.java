package vn.loh.services;

import vn.loh.entity.User;

public interface IUserService {
    User findByUsername(String username);
    User login(String username, String password);
    void insert(User user);
    boolean register(String username, String password, String email, String fullname, String image, String phone);
    boolean checkExistUsername(String username);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
    boolean updatePassword(String username, String password);
}
