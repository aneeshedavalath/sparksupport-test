package com.sparksupport.product.utils;

public class Constants {

    private Constants() {
    }

    public static final String PRODUCT_NOT_FOUND = "Product with id {} is not found.";

    public static final String BRACES = "{}";

    public static final String CONTENT_TYPE_JSON = "application/json";

    public static final String ERROR_UNAUTHORIZED_MESSAGE = "{\"title\": \"UNAUTHORIZED\", \"status\": 401, \"message\": \"Username or password is incorrect\"}";

    public static final String ERROR_FORBIDDEN_MESSAGE = "{\"title\": \"FORBIDDEN\", \"status\": 403, \"message\": \"Access denied\"}";

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String PRICE_VALIDATION_MESSAGE = "Invalid price: The value must be zero or higher.";

    public static final String QUANTITY_VALIDATION_MESSAGE = "Invalid quantity: The value must be zero or higher.";

    public static final String NAME_VALIDATION_MESSAGE = "Name is required and cannot be blank.";

    public static final String SALE_NOT_FOUND = "Sale with id {} is not found.";

    public static final String SALE_FOR_PRODUCT_ID_NOT_FOUND = "Sale for product with id {} is not found.";

    public static final String PRODUCT_ID_VALIDATION_MESSAGE = "Invalid productId: The value must be one or higher.";

    public static final String INSUFFICIENT_QUANTITY_MESSAGE = "Insufficient quantity available.";

    public static final String INDIAN_CURRENCY = "INR ";

}
