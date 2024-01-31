package com.sasoftbd.cafesystem.Cafe.Management.System.restimpl;

import com.sasoftbd.cafesystem.Cafe.Management.System.constents.CafeConstants;
import com.sasoftbd.cafesystem.Cafe.Management.System.rest.UserRest;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.UserService;
import com.sasoftbd.cafesystem.Cafe.Management.System.utils.CafeUtils;
import com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);

        }catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        //return  new ResponseEntity<String>("{\"message\" : \"Something Went Wrong\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {

        try {
            return userService.getAllUser();

        }catch (Exception e){
            e.printStackTrace();
        }


        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {

        try {
            return userService.update(requestMap);
        }catch (Exception e){
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
