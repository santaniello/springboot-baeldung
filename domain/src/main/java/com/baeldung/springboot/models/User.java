package com.baeldung.springboot.models;

public interface User extends Resource {
    String getName();
    void setName(String name);
}
