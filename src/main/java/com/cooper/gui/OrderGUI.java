package com.cooper.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OrderGUI extends GUI implements ActionListener{

    //private ImageIcon backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/checkout.jpg"));

    private JButton button;

    private JPanel panel;

    //constructor
    public OrderGUI(String title,int windowSizeX, int windowSizeY){
        super(title, windowSizeX, windowSizeY);

        //BasicBackgroundPanel background = new BasicBackgroundPanel(backgroundIcon.getImage());

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

        
        add(panel,BorderLayout.CENTER);
        //add(background);
        
        //keep at end
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainMenuGUI menuOrderGui = new MainMenuGUI("Pick Your Pizza's!!!!", 700, 800);
        //dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }


}
