package com.baeldung.springboot.apis;

import com.baeldung.springboot.AbstractController;
import com.baeldung.springboot.dtos.UserDTO;
import com.baeldung.springboot.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAPI extends AbstractController<UserDTO> {

    @Autowired
    private UserService userAppService;

    public UserAPI() {
        super(UserDTO.class);
    }

    @PostMapping
    public void save(@RequestBody @Valid  UserDTO dto){
       saveInternall(dto);
    }

    @GetMapping
    public List<UserDTO> findAll(){
       return findAllInternal();
    }

    @Override
    protected final UserService getService() {
        return userAppService;
    }
}
