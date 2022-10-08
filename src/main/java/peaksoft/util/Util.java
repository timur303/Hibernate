package peaksoft.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import peaksoft.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String url = "jdbc:postgresql://localhost:5432/user1";
    private static final String user = "postgres";
    private static final String password = "3019";

    public static Connection connection() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Connection on postgres");
        } catch (SQLException e) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure().build();
            SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            User user2 = new User();
            System.out.println(e.getMessage());
        }
        return connect;
    }

    //Hibernate
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "org.postgresql.Driver");
                properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/user1");
                properties.put(Environment.USER, "postgres");
                properties.put(Environment.PASS, "3019");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }

}





