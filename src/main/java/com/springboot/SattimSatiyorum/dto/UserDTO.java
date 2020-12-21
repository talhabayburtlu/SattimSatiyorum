package com.springboot.SattimSatiyorum.dto;

import java.util.ArrayList;

public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String mail;
    private String phoneNumber;
    private ArrayList<Integer> createdCommercialIds;
    private ArrayList<Integer> appliedCommercialIds;
    private int package_id;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Integer> getCreatedCommercialIds() {
        return createdCommercialIds;
    }

    public void setCreatedCommercialIds(ArrayList<Integer> createdCommercialIds) {
        this.createdCommercialIds = createdCommercialIds;
    }

    public ArrayList<Integer> getAppliedCommercialIds() {
        return appliedCommercialIds;
    }

    public void setAppliedCommercialIds(ArrayList<Integer> appliedCommercialIds) {
        this.appliedCommercialIds = appliedCommercialIds;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }
}
