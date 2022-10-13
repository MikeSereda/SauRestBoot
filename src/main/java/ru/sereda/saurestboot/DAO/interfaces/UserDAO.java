package ru.sereda.saurestboot.DAO.interfaces;

import ru.sereda.saurestboot.businesslogic.User;

import java.util.List;

public interface UserDAO {
    User save(User user);
    List<User> getAll();
    User getUserByUsername(String username);
    User getUserById(Long userId);
}
