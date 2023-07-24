package com.bms.schoolmanagementsystem.controller;

import com.bms.schoolmanagementsystem.dto.request.RegistrationRequest;
import com.bms.schoolmanagementsystem.exception.UsernameAlreadyExistsException;
import com.bms.schoolmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest request) {
        try {
            // Register the user using the UserService
            userService.registerNewUser(request.getUsername(), request.getPassword(), request.getRoleName());
            return ResponseEntity.ok("User registered successfully.");
        } catch (UsernameAlreadyExistsException | RoleNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during registration.");
        }
    }
}

