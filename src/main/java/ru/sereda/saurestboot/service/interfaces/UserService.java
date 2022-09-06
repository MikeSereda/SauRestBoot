package ru.sereda.saurestboot.service.interfaces;

import ru.sereda.saurestboot.businesslogic.User;

import java.util.List;

public interface UserService {
    User getUser(String username);
    User getUser(Long id);
    User register(User user);
    List<User> getUsers();
}
