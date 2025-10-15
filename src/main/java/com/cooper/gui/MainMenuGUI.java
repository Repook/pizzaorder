package com.cooper.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.cooper.gui.panel.BasicBackgroundPanel;
import com.cooper.pizza.GourmetPizza;
import com.cooper.pizza.Pizza;

public class MainMenuGUI extends GUI implements ActionListener {

    private ImageIcon backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/pizzaoven.jpeg"));
    public ImageIcon pizzaIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/pizza.png"));

    private static final int MAX_PIZZAS = 5;

    private String writeNameText = "write your name here";
    private String writeAddressText = "write your address here";
    private String writeNameDefaultText = "write your name here";
    private String writeAddressDefaultText = "write your address here";

    public int pizzaAmount;
    public ArrayList<Pizza> pizzas = new ArrayList<Pizza>();

    public String name;
    public String address;

    // Label for pizza amount
    JLabel pizzaAmountLabel = new JLabel("Pizza Amount: " + pizzaAmount);
    JLabel pizzaTypesLabel = new JLabel(calculatePizzaTypes());

    // Complete order button
    private JButton completeOrderButton = new JButton();

    // Main pizza panel
    private JPanel panel;

    // Pizza buttons
    private JButton[] pizzaButton = new JButton[12];
    private JButton pepPizzaButton, cheesePizzaButton, hawiPizzaButton,
            bbqPizzaButton, fishPizzaButton, chocPizzaButton, eggPizzaButton,
            peterPizzaButton, pitaPizzaButton, brocPizzaButton, sausPizzaButton,
            garlicPizzaButton;

    private JTextArea nameField;
    private JTextArea addressField;
    private JPanel namePanel;
    private JPanel addressPanel;

    private Pizza normalPizza = new Pizza(getName());
    private GourmetPizza gormetPizza = new GourmetPizza(getName());

