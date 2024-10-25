package com.sparksupport.product.service.impl;

import com.sparksupport.product.dto.CreateProductRequestDto;
import com.sparksupport.product.dto.PageView;
import com.sparksupport.product.dto.ProductView;
import com.sparksupport.product.dto.UpdateProductRequestDto;
import com.sparksupport.product.exception.DataNotFoundException;
import com.sparksupport.product.exception.ValidationException;
import com.sparksupport.product.helper.ProductHelper;
import com.sparksupport.product.persistence.dao.ProductDao;
import com.sparksupport.product.persistence.dao.SaleDao;
import com.sparksupport.product.persistence.entity.Product;
import com.sparksupport.product.service.ProductService;
import com.sparksupport.product.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    private final SaleDao saleDao;

    public ProductServiceImpl(ProductDao productDao, SaleDao saleDao) {
        this.productDao = productDao;
        this.saleDao = saleDao;
    }

    @Override
    public PageView getAllProducts(Pageable pageable) {
        Page<Product> productPage = productDao.getAllProducts(pageable);
        List<ProductView> productViews = ProductHelper.transformProductsToProductViews(productPage.getContent());

        return new PageView(productViews, productPage.getTotalElements(), productPage.getTotalPages(),
                productPage.getNumberOfElements(), productPage.isFirst(), productPage.isLast());
    }

    @Override
    public ProductView getProductById(int id) {
        return productDao.getProductById(id).map(ProductHelper::transformProductEntityToProductView).orElseThrow(
                () -> new DataNotFoundException(
                        Constants.PRODUCT_NOT_FOUND.replace(Constants.BRACES, String.valueOf(id))));
    }

    @Override
    public ProductView addProduct(CreateProductRequestDto createProductRequestDto) {
        return ProductHelper.transformProductEntityToProductView(
                productDao.saveProduct(ProductHelper.transformProductRequestDtoToProduct(createProductRequestDto)));
    }

    @Override
    public ProductView updateProduct(int id, UpdateProductRequestDto updateProductRequestDto) {
        Product product = productDao.getProductById(id).orElseThrow(() -> new DataNotFoundException(
                Constants.PRODUCT_NOT_FOUND.replace(Constants.BRACES, String.valueOf(id))));

        if (updateProductRequestDto.name() != null) {
            product.setName(updateProductRequestDto.name());
        }
        if (updateProductRequestDto.description() != null) {
            product.setDescription(updateProductRequestDto.description());
        }
        if (updateProductRequestDto.price() != null) {
            if (updateProductRequestDto.price() < 0.0) {
                throw new ValidationException(Constants.PRICE_VALIDATION_MESSAGE);
            }
            product.setPrice(updateProductRequestDto.price());
        }
        if (updateProductRequestDto.quantity() != null) {
            if (updateProductRequestDto.quantity() < 0.0) {
                throw new ValidationException(Constants.QUANTITY_VALIDATION_MESSAGE);
            }
            product.setQuantity(updateProductRequestDto.quantity());
        }

        return ProductHelper.transformProductEntityToProductView(productDao.saveProduct(product));
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productDao.getProductById(id).orElseThrow(() -> new DataNotFoundException(
                Constants.PRODUCT_NOT_FOUND.replace(Constants.BRACES, String.valueOf(id))));
        productDao.deleteProductById(product.getId());
    }

    @Override
    public double getTotalRevenue() {
        return saleDao.getAllSales().stream().mapToDouble(sale -> sale.getProduct().getPrice() * sale.getQuantity())
                .sum();
    }

    @Override
    public double getRevenueByProduct(int productId) {
        return saleDao.getSaleByProductId(productId).stream()
                .mapToDouble(sale -> sale.getProduct().getPrice() * sale.getQuantity()).sum();
    }
}
