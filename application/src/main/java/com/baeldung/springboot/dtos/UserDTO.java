package com.baeldung.springboot.dtos;

import com.baeldung.springboot.models.Resource;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO implements Resource {
    private Long id;
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
}
