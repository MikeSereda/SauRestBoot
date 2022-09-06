package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.RoleDAO;
import ru.sereda.saurestboot.DAO.interfaces.UserDAO;
import ru.sereda.saurestboot.businesslogic.Role;
import ru.sereda.saurestboot.businesslogic.User;
import ru.sereda.saurestboot.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleDAO roleDAO, BCryptPasswordEncoder passwordEncoder, UserDAO userDAO) {
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }

    @Override
    public User getUser(String username) {
        User user = userDAO.getUserByUsername(username);
        if (user==null){
            System.out.println("IN getUser - user not found by username="+username);
        }
        return user;
    }

    @Override
    public User getUser(Long id) {
        User user = userDAO.getUserById(id);
        if (user==null){
            System.out.println("IN getUser - user not found by id="+id);
        }
        return user;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleDAO.getRole("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        User registeredUser = userDAO.save(user);
        System.out.println("IN register - user successfully registered :"+ registeredUser.toString());
        return registeredUser;
    }

    @Override
    public List<User> getUsers() {
//        List<User> users = userDAO.getAll();
//        return users;
        return userDAO.getAll();
    }
}
