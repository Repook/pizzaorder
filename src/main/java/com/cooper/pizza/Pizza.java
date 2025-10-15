package com.cooper.pizza;

public class Pizza {

    private static final float PRICE = 8.5F;
    private String name;

    public Pizza(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return PRICE;
    }

}
