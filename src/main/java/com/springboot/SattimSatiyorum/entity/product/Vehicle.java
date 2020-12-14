package com.springboot.SattimSatiyorum.entity.product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Vehicle")
public class Vehicle extends Product {

}
