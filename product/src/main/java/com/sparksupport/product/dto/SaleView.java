package com.sparksupport.product.dto;

import java.time.LocalDate;

public record SaleView(int id, int productId, double quantity, LocalDate saleDate) {

}
