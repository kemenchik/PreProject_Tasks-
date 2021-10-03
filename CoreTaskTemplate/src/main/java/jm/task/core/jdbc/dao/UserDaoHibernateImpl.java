package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory factory;

    public UserDaoHibernateImpl() {
        factory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        Session session = factory.openSession();
        session.getTransaction().begin();
        session.createSQLQuery("create table if not exists users (id bigint auto_increment, user_name varchar(256), user_lastname varchar (256), user_age smallint, primary key (id))").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = factory.openSession();
        session.getTransaction().begin();
        session.createSQLQuery("drop table if exists users").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = factory.openSession();
        session.getTransaction().begin();
        session.save(new User(name, lastName, age));
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = factory.openSession();
        session.getTransaction().begin();
        User user = session.load(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List getAllUsers() {
        Session session = factory.openSession();
        session.getTransaction().begin();
        List result = session.createQuery("from User").getResultList();
        session.close();
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Session session = factory.openSession();
        session.getTransaction().begin();
        session.createSQLQuery("DELETE FROM users").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
