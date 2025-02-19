package com.utez.integradora.service;

import com.utez.integradora.entity.UserEntity;
import com.utez.integradora.entity.dto.ReqRes;
import com.utez.integradora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UsersManagementService {

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;


    private final PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes reqRes) {
        ReqRes res = new ReqRes();
        try {
            UserEntity ourUsers = UserEntity.builder()
                    .email(reqRes.getEmail())
                    .role(reqRes.getRole())
                    .name(reqRes.getName())
                    .password(passwordEncoder.encode(reqRes.getPassword()))
                    .sexo(reqRes.getSexo())
                    .phone(reqRes.getPhone())
                    .direccion(reqRes.getDireccion())
                    .fechaNacimiento(reqRes.getFecha())
                    .lastName(reqRes.getLastName())
                    .build();
            UserEntity user = userRepository.save(ourUsers);
            if(user.getId()>0){
                res.setUser(user);
                res.setMessage("User successfully registered!");
                res.setStatusCode(200);
            }

        }catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public ReqRes login(ReqRes reqRes) {
        SecurityContextHolder.clearContext();
        ReqRes res = new ReqRes();
        System.out.println(reqRes.getEmail());
        System.out.println(reqRes.getPassword());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken( reqRes.getEmail(), reqRes.getPassword()));
            var user = userRepository.findByEmail(reqRes.getEmail()).orElseThrow();

            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            res.setStatusCode(200);
            res.setMessage("User successfully logged in!");
            res.setRole(user.getRole());
            res.setToken(jwt);
            res.setRefreshToken(refreshToken);
        }catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public ReqRes refreshToken(ReqRes reqRes) {
        ReqRes res = new ReqRes();
        try {
            String ourEmail = jwtUtils.extractUsername(reqRes.getToken());
            System.out.println(ourEmail);
            UserEntity ourUser = userRepository.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(reqRes.getToken(), ourUser)) {
                var jwt = jwtUtils.generateToken(ourUser);
                res.setStatusCode(200);
                res.setMessage("User successfully logged in!");
                res.setToken(jwt);
                res.setRefreshToken(reqRes.getToken());

            }
        }catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public ReqRes getAllUsers() {
        ReqRes res = new ReqRes();
        try {
            List<UserEntity> users = userRepository.findAll();
            if (!users.isEmpty()) {
                res.setStatusCode(200);
                res.setMessage("Successfully retrieved all users!");
                res.setUserEntityList(users);
            } else {
                res.setStatusCode(404);
                res.setMessage("User not found!");
            }
            return res;
        }catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public ReqRes getUserById(Integer id) {
        ReqRes res = new ReqRes();
        try {
            UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
            res.setStatusCode(200);
            res.setMessage("User successfully retrieved!");
            res.setUser(user);
        }catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }
    public ReqRes deleteUser(Integer id ) {
        ReqRes res = new ReqRes();
        try {
            Optional<UserEntity> user = userRepository.findById(id);
            if (user.isPresent()) {
                userRepository.deleteById(id);
                res.setStatusCode(200);
                res.setMessage("User successfully deleted!");
            } else {
                res.setStatusCode(404);
                res.setMessage("User not found!");
            }

        }catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public ReqRes updateUser(Integer id , ReqRes updatedUser) {
        updatedUser.setPassword("");
        ReqRes res = new ReqRes();
        try {
            Optional<UserEntity> user = userRepository.findById(id);

            if (user.isPresent()) {
                UserEntity ourUser = user.get(); //Asociar a un nuevo objeto
                System.out.println(ourUser.getPassword());
                ourUser.setRole(updatedUser.getRole());
                ourUser.setName(updatedUser.getName());
                ourUser.setDireccion(updatedUser.getDireccion());
                ourUser.setSexo(updatedUser.getSexo());
                ourUser.setPhone(updatedUser.getPhone());
                ourUser.setLastName(updatedUser.getLastName());
                ourUser.setFechaNacimiento(updatedUser.getFechaNacimiento());
                if(updatedUser.getPassword()!=null && !updatedUser.getPassword().isEmpty()){
                    ourUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

                }
                UserEntity ourUsers = userRepository.save(ourUser);
                System.out.println(ourUsers.getPassword());
                res.setStatusCode(200);
                res.setUser(ourUsers);
                res.setMessage("User successfully updated!");
            } else {
                res.setStatusCode(404);
                res.setMessage("User not found!");
            }

        }catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    public ReqRes getMyInfo(String email) {
        ReqRes res = new ReqRes();
        try{
            Optional<UserEntity> user = userRepository.findByEmail(email);
            if (user.isPresent()) {
                UserEntity ourUser = user.get();
                res.setStatusCode(200);
                res.setMessage("User successfully retrieved!");
                res.setUser(ourUser);
            }else {
                res.setStatusCode(404);
                res.setMessage("User not found!");
            }
        }catch (Exception e) {
            res.setStatusCode(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }
}
