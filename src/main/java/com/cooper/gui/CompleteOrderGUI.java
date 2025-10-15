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

public class CompleteOrderGUI extends GUI implements ActionListener {

    private JButton exit = new JButton("Exit");
    private ImageIcon backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/checkout.jpg"));

    public CompleteOrderGUI(String title, int windowSizeX, int windowSizeY, ArrayList<Pizza> pizzas, int pizzaAmount, String customerName) {
        super(title, windowSizeX, windowSizeY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background panel
        BasicBackgroundPanel background = new BasicBackgroundPanel(backgroundIcon.getImage());

        // Main panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBounds(100, 100, 500, 500);
        panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));

        // Exit button panel
        JPanel exitPanel = new JPanel(new BorderLayout());
        exitPanel.setBounds(300, 400, 100, 60);
        exitPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 10));

        // Calculate total order info
        String allPizzas = "";
        float fullPrice = 0;

        for (int i = 0; i < pizzaAmount; i++) {
            allPizzas += pizzas.get(i).getName() + "<br>";
            fullPrice += pizzas.get(i).getPrice();
        }

        // Label with HTML formatting for multiline
        JLabel label = new JLabel("<html>"
                + "<b>Customer Name and Address:</b> " + customerName + "<br><br>"
                + "<b>Customer Order:</b><br>" + allPizzas + "<br>"
                + "<b>Total Price:</b> $" + fullPrice
                + "</html>");

        // Add components
        panel.add(label, BorderLayout.CENTER);
        exit.addActionListener(this);
        exitPanel.add(exit);

        add(panel);
        add(exitPanel);
        add(background);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }
}
