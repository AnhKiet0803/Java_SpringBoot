package com.example.T2507e.controller;

import com.example.T2507e.common.ResponseHandler;
import com.example.T2507e.dto.common.ResponseDTO;
import com.example.T2507e.dto.req.LoginUser;
import com.example.T2507e.dto.req.RegisterUser;
import com.example.T2507e.dto.res.LoginRes;
import com.example.T2507e.enums.StatusCode;
import com.example.T2507e.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {
    public final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<Boolean>> register(@RequestBody RegisterUser req){
        try {
            return ResponseHandler.success(authService.register(req),"Register successfully");
        }catch (Exception e){
            return ResponseHandler.error(StatusCode.BAD_REQUEST,"Register failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<LoginRes>> login(@RequestBody LoginUser req){
        try {
            return ResponseHandler.success(authService.authenticate(req),"Login successfully");
        }catch (Exception e){
            return ResponseHandler.error(StatusCode.UNAUTHORIZED,e.getMessage());
        }
    }
}
