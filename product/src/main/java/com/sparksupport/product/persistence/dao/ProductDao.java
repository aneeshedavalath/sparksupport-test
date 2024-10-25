package com.sparksupport.product.persistence.dao;

import com.sparksupport.product.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductDao {

    Optional<Product> getProductById(int id);

    Product saveProduct(Product product);

    void deleteProductById(int id);

    Page<Product> getAllProducts(Pageable pageable);

}
