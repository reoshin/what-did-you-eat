package ui;

import java.util.Scanner;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

// represents application's main window.
public class WhatDidYouEatGUI extends JFrame{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 800;

	private static final String JSON_STORE = "./data/foodList.json";
    private User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructor sets up visual window and user.
    public WhatDidYouEatGUI() {
        // stub
    }

    // EFFECTS: initialize json writer and reader.
    // MODIFIES: this
    public void initializeFields() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: initialize JFrame window where WhatDidYouEat will operate, and populates the tools to be used
    public void initializeGraphics() {
        // stub
    }

    // MODIFIES: this
	// EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
    private void initializeInteraction() {
        // stub
    }

    // MODIFIES: thjis
    // EFFECTS: a helper method which declares and instaniates all options.
    private void createMenu() {
        // stub
    }
}


