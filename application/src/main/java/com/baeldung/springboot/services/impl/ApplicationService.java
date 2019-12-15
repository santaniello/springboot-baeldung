package com.baeldung.springboot.services.impl;

import java.util.List;

public interface ApplicationService<T> {
    void save(T resource);
    List<T> findAll();
}
