package com.example.kemen.services;

import com.example.kemen.entities.Role;
import com.example.kemen.entities.User;
import com.example.kemen.repos.RoleCrud;
import com.example.kemen.repos.UserCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserCrud userCrud;
    @Autowired
    private CacheImageService cacheImageService;

    @Override
    public void addUser(User user) {
        userCrud.save(user);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userCrud.findAll(pageable);
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
        System.out.println(login);
        return getUserByLogin(login);
    }

    @Override
    public User getUserByGoogleId(String id) {
        return userCrud.findUserByGoogleId(id);
    }

    @Override
    public String getImageByVkUserId(String str) {
        return cacheImageService.getCacheableImageByUserVkId(str);
    }
}

