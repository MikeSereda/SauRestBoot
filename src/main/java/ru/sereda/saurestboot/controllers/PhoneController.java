package ru.sereda.saurestboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sereda.saurestboot.businesslogic.PhoneRegion;
import ru.sereda.saurestboot.service.interfaces.PhoneService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @GetMapping("/phones")
    public List<PhoneRegion> getPhones(){
        return phoneService.getPhones();
    }

    @GetMapping("/phones/{city}")
    public ResponseEntity<PhoneRegion> getPhones(@PathVariable("city") String city){
        PhoneRegion phoneRegion = phoneService.getPhones(city);
        if (phoneRegion!=null){
            return new ResponseEntity<>(phoneRegion, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
