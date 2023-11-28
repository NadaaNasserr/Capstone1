package com.example.capstoneone.Service;


import com.example.capstoneone.Model.MerchantStock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MerchantStockService {


    private final ProductService productService;
    private final MerchantService merchantService;

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();


    public ArrayList<MerchantStock> getMerchantStock() {


        return merchantStocks;
    }

//    public boolean addMerchantStock(MerchantStock merchantStock){
//
//        for (int i = 0; i <productService.products.size() ; i++) {
//            if(productService.products.get(i).getId().equals(merchantStock.getProductId())){
//                for (int j = 0; j <merchantService.merchants.size() ; j++) {
//                    if(merchantService.merchants.get(j).getId().equals(merchantStock.getMerchantId())){
//                        merchantStocks.add(merchantStock);
//                        return true;
//                    }
//
//                }
//            }
//
//        }
//        return false;
//
//    }



    public String addMerchantStock(MerchantStock merchantStock){

        for (int i = 0; i <productService.products.size() ; i++) {
            if(productService.products.get(i).getId().equals(merchantStock.getProductId())){
                for (int j = 0; j <merchantService.merchants.size() ; j++) {
                    if(merchantService.merchants.get(j).getId().equals(merchantStock.getMerchantId())){
                        merchantStocks.add(merchantStock);
                        return "merchantStock added";
                    }

                }
                return "wrong merchants id";
            }

        }
        return "wrong products id";

    }

    public boolean  updateMerchantStock(String id, MerchantStock merchantStock){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(id)){
              merchantStocks.set(i,merchantStock);
              return true;

            }

        }
        return false;
    }




//    public String  updateMerchantStock(String id, MerchantStock merchantStock) {
//        for (int i = 0; i < merchantStocks.size(); i++) {
//            if (merchantStocks.get(i).getId().equals(id)) {
//                for (int j = 0; j < productService.products.size(); j++) {
//                    if (productService.products.get(i).equals(merchantStock.getProductId())) {
//
//                        for (int k = 0; k < merchantService.merchants.size(); k++) {
//                            if (merchantService.merchants.get(i).equals(merchantStock.getMerchantId())) {
//
//                                merchantStocks.set(i, merchantStock);
//                                return "merchantStock updated";
//
//                            }
//
//                        }
//                        return "wrong merchants id";
//                    }
//
//
//                }
//                return "wrong products id";
//            }
//        }
//        return "wrong merchantStocks ";
//    }

//    }



    public boolean deleteMerchantStock(String id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }
}



//    public boolean addStocks(String productId, String merchantId, int amount) {
//        for (int i = 0; i < merchantStocks.size(); i++) {
//            if (merchantStocks.get(i).getProduct().getId().equals(productId)) {
//                if (merchantStocks.get(i).getMerchant().getId().equals(merchantId)) {
//                    merchantStocks.get(i).setStock(amount);
//
//                    return true;
//                }
//            }
//
//        }
//
//        return false;
//    }

