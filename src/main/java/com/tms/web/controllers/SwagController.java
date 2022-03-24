package com.tms.web.controllers;

import com.tms.web.entity.User;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swag")
@Api(value = "Swagger")
public class SwagController {

    @GetMapping()
    @ApiResponse(description = "Get user")
    public User get() {
        return new User("Jenya", "uiii", 15);
    }

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Save user"),
            @ApiResponse(responseCode = "500", description = "Internal server exception")
    })
    public User save(@RequestBody User user) {
        System.out.println("Save user - " + user);
        return user;
    }

    @PutMapping
    @ApiResponse(responseCode = "200", description = "Update user")
    public User updateUserName(@RequestParam String name, @RequestBody User user) {
        System.out.println("Update user with name - " + name + ". New user data - " + user);
        return user;
    }

    @DeleteMapping
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Delete user"),
            @ApiResponse(responseCode = "500", description = "Server internal exc")})
    public void deleteUser(@RequestParam String name) {
        System.out.println("Delete user with name - " + name);
    }
}