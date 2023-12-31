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
@RequestMapping("/user")
@CrossOrigin()
public class UserController {
    @Autowired
    private UserService userServiceImp;
    private ApiResponse apiResponse;

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        try {
            apiResponse = new ApiResponse(Constants.REGISTER_FOUND, userServiceImp.getUserById(id));
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(value = "")
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            apiResponse = new ApiResponse(Constants.REGISTER_CREATED, userServiceImp.createUser(user));
            return new ResponseEntity(apiResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_CREATED, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity getAll() {
        try {
            apiResponse = new ApiResponse(Constants.REGISTERS_FOUND, userServiceImp.allUsers());
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTERS_NOT_FOUND, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUser(@PathVariable(name = "id") Long id, User user) {
        try {
            apiResponse = new ApiResponse(Constants.REGISTER_UPDATED, userServiceImp.updateUser(id, user));
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_UPDATED, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
