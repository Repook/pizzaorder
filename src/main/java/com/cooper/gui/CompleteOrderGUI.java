package com.cooper.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cooper.gui.panel.BasicBackgroundPanel;
import com.cooper.pizza.Pizza;

public class CompleteOrderGUI extends GUI implements ActionListener{

    private JButton exit  = new JButton("Exit");;
    private ImageIcon backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/checkout.jpg"));



    public CompleteOrderGUI(String title, int windowSizeX, int windowSizeY, ArrayList<Pizza> pizzas, int pizzaAmount) {
        super(title, windowSizeX, windowSizeY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BasicBackgroundPanel background = new BasicBackgroundPanel(backgroundIcon.getImage());
        //panel stuff
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(100, 100, 500, 200);
        panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));

        JPanel exitPanel = new JPanel(new BorderLayout());
        exitPanel.setBounds(300, 400, 100, 60);
        exitPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));

        String allPizzas = "";
        float fullPrice = 0;

        for (int i = 0; i < pizzaAmount; i++) {
                allPizzas = allPizzas.concat(pizzas.get(i).getName()) + ", ";
                fullPrice =  fullPrice + pizzas.get(i).getPrice();
        }

        exit.addActionListener(this);
        exitPanel.add(exit);

        JLabel label = new JLabel("Your Order: " + allPizzas + "$" + String.valueOf(fullPrice));
        panel.add(label);
     

        add(panel);
        add(exitPanel);
        add(background);
        
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.out.println("exit!!!");
            System.exit(0);
        }
    }

}
