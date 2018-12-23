package com.spikeysanju98gmail.complaint.Models;

public class ChairBanner {

    private String id, name, image, price, height, weight, width, discount, description;

    public ChairBanner(){

    }

    public ChairBanner(String id, String name, String image, String price, String height, String weight, String width, String discount, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.height = height;
        this.weight = weight;
        this.width = width;
        this.discount = discount;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

