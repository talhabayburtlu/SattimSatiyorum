package com.springboot.SattimSatiyorum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @Column(name = "mail")
    private String mail;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "seller")
    private List<Commercial> createdCommercials;

    @OneToMany(mappedBy = "buyer")
    private List<Commercial> appliedCommercials;

    @ManyToOne()
    @JoinColumn(name = "package_id")
    private Package aPackage;

    public User() {

    }

    public User(String name, String surname, int age, String mail, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Commercial> getCreatedCommercials() {
        return createdCommercials;
    }

    public void setCreatedCommercials(List<Commercial> createdCommercials) {
        this.createdCommercials = createdCommercials;
    }

    public List<Commercial> getAppliedCommercials() {
        return appliedCommercials;
    }

    public void setAppliedCommercials(List<Commercial> appliedCommercials) {
        this.appliedCommercials = appliedCommercials;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}