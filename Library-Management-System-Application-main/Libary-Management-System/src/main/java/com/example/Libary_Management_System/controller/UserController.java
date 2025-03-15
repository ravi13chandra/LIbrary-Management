package com.example.Libary_Management_System.controller;

import com.example.Libary_Management_System.enity.Users;
import com.example.Libary_Management_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/welcome")
    public String welcome() {
        return "Welcome to the new Library Management System";
    }

    //create new user POST
    @PostMapping(path = "/admin/user")
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        Users savedUser = userService.saveUser(user).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Update the user PUT
    @PutMapping(path = "/admin/user/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users user) {
        Users updatedUser = userService.updateUser(id, user).getBody();
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    // DELETE the USER BY ID
    @DeleteMapping(path = "/admin/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

}
