package vn.loh.services.impl;

import vn.loh.dao.IRoleDao;
import vn.loh.dao.IUserDao;
import vn.loh.dao.implement.RoleDaoImpl;
import vn.loh.dao.implement.UserDaoImpl;
import vn.loh.entity.User;
import vn.loh.services.IUserService;

public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        User user = this.findByUsername(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String image, String phone) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullname(fullname);
        user.setImage(image);
        IRoleDao roleDao = new RoleDaoImpl();
        user.setRole(roleDao.findById(3));
        user.setCreatedate(date);
        user.setPhone(phone);
        userDao.insert(user);
        return true;
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
        User user = userDao.findByEmail(email);
        if (user != null) {
            duplicate = true;
        }
        return duplicate;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        boolean duplicate = false;
        User user = userDao.findByPhone(phone);
        if (user != null) {
            duplicate = true;
        }
        return duplicate;
    }

    @Override
    public boolean updatePassword(String username, String password) {
        return userDao.updatePassword(username, password);
    }

    public static void main(String[] args) {
        try {
            IUserService userService = new UserServiceImpl();
            System.out.println(userService.checkExistEmail("tuan@gmail.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
