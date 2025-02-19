package com.utez.integradora.controller;

import com.utez.integradora.entity.dto.ReqRes;
import com.utez.integradora.repository.UserRepository;
import com.utez.integradora.service.UsersManagementService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {
    private final UserRepository userRepository;

    static final Logger logger = LoggerFactory.getLogger(UserAuthController.class);

    private final UsersManagementService usersManagementService;


    @PostMapping("/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reqRes ) {
        return ResponseEntity.ok(usersManagementService.register(reqRes));
    }

    @PostMapping("/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes reqRes ) {
        return ResponseEntity.ok(usersManagementService.login(reqRes));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refresh(@RequestBody ReqRes reqRes ) {
        return ResponseEntity.ok(usersManagementService.refreshToken(reqRes));
    }

   
}
