package com.sparksupport.product.persistence.dao.impl;

import com.sparksupport.product.persistence.dao.ProductDao;
import com.sparksupport.product.persistence.entity.Product;
import com.sparksupport.product.persistence.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository productRepository;

    public ProductDaoImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
