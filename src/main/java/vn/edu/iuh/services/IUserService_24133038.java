package vn.edu.iuh.services;

import vn.edu.iuh.models.User_24133038;
import java.util.List;

public interface IUserService_24133038 {
    boolean insert(User_24133038 user);
    boolean update(User_24133038 user);
    boolean delete(String username);
    User_24133038 findByUsername(String username);
    List<User_24133038> findAll(int offset, int limit);
    int countAll();
    User_24133038 login(String username, String password);
}
