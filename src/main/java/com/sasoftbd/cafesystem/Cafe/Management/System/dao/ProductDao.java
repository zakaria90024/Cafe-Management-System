package com.sasoftbd.cafesystem.Cafe.Management.System.dao;

import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Product;
import com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    List<ProductWrapper> getAllProduct();

}
