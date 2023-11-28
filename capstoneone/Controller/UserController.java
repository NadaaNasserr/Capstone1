package com.example.capstoneone.Controller;

import com.example.capstoneone.Model.User;
import com.example.capstoneone.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor

public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUser() {

        return ResponseEntity.status(200).body(userService.getUsers());
    }


    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("user added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        boolean isUpdated = userService.updateUser(id, user);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("user updated");

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {


        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("user Deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong id");
    }



//    @PutMapping("/addStocks/{productId}/{merchantId}/{amount}")
//    public ResponseEntity addStocks(@PathVariable String productId,@PathVariable String merchantId, @PathVariable int amount) {
//
//        boolean isAddedStocks = userService.addStocks(productId, merchantId, amount);
//        if (isAddedStocks) {
//            return ResponseEntity.status(HttpStatus.OK).body("stock added successfully");
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong ids");
//    }

        @PutMapping("/addStocks/{productId}/{merchantId}/{amount}")
    public ResponseEntity addStocks(@PathVariable String productId,@PathVariable String merchantId, @PathVariable int amount) {

        String addedStocks = userService.addStocks(productId, merchantId, amount);
        if (addedStocks.equals("Stoke added")) {
            return ResponseEntity.status(HttpStatus.OK).body("stock added successfully");
        }
        else if (addedStocks.equals("merchant Id not found")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong merchant Id");
        }
else
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong product Id");
    }




    @PutMapping("/buy/{userId}/{productId}/{merchantId}/{quantity}")
    public ResponseEntity buyProduct(@PathVariable String userId, @PathVariable String productId, @PathVariable String merchantId ,@PathVariable  int quantity) {

        boolean buyProduct = userService.buyProduct(userId,productId,merchantId,quantity);
        if(buyProduct){
            return ResponseEntity.status(HttpStatus.OK).body("purchase successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }



