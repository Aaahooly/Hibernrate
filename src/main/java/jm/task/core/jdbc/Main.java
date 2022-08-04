package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl(); // <- при создании объекта автоматически создаётся таблица , как откуда не понятно
//        userService.createUsersTable(); // <- Не работает метод
//        userService.saveUser("Maksim", "Glukhov", (byte) 4);
//        userService.saveUser("Vitaliya", "Vasyliewa", (byte) 32);
//        userService.saveUser("Andrey", "Glukhov", (byte) 27);
//        userService.saveUser("Oleg", "Glukhov", (byte) 29);
//        System.out.println(userService.getAllUsers());
//        userService.cleanUsersTable();
        userService.dropUsersTable(); // <- Не удаляется
    }
}
