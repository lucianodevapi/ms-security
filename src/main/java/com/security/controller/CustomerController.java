package com.security.controller;

import com.security.dto.request.SaveUser;
import com.security.dto.response.RegisteredUser;
import com.security.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.security.persistence.entity.security.User;

import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationService;
    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<RegisteredUser> registerOne(@RequestBody SaveUser saveUser){
        RegisteredUser registeredUser = authenticationService.registerOnCustomer(saveUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
    @PreAuthorize("denyAll")
    @GetMapping
    public ResponseEntity<List<User>> findAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(List.of());
    }
}
