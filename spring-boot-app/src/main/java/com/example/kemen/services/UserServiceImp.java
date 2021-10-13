package com.example.kemen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.kemen.entities.User;
import com.example.kemen.repos.UserCrud;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserCrud userCrud;

    @Override
    public void addUser(User user) {
        userCrud.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userCrud.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userCrud.findUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userCrud.findUserByLogin(login);
    }

    @Override
    public void updateUser(User user) {
        userCrud.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        userCrud.removeUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return getUserByLogin(login);
    }
}

