package com.example.kemen.repos;

import com.example.kemen.entities.User;

import java.util.List;

public interface UserDao {

    void createUser(User u);

    List<User> getUsers();

    void setAdminRole(User user);

    User userById(long id);

    User userByLogin(String l);

    void updateUser(long id, User u);

    void deleteUserByLogin(String l);

    void deleteUserById(long id);

    void removeAdminRole(User u);
}

