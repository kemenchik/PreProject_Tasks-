package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getMysqlConnection();
    }

    public void createUsersTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("create table if not exists users (id bigint auto_increment, user_name varchar(256), user_lastname varchar (256), user_age smallint, primary key (id))");
            stmt.close();
        } catch (SQLException ignored) {

        }
    }

    public void dropUsersTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("drop table users");
            stmt.close();
        } catch (SQLException ignored) {

        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(String.format("insert into users (user_name, user_lastname, user_age) values ('%s', '%s', %d)", name, lastName, age));
        stmt.close();
    }

    public void removeUserById(long id) {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(String.format("DELETE FROM users WHERE id = %d", id));
            stmt.close();
        } catch (SQLException ignored) {

        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("SELECT * FROM users");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                list.add(new User(result.getString(2), result.getString(3), result.getByte(4)));
            }
            result.close();
            stmt.close();
            return list;
        } catch (Exception ignored) {

        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM users");
            stmt.close();
        } catch (SQLException ignored) {

        }
    }
}
