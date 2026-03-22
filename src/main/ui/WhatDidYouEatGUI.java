package ui;

import java.util.Scanner;

import model.FoodList;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// represents application's main window.
public class WhatDidYouEatGUI extends JFrame{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 800;

	private static final String JSON_STORE = "./data/foodList.json";
    private User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton addButton;
    private JButton printButton;
    private JButton logButton;
    private JButton saveButton;
    private JButton loadButton;

    // Constructor sets up visual window and user.
    public WhatDidYouEatGUI() {
        super("What Did You Eat?");
        initializeFields();
        initializeGraphics();
        initializeInteraction();
    }

    // EFFECTS: initialize json writer and reader.
    // MODIFIES: this
    public void initializeFields() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: initialize JFrame window where WhatDidYouEat will operate, and populates the tools to be used
    public void initializeGraphics() {
        setTitle("What Did You Eat?");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        askForLoadFoodlist();
    }

    // MODIFIES: this
    // EFFECTS: asks user to load foodlist or not.
    private void askForLoadFoodlist() {
        Object[] options = {"Yes", "No"};

        int choice = JOptionPane.showOptionDialog(null, 
            "Would you like to load the data?", 
            "Load Data", 
            JOptionPane.YES_NO_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
             null, 
             options, 
             options[0]);
        if (choice == JOptionPane.YES_OPTION) {
            loadFoodList();
        } else {
            JOptionPane.showMessageDialog(null, "Starting without loading data.");
        }

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadFoodList() {
        try {
            FoodList fl = jsonReader.read();
            user.loadFoodList(fl);
            JOptionPane.showMessageDialog(null, "Loaded your food list from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
	// EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    private void initializeInteraction() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: a helper method which declares and instaniates all options.
    private void createMenu() {
        
    }
}


