package com.springboot.SattimSatiyorum.entity.product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Residence")
public class Residence extends Product {

}
