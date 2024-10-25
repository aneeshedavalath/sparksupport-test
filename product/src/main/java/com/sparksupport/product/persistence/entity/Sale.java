package com.sparksupport.product.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "sales")
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double quantity;

    private LocalDate saleDate;

    public Sale(Product product, double quantity, LocalDate saleDate) {
        this.product = product;
        this.quantity = quantity;
        this.saleDate = saleDate;
    }

}
