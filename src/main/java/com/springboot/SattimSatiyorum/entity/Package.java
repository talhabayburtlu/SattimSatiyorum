package com.springboot.SattimSatiyorum.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "showcase_bold")
    private Boolean showcaseBold;

    @Column(name = "max_num_of_images")
    private int maxNumOfImages;

    @Column(name = "max_num_of_commercials_per_three_month")
    private int maxNumOfCommercialsPerThreeMonth;

    @Column(name = "urgent")
    private Boolean urgent;

    @OneToMany(mappedBy = "aPackage")
    private List<User> users;

    public Package() {
    }

    public Package(Boolean showcaseBold, int maxNumOfImages, int maxNumOfCommercialsPerThreeMonth, Boolean urgent) {
        this.showcaseBold = showcaseBold;
        this.maxNumOfImages = maxNumOfImages;
        this.maxNumOfCommercialsPerThreeMonth = maxNumOfCommercialsPerThreeMonth;
        this.urgent = urgent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getShowcaseBold() {
        return showcaseBold;
    }

    public void setShowcaseBold(Boolean showcaseBold) {
        this.showcaseBold = showcaseBold;
    }

    public int getMaxNumOfImages() {
        return maxNumOfImages;
    }

    public void setMaxNumOfImages(int maxNumOfImages) {
        this.maxNumOfImages = maxNumOfImages;
    }

    public int getMaxNumOfCommercialsPerThreeMonth() {
        return maxNumOfCommercialsPerThreeMonth;
    }

    public void setMaxNumOfCommercialsPerThreeMonth(int maxNumOfCommercialsPerThreeMonth) {
        this.maxNumOfCommercialsPerThreeMonth = maxNumOfCommercialsPerThreeMonth;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
