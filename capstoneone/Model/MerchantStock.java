package com.example.capstoneone.Model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "id must not be empty")
    private String id;

    @NotEmpty(message = "productId must not be empty")
    private String productId;

    @NotEmpty(message = "merchantId must not be empty")
    private String merchantId;


    @Min(value = 10 , message = "stock must be more than 10 at start")
    @NotNull(message = "stock must not be empty")
    private int stock;

}
