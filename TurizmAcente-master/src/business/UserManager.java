package business;

import core.Db;
import core.Helper;
import dao.UserDao;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManager {
    // private Connection con;
    private final UserDao userDao;

    public UserManager() {

        this.userDao = new UserDao();

    }

    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);

    }

    public ArrayList<User> findAll() {

        return this.userDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> userList) {
        ArrayList<Object[]> userRoleList = new ArrayList<>();
        for (User user : userList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = user.getUserId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            userRoleList.add(rowObject);

        }
        return userRoleList;
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }


    public ArrayList<User> searcForTable(User.Role role) {
        String query = "SELECT * FROM public.user WHERE user_role = '" + role.toString() + "'";
        return this.userDao.selectByQuery(query);

    }

    public boolean save(User user) {
        if (this.getById(user.getUserId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }

    public boolean update(User user) {
        if (this.getById(user.getUserId()) == null) {
            Helper.showMsg(user.getUserId() + " ID bulunamadı");
            return false;
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id) {

        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }

}
