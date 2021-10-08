package userApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import userApp.entities.User;
import userApp.repos.UserDao;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.createUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return userDao.userById(id);
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) throws SQLException {
        User user = userDao.userByLogin(login);
        return user;
    }

    @Override
    @Transactional
    public void updateUser(long id, User user) {
        userDao.updateUser(id, user);
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }
}
