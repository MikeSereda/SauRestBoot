package ru.sereda.saurestboot.DAO.interfaces;

import ru.sereda.saurestboot.businesslogic.Role;
import ru.sereda.saurestboot.businesslogic.User;

import java.util.List;

public interface RoleDAO {
    Role getRole(String name);
    List<Role> getRoles (String username);
    List<Role> getRoles (Long id);
    List<Role> getRoles (User user);
    void changeRoles(User user, List<Role> roles);
    void changeRoles(User user);
}
