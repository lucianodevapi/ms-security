package com.security.controller;

import com.security.dto.request.SaveUser;
import com.security.dto.response.RegisteredUser;
import com.security.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<RegisteredUser> registerOne(@RequestBody SaveUser saveUser){
        RegisteredUser registeredUser = authenticationService.registerOnCustomer(saveUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
