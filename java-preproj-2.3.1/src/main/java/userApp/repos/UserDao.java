package userApp.repos;

import org.springframework.stereotype.Repository;
import userApp.entities.Role;
import userApp.entities.User;

import java.util.List;

@Repository
public interface UserDao {

    void createUser(User u);

    List<User> getUsers();

    Role getRoleById(long id);

    void setAdminRole(User user);

    User userById(long id);

    User userByLogin(String l);

    void updateUser(long id, User u);

    void deleteUserByLogin(String l);

    void deleteUserById(long id);
}
