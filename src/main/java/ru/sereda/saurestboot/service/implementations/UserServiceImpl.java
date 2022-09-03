package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.RoleDAO;
import ru.sereda.saurestboot.DAO.interfaces.UserDAO;
import ru.sereda.saurestboot.businesslogic.User;
import ru.sereda.saurestboot.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public List<User> getUsers(String username) {
        return null;
    }
}
