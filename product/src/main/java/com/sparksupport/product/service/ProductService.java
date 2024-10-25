package com.sparksupport.product.service;

import com.sparksupport.product.dto.CreateProductRequestDto;
import com.sparksupport.product.dto.PageView;
import com.sparksupport.product.dto.ProductView;
import com.sparksupport.product.dto.UpdateProductRequestDto;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    PageView getAllProducts(Pageable pageable);

    ProductView getProductById(int id);

    ProductView addProduct(CreateProductRequestDto createProductRequestDto);

    ProductView updateProduct(int id, UpdateProductRequestDto updateProductRequestDto);

    void deleteProduct(int id);

    double getTotalRevenue();

    double getRevenueByProduct(int productId);
}
