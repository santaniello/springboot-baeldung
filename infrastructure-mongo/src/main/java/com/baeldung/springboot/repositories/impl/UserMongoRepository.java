package com.baeldung.springboot.repositories.impl;

import com.baeldung.springboot.models.User;
import com.baeldung.springboot.models.UserMongo;
import com.baeldung.springboot.repositories.UserRepository;
import com.baeldung.springboot.repositories.UserSpringDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("userMongoRepository")
public class UserMongoRepository implements UserRepository {

    @Autowired
    private UserSpringDataRepository userSpringDataRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void save(User user) {
        var userMongo = this.mapper.map(user, UserMongo.class);
        userSpringDataRepository.save(userMongo);
    }
}
