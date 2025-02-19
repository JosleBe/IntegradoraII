package com.utez.integradora.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.utez.integradora.entity.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Setter@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;           // Token JWT
    private String refreshToken;    // Token de actualización
    private String name;         // Nombre del usuario
    private String lastName;
    private String role;            // Rol del usuario
    private String email;           // Email para autenticación por correo
    private String phone;           // Teléfono para autenticación por número
    private String password;        // Contraseña para email/teléfono
    private String facebookToken;   // Token de acceso para autenticación con Facebook
    private String direccion;
    private String fecha;
    private String sexo;
    private UserEntity user;
    private String fechaNacimiento;
    private List<UserEntity> userEntityList;


    public ReqRes(int i, String accessTokenIsRequired) {

    }
    public ReqRes() {
    }

    public ReqRes(String success, CampaignDto campaignDto) {
    }
}

