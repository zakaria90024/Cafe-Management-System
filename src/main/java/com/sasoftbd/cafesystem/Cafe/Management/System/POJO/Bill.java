package com.sasoftbd.cafesystem.Cafe.Management.System.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

//@NamedQuery(name = "Product.getAllProduct", query = "select new com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name)  from Product p where p.status = 'true'")
//@NamedQuery(name = "Product.updateProductStatus", query = "update Product p set p.status=:status where p.id=:id")
//@NamedQuery(name = "Product.getProductByCategory", query = "select new com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.ProductWrapper(p.id, p.name) from Product p where p.category.id=:id and p.status='true'")
//@NamedQuery(name = "Product.getProductById", query = "select new com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.ProductWrapper(p.id, p.name, p.description, p.price,p.status, p.category.name) from Product p where p.id=:id")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "bill")
public class Bill implements Serializable {

    private static final long longSerialVersionUID = 41L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contactnumber")
    private String contactNumber;


    @Column(name = "paymentmethod")
    private String paymentMethod;

    @Column(name = "total")
    private String total;

    @Column(name = "productdetails", columnDefinition = "json")
    private String productDetails;


    @Column(name = "createdby")
    private String createdBy;


}
