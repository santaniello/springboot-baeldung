package com.baeldung.springboot.services;

import com.baeldung.springboot.dtos.UserDTO;
import com.baeldung.springboot.models.User;
import com.baeldung.springboot.models.UserMongo;
import com.baeldung.springboot.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAppService {

    private ModelMapper mapper;

    private UserRepository repository;

    @Autowired
    public UserAppService(ModelMapper mapper,
                          @Qualifier("userMongoRepository") UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public void save(UserDTO dto){
        UserMongo userMongo = mapper.map(dto, UserMongo.class);
        repository.save(userMongo);
    }

    public List<User> findAll(){
        return repository.findAll();
    }
}
