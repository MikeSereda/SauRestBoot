package ru.sereda.saurestboot.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.DAO.interfaces.RoleDAO;
import ru.sereda.saurestboot.DAO.interfaces.UserDAO;
import ru.sereda.saurestboot.businesslogic.Role;
import ru.sereda.saurestboot.businesslogic.User;
import ru.sereda.saurestboot.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
//    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleDAO roleDAO, /*BCryptPasswordEncoder passwordEncoder,*/ UserDAO userDAO) {
        this.roleDAO = roleDAO;
//        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
    }


    @Override
    public User getUser(String username) {
        User user = userDAO.getUserByUsername(username);
        if (user==null){
            System.out.println("IN getUser - user not found by username="+username);
        }
        else{
            user.setRoles(roleDAO.getRoles(user.getId()));
        }
        return user;
    }

    @Override
    public User getUser(Long id) {
        User user = userDAO.getUserById(id);
        if (user==null){
            System.out.println("IN getUser - user not found by id="+id);
        }
        else {
            user.setRoles(roleDAO.getRoles(user.getId()));
        }
        return user;
    }

    @Override
    public User register(User user) {
        User registeredUser = userDAO.save(user);
        roleDAO.changeRoles(user);
        System.out.println("IN register - user successfully registered :"+ registeredUser.toString());
        return registeredUser;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userDAO.getAll();
        for (User user : users){
            user.setRoles(roleDAO.getRoles(user.getId()));
        }
        return users;
    }

    @Override
    public void setUserRoles(String username, List<Role> roles) {
        User user = userDAO.getUserByUsername(username);
        user.setRoles(roles);
        roleDAO.changeRoles(user);
    }

    @Override
    public void setUserRoles(User user, List<Role> roles) {
        user.setRoles(roles);
        roleDAO.changeRoles(user);
    }

    @Override
    public User editUser(User user) {
        User updatedUser = userDAO.save(user);
        roleDAO.changeRoles(user);
        System.out.println("IN register - user successfully updated :"+ updatedUser.toString());
        return updatedUser;
    }
}
