package com.cooper.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame{
    public ImageIcon pizzaIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/pizzalogo.png"));
    public JLabel pizzaLabel;
    
        public GUI(String title,int windowSizeX, int windowSizeY){
        closeIfOneWindow();

        setTitle(title);
        setSize(windowSizeX,windowSizeY);
        //puts in centre
        setLocationRelativeTo(null);
        //cant resize window
        setResizable(false);


        //pizza time logo
        setIconImage(pizzaIcon.getImage());
        Image image = pizzaIcon.getImage();
        //scales image smoothly
        Image newimg = image.getScaledInstance(200,120, java.awt.Image.SCALE_SMOOTH);
        pizzaIcon = new ImageIcon(newimg);

        //label for the pizza time img
        pizzaLabel = new JLabel(pizzaIcon);
        pizzaLabel.setVerticalAlignment(JLabel.TOP);
        pizzaLabel.setHorizontalAlignment(JLabel.RIGHT);

        add(pizzaLabel);
        setVisible(true);
    }

    //makes a default button
    public JButton createButton(JButton button, JFrame frame, String text){
        button.setText(text);
        button.addActionListener((ActionListener) frame);
        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.BOLD, 8));
        return button;
    }

    //makes a grid layout panel
    public JPanel createGridLayoutPanel(JPanel panel, LayoutManager layoutManager){
        layoutManager = new GridLayout();
        return panel;
    }

    //checks if there is one window, if there is allow the program to close if the window is closed
    public void closeIfOneWindow(){
        if (Window.getWindows().length == 1) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
