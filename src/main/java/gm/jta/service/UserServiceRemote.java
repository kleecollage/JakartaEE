package gm.jta.service;

import gm.jta.domain.User;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface UserServiceRemote {
    public List<User> findAllUsers();

    public User findUserById(User user);

    public User findUserByUsername(User user);

    public void insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);
}
