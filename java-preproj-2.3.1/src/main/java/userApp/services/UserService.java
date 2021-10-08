package userApp.services;

import org.springframework.stereotype.Service;
import userApp.entities.User;

import java.sql.SQLException;
import java.util.List;

@Service
public interface UserService {
    void addUser(User u);

    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByLogin(String l) throws SQLException;

    void updateUser(long id,User user);

    void deleteUserById(long id);
}
