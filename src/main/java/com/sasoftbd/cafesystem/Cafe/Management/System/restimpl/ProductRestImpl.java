package com.sasoftbd.cafesystem.Cafe.Management.System.restimpl;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import com.sasoftbd.cafesystem.Cafe.Management.System.constents.CafeConstants;
import com.sasoftbd.cafesystem.Cafe.Management.System.rest.CategoryRest;
import com.sasoftbd.cafesystem.Cafe.Management.System.rest.ProductRest;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.CategoryService;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.ProductService;
import com.sasoftbd.cafesystem.Cafe.Management.System.utils.CafeUtils;
import com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ProductRestImpl implements ProductRest {


    @Autowired
    ProductService productService;

//    @Override
//    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
//        try {
//            return categoryService.addNewCategory(requestMap);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
//        try {
//            return categoryService.getAllCategory(filterValue);
//        }catch (Exception e){
//
//        }
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
//        try {
//            return categoryService.updateCategory(requestMap);
//        }catch (Exception e){
//
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            return productService.addNewProduct(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try {
            return productService.getAllProduct();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new  ResponseEntity(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            return productService.updateProduct(requestMap);
        } catch (Exception e) {

        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {
            return productService.deleteProduct(id);
        } catch (Exception e) {

        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
//
//    @Override
//    public ResponseEntity<List<Category>> getAllProduct(String filterValue) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
//        return null;
//    }
}
