package com.baeldung.springboot.dtos;

import com.baeldung.springboot.models.Resource;
import lombok.Data;

@Data
public class UserDTO implements Resource {
    private Long id;
    private String name;
}
