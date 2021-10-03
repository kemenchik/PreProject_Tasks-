package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("PreProj?").
                    append("user=root&").
                    append("password=");

            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                .setProperty("connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.order_updates", "true")
                .setProperty("connection.url", "jdbc:mysql://localhost:3306/preproj")
                .setProperty("connection.username", "root")
                .setProperty("connection.password", "")
                .setProperty("connection.pool_size", "10")
                .setProperty("show_sql", "true")
                .setProperty("hbm2ddl.auto", "create")
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
