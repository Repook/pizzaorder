package com.cooper.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import com.cooper.gui.panel.BasicBackgroundPanel;
import com.cooper.pizza.GourmetPizza;
import com.cooper.pizza.Pizza;

public class MainMenuDeliveryGUI extends MainMenuGUI implements ActionListener {

    private static final int MAX_PIZZAS = 5;

    private ImageIcon backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/pizzaoven.jpeg"));
    private ImageIcon pizzaIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/pizza.png"));

    private String writeNameText = "write your name here";
    private String writeAddressText = "write your address here";

    private int pizzaAmount = 0;
    private ArrayList<Pizza> pizzas = new ArrayList<>();
    private String name;
    private String address;

    private JLabel pizzaAmountLabel = new JLabel("Pizza Amount: " + pizzaAmount);

    private JButton completeOrderButton = new JButton("Complete Order");
    private JButton[] pizzaButton = new JButton[12];

    private JTextArea nameField;
    private JTextArea addressField;

    private Pizza normalPizza = new Pizza(getName());
    private GourmetPizza gourmetPizza = new GourmetPizza(getName());

    public MainMenuDeliveryGUI(String title, int windowSizeX, int windowSizeY) {
        super(title, windowSizeX, windowSizeY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BasicBackgroundPanel background = new BasicBackgroundPanel(backgroundIcon.getImage());
        background.setLayout(null);

        // Scale pizza icon
        Image pizzaImage = pizzaIcon.getImage();
        Image pizzaScaled = pizzaImage.getScaledInstance(50, 30, Image.SCALE_SMOOTH);
        pizzaIcon = new ImageIcon(pizzaScaled);

        // Create pizza buttons
        String[] names = { "Pepperoni", "Cheese", "Hawaiian", "BBQ", "Fish", "Chocolate",
                "Egg", "Peter", "Pita", "Broccoli", "Sausage", "Garlic" };
        for (int i = 0; i < pizzaButton.length; i++) {
            pizzaButton[i] = new JButton(names[i] + " $" + (i < 7 ? normalPizza.getPrice() : gourmetPizza.getPrice()), pizzaIcon);
            pizzaButton[i].addActionListener(this);
            if (i >= 7) pizzaButton[i].setBackground(Color.YELLOW);
        }

        // Pizza grid
        JPanel pizzaPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        pizzaPanel.setBounds(100, 100, 500, 400);
        pizzaPanel.setOpaque(false);
        for (JButton btn : pizzaButton) pizzaPanel.add(btn);
        background.add(pizzaPanel);

        // Pizza amount label
        pizzaAmountLabel.setBounds(50, 50, 200, 20);
        background.add(pizzaAmountLabel);

        // Complete order button
        completeOrderButton.setBounds(50, 70, 150, 30);
        completeOrderButton.addActionListener(this);
        background.add(completeOrderButton);

        // Address field
        addressField = new JTextArea(writeAddressText);
        addressField.setBounds(50, 630, 200, 25);
        background.add(addressField);
        setupPlaceholder(addressField, writeAddressText);

        // Name field (just below address)
        nameField = new JTextArea(writeNameText);
        nameField.setBounds(50, 665, 200, 25);
        background.add(nameField);
        setupPlaceholder(nameField, writeNameText);

        // Unfocus both when clicking outside
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (!(event instanceof MouseEvent me)) return;
            if (me.getID() != MouseEvent.MOUSE_PRESSED) return;
            Component comp = SwingUtilities.getDeepestComponentAt(me.getComponent(), me.getX(), me.getY());
            if (comp == null || (!SwingUtilities.isDescendingFrom(comp, nameField)
                    && !SwingUtilities.isDescendingFrom(comp, addressField))) {
                KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
            }
        }, AWTEvent.MOUSE_EVENT_MASK);

        add(background);
        setVisible(true);
        SwingUtilities.invokeLater(() -> this.requestFocusInWindow());
    }

    private void setupPlaceholder(JTextArea field, String placeholder) {
        final boolean[] showing = { true };
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showing[0]) {
                    field.setText("");
                    showing[0] = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    showing[0] = true;
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle pizza selection
        for (int i = 0; i < pizzaButton.length; i++) {
            if (e.getSource() == pizzaButton[i]) {
                if (pizzaAmount >= MAX_PIZZAS) {
                    JOptionPane.showMessageDialog(this, "You have reached maximum pizza amount");
                    return;
                }

                pizzaAmount++;
                pizzaAmountLabel.setText("Pizza Amount: " + pizzaAmount);
                if (i < 7)
                    pizzas.add(new Pizza(pizzaButton[i].getText().split(" ")[0]));
                else
                    pizzas.add(new GourmetPizza(pizzaButton[i].getText().split(" ")[0]));
            }
        }

        // Handle Complete Order button
        if (e.getSource() == completeOrderButton) {
            String enteredName = nameField.getText();
            String enteredAddress = addressField.getText();

            if (pizzaAmount == 0) {
                JOptionPane.showMessageDialog(this, "You must order at least 1 pizza to complete order");
                return;
            }
            if (enteredName.equals(writeNameText) || enteredName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please write down a suitable name");
                return;
            }
            if (enteredAddress.equals(writeAddressText) || enteredAddress.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid address");
                return;
            }

            name = enteredName;
            address = enteredAddress;
            System.out.println("Name: " + name + ", Address: " + address);

            new CompleteOrderGUI("Pizza Receipt", 700, 800, pizzas, pizzaAmount, name);
        }
    }
}
