package com.sasoftbd.cafesystem.Cafe.Management.System.serviceimpl;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.User;
import com.sasoftbd.cafesystem.Cafe.Management.System.constents.CafeConstants;
import com.sasoftbd.cafesystem.Cafe.Management.System.dao.UserDao;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.UserService;
import com.sasoftbd.cafesystem.Cafe.Management.System.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

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
}
