package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.DAO.interfaces.RoleDAO;
import ru.sereda.saurestboot.DAO.interfaces.UserDAO;
import ru.sereda.saurestboot.businesslogic.Device;
import ru.sereda.saurestboot.businesslogic.Role;
import ru.sereda.saurestboot.businesslogic.User;
import ru.sereda.saurestboot.rowmappers.DeviceMapperImpl;
import ru.sereda.saurestboot.rowmappers.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        for (User user : users){
            user.setRoles(roleDAO.getRoles(user.getId()));
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE name=?";
        List<User> users = jdbcTemplate.query(sql, new UserMapper(),username);
        users.get(0).setRoles(roleDAO.getRoles(users.get(0).getId()));
        return users.get(0);
    }

    @Override
    public User getUserById(Long userId) {
        String sql = "SELECT * FROM users WHERE id=?";
        List<User> users = jdbcTemplate.query(sql, new UserMapper(),userId);
        users.get(0).setRoles(roleDAO.getRoles(users.get(0).getId()));
        return users.get(0);
    }

    @Override
    public void setUserRoles(User user, List<Role> roles) {
        roleDAO.changeRoles(user,roles);
    }

    @Override
    public void setUserRoles(Long userId, List<Role> roles) {
        roleDAO.changeRoles(getUserById(userId),roles);
    }
}
