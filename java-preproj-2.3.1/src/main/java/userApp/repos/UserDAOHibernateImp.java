package userApp.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import userApp.entities.Role;
import userApp.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOHibernateImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        Set<Role> roles = new HashSet();
        roles.add(getRoleById(RoleId.USER.getId()));
        user.setRoles(roles);
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
        return entityManager
                .createQuery("select u from User u where u.login=:login", User.class)
                .setParameter("login", login)
                .getSingleResult();
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

    @Transactional
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Transactional
    @Override
    public void setAdminRole(User user) {
        User dbUser = entityManager.find(User.class, user.getId());
        dbUser.addRole(getRoleById(RoleId.ADMIN.getId()));
        entityManager.merge(dbUser);
    }
}