    public MainMenuGUI(String title, int windowSizeX, int windowSizeY) {
        super(title, windowSizeX, windowSizeY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BasicBackgroundPanel background = new BasicBackgroundPanel(backgroundIcon.getImage());

        // Pizza icon scaling
        Image pizzaImage = pizzaIcon.getImage();
        Image pizzaScaled = pizzaImage.getScaledInstance(50, 30, java.awt.Image.SCALE_SMOOTH);
        pizzaIcon = new ImageIcon(pizzaScaled);

        // Pizza buttons setup ---------------------------------------------------------------
        pepPizzaButton = new JButton("Pepperoni " + normalPizza.getPrice() + "$", pizzaIcon);
        cheesePizzaButton = new JButton("Cheese " + normalPizza.getPrice() + "$", pizzaIcon);
        hawiPizzaButton = new JButton("Hawaiian " + normalPizza.getPrice() + "$", pizzaIcon);
        bbqPizzaButton = new JButton("BBQ " + normalPizza.getPrice() + "$", pizzaIcon);
        fishPizzaButton = new JButton("Anchovies " + normalPizza.getPrice() + "$", pizzaIcon);
        chocPizzaButton = new JButton("Chocolate " + normalPizza.getPrice() + "$", pizzaIcon);
        eggPizzaButton = new JButton("Egg " + normalPizza.getPrice() + "$", pizzaIcon);
        peterPizzaButton = new JButton("Peter " + gormetPizza.getPrice() + "$", pizzaIcon);
        pitaPizzaButton = new JButton("Pita " + gormetPizza.getPrice() + "$", pizzaIcon);
        brocPizzaButton = new JButton("Broccoli " + gormetPizza.getPrice() + "$", pizzaIcon);
        sausPizzaButton = new JButton("Sausage " + gormetPizza.getPrice() + "$", pizzaIcon);
        garlicPizzaButton = new JButton("Garlic " + gormetPizza.getPrice() + "$", pizzaIcon);

        pizzaButton[0] = pepPizzaButton;
        pizzaButton[1] = cheesePizzaButton;
        pizzaButton[2] = hawiPizzaButton;
        pizzaButton[3] = bbqPizzaButton;
        pizzaButton[4] = fishPizzaButton;
        pizzaButton[5] = chocPizzaButton;
        pizzaButton[6] = eggPizzaButton;
        pizzaButton[7] = peterPizzaButton;
        pizzaButton[8] = pitaPizzaButton;
        pizzaButton[9] = brocPizzaButton;
        pizzaButton[10] = sausPizzaButton;
        pizzaButton[11] = garlicPizzaButton;
        // ------------------------------------------------------------------------------

        for (int i = 0; i < pizzaButton.length; i++) {
            pizzaButton[i] = createButton(pizzaButton[i], this, pizzaButton[i].getText());
        }

        // Make gourmet pizza buttons yellow
        for (int i = 7; i < pizzaButton.length; i++) {
            pizzaButton[i].setBackground(Color.YELLOW);
        }

        // Text + button panels ---------------------------------------------------------------
        completeOrderButton = createButton(completeOrderButton, this, "Complete Order");
        pizzaTypesLabel = new JLabel(calculatePizzaTypes());

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBounds(50, 50, 100, 20);
        textPanel.add(pizzaAmountLabel);

        JPanel completeOrderPanel = new JPanel(new BorderLayout());
        completeOrderPanel.setBounds(50, 70, 150, 20);
        completeOrderPanel.add(completeOrderButton);

        // Address field + panel --------------------------------------------------------------
        addressField = new JTextArea(writeAddressText);
        addressPanel = new JPanel(new FlowLayout());
        addressPanel.setBounds(50, 40, 150, 20);
        addressPanel.add(addressField);

        final boolean[] showingAddressPlaceholder = { true };
        addressField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingAddressPlaceholder[0]) {
                    addressField.setText("");
                    showingAddressPlaceholder[0] = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (addressField.getText().isEmpty()) {
                    addressField.setText(writeAddressText);
                    showingAddressPlaceholder[0] = true;
                }
            }
        });

        // Name field + panel ----------------------------------------------------------------
        nameField = new JTextArea(writeNameText);
        namePanel = new JPanel(new FlowLayout());
        namePanel.setBounds(50, 70, 150, 20);
        namePanel.add(nameField);

        final boolean[] showingNamePlaceholder = { true };
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingNamePlaceholder[0]) {
                    nameField.setText("");
                    showingNamePlaceholder[0] = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().isEmpty()) {
                    nameField.setText(writeNameText);
                    showingNamePlaceholder[0] = true;
                }
            }
        });

        // Clear focus when clicking outside -------------------------------------------------
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (!(event instanceof MouseEvent)) return;
                MouseEvent me = (MouseEvent) event;
                if (me.getID() != MouseEvent.MOUSE_PRESSED) return;

                Component sourceComp = SwingUtilities.getDeepestComponentAt(
                        me.getComponent(), me.getX(), me.getY());

                if (sourceComp == null || 
                    (!SwingUtilities.isDescendingFrom(sourceComp, nameField) &&
                     !SwingUtilities.isDescendingFrom(sourceComp, addressField))) {
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK);

        // Pizza selection panel --------------------------------------------------------------
        panel = new JPanel();
        panel.setBounds(100, 100, 500, 400);
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(4, 3, 10, 10));
        for (JButton button : pizzaButton) {
            panel.add(button);
        }

        // Add panels to frame ---------------------------------------------------------------
        add(textPanel);
        add(completeOrderPanel);

        // Create a container panel for address and name stacked vertically
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(addressPanel);
        infoPanel.add(namePanel);

        add(infoPanel, BorderLayout.SOUTH);
        add(panel);
        add(background);

        setVisible(true);
        SwingUtilities.invokeLater(() -> this.requestFocusInWindow());
    }

    private String calculatePizzaTypes() {
        String allPizzas = "Pizzas: ";
        for (int i = 0; i < pizzaAmount; i++) {
            allPizzas = allPizzas.concat(pizzas.get(i).getName()) + ", ";
        }
        return allPizzas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < pizzaButton.length; i++) {
            if (e.getSource() == pizzaButton[i]) {
                if (pizzaAmount < MAX_PIZZAS) {
                    System.out.println("you are ordering " + pizzaButton[i].getText());
                    pizzaAmount++;
                    pizzaAmountLabel.setText("Pizza Amount: " + pizzaAmount);
                    switch (i) {
                        case 0 -> pizzas.add(new Pizza("Pepperoni"));
                        case 1 -> pizzas.add(new Pizza("Cheese"));
                        case 2 -> pizzas.add(new Pizza("Hawaiian"));
                        case 3 -> pizzas.add(new Pizza("BBQ"));
                        case 4 -> pizzas.add(new Pizza("Fish"));
                        case 5 -> pizzas.add(new Pizza("Chocolate"));
                        case 6 -> pizzas.add(new Pizza("Egg"));
                        case 7 -> pizzas.add(new GourmetPizza("Peter"));
                        case 8 -> pizzas.add(new GourmetPizza("Pita"));
                        case 9 -> pizzas.add(new GourmetPizza("Broccoli"));
                        case 10 -> pizzas.add(new GourmetPizza("Sausage"));
                        case 11 -> pizzas.add(new Pizza("Garlic"));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have reached maximum pizza amount");
                }
            }
        }

        // Complete order button logic -------------------------------------------------------
        if (e.getSource() == completeOrderButton && pizzaAmount > 0
                && !nameField.getText().equals(writeNameDefaultText)
                && !addressField.getText().equals(writeAddressDefaultText)
                && !nameField.getText().isEmpty()
                && !addressField.getText().isEmpty()) {

            name = nameField.getText();
            address = addressField.getText();
            System.out.println(name + " | " + address);

            CompleteOrderGUI completeOrderGUI = new CompleteOrderGUI(
                    "Pizza Receipt", 700, 800, this.pizzas, this.pizzaAmount, name + " (" + address + ")");

        } else if (pizzaAmount == 0) {
            JOptionPane.showMessageDialog(null, "You must order at least 1 pizza to complete order");
        } else if (e.getSource() == completeOrderButton
                && (nameField.getText().equals(writeNameDefaultText)
                    || addressField.getText().equals(writeAddressDefaultText)
                    || nameField.getText().isEmpty()
                    || addressField.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Please write down a suitable name and address");
        }
    }
}
