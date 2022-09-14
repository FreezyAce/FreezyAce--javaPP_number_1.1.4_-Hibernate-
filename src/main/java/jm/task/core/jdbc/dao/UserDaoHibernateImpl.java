package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;

import javax.persistence.criteria.CriteriaQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS lol4k " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    " name VARCHAR(300)," +
                    " lastName VARCHAR(300), " +
                    "age INT)").executeUpdate();
            transaction.commit();
            System.out.println("Table created!\n");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS lol4k").executeUpdate();
            System.out.println("Table cleared\n");
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User is name " + name + " saved\n");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String a = session.get(User.class, id).getName();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("\nUser " + a + " deleted!!\n");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            usersList = session.createSQLQuery("SELECT * FROM lol4k").addEntity(User.class).list();
            transaction.commit();
            return usersList;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE lol4k").executeUpdate();
            transaction.commit();
            System.out.println("Table cleared!\n");
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
