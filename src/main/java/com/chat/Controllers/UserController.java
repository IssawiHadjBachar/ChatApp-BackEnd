package com.chat.Controllers;

import com.chat.Entities._user;
import com.chat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public _user register(@RequestBody _user user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public _user login(@RequestBody _user user) {
        return userService.login(user);
    }
    
    @GetMapping("/user")
    public _user findByUsername(@RequestParam String username) {
        System.out.println("Received request for username: " + username); // Debugging output
        _user user = userService.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return user;
    }

}
