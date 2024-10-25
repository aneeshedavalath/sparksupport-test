package com.sparksupport.product.controller;

import com.sparksupport.product.dto.CreateProductRequestDto;
import com.sparksupport.product.dto.PageView;
import com.sparksupport.product.dto.ProductView;
import com.sparksupport.product.dto.UpdateProductRequestDto;
import com.sparksupport.product.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ProductView> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<PageView> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductView> addProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(createProductRequestDto));
    }

    @PatchMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductView> updateProduct(@PathVariable int id,
            @RequestBody UpdateProductRequestDto updateProductRequestDto) {
        return ResponseEntity.ok(productService.updateProduct(id, updateProductRequestDto));
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
