package com.sasoftbd.cafesystem.Cafe.Management.System.service;

import com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface CategoryService {
//    ResponseEntity<String> signUp(Map<String, String> requestMap);
//    ResponseEntity<String> login(Map<String, String> requestMap);
//    ResponseEntity<List<UserWrapper>> getAllUser();
//    ResponseEntity<String> update(Map<String, String> requestMap);
//    ResponseEntity<String> checkToken();
//
//    ResponseEntity<String> changePassword(Map<String, String> requestMap);
//
//    ResponseEntity<String> forgetPassword(Map<String, String> requestMap);

    ResponseEntity<String> addNewCategory(Map<String, String> requestMap);
}
