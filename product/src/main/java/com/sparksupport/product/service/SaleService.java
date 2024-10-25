package com.sparksupport.product.service;

import com.sparksupport.product.dto.CreateSaleRequestDto;
import com.sparksupport.product.dto.ProductRevenueView;
import com.sparksupport.product.dto.RevenueView;
import com.sparksupport.product.dto.SaleView;
import com.sparksupport.product.dto.UpdateSaleRequestDto;

public interface SaleService {

    RevenueView getTotalRevenue();

    ProductRevenueView getRevenueByProduct(int productId);

    SaleView addSale(CreateSaleRequestDto createSaleRequestDto);

    SaleView updateSale(int id, UpdateSaleRequestDto updateSaleRequestDto);

    void deleteSale(int id);

}
