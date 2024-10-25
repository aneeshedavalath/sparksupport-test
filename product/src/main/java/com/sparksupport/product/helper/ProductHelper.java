package com.sparksupport.product.helper;

import com.sparksupport.product.dto.CreateProductRequestDto;
import com.sparksupport.product.dto.ProductView;
import com.sparksupport.product.persistence.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductHelper {

    private ProductHelper() {
    }

    public static ProductView transformProductEntityToProductView(Product product) {
        return new ProductView(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getQuantity());
    }

    public static Product transformProductRequestDtoToProduct(CreateProductRequestDto createProductRequestDto) {
        return new Product(createProductRequestDto.name(), createProductRequestDto.description(), createProductRequestDto.price(),
                createProductRequestDto.quantity());
    }

    public static List<ProductView> transformProductsToProductViews(List<Product> products) {
        List<ProductView> productViews = new ArrayList<>();
        for (Product product : products) {
            productViews.add(transformProductEntityToProductView(product));
        }
        return productViews;
    }

}
