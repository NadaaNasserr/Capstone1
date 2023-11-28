package com.example.capstoneone.Controller;


import com.example.capstoneone.Model.Product;
import com.example.capstoneone.Service.CategoryService;
import com.example.capstoneone.Service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {


    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping("/get")
    public ResponseEntity getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());

    }



    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product , Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        String add = productService.addProduct(product);
        if (add.equals("product added")) {
            return ResponseEntity.status(HttpStatus.OK).body("Product added");
        }
        if (add.equals("Category id")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category id not found");
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category name not found");

    }


//    @PostMapping("/add")
//    public ResponseEntity addProduct(@Valid @RequestBody Product product , Errors errors){
//        if(errors.hasErrors()){
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//
//        }
//        boolean isAdded = productService.addProduct(product);
//        if (isAdded){
//
//            return ResponseEntity.status(HttpStatus.OK).body("Product addad");
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category not found");
//
//
//
//    }
//    @PutMapping("/update/{id}")
//    public ResponseEntity updateProduct(@PathVariable String id, @Valid @RequestBody Product product , Errors errors){
//        if(errors.hasErrors()){
//            String message = errors.getFieldError().getDefaultMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
//
//        }
//        boolean isUpdated = productService.updateProduct(id,product);
//        if (isUpdated){
//
//            return ResponseEntity.status(HttpStatus.OK).body("Product updated");
//        }
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("worng id");
//    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @Valid @RequestBody Product product , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);

        }
        String isUpdated = productService.updateProduct(id,product);
        if (isUpdated.equals("Product updated")){

            return ResponseEntity.status(HttpStatus.OK).body("Product updated");
        }
        if(isUpdated.equals("Category id not found")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category id not found");

        }
        if(isUpdated.equals("Category name not found")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category name not found");

        }
        else  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product id not found");

    }


    @DeleteMapping("/delete/{id}")
public ResponseEntity deleteProduct(@PathVariable String id ){
boolean isDeleted = productService.deleteProduct(id);

if(isDeleted){
    return ResponseEntity.status(HttpStatus.OK).body("Product Deleted");
}
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("worng id");
}



@PostMapping("/search/{category}")
private ResponseEntity SearchByCategory(@PathVariable String category){
        if(productService.searchByCategory(category).isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("not found this category");

        }

    return ResponseEntity.status(HttpStatus.OK).body(productService.searchByCategory(category));

}
}
