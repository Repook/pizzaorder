package com.cooper.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.cooper.gui.panel.BasicBackgroundPanel;
import com.cooper.pizza.GourmetPizza;
import com.cooper.pizza.Pizza;
import com.cooper.pizza.PizzaOrder;

public class MainMenuGUI extends GUI implements ActionListener{
    
    //private ImageIcon backgroundIcon = new ImageIcon("src/main/resources/assets/images/pizzaoven.jpeg");
    private ImageIcon backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/pizzaoven.jpeg"));

    public ImageIcon pizzaIcon = new ImageIcon(getClass().getClassLoader().getResource("assets/images/pizza.png"));

    PizzaOrder order = new PizzaOrder(null);
    public int pizzaAmount;
    public ArrayList<Pizza> pizzas = new ArrayList<Pizza>();

    //label for pizza amount
    JLabel pizzaAmountLabel = new JLabel("Pizza Amount: " + pizzaAmount);
    JLabel pizzaTypesLabel = new JLabel(calculatePizzaTypes());

    //complete order button
    private JButton completeOrderButton = new JButton();

    //main panel for pizza buttons, grid layout
    private JPanel panel;

    //pizza buttons
    //12 pizza types
    private JButton[] pizzaButton = new JButton[12];
    private JButton pepPizzaButton,cheesePizzaButton,hawiPizzaButton,
    bbqPizzaButton,fishPizzaButton,chocPizzaButton,eggPizzaButton,
    peterPizzaButton, pitaPizzaButton,brocPizzaButton,sausPizzaButton,
    garlicPizzaButton;

    private Pizza normalPizza = new Pizza(getName());
    private GourmetPizza gormetPizza = new GourmetPizza(getName());


    //should clean this, dunno how tho
    public MainMenuGUI(String title, int windowSizeX, int windowSizeY) {
        super(title, windowSizeX, windowSizeY);

        //close the operation when close window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BasicBackgroundPanel background = new BasicBackgroundPanel(backgroundIcon.getImage());

        Image pizzaImage = pizzaIcon.getImage();
        Image pizzaScaled = pizzaImage.getScaledInstance(50,30, java.awt.Image.SCALE_SMOOTH);
        pizzaIcon = new ImageIcon(pizzaScaled);
        //make sure to add custom images for the different pizza types
        // all different pizza button logic-----------------------------
        pepPizzaButton = new JButton("Pepperoni " + normalPizza.getPrice() + "$", pizzaIcon);
        cheesePizzaButton = new JButton("Cheese " + normalPizza.getPrice() + "$", pizzaIcon);
        hawiPizzaButton = new JButton("Hawaiian " + normalPizza.getPrice() + "$", pizzaIcon);
        bbqPizzaButton = new JButton("BBQ " + normalPizza.getPrice() + "$", pizzaIcon);
        fishPizzaButton = new JButton("Anchovies "+ normalPizza.getPrice() + "$" , pizzaIcon);
        chocPizzaButton = new JButton("Chocolate "+ normalPizza.getPrice() + "$", pizzaIcon);
        eggPizzaButton = new JButton("Egg "+ normalPizza.getPrice() + "$", pizzaIcon);
        //gormet pizzas
        peterPizzaButton = new JButton("Peter "+ gormetPizza.getPrice() + "$", pizzaIcon);
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
        //-----------------------------------------------

        for (int i = 0; i < pizzaButton.length; i++) {
            pizzaButton[i] = createButton(pizzaButton[i],this, pizzaButton[i].getText());
        }

        for (int i = 7; i < pizzaButton.length; i++) {
            pizzaButton[i].setBackground(Color.YELLOW);
        }

        // JPanel pizzaTypesPanel = new JPanel(new BorderLayout());
        // pizzaTypesPanel.setBounds(300, 70, 150, 20);
        // pizzaTypesPanel.add(pizzaTypesLabel);


        completeOrderButton = createButton(completeOrderButton, this, "Complete Order");
        pizzaTypesLabel = new JLabel(calculatePizzaTypes());
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBounds(50, 50, 100, 20);
        textPanel.add(pizzaAmountLabel);

        JPanel completeOrderPanel = new JPanel(new BorderLayout());
        completeOrderPanel.setBounds(50, 70, 150, 20);
        completeOrderPanel.add(completeOrderButton);

        panel = new JPanel();
        panel.setBounds(100,100,500,400);
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(4,3,10,10));
        panel.add(pizzaButton[0]);
        panel.add(pizzaButton[1]);
        panel.add(pizzaButton[2]);
        panel.add(pizzaButton[3]);
        panel.add(pizzaButton[4]);
        panel.add(pizzaButton[5]);
        panel.add(pizzaButton[6]);
        panel.add(pizzaButton[7]);
        panel.add(pizzaButton[8]);
        panel.add(pizzaButton[9]);
        panel.add(pizzaButton[10]);
        panel.add(pizzaButton[11]);

        
        add(textPanel);
        //add(pizzaTypesPanel);
        add(completeOrderPanel);
        
        add(panel);
        add(background);

        setVisible(true);
    }

    private String calculatePizzaTypes(){
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
                if(pizzaAmount != 5 && pizzaAmount < 5){
                    //pizzaTypesLabel.setText("Pizzas: " + calculatePizzaTypes());
                    System.out.println("you are ordering " + pizzaButton[i].getText());
                    pizzaAmount++;
                    pizzaAmountLabel.setText("Pizza Amount: " + pizzaAmount); 
                    System.out.println(pizzaAmount);    
                } else {
                    JOptionPane.showMessageDialog(null,"You have reached maximum pizza amount");
                }

                switch (i) {
                    case 0:
                        pizzas.add(new Pizza("Pepperoni"));
                        break;
                    case 1:
                        pizzas.add(new Pizza("Cheese"));
                        break;
                    case 2:
                        pizzas.add(new Pizza("Hawaiian"));
                        break;
                    case 3:
                        pizzas.add(new Pizza("BBQ"));
                        break;
                    case 4:
                        pizzas.add(new Pizza("Fish"));
                        break;
                    case 5:
                        pizzas.add(new Pizza("Chocolate"));
                        break;
                    case 6:
                        pizzas.add(new Pizza("Egg"));
                        break;
                    case 7:
                        pizzas.add(new GourmetPizza("Peter"));
                        break;
                    case 8:
                        pizzas.add(new GourmetPizza("Pita"));
                        break;
                    case 9:
                        pizzas.add(new GourmetPizza("Broccoli"));
                        break;
                    case 10:
                        pizzas.add(new GourmetPizza("Sausage"));
                        break;
                    case 11:
                        pizzas.add(new Pizza("Garlic"));
                        break;                                                            
                    default:
                        break;
                }
            }
        }
        
        if (e.getSource() == completeOrderButton && pizzaAmount > 0) {
            CompleteOrderGUI completeOrderGUI = new CompleteOrderGUI("Pizza Receipt", 700, 800, this.pizzas,this.pizzaAmount);
        } else if(pizzaAmount == 0){
                    JOptionPane.showMessageDialog(null,"You must order atleast 1 pizza to complete order");
        }

    }
}
    
