package com.cooper.customer;

public class Customer {
    private String name;
    private String address;
    private String phoneNumber;


    //constructor for delivery
    public Customer(String name, String address, String phoneNumber){
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    //constructor for pickup
    public Customer(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
