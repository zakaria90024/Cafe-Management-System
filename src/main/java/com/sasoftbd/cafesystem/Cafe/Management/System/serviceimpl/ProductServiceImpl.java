package com.sasoftbd.cafesystem.Cafe.Management.System.serviceimpl;

import com.sasoftbd.cafesystem.Cafe.Management.System.JWT.JwtFilter;
import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Category;
import com.sasoftbd.cafesystem.Cafe.Management.System.POJO.Product;
import com.sasoftbd.cafesystem.Cafe.Management.System.constents.CafeConstants;
import com.sasoftbd.cafesystem.Cafe.Management.System.dao.CategoryDao;
import com.sasoftbd.cafesystem.Cafe.Management.System.dao.ProductDao;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.CategoryService;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.ProductService;
import com.sasoftbd.cafesystem.Cafe.Management.System.utils.CafeUtils;
import com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.ProductWrapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    JwtFilter jwtFilter;


    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, false)) {
                    productDao.save(getProductFromMap(requestMap, false));
                    return CafeUtils.getResponseEntity("Product Added Successfully", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateProductMap(Map<String, String> requestMap, boolean validate) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validate) {
                return true;
            } else if (!validate) {
                return true;
            }
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, Boolean isAdd) {

        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));


        Product product = new Product();
        if (isAdd) {
            product.setId(Integer.parseInt(requestMap.get("categoryId")));
        } else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));

        return product;
    }


    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try {
            return new ResponseEntity(productDao.getAllProduct(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateProductMap(requestMap, true)) {

                    Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("id")));

                    if (!optional.isEmpty()) {
                        Product product = getProductFromMap(requestMap, true);
                        product.setStatus(optional.get().getStatus());
                        productDao.save(product);

                        return CafeUtils.getResponseEntity("Product Update Successfully", HttpStatus.OK);

                    } else {
                        return CafeUtils.getResponseEntity("Product Id Not Match", HttpStatus.OK);
                    }

                }
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {
            if (jwtFilter.isAdmin()) {


                Optional<Product> optional = productDao.findById(id);

                if (!optional.isEmpty()) {

                    productDao.deleteById(id);

                    return CafeUtils.getResponseEntity("Delete Successfully", HttpStatus.OK);

                } else {
                    return CafeUtils.getResponseEntity("Delete Id Not Match", HttpStatus.OK);
                }


            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {


                    Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("id")));

                    if (!optional.isEmpty()) {

                        productDao.updateProductStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));


                        return CafeUtils.getResponseEntity("Product Status Updated Successfully", HttpStatus.OK);

                    } else {
                        return CafeUtils.getResponseEntity("Product Id Not Match", HttpStatus.OK);
                    }

            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
        try {
            return new ResponseEntity(productDao.getProductByCategory(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ProductWrapper> getProductById(Integer id) {
        try {
            return new ResponseEntity(productDao.getProductById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
