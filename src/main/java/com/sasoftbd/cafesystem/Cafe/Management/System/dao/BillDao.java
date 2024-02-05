package com.sasoftbd.cafesystem.Cafe.Management.System.dao;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Bill;
import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDao extends JpaRepository<Category, Integer> {
    List<Bill> getAllCategory();

}
