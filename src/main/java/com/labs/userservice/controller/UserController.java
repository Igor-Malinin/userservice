package com.labs.userservice.controller;

import com.labs.userservice.config.Config;
import com.labs.userservice.model.ChangeUserDto;
import com.labs.userservice.model.UserDto;
import com.labs.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/exists-by-id/{id}")
    public Boolean existsById(@PathVariable String id) {
        return userService.existsById(id);
    }

    @GetMapping("/get-dir-name/{id}")
    public String getDirName(@PathVariable String id) {
        return userService.getName(id);
    }

    @GetMapping("/get-all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/change-enabled/{id}")
    public String changeEnabled(@PathVariable String id) {
        return userService.changeEnabled(id);
    }

    @PatchMapping("/update-user/{id}")
    public String changeUser(@PathVariable String id, @RequestBody ChangeUserDto changeUserDto) {
        return userService.changeUser(id, changeUserDto);
    }

}





//    private final Config config;
//
//    @GetMapping("/property")
//    public String getUserProperty() {
//        return config.getUserProperty();
//    }
//
//    @GetMapping("/desc")
//    public String getDescription() {
//        return config.getDescription();
//    }
