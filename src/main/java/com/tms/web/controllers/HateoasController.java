package com.tms.web.controllers;

import com.tms.web.entity.User;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hate")
public class HateoasController {
    private CollectionModel<User> collectionModel;

    @GetMapping
    public CollectionModel<User> get() {
        User whale = new User("Whale", "1", 20);
        Link whaleLink = WebMvcLinkBuilder.linkTo(HateoasController.class).slash("User1").withSelfRel();
        whale.add(whaleLink);
        User andy = new User("Andy", "1", 20);
        Link andyLink = WebMvcLinkBuilder.linkTo(HateoasController.class).slash("User2").withSelfRel();
        whale.add(andyLink);
        User pandy = new User("Pandy", "1", 20);
        Link pandyLink = WebMvcLinkBuilder.linkTo(HateoasController.class).slash("User3").withSelfRel();
        whale.add(pandyLink);
        Link link = WebMvcLinkBuilder.linkTo(HateoasController.class).withSelfRel();
        return CollectionModel.of(List.of(andy, pandy, whale), link);
    }

    @GetMapping("{userName}")
    public User getUser(@PathVariable("userName") String name) {
        return new User(name, "pass", 20);
    }
}