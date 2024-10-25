package com.sparksupport.product.dto;

import com.sparksupport.product.exception.ValidationException;
import com.sparksupport.product.utils.Constants;
import org.springframework.util.StringUtils;

public record CreateProductRequestDto(String name, String description, double price, double quantity) {

    public CreateProductRequestDto {
        if (!StringUtils.hasText(name)) {
            throw new ValidationException(Constants.NAME_VALIDATION_MESSAGE);
        }

        if (price < 0.0 ) {
            throw new ValidationException(Constants.PRICE_VALIDATION_MESSAGE);
        }

        if (quantity < 0.0) {
            throw new ValidationException(Constants.QUANTITY_VALIDATION_MESSAGE);
        }
    }
}
