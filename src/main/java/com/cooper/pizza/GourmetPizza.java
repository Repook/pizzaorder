package com.cooper.pizza;

public class GourmetPizza extends Pizza{
    private static final float PRICE = 13.5F;


    public GourmetPizza(String name) {
        super(name);
    }

    public float getPrice() {
        return PRICE;
    }

}
