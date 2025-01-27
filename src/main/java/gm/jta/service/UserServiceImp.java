package gm.jta.service;

import gm.jta.data.UserDao;
import gm.jta.domain.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class UserServiceImp implements UserService, UserServiceRemote {

    @Inject
    private UserDao userDao;

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public User findUserById(User user) {
        return userDao.findUserById(user);
    }

    @Override
    public User findUserByUsername(User user) {
        return userDao.findUserByUsername(user);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
