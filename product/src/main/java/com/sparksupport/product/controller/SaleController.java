package com.sparksupport.product.controller;

import com.sparksupport.product.dto.CreateSaleRequestDto;
import com.sparksupport.product.dto.ProductRevenueView;
import com.sparksupport.product.dto.RevenueView;
import com.sparksupport.product.dto.SaleView;
import com.sparksupport.product.dto.UpdateSaleRequestDto;
import com.sparksupport.product.service.SaleService;
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
@RequestMapping("/v1/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaleView> addSale(@RequestBody CreateSaleRequestDto createSaleRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.addSale(createSaleRequestDto));
    }

    @PatchMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaleView> updateSale(@PathVariable int id,
            @RequestBody UpdateSaleRequestDto updateSaleRequestDto) {
        return ResponseEntity.ok(saleService.updateSale(id, updateSaleRequestDto));
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSale(@PathVariable int id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/revenue")
    public ResponseEntity<RevenueView> getTotalRevenue() {
        return ResponseEntity.ok(saleService.getTotalRevenue());
    }

    @GetMapping("/revenue/product/{productId}")
    public ResponseEntity<ProductRevenueView> getRevenueByProduct(@PathVariable int productId) {
        return ResponseEntity.ok(saleService.getRevenueByProduct(productId));
    }

}
