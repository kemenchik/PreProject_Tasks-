package com.example.kemen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.kemen.entities.User;
import com.example.kemen.repos.UserDao;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDao.userById(id);
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        User user = userDao.userByLogin(login);
        return user;
    }

    @Override
    public void updateUser(long id, User user) {
        userDao.updateUser(id, user);
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void addAdmin(User user) {
        userDao.setAdminRole(user);
    }

    @Override
    public void removeAdmin(User user) {
        userDao.removeAdminRole(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return getUserByLogin(login);
    }
}

