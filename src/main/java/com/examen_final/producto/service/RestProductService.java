package com.examen_final.producto.service;

import com.examen_final.producto.models.ApiProduct;
import com.examen_final.producto.models.Product;
import com.examen_final.producto.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestProductService {
    private final RestTemplate restTemplate;
    private final ProductRepository productRepository;

    public ApiProduct getById(Long id) throws JsonProcessingException {
        String url = "https://fakestoreapi.com/products/" + id;
        ApiProduct product = restTemplate.getForObject(url, ApiProduct.class);
        System.out.println(product);
        return product;
    }

    public List<ApiProduct> getAllProducts() throws JsonProcessingException {
        String url = "https://fakestoreapi.com/products";
        ApiProduct[] product = restTemplate.getForObject(url, ApiProduct[].class);
        List<ApiProduct> listProducts = Arrays.asList(product);
        return listProducts;
    }

    public Product saveProduct(Long id) throws JsonProcessingException {
        String url = "https://fakestoreapi.com/products/" + id;
        Product product = restTemplate.getForObject(url, Product.class);
        if (product != null) {
            return productRepository.save(product);
        }
        return null;
    }
}
