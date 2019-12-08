package com.baeldung.springboot.models;

import lombok.Data;

@Data
public class UserMongo implements User{
    private Long id;
    private String name;
}
