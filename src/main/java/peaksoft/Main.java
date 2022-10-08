package peaksoft;

import peaksoft.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        //hibernate
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        //creat table
        userDaoHibernate.createUsersTable();
        //getAll users
        userDaoHibernate.getAllUsers();
        //saveUsers
        userDaoHibernate.saveUser("Timur", "Kadyrbekov", (byte) 21);
        userDaoHibernate.saveUser("Atabek", "Dosbaev", (byte) 21);
        //remove users by id
        userDaoHibernate.removeUserById(52);
        //clean users
        userDaoHibernate.cleanUsersTable();
        //drop table
        userDaoHibernate.dropUsersTable();


//        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
        //createTable user1
//        userDaoJdbc.createUsersTable();
        //save User (INTO INSERT )
//        userDaoJdbc.saveUser("Timur","Kadyrbekov", (byte) 21);
//        userDaoJdbc.saveUser("Zukh", "Kamchybekov",(byte) 20);
//        userDaoJdbc.saveUser("Zarip","Kursanov",(byte) 20);
//        userDaoJdbc.saveUser("Atabek","Dosbaev",(byte) 20);
        //clean User
//        userDaoJdbc.cleanUsersTable();

        //callByIdRemove
//        userDaoJdbc.removeUserById(3);

//        userDaoJdbc.getAllUsers();
//        List<User> userDaoJdbcList = userDaoJdbc.getAllUsers();
//        System.out.println(userDaoJdbcList);

        //drop user1 table
//        userDaoJdbc.dropUsersTable();

    }
}
