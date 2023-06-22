package com.examen_final.producto.repository;

import com.examen_final.producto.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
