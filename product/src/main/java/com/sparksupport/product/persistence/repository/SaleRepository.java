package com.sparksupport.product.persistence.repository;

import com.sparksupport.product.persistence.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    List<Sale> findByProductId(int productId);

}
