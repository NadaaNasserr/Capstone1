package com.example.capstoneone.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Data
@AllArgsConstructor
public class Product {

@NotEmpty(message = "id must not be empty")
    private String id;


    @NotEmpty(message = "name must not be empty")
    @Size(min = 4 , message = "name must more than 3 length long")
    private String name;


    @NotNull(message = "price must not be empty")
    @Positive (message = " price must be positive number")
    private double price;


    @NotEmpty(message = "category must not be empty")
    private String categoryID;

    @NotEmpty(message = "category name must not be empty")
    private String categoryName;

}
