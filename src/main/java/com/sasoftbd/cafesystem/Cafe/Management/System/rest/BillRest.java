package com.sasoftbd.cafesystem.Cafe.Management.System.rest;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Bill;
import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/bill")
public interface BillRest {
    @PostMapping(path = "/generateReport")
    ResponseEntity<String> generateReport(@RequestBody(required = true)Map<String, Object> requestMap);

    @GetMapping(path = "/getBills")
    ResponseEntity<List<Bill>> getBills();
    @PostMapping(path = "/getPdf")
    ResponseEntity<byte[]> getPdf(@RequestBody(required = true) Map<String, Object> requestMap);

    @PostMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteBill(@PathVariable Integer id);

}
