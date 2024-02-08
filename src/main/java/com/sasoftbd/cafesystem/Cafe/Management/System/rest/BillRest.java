package com.sasoftbd.cafesystem.Cafe.Management.System.rest;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/bill")
public interface BillRest {
    @PostMapping(path = "/generateReport")
    ResponseEntity<String> generateReport(@RequestBody(required = true)Map<String, Object> requestMap);
    
}
