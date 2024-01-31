package com.sasoftbd.cafesystem.Cafe.Management.System.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@org.hibernate.annotations.NamedQuery(name =  "User.findByEmailId", query = "select u from User u where u.email=:email")
@org.hibernate.annotations.NamedQuery(name =  "User.getAllUser", query = "select new com.sasoftbd.cafesystem.Cafe.Management.System.wrapper.UserWrapper(u.id, u.name, u.email, u.contact_number,u.status) from User u where u.role='user'")
@org.hibernate.annotations.NamedQuery(name =  "User.getAllAdmin", query = "select u.email from User u where u.role='admin'")
@org.hibernate.annotations.NamedQuery(name = "User.updateStatus", query ="update User u set u.status=:status where u.id=:id")



@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    ArrayList<Object> objects = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;

    @Column(name="name")
    private String name;

    @Column(name = "contact_number")
    private String contact_number;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "role")
    private  String role;

//    public User(String email, String password, ArrayList<Object> objects) {
//        this.email = email;
//        this.password = password;
//        this.objects = objects;
//
//    }

//    just use @Data anottation
//    public User(String name, String email, String password, String status, String role) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.status = status;
//        this.role = role;
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
