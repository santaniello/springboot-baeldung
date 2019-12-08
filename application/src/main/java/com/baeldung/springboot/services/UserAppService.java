package com.baeldung.springboot.services;

import com.baeldung.springboot.dtos.UserDTO;
import com.baeldung.springboot.models.User;
import com.baeldung.springboot.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserAppService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    @Qualifier("userMongoRepository")
    private UserRepository repository;

    public void save(UserDTO dto){
        var user = mapper.map(dto, User.class);
        repository.save(user);
    }
}
