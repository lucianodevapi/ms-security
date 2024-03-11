package com.security.controller;

import com.security.dto.request.AuthenticationRequest;
import com.security.dto.response.AuthenticationResponse;
import com.security.persistence.entity.User;
import com.security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest authenticationRequest){

        AuthenticationResponse response = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
