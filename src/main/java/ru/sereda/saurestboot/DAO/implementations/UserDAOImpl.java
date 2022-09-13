package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.DAO.interfaces.RoleDAO;
import ru.sereda.saurestboot.DAO.interfaces.UserDAO;
import ru.sereda.saurestboot.businesslogic.User;
import ru.sereda.saurestboot.rowmappers.UserMapper;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        // Раздельные получение из БД и ретурн
        //        List<User> users = jdbcTemplate.query(sql, new UserMapper());
        //        return users;
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE name=?";
        List<User> users = jdbcTemplate.query(sql, new UserMapper(),username);
        if (users.size()>0){
            return users.get(0);
        }
        else return null;
    }

    @Override
    public User getUserById(Long userId) {
        String sql = "SELECT * FROM users WHERE id=?";
        List<User> users = jdbcTemplate.query(sql, new UserMapper(),userId);
        if (users.size()>0){
            return users.get(0);
        }
        else return null;
    }
}
