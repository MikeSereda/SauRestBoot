package ru.sereda.saurestboot.DAO.interfaces;

import ru.sereda.saurestboot.businesslogic.Role;
import ru.sereda.saurestboot.businesslogic.User;

import java.util.List;

public interface UserDAO {
    User save(User user);
    List<User> getAll();
    User getUserByUsername(String username);
    User getUserById(Long userId);
    void setUserRoles(User user, List<Role> roles);
    void setUserRoles(Long userId, List<Role> roles);
}
