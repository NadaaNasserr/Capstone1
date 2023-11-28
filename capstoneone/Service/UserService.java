package com.example.capstoneone.Service;

import com.example.capstoneone.Model.MerchantStock;
import com.example.capstoneone.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import com.example.capstoneone.Model.Product;
import com.example.capstoneone.Service.MerchantStockService;

import java.util.ArrayList;
@Service
@Data
@AllArgsConstructor
public class UserService {

    private final MerchantStockService merchantStockService;
    private final ProductService productService;




    ArrayList<User> users = new ArrayList<>();


    public ArrayList<User> getUsers() {

        return users;
    }

    public void addUser(User user) {

        users.add(user);
    }

    public boolean updateUser(String id, User user) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }

        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }


    public String addStocks(String productId, String merchantId, int amount) {
        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
            if (merchantStockService.merchantStocks.get(i).getProductId().equals(productId)) {
                if (merchantStockService.merchantStocks.get(i).getMerchantId().equals(merchantId)) {
                    merchantStockService.merchantStocks.get(i).setStock(merchantStockService.merchantStocks.get(i).getStock() + amount);

                    return "Stoke added";
                }
                return "merchant Id not found";
            }

        }
        return "Product Id not found";
    }

// public boolean addStocks(String productId, String merchantId, int amount) {
//        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
//            if (merchantStockService.merchantStocks.get(i).getProductId().equals(productId) && merchantStockService.merchantStocks.get(i).getMerchantId().equals(merchantId)) {
//                    merchantStockService.merchantStocks.get(i).setStock(merchantStockService.merchantStocks.get(i).getStock() + amount);
//
//                    return true;
//                }
//            }
//        return false;
//        }


    public boolean buyProduct(String userId, String productId, String merchantId, int quantity) {

        for (int j = 0; j < users.size(); j++) {
            if (users.get(j).getId().equals(userId)) {
                for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
                    if (merchantStockService.merchantStocks.get(i).getProductId().equals(productId) && merchantStockService.merchantStocks.get(i).getMerchantId().equals(merchantId)) {
                        if (merchantStockService.merchantStocks.get(i).getStock() > 0 && merchantStockService.merchantStocks.get(i).getStock() >= quantity) {
                            merchantStockService.merchantStocks.get(i).setStock((merchantStockService.merchantStocks.get(i).getStock() - quantity));
                            if ((productService.products.get(i).getPrice() * quantity) < users.get(j).getBalance()) {
                                if (users.get(i).getCountOfPurchases() < 5) {
                                    users.get(j).setBalance(users.get(j).getBalance() - (productService.products.get(i).getPrice() * quantity));
                                } else {
                                    users.get(j).setBalance(users.get(j).getBalance() - ((productService.products.get(i).getPrice() * quantity) -
                                            ((productService.products.get(i).getPrice() * quantity) * .10)));
                                }
                                users.get(i).setCountOfPurchases(users.get(i).getCountOfPurchases() + 1);
                                return true;

                            }

                        }
                    }
                }
            }
        }
        return false;
    }
}






