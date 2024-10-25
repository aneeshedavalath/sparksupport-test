package com.sparksupport.product.persistence.dao;

import com.sparksupport.product.persistence.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleDao {

    List<Sale> getAllSales();

    List<Sale> getSaleByProductId(int productId);

    Sale saveSale(Sale sale);

    void deleteSaleById(int id);

    Optional<Sale> getSaleById(int id);

}
