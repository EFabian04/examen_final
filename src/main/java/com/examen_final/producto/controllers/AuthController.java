package com.examen_final.producto.controllers;

import com.examen_final.producto.models.User;
import com.examen_final.producto.service.UserService;
import com.examen_final.producto.utils.ApiResponse;
import com.examen_final.producto.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin()
public class AuthController {
    @Autowired
    private UserService userService;
    private ApiResponse apiResponse;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        try {
            apiResponse = new ApiResponse(Constants.USER_LOGIN, userService.login(user));
            return new ResponseEntity(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            apiResponse = new ApiResponse(e.getMessage(), "");
            return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
}