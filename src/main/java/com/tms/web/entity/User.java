package com.tms.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends RepresentationModel<User> {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private int age;

    public User(String name) {
        this.name = name;
    }
}