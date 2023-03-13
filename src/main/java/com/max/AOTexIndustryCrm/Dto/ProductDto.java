package com.max.AOTexIndustryCrm.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String modelName;
    private String color;
    private String sortType;
    private String description;
    private BigDecimal price;
}
