package com.sasoftbd.cafesystem.Cafe.Management.System.rest;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/product")
public interface ProductRest {
    @PostMapping(path = "/add")
    ResponseEntity<String> addNewProduct(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<ProductWrapper>> getAllProduct();
//
//    @PostMapping(path = "/update")
//    ResponseEntity<String> updateProduct(@RequestBody(required = true) Map<String, String> requestMap);

}
