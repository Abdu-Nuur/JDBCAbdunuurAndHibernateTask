package peaksoft.service;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoJdbcImpl();
    private final UserDao userDaoh = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoh.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoh.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoh.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoh.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDaoh.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoh.cleanUsersTable();
    }
}
