package com.sasoftbd.cafesystem.Cafe.Management.System.restimpl;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import com.sasoftbd.cafesystem.Cafe.Management.System.constents.CafeConstants;
import com.sasoftbd.cafesystem.Cafe.Management.System.rest.BillRest;
import com.sasoftbd.cafesystem.Cafe.Management.System.rest.CategoryRest;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.BillService;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.CategoryService;
import com.sasoftbd.cafesystem.Cafe.Management.System.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BillRestImpl implements BillRest {


    @Autowired
    BillService billService;
//
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
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        try {
            return billService.generateReport(requestMap);
        } catch (Exception e) {

        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
