package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.DAO.interfaces.RoleDAO;
import ru.sereda.saurestboot.businesslogic.Role;
import ru.sereda.saurestboot.businesslogic.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public Role getRole(String name) {
        return null;
    }

    @Override
    public List<Role> getRoles(String username) {
        String sql = "SELECT name FROM roles WHERE id IN " +
                "(SELECT role_id FROM users RIGHT JOIN role_users " +
                "ON users.id = role_users.user_id WHERE users.name=?);";
        List<String> roleNames = jdbcTemplate.queryForList(sql,String.class,username);
        List<Role> roles = new ArrayList<>();
        for (String roleName : roleNames){
            roles.add(new Role(roleName));
        }
        return roles;
    }

    @Override
    public List<Role> getRoles(Long id) {
        String sql = "SELECT name FROM roles WHERE id IN " +
                "(SELECT role_id FROM users RIGHT JOIN role_users " +
                "ON users.id = role_users.user_id WHERE users.id=?);";
        List<String> roleNames = jdbcTemplate.queryForList(sql,String.class,id);
        List<Role> roles = new ArrayList<>();
        for (String roleName : roleNames){
             roles.add(new Role(roleName));
        }
        return roles;
    }

    @Override
    public void changeRoles(User user, List<Role> roles) {
        String sqlDelete = "DELETE FROM role_users WHERE user_id=?";
        jdbcTemplate.update(sqlDelete,user.getId());
        String sqlInsert = "INSERT INTO public.role_users" +
                "(user_id, role_id) VALUES " +
                "(?,(SELECT id FROM roles WHERE name=?));";
        for (Role role : roles){
            jdbcTemplate.update(sqlInsert,user.getId(),role.getName());
        }
    }

    @Override
    public void changeRoles(User user) {
        String sqlDelete = "DELETE FROM role_users WHERE user_id=?";
        jdbcTemplate.update(sqlDelete,user.getId());
        String sqlInsert = "INSERT INTO public.role_users" +
                "(user_id, role_id) VALUES " +
                "(?,(SELECT id FROM roles WHERE name=?));";
        for (Role role : user.getRoles()){
            jdbcTemplate.update(sqlInsert,user.getId(),role.getName());
        }
    }
}



































