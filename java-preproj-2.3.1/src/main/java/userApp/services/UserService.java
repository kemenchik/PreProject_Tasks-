package userApp.services;

import userApp.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void deleteUserByLogin(String l);

    void deleteUserById(long id);


}
