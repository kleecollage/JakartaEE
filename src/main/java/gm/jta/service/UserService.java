package gm.jta.service;

import gm.jta.domain.User;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserService {
    public List<User> findAllUsers();

    public User findUserById(User user);

    public User findUserByUsername(User user);

    public void insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);
}
