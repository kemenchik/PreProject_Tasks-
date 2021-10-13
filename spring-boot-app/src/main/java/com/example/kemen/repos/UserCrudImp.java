//package com.example.kemen.repos;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import com.example.kemen.entities.Authority;
//import com.example.kemen.entities.Role;
//import com.example.kemen.entities.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Repository
//public class UserCrudImp implements UserCrud {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public void createUser(User user) {
//        Set<Authority> authorities = new HashSet();
//        authorities.add(getAuthorityByRole(Role.ROLE_USER));
//        user.setAuthorities(authorities);
//        entityManager.persist(user);
//    }
//
//    @Override
//    public List<User> getUsers() {
//        return entityManager.createNativeQuery("select * from users", User.class).getResultList();
//    }
//
//    @Override
//    public User userById(long id) {
//        return entityManager.find(User.class, id);
//    }
//
//    @Override
//    public User userByLogin(String login) {
//        return entityManager
//                .createQuery("select u from User u where u.login=:login", User.class)
//                .setParameter("login", login)
//                .getSingleResult();
//    }
//
//    @Override
//    public void updateUser(long id, User user) {
//        user.setId(id);
//        entityManager.merge(user);
//    }
//
//    @Override
//    public void deleteUserByLogin(String login) {
//        entityManager.remove(userByLogin(login));
//    }
//
//    @Override
//    public void deleteUserById(long id) {
//        entityManager.remove(userById(id));
//    }
//
//    @Transactional
//    protected Authority getAuthorityByRole(Role role) {
//        return entityManager
//                .createQuery("select a from Authority a where a.role=:role", Authority.class)
//                .setParameter("role", role)
//                .getSingleResult();
//    }
//
//    @Override
//    public void setAdminRole(User user) {
//        User dbUser = entityManager.find(User.class, user.getId());
//        dbUser.addAuthority(getAuthorityByRole(Role.ROLE_ADMIN));
//        entityManager.merge(dbUser);
//    }
//
//    @Override
//    public void removeAdminRole(User user) {
//        User dbUser = entityManager.find(User.class, user.getId());
//        dbUser.removeAuthority(getAuthorityByRole(Role.ROLE_ADMIN));
//        entityManager.merge(dbUser);
//    }
//}
//
