package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.DTO.UserDTO;
import ru.sereda.saurestboot.businesslogic.User;
import ru.sereda.saurestboot.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdministrationController {

    @Autowired
    UserService userService;

    @GetMapping("/admin/users")
    public List<UserDTO> getUsers(){
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : userService.getUsers()){
            userDTOs.add(new UserDTO(user));
        }
        return userDTOs;
    }

    @GetMapping("/admin/users/{id}")
    public ResponseEntity<User> getUsers(@PathVariable("id") Long userId){
        User user = userService.getUser(userId);
        if (user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/admin/users/{id}")
    public ResponseEntity<UserDTO> editUser(@PathVariable("id") Long userId, @RequestBody UserDTO user){
        if (userService.getUser(userId)!=null){
            userService.editUser(UserDTO.getUserFromDTO(user));
            userService.setUserRoles(user.getUsername(),user.getRoles());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
//
//    @PostMapping("/admin/user-add")
//    public User register(@RequestBody User user){ //DTO для создания юзера с Base64 пароля
//        //создать пользователя
//        return null;
//    }
}
