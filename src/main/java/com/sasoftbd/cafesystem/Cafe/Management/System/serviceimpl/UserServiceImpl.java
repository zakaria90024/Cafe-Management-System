package com.sasoftbd.cafesystem.Cafe.Management.System.serviceimpl;

import com.sasoftbd.cafesystem.Cafe.Management.System.JWT.CustomUsersDetailsService;
import com.sasoftbd.cafesystem.Cafe.Management.System.JWT.JwtFilter;
import com.sasoftbd.cafesystem.Cafe.Management.System.JWT.JwtUtils;
import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.User;
import com.sasoftbd.cafesystem.Cafe.Management.System.constents.CafeConstants;
import com.sasoftbd.cafesystem.Cafe.Management.System.dao.UserDao;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.UserService;
import com.sasoftbd.cafesystem.Cafe.Management.System.utils.CafeUtils;
import com.sasoftbd.cafesystem.Cafe.Management.System.utils.EmailUtils;
import com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authcationManager;

    @Autowired
    CustomUsersDetailsService customUsersDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;


    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {

        try {

            if (validDateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("Email already Exits", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside Login");
        try {
            Authentication authentication = authcationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (authentication.isAuthenticated()) {
                if (customUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\":\"" + jwtUtils.generateToken(customUsersDetailsService.getUserDetail().getEmail(),
                            customUsersDetailsService.getUserDetail().getRole()) + "\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"Wait for Admin Approval\"}", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            log.error("{}", e);
        }

        return new ResponseEntity<>("{\"message\":\"Bad Credential\"}", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {

        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {

            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));

                    //sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());

                    return CafeUtils.getResponseEntity("Updated Successfully", HttpStatus.OK);
                } else {
                    CafeUtils.getResponseEntity("User id not exist", HttpStatus.OK);
                }
                //return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.BAD_REQUEST);
    }


    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());

        if (status != null && status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account approved", "User " + user + "by Admin" + jwtFilter.getCurrentUser(), allAdmin);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disable", "User " + user + "by Admin" + jwtFilter.getCurrentUser(), allAdmin);

        }
    }

//    @Override
//    public ResponseEntity<String> updateRole(Map<String, String> requestMap) {
//
//
//    }

    private boolean validDateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("name") && requestMap.containsKey("contact_number") && requestMap.containsKey("email") &&
                requestMap.containsKey("password") && requestMap.containsKey("status") && requestMap.containsKey("role")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> request) {
        User user = new User();
        user.setName(request.get("name"));
        user.setContact_number(request.get("contact_number"));
        user.setEmail(request.get("email"));
        user.setPassword(request.get("password"));
        user.setStatus(request.get("status"));
        user.setRole(request.get("user"));
        return user;

    }


    @Override
    public ResponseEntity<String> checkToken() {
        return CafeUtils.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
            if (!userObj.equals(null)) {
                if (userObj.getPassword().equals(requestMap.get("oldPassword"))) {
                    userObj.setPassword(requestMap.get("newPassword"));
                    userDao.save(userObj);
                    return CafeUtils.getResponseEntity("Password Updated Successfully", HttpStatus.OK);
                }
                return CafeUtils.getResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
            }
            return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgetPassword(Map<String, String> requestMap) {
        try {
            User userObj = userDao.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(userObj) && !Strings.isNullOrEmpty(userObj.getEmail()))
                emailUtils.forgetMail(userObj.getEmail(), "Credentials for Cafe Management System ", userObj.getPassword());
            return CafeUtils.getResponseEntity("Check Your Mail For Credentials", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
