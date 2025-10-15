package com.cooper.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class OrderGUI extends GUI implements ActionListener{

    private JButton button;

    private JPanel panel;

    private JPanel deliveryPanel;

    private JCheckBox deliveryCheckBox;

    public boolean isForDelivery;


    //constructor
    public OrderGUI(String title,int windowSizeX, int windowSizeY){
        super(title, windowSizeX, windowSizeY);

        // new order button
        button = new JButton("New Order");
        button.setPreferredSize(new Dimension(100,100));
        button.addActionListener(this);



        //panel logic
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new FlowLayout());
        panel.add(this.pizzaLabel);
        panel.add(button);

        //Delivery Pnael logic
        deliveryPanel = new JPanel();
        deliveryPanel.setLayout(new FlowLayout());


        add(panel,BorderLayout.CENTER);
        add(deliveryPanel,BorderLayout.SOUTH);
        
        //keep at end
        setVisible(true);
    }


    //called when button's are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
            //make the Main Order Window, with the grid of pizzas
            MainMenuGUI menuOrderGui = new MainMenuGUI("Pick Your Pizza's!!!!", 700, 650);
        


    }


}
