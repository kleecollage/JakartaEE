package gm.jta.data;

import gm.jta.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAllUsers();

    public User findUserById(User user);

    public User findUserByUsername(User user);

    public void insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);
}
