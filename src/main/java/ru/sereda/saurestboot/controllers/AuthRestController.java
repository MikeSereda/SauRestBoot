package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.DTO.AuthRequestDTO;
import ru.sereda.saurestboot.businesslogic.User;
import ru.sereda.saurestboot.security.jwt.JwtAuthException;
import ru.sereda.saurestboot.security.jwt.JwtTokenProvider;
import ru.sereda.saurestboot.service.interfaces.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManagerBean, JwtTokenProvider jwtTokenProvider, UserService userService){
        this.authenticationManager = authenticationManagerBean;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody AuthRequestDTO authRequestDTO){
        try {
            String username = authRequestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,authRequestDTO.getPassword()));
            User user = userService.getUser(username);

            if (user == null){
                throw new UsernameNotFoundException("not found");
            }

            String token = jwtTokenProvider.createToken(username,user.getRoles());
            Map<String,String> response = new HashMap<>();
            response.put("username",username);
            response.put("token",token);
            return ResponseEntity.ok(response);
        }
        catch (JwtAuthException e){
            throw new BadCredentialsException("invalid username or password");
        }
    }
}
