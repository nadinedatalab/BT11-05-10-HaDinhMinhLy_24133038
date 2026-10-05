package vn.edu.iuh.services;

import vn.edu.iuh.dao.IUserDao_24133038;
import vn.edu.iuh.dao.UserDaoImpl_24133038;
import vn.edu.iuh.models.User_24133038;
import java.util.List;

public class UserServiceImpl_24133038 implements IUserService_24133038 {
    private IUserDao_24133038 userDao = new UserDaoImpl_24133038();

    @Override
    public boolean insert(User_24133038 user) { return userDao.insert(user); }
    @Override
    public boolean update(User_24133038 user) { return userDao.update(user); }
    @Override
    public boolean delete(String username) { return userDao.delete(username); }
    @Override
    public User_24133038 findByUsername(String username) { return userDao.findByUsername(username); }
    @Override
    public List<User_24133038> findAll(int offset, int limit) { return userDao.findAll(offset, limit); }
    @Override
    public int countAll() { return userDao.countAll(); }
    @Override
    public User_24133038 login(String username, String password) { return userDao.login(username, password); }
}
