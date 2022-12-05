package com.example.nguyentruongtho_dh51900920.Model;

import java.io.Serializable;
import java.util.Arrays;

public class Product implements Serializable {
    private int id;
    private String name;
    private byte [] image;
    private String description;
    private String price;
    private int typeid;
    private String type;
    private double priceD;

    public Product(String name, byte[] image, String description, double priceD, int typeid) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.priceD = priceD;
        this.typeid = typeid;


    }

    public Product(int id, String name, byte[] image, String description, double priceD, int typeid, String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.priceD = priceD;
        this.typeid = typeid;
        this.type = type;

    }

    public Product(int id, String name, byte[] image, String description, String price, int typeid, String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.typeid = typeid;
        this.type = type;
    }

    public Product(int id, String name, byte[] image, String description, String price, String type) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public Product() {
    }

    public Product(int id, String name, byte[] image, String description, String price, int typeid) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.typeid = typeid;
    }

    public Product(String name, byte[] image, String description, String price, int typeid) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.typeid = typeid;
    }

    public double getPriceD() {
        return priceD;
    }

    public void setPriceD(double priceD) {
        this.priceD = priceD;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    @Override
    public String toString() {
        return id + name+ type + description+typeid;
    }
}
