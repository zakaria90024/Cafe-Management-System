package com.sasoftbd.cafesystem.Cafe.Management.System.rest;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/category")
public interface CategoryRest {
    @PostMapping(path = "/add")
    ResponseEntity<String> addNewCategory(@RequestBody(required = true)Map<String, String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<Category>> getAllCategory(@RequestParam(required = true) String filterValue);
}
