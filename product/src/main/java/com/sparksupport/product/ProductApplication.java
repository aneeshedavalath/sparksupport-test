package com.sparksupport.product;

import com.sparksupport.product.dto.CreateProductRequestDto;
import com.sparksupport.product.dto.CreateSaleRequestDto;
import com.sparksupport.product.service.ProductService;
import com.sparksupport.product.service.SaleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ProductApplication implements CommandLineRunner {

    private final ProductService productService;

    private final SaleService saleService;

    public ProductApplication(ProductService productService, SaleService saleService) {
        this.productService = productService;
        this.saleService = saleService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Products
        productService.addProduct(new CreateProductRequestDto("Apple iPhone 16 Pro",
                "iPhone 16 Pro has a strong and light titanium design with a larger display.", 119900, 40));
        productService.addProduct(
                new CreateProductRequestDto("Apple iPhone 16", "Built for Apple Intelligence", 79900.00, 55));
        productService.addProduct(new CreateProductRequestDto("Samsung Galaxy S24 Ultra 5G",
                "Meet Galaxy S24 Ultra, the ultimate form of Galaxy Ultra with a new titanium exterior.", 131999.00,
                60));
        productService.addProduct(new CreateProductRequestDto("Samsung Galaxy S24 5G",
                "Easy to grip. Satisfying to hold. With their unified design and satin finish, Galaxy S24 feels as smooth as it looks.",
                62999.00, 70));

        // Sales
        saleService.addSale(new CreateSaleRequestDto(1, 2.0));
        saleService.addSale(new CreateSaleRequestDto(2, 5.0));
        saleService.addSale(new CreateSaleRequestDto(3, 3.0));
        saleService.addSale(new CreateSaleRequestDto(4, 1.0));
        saleService.addSale(new CreateSaleRequestDto(2, 10.0));
        saleService.addSale(new CreateSaleRequestDto(1, 1.0));
        saleService.addSale(new CreateSaleRequestDto(3, 4.0));
        saleService.addSale(new CreateSaleRequestDto(4, 3.0));
        saleService.addSale(new CreateSaleRequestDto(4, 2.0));
        saleService.addSale(new CreateSaleRequestDto(2, 7.0));
    }
}
