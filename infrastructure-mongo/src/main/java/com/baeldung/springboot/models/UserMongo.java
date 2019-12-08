package com.baeldung.springboot.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "user")
public class UserMongo implements User{
    @Id
    @Field("_id")
    private Long id;
    private String name;
}
