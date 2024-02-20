package com.sasoftbd.cafesystem.Cafe.Management.System.dao;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.User;
import com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.UserWrapper;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);
    List<UserWrapper> getAllUser();
    List<String> getAllAdmin();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    User findByEmail(String email);
}
