package com.example.capstoneone.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;


@Data
@AllArgsConstructor

public class User {
    @NotEmpty(message = "id must not be empty")
    private String id;

    @NotEmpty(message = "username must not be empty")
@Size(min = 6 , message ="username must be more than 5 length long" )

    private String username;


    @NotEmpty(message = "password must not be empty")
   // @Size(min = 7 , message ="password must be more than 6 length long" )

    @Pattern( regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$" , message = "password  must have\n" + "characters and digit and minimum 6 length")
    private String password;
    @NotEmpty(message = "email must not be empty")
    @Email(message = "must be valid email")
    private String email;

    @NotEmpty(message = "role must not be empty")
    @Pattern(regexp = "^(Admin|Customer)$" , message = "role must be enter Admin or Customer ")
    private String role;

    @NotNull(message = "balance must not be empty")

    private double balance;



  @NegativeOrZero (message = "The initial value is zero ")
   private int countOfPurchases;
}