package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("create table  users(" +
                    "id serial primary key," +
                    "name varchar(50)  ," +
                    "last_name varchar(50) ," +
                    "age int not null );").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Table created successfully!");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery(
                    "drop table  users;").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("The table was deleted successfully!");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.persist(new User(name, lastName, age));

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            System.out.println("error saveUsers!!!");
        }

    }
    @Override
    public void removeUserById(long id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            session.getTransaction().commit();
            session.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            List users = session.createQuery("select u from User u").list();

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.createSQLQuery("truncate table users;").executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}

