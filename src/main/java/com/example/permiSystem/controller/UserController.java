package com.example.permiSystem.controller;

import com.example.permiSystem.dto.CreateUserRequest;
import com.example.permiSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("this is homepage");
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest){
        try {
            userService.createUser(createUserRequest);
            return ResponseEntity.ok("User created Successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error"+ e.getMessage());
        }

    }

}
