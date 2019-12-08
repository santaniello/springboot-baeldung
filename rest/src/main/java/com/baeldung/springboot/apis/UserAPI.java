package com.baeldung.springboot.apis;

import com.baeldung.springboot.dtos.UserDTO;
import com.baeldung.springboot.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAPI {

    @Autowired
    private UserAppService userAppService;

    @PostMapping
    public void save(UserDTO dto){
        userAppService.save(dto);
    }
}
