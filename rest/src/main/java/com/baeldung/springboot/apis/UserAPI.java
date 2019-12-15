package com.baeldung.springboot.apis;

import com.baeldung.springboot.dtos.UserDTO;
import com.baeldung.springboot.models.User;
import com.baeldung.springboot.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAPI {

    @Autowired
    private UserAppService userAppService;

    @PostMapping
    public void save(@RequestBody UserDTO dto){
       userAppService.save(dto);
    }

    @GetMapping
    public List<User> findAll(){
        return userAppService.findAll();
    }
}
