package peaksoft.dao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import peaksoft.model.User;
import peaksoft.util.Util;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully users table created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("delete User");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully drop");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            session.close();
            System.out.println("Successfully save users " + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("delete from  users where id = ?").executeUpdate();
            transaction.commit();
            System.out.println("Successfully remove" + id);
        } catch (Exception e) {
            System.out.println(" can't found users byId ");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            List<User> userList = session.createQuery("from User").list();
            session.getTransaction().commit();
            session.close();
            System.out.println("Found " + userList + " users");
            return userList;
        } catch (Exception r) {
            System.out.println(r.getMessage());
        }

        return null;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("DELETE User");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Cleaning  table users");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}