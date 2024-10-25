package com.sparksupport.product.dto;

import com.sparksupport.product.exception.ValidationException;
import com.sparksupport.product.utils.Constants;

public record CreateSaleRequestDto(int productId, double quantity) {

    public CreateSaleRequestDto {
        if (productId < 1) {
            throw new ValidationException(Constants.PRODUCT_ID_VALIDATION_MESSAGE);
        }
        if (quantity <= 0.0) {
            throw new ValidationException(Constants.QUANTITY_VALIDATION_MESSAGE);
        }
    }

}
