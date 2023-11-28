package com.example.capstoneone.Controller;

import com.example.capstoneone.Model.MerchantStock;
import com.example.capstoneone.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor

@RequestMapping("/api/v1/MerchantStock")
public class MerchantStockController {


    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getMerchantStock());
    }


//    @PostMapping("/add")
//    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock , Errors errors){
//
//        if(errors.hasErrors()){
//            String message = errors.getFieldError().getDefaultMessage();
//            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//        }
//        boolean isAdded = merchantStockService.addMerchantStock(merchantStock);
//        if(isAdded){
//         return ResponseEntity.status(HttpStatus.OK).body("MerchantStock added");
//        }
//        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("worng id");
//    }


    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        String isAdded = merchantStockService.addMerchantStock(merchantStock);
        if (isAdded.equals("merchantStock added")) {
            return ResponseEntity.status(HttpStatus.OK).body("MerchantStock added");
        }
        if (isAdded.equals("merchants id not fuond")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchants id not fuond");


        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("prodact id not fuond");
    }



    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @Valid @RequestBody MerchantStock merchantStock , Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

        }
        boolean isUpdated = merchantStockService.updateMerchantStock(id,merchantStock);
        if (isUpdated){

            return ResponseEntity.status(HttpStatus.OK).body("merchantStock updated");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("worng id");
    }




//    @PutMapping("/update/{id}")
//    public ResponseEntity updateMerchantStock(@PathVariable String id, @Valid @RequestBody MerchantStock merchantStock, Errors errors) {
//
//        if (errors.hasErrors()) {
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//
//        }
//        String isUpdated = merchantStockService.updateMerchantStock(id, merchantStock);
//        if (isUpdated.equals("merchantStock updated")) {
//
//            return ResponseEntity.status(HttpStatus.OK).body("merchantStock updated");
//        }
//        if (isUpdated.equals("merchants id not found")) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchants id not found");
//        }
//        if (isUpdated.equals("products")) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("products id not found");
//        }
//
//        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchantStocks id not found");
//    }
}

