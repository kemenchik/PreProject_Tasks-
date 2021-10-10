package com.example.kemen.services;

import org.springframework.stereotype.Service;
import com.example.kemen.entities.User;

import java.sql.SQLException;
import java.util.List;

@Service
public interface UserService {
    void addUser(User u);

    List<User> getAllUsers();

    void addAdmin(User user);

    User getUserById(long id);

    User getUserByLogin(String l) throws SQLException;

    void updateUser(long id, User user);

    void deleteUserById(long id);

    void removeAdmin(User user);
}
