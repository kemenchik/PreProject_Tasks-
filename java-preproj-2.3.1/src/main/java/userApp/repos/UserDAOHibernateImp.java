package userApp.repos;

import org.springframework.stereotype.Repository;
import userApp.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOHibernateImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createNativeQuery("select * from users", User.class).getResultList();
    }

    @Override
    public User userById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User userByLogin(String login) {
        return (User) entityManager.createNativeQuery("select from users where login='" + login + "'").getSingleResult();
    }

    @Override
    public void updateUser(long id, User user) {
        user.setId(id);
        entityManager.merge(user);
    }

    @Override
    public void deleteUserByLogin(String login) {
        entityManager.remove(userByLogin(login));
    }

    @Override
    public void deleteUserById(long id) {
        entityManager.remove(userById(id));
    }
}
