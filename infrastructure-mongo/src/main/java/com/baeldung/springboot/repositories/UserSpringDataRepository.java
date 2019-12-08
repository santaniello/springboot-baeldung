package com.baeldung.springboot.repositories;

import com.baeldung.springboot.models.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpringDataRepository extends MongoRepository<UserMongo, Long> {
}
