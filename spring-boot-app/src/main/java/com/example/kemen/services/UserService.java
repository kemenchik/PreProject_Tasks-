package com.example.kemen.services;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.kemen.entities.User;

import org.springframework.data.domain.Pageable;
import java.sql.SQLException;

@Service
public interface UserService {
    void addUser(User u);

    Page<User> getAllUsers(Pageable pageable);

    User getUserById(long id);

    User getUserByLogin(String l) throws SQLException;

    void updateUser(User user);

    void deleteUserById(long id);

    String getImageByVkUserId(String str);

    User getUserByGoogleId(String id);

    UserDetails loadUserByUsername(String login) throws UsernameNotFoundException;
}

