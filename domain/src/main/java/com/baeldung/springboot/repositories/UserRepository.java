package com.baeldung.springboot.repositories;

import com.baeldung.springboot.models.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    List<User> findAll();
}
