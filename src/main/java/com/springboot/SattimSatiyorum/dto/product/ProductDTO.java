package com.springboot.SattimSatiyorum.dto.product;

public class ProductDTO {
    private int id;
    private String header;
    private String description;
    private String type;
    private int commercial_id;
    private int category_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCommercial_id() {
        return commercial_id;
    }

    public void setCommercial_id(int commercial_id) {
        this.commercial_id = commercial_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
