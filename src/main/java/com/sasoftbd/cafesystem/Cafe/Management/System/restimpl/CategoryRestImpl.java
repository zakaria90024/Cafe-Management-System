package com.sasoftbd.cafesystem.Cafe.Management.System.restimpl;

import com.sasoftbd.cafesystem.Cafe.Management.System.constents.CafeConstants;
import com.sasoftbd.cafesystem.Cafe.Management.System.rest.CategoryRest;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.CategoryService;
import com.sasoftbd.cafesystem.Cafe.Management.System.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CategoryRestImpl implements CategoryRest {


    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
            return categoryService.addNewCategory(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
