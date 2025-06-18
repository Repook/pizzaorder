package com.cooper.pizza;

public class Pizza {

    private float price;
    private String name;

    public Pizza(String name){
        this.price = 8.50f;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
