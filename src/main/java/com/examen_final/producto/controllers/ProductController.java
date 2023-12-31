package com.examen_final.producto.controllers;

import com.examen_final.producto.utils.ApiResponse;
import com.examen_final.producto.utils.Constants;
import com.examen_final.producto.utils.JWTUtil;
import com.examen_final.producto.models.Product;
import com.examen_final.producto.service.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;
    private ApiResponse apiResponse;
    private JWTUtil jwtUtil;

    @GetMapping(value = "/product/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {

        try {
            apiResponse = new ApiResponse(Constants.REGISTER_FOUND, productServiceImp.getProductById(id));
            System.out.println(apiResponse);
            return new ResponseEntity(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List> getAllProduct() {

        try {
            apiResponse = new ApiResponse(Constants.REGISTERS_FOUND, productServiceImp.getAllProduct());
            return new ResponseEntity(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTERS_NOT_FOUND, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/product")
    public ResponseEntity createProduct(@RequestBody Product product,@RequestHeader(value = "Authorization") String token) {

        try {
            if(productServiceImp.validateId(product.getId())) {
                apiResponse = new ApiResponse(Constants.REGISTER_NOT_CREATED, "");
                return new ResponseEntity(apiResponse, HttpStatus.CONFLICT);
            }
            if(!validateToken(token)) {
                apiResponse = new ApiResponse(Constants.REGISTER_NOT_CREATED, "");
                return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
            }

            apiResponse = new ApiResponse(Constants.REGISTER_CREATED, productServiceImp.createProduct(product));
            return new ResponseEntity(apiResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_CREATED, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/product/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product,@RequestHeader(value = "Authorization") String token) {

        try {
            apiResponse = new ApiResponse(Constants.REGISTER_UPDATED, productServiceImp.updateProduct(id, product));
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_UPDATED, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "product/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id, Product product) {

        Boolean productDB = productServiceImp.deleteProduct(id, product);
        try {
            if (productDB == null) {
                apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, "");
                return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
            } else {
                apiResponse = new ApiResponse(Constants.REGISTER_UPDATED, productDB);
                return new ResponseEntity(apiResponse, HttpStatus.OK);
            }
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_DELETE, e.getMessage());
            return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    private Boolean validateToken(String token){
        try{
            if(jwtUtil.getKey(token) != null){
                return true;
            }
            return  false;
        }catch (Exception e){
            return  false;
        }
    }

}
