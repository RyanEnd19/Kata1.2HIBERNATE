package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String GET_ALL_USERS_QUERY = "FROM User";
    private static final String DELETE_ALL_USERS_QUERY = "DELETE FROM User";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS users " +
            "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
            "name VARCHAR(50), " +
            "lastName VARCHAR(50), " +
            "age TINYINT)";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS users";

    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE_QUERY).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(DROP_TABLE_QUERY).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(GET_ALL_USERS_QUERY, User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(DELETE_ALL_USERS_QUERY).executeUpdate();
            transaction.commit();
        }
    }
}
