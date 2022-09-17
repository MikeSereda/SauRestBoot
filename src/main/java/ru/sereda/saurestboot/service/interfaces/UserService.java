package ru.sereda.saurestboot.service.interfaces;

import ru.sereda.saurestboot.businesslogic.Role;
import ru.sereda.saurestboot.businesslogic.User;

import java.util.List;

public interface UserService {
    User getUser(String username);
    User getUser(Long id);
    User register(User user);
    List<User> getUsers();
    void setUserRoles(String username, List<Role> roles);
    void setUserRoles(User user, List<Role> roles);
    User editUser (User user);
}
