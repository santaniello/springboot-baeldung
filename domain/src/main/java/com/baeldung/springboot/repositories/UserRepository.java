package com.baeldung.springboot.repositories;

import com.baeldung.springboot.models.User;

public interface UserRepository {
    void save(User user);
}
