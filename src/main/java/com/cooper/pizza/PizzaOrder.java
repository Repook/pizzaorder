package com.cooper.pizza;

import java.util.Scanner;

import com.cooper.customer.Customer;

public class PizzaOrder {

    private Customer customer;

    private boolean isForDelivery;

    private int maximumPizzas = 5;
    public PizzaTypes[] pizzas = new PizzaTypes[12];
    public int pizzaAmount;


    public PizzaOrder(Customer customer){
        this.customer = customer;
    }

    public boolean checkForDelivery(Scanner scanner){
        boolean input = true;
        String userInput;
        while (input) {
            System.out.println("will you be taking delivery? yes[1] no[2]");
            userInput = scanner.nextLine();
            if (userInput == "1") {
                System.out.println("you will be taking delivery");
                isForDelivery =true;
                input = false;
            }
            else if (userInput == "2") {
                System.out.println("you will be taking pickup");
                isForDelivery = false;
                input = false;
            } else {
                System.out.println("please select an option");
            }
        }
        scanner.close();
        return isForDelivery;
    }
    
}
