package com.utez.integradora.controller;


import com.utez.integradora.entity.UserEntity;
import com.utez.integradora.entity.dto.ReqRes;
import com.utez.integradora.service.UsersManagementService;
import com.utez.integradora.service.UsrDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UsersManagementService usersManagementService;
    private final UsrDetailsService usrDetailsService;

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers() {
        return ResponseEntity.ok(usersManagementService.getAllUsers());
    }
    @GetMapping("/admin/get-users/{id}")
    public ResponseEntity<ReqRes> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(usersManagementService.getUserById(id));
    }
    @PutMapping("/admin/update/{id}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer id, @RequestBody ReqRes reqRes ) {
        return ResponseEntity.ok(usersManagementService.updateUser(id, reqRes));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        ReqRes reqRes = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(reqRes.getStatusCode()).body(reqRes);
    }
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ReqRes reqRes) {
        Optional<UserEntity> optionalUserEntity = usersManagementService.findByEmail(reqRes.getEmail());

        if (!optionalUserEntity.isPresent()) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        UserEntity userEntity = optionalUserEntity.get();

        if (!usersManagementService.checkPassword(userEntity, reqRes.getPassword())) {
            return ResponseEntity.status(403).body("Contraseña actual incorrecta");
        }

        usersManagementService.changePassword(userEntity, reqRes.getNewPassword());

        return ResponseEntity.ok("Contraseña cambiada exitosamente");
    }
}
