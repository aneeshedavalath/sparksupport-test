package com.sparksupport.product.helper;

import com.sparksupport.product.dto.CreateSaleRequestDto;
import com.sparksupport.product.dto.SaleView;
import com.sparksupport.product.persistence.entity.Product;
import com.sparksupport.product.persistence.entity.Sale;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SaleHelper {

    private SaleHelper() {
    }

    public static SaleView transformSaleEntityToSaleView(Sale sale) {
        return new SaleView(sale.getId(), sale.getProduct().getId(), sale.getQuantity(), sale.getSaleDate());
    }

    public static Sale transformSaleRequestDtoToSale(CreateSaleRequestDto createSaleRequestDto, Product product) {
        return new Sale(product, createSaleRequestDto.quantity(), LocalDate.now());
    }

}
