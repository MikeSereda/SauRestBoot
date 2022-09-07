package ru.sereda.saurestboot.DAO.implementations;

import org.springframework.stereotype.Repository;
import ru.sereda.saurestboot.DAO.interfaces.UserDAO;
import ru.sereda.saurestboot.businesslogic.Role;
import ru.sereda.saurestboot.businesslogic.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        Role role = new Role();
        role.setName("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setPassword("$2a$09$JnA374DlP5IqX/fi9Ho4j.12B3khyKtv3srZSmNodTIJRKoC9GvAC");
        return user;
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }
}
