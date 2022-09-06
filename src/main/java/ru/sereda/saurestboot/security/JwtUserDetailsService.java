package ru.sereda.saurestboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.sereda.saurestboot.businesslogic.User;
import ru.sereda.saurestboot.security.jwt.JwtUser;
import ru.sereda.saurestboot.security.jwt.JwtUserFactory;
import ru.sereda.saurestboot.service.interfaces.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if (user==null){
            throw new UsernameNotFoundException("User with name: "+username+" not found");
        }
//        JwtUser jwtUser = JwtUserFactory.generateUser(user);
//        return jwtUser;
        return JwtUserFactory.generateUser(user);
    }
}
