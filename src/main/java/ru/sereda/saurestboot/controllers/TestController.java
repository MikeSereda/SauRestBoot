package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.DTO.UserDTO;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.DeviceParameterSetService;
import ru.sereda.saurestboot.service.interfaces.SessionService;
import ru.sereda.saurestboot.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    DeviceParameterSetService parameterSetService;

    @Autowired
    SessionService sessionService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    UserService userService;

    @PostMapping("/phones")
    public PhoneRegion addPhoneRegions(
            @RequestBody List<PhoneRegion> phoneRegions
    ){
        System.out.println(phoneRegions.toString());
        return null;
    }

    @GetMapping("/authenticated")
    public String authenticated(){
        try {
            return "Auth";
        } catch (ArithmeticException e){
            throw new BadCredentialsException("invalid.");
        }
    }

    @GetMapping("/admin")
    public String admin(){
        return "Admin";
    }

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
    public ResponseEntity<UserDTO> editUser(@PathVariable("id") Long userId, @RequestBody User user){
        //DTO для апдейта описания и ролей
        //изменить пользоателя (по сути - только роли и описание)
        return null;
    }

    @PostMapping("/admin/user-add")
    public User register(@RequestBody User user){ //DTO для создания юзера с Base64 пароля
        //создать пользователя
        return null;
    }

}
