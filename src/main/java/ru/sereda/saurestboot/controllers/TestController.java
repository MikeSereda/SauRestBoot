package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.*;
import ru.sereda.saurestboot.service.interfaces.DeviceService;
import ru.sereda.saurestboot.service.interfaces.DeviceParameterSetService;
import ru.sereda.saurestboot.service.interfaces.SessionService;
import ru.sereda.saurestboot.service.interfaces.UserService;

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
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/admin/users/{id}")
    public User getUsers(@PathVariable("id") Long userId){
        return userService.getUser(userId);
    }

    @PostMapping("/admin/users/{id}")
    public User editUser(@PathVariable("id") Long userId, @RequestBody User user){ //DTO для апдейта описания и ролей
        //изменить пользоателя (по сути - только роли и описание)
        return null;
    }

    @PostMapping("/admin/user-add")
    public User register(@RequestBody User user){ //DTO для создания юзера с Base64 пароля
        //создать пользователя
        return null;
    }

}
