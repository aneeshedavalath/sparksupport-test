package com.sparksupport.product.persistence.dao.impl;

import com.sparksupport.product.persistence.dao.SaleDao;
import com.sparksupport.product.persistence.entity.Sale;
import com.sparksupport.product.persistence.repository.SaleRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SaleDaoImpl implements SaleDao {

    private final SaleRepository saleRepository;

    public SaleDaoImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> getSaleByProductId(int productId) {
        return saleRepository.findByProductId(productId);
    }

    @Override
    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public void deleteSaleById(int id) {
        saleRepository.deleteById(id);
    }

    @Override
    public Optional<Sale> getSaleById(int id) {
        return saleRepository.findById(id);
    }
}
