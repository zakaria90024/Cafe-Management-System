package com.sasoftbd.cafesystem.Cafe.Management.System.service;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



public interface BillService {

    ResponseEntity<String> generateReport(Map<String, Object> requestMap);
}
