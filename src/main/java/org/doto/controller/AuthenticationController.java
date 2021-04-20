package org.doto.controller;

import org.doto.dto.LoginRequest;
import org.doto.dto.RegisterRequest;
import org.doto.entity.Role;
import org.doto.entity.User;
import org.doto.service.TokenService;
import org.doto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        User loggedUser = userService.findByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword());

        return new ResponseEntity<>(tokenService.tokenProvider(loggedUser), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest){
        User newUser = User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(Role.ROLE_USER)
                .build();

        return new ResponseEntity<>(userService.save(newUser), HttpStatus.OK);
    }
}
