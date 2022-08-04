package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.query.NativeQuery;

import java.util.Iterator;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Util util = new Util();
    SessionFactory sessionFactory = util.getSessionFactory();
    Session session = sessionFactory.openSession();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            NativeQuery sqlQuery = session.createNativeQuery("CREATE TABLE IF NOT EXISTS user (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(30) NOT NULL, lastName VARCHAR(30) NOT NULL, age SMALLINT NOT NULL,PRIMARY KEY(id))", User.class);
            sqlQuery.addEntity(User.class);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
                e.printStackTrace();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            NativeQuery sqlQuery = session.createNativeQuery("DROP TABLE IF EXISTS user",User.class);
            sqlQuery.addEntity(User.class);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User c именем - " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
                e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
                e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
        List<User> userList = null;
        try {
            session.beginTransaction();
            NativeQuery sqlQuery = session.createNativeQuery("SELECT  * from user");
            sqlQuery.addEntity(User.class);
            userList = sqlQuery.list();
            for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
                User user = (User) iterator.next();
                System.out.println(user.toString());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            NativeQuery sqlQuery = session.createNativeQuery("delete from user");
            sqlQuery.addEntity(User.class);
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if(session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
