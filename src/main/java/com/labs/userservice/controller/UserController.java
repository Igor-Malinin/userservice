package com.labs.userservice.controller;

import com.labs.userservice.config.Config;
import com.labs.userservice.model.UserDto;
import com.labs.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class PropertyController {

    private final UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

//    private final Config config;

//    @GetMapping
//    public String getUserProperty() {
//        return config.getUserProperty();
//    }

//    @GetMapping
//    public String getDescription() {
//        return config.getDescription();
//    }
}
