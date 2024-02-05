package com.sasoftbd.cafesystem.Cafe.Management.System.service;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
//    ResponseEntity<String> signUp(Map<String, String> requestMap);
//    ResponseEntity<String> login(Map<String, String> requestMap);
//    ResponseEntity<List<UserWrapper>> getAllUser();
//    ResponseEntity<String> update(Map<String, String> requestMap);
//    ResponseEntity<String> checkToken();
//
//    ResponseEntity<String> changePassword(Map<String, String> requestMap);
//
//    ResponseEntity<String> forgetPassword(Map<String, String> requestMap);

    ResponseEntity<String> addNewProduct(Map<String, String> requestMap);

    ResponseEntity<List<ProductWrapper>> getAllProduct();

    ResponseEntity<String> updateProduct(Map<String, String> requestMap);

    ResponseEntity<String> deleteProduct(Integer id);

    ResponseEntity<String> updateStatus(Map<String, String> requestMap);

    ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);

    ResponseEntity<ProductWrapper> getProductById(Integer id);


    //ResponseEntity<List<Category>> getAllProduct(String filterValue);

    //ResponseEntity<String> updateProduct(Map<String, String> requestMap);
}
