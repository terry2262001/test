package com.example.nguyentruongtho_dh51900920.Model;

import java.io.Serializable;

public class ProductType implements Serializable {
    private int id ;
    private String name ;

    public ProductType() {
    }

    public ProductType(String name) {
        this.name = name;
    }

    public ProductType(int id, String name) {
        this.id = id;
        this.name = name;
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
}
