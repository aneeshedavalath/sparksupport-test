package com.sparksupport.product.dto;

import java.util.Collection;

public record PageView(Collection<?> content, long totalElements, int totalPages, int numberOfElements,
                       boolean startOfResult, boolean endOfResult) {

}
