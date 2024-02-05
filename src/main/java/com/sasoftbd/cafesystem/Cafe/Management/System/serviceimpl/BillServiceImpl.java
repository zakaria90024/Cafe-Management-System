package com.sasoftbd.cafesystem.Cafe.Management.System.serviceimpl;

import com.sasoftbd.cafesystem.Cafe.Management.System.JWT.JwtFilter;
import com.sasoftbd.cafesystem.Cafe.Management.System.constents.CafeConstants;
import com.sasoftbd.cafesystem.Cafe.Management.System.dao.BillDao;
import com.sasoftbd.cafesystem.Cafe.Management.System.service.BillService;
import com.sasoftbd.cafesystem.Cafe.Management.System.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillDao billDao;
    @Autowired
    JwtFilter jwtFilter;

    //
//    @Override
//    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
//        try {
//            if(jwtFilter.isAdmin()){
//                if(validateCategoryMap(requestMap, false)){
//                    categoryDao.save(getCategoryFromMap(requestMap, false));
//                    return CafeUtils.getResponseEntity("Category Added Successfully", HttpStatus.OK);
//                }
//            }else {
//                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//
//
    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name") &&
                requestMap.containsKey("contactNumber") &&
                requestMap.containsKey("email") &&
                requestMap.containsKey("paymentMethod") &&
                requestMap.containsKey("totalAmount") &&
                requestMap.containsKey("productDetails");
    }

//    private Category getCategoryFromMap(Map<String, String> requestMap, Boolean isAdd){
//        Category category = new Category();
//        if(isAdd){
//            category.setId(Integer.parseInt(requestMap.get("id")));
//        }
//        category.setName(requestMap.get("name"));
//        return category;
//    }
//
//
//    @Override
//    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
//        try {
//            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")){
//                log.info("Inside If");
//                return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
//            }
//            log.info("Out If");
//            return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @Override
//    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
//
//        try {
//            if(jwtFilter.isAdmin()){
//                if(validateCategoryMap(requestMap, true)){
//                    Optional<Category> optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
//
//                    if (!optional.isEmpty()){
//                        categoryDao.save(getCategoryFromMap(requestMap, true));
//                        return CafeUtils.getResponseEntity("Category Update Successfully", HttpStatus.OK);
//
//                    }else {
//                        return CafeUtils.getResponseEntity("Category Id Not Match", HttpStatus.OK);
//                    }
//
//                }
//                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
//            }else {
//                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        try {
            String fileName;
            if (validateRequestMap(requestMap)) {
                if(requestMap.containsKey("isGenerate") && !(Boolean)requestMap.get("isGenerate")){
                    fileName = (String) requestMap.get("uuid");
                }else {
                    fileName = CafeUtils.getUUID();
                    requestMap.put("uuid", fileName);
                    insertBill(requestMap);
                }

                return CafeUtils.getResponseEntity("Required Data Not Found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WANT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void insertBill(Map<String, Object> requestMap) {
        
    }
}
