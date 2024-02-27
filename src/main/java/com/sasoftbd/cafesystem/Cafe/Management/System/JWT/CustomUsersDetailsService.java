package com.sasoftbd.cafesystem.Cafe.Management.System.JWT;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.User;
import com.sasoftbd.cafesystem.Cafe.Management.System.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
@Slf4j
@Service
public class CustomUsersDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDetail = userDao.findByEmailId(username);
        if(!Objects.isNull(userDetail)){
            return new org.springframework.security.core.userdetails.User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        }else {
            throw  new UsernameNotFoundException("User Not Found ");
        }
    }

    public User getUserDetail(){
        return userDetail;//get usr details

//        User user = userDetail;
//        user.setPassword(null);
//        return userDetail;

    }
}
