package com.example.capstoneone.Service;


import com.example.capstoneone.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@Data
public class ProductService {

    private final CategoryService categoryService;

    ArrayList<Product> products = new ArrayList<>();


    public ArrayList<Product> getProducts() {
        return products;
    }


    public String addProduct(Product product) {
        for (int i = 0; i < categoryService.categories.size(); i++) {
            if (categoryService.categories.get(i).getId().equals(product.getCategoryID())) {

                if (categoryService.categories.get(i).getName().equals(product.getCategoryName())) {
                    products.add(product);
                    return "product added";
                }
                return "Category name";
            }
        }
        return "Category id";
    }


//    public boolean addProduct(Product product) {
//        for (int i = 0; i < categoryService.categories.size(); i++) {
//            if (categoryService.categories.get(i).getId().equals(product.getCategoryID())) {
//                products.add(product);
//                return true;
//
//            }
//
//        }
//        return false;
//    }


    public String updateProduct(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                for (int j = 0; j < categoryService.categories.size(); j++) {
                    if (categoryService.categories.get(i).getId().equals(product.getCategoryID())) {
                        if (categoryService.categories.get(i).getName().equals(product.getCategoryName())) {
                            products.set(i, product);
                            return "Product updated";
                        }
                        return "Category name not found";
                    }
                }
                return "Category id not found";
            }
        }
        return "product id not found";
    }



//    public boolean updateProduct(String id, Product product) {
//        for (int i = 0; i < products.size(); i++) {
//            if (products.get(i).getId().equals(id)) {
//                for (int j = 0; j < categoryService.categories.size(); j++) {
//                    if (categoryService.categories.get(i).getId().equals(product.getCategoryID())) {
//                       products.set(i, product);
//
//                        return true;
//
//                    }
//                }
//            }
//
//        }
//        return false;
//    }




    public boolean deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                return true;
            }

        }
        return false;
    }





    public ArrayList<Product> searchByCategory(String category) {
        ArrayList<Product> temp = new ArrayList<>();
        for (int i = 0; i < categoryService.categories.size(); i++) {
            if (categoryService.categories.get(i).getName().equals(category)) {
                for (int j = 0; j < products.size(); j++) {
                    if (products.get(j).getCategoryName().equals(category)) {
                        temp.add(products.get(j));
                    }
                }
            }
        }
        return temp;
    }
}

