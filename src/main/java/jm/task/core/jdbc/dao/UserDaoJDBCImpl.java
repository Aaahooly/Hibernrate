package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    //создать таблицу пользователей
    public void createUsersTable() {
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(30) NOT NULL, lastName VARCHAR(30) NOT NULL, age SMALLINT NOT NULL,PRIMARY KEY(id))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //удалить таблицу пользователей
    public void dropUsersTable() {
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //сохранить пользователя
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (name,lastNAme,age) values (?,?,?)";
        try (PreparedStatement preparedStatement = new Util().getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User c именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // удалить пользователя по id
    public void removeUserById(long id) {
        try (PreparedStatement statement = new Util().getConnection().prepareStatement("DELETE FROM user WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    //Получить вссех пользователей списком юзеров
    public List<User> getAllUsers() {
        List<User> userList = new LinkedList<>();
        try (Statement statement = new Util().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString(2), resultSet.getString(3), (Byte) resultSet.getByte(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    // очистить таблицу Пользователей
    public void cleanUsersTable() {
        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.executeUpdate("DELETE FROM user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
