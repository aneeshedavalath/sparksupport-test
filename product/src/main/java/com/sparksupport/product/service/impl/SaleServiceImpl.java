package com.sparksupport.product.service.impl;

import com.sparksupport.product.dto.CreateSaleRequestDto;
import com.sparksupport.product.dto.ProductRevenueView;
import com.sparksupport.product.dto.RevenueView;
import com.sparksupport.product.dto.SaleView;
import com.sparksupport.product.dto.UpdateSaleRequestDto;
import com.sparksupport.product.exception.DataNotFoundException;
import com.sparksupport.product.exception.ValidationException;
import com.sparksupport.product.helper.SaleHelper;
import com.sparksupport.product.persistence.dao.ProductDao;
import com.sparksupport.product.persistence.dao.SaleDao;
import com.sparksupport.product.persistence.entity.Product;
import com.sparksupport.product.persistence.entity.Sale;
import com.sparksupport.product.service.SaleService;
import com.sparksupport.product.utils.Constants;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleDao saleDao;

    private final ProductDao productDao;

    public SaleServiceImpl(SaleDao saleDao, ProductDao productDao) {
        this.saleDao = saleDao;
        this.productDao = productDao;
    }

    @Override
    public RevenueView getTotalRevenue() {
        List<Sale> sales = saleDao.getAllSales();
        if (CollectionUtils.isEmpty(sales)) {
            return new RevenueView(0, Constants.INDIAN_CURRENCY + "0");
        }
        double revenue = sales.stream().mapToDouble(sale -> sale.getProduct().getPrice() * sale.getQuantity()).sum();
        return new RevenueView(sales.size(), Constants.INDIAN_CURRENCY + revenue);
    }

    @Override
    public ProductRevenueView getRevenueByProduct(int productId) {
        Product product = productDao.getProductById(productId).orElseThrow(() -> new DataNotFoundException(
                Constants.PRODUCT_NOT_FOUND.replace(Constants.BRACES, String.valueOf(productId))));
        List<Sale> sales = saleDao.getSaleByProductId(productId);
        if (CollectionUtils.isEmpty(sales)) {
            throw new DataNotFoundException(
                    Constants.SALE_FOR_PRODUCT_ID_NOT_FOUND.replace(Constants.BRACES, String.valueOf(productId)));
        }
        double revenue = sales.stream().mapToDouble(sale -> sale.getProduct().getPrice() * sale.getQuantity()).sum();
        return new ProductRevenueView(product.getId(), product.getName(), Constants.INDIAN_CURRENCY + revenue);
    }

    @Override
    public SaleView addSale(CreateSaleRequestDto createSaleRequestDto) {
        Product product = updateProductQuantityOnSales(createSaleRequestDto.productId(),
                createSaleRequestDto.quantity());
        return SaleHelper.transformSaleEntityToSaleView(
                saleDao.saveSale(SaleHelper.transformSaleRequestDtoToSale(createSaleRequestDto, product)));
    }

    @Override
    public SaleView updateSale(int id, UpdateSaleRequestDto updateSaleRequestDto) {
        Sale sale = saleDao.getSaleById(id).orElseThrow(() -> new DataNotFoundException(
                Constants.SALE_NOT_FOUND.replace(Constants.BRACES, String.valueOf(id))));
        if (updateSaleRequestDto.productId() != null) {
            if (updateSaleRequestDto.productId() <= 0) {
                throw new ValidationException(Constants.PRODUCT_ID_VALIDATION_MESSAGE);
            }
            Product product = productDao.getProductById(updateSaleRequestDto.productId()).orElseThrow(
                    () -> new DataNotFoundException(Constants.PRODUCT_NOT_FOUND.replace(Constants.BRACES,
                            String.valueOf(updateSaleRequestDto.productId()))));
            revertProductQuantity(sale.getProduct().getId(), sale.getQuantity());
            sale.setProduct(product);
        }
        if (updateSaleRequestDto.quantity() != null) {
            if (updateSaleRequestDto.quantity() <= 0.0) {
                throw new ValidationException(Constants.QUANTITY_VALIDATION_MESSAGE);
            }
            updateProductQuantityOnSales(sale.getProduct().getId(), updateSaleRequestDto.quantity());
            sale.setQuantity(updateSaleRequestDto.quantity());
        }
        return SaleHelper.transformSaleEntityToSaleView(saleDao.saveSale(sale));
    }

    @Override
    public void deleteSale(int id) {
        Sale sale = saleDao.getSaleById(id).orElseThrow(() -> new DataNotFoundException(
                Constants.SALE_NOT_FOUND.replace(Constants.BRACES, String.valueOf(id))));
        saleDao.deleteSaleById(sale.getId());
    }

    private Product updateProductQuantityOnSales(int productId, double quantity) {
        Product product = productDao.getProductById(productId).orElseThrow(() -> new DataNotFoundException(
                Constants.PRODUCT_NOT_FOUND.replace(Constants.BRACES, String.valueOf(productId))));
        product.setQuantity(product.getQuantity() - quantity);
        if (product.getQuantity() <= 0) {
            throw new ValidationException(Constants.INSUFFICIENT_QUANTITY_MESSAGE);
        }
        productDao.saveProduct(product);

        return product;
    }

    private void revertProductQuantity(int productId, double quantity) {
        Product product = productDao.getProductById(productId).orElseThrow(() -> new DataNotFoundException(
                Constants.PRODUCT_NOT_FOUND.replace(Constants.BRACES, String.valueOf(productId))));
        product.setQuantity(product.getQuantity() + quantity);
        productDao.saveProduct(product);
    }

}
