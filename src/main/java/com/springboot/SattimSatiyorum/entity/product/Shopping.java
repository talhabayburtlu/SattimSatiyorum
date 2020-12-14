package com.springboot.SattimSatiyorum.entity.product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Shopping")
public class Shopping extends Product {

}
