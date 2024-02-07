package com.sasoftbd.cafesystem.Cafe.Management.System.service;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



public interface BillService {


//    ResponseEntity<String> addNewCategory(Map<String, String> requestMap);
//
//    ResponseEntity<List<Category>> getAllCategory(String filterValue);
//
//    ResponseEntity<String> updateCategory(Map<String, String> requestMap);

    ResponseEntity<String> generateReport(Map<String, Object> requestMap);
}
