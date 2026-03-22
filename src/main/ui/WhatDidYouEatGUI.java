package ui;

import model.Food;
import model.FoodList;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// represents application's main window.
public class WhatDidYouEatGUI extends JFrame{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 380;

	private static final String JSON_STORE = "./data/foodList.json";

    private User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton addButton;
    private JButton printButton;
    private JButton logButton;
    private JButton saveButton;

    private JLabel dailyScoreLabel;
    private JLabel dailyEmojiLabel;
    private JLabel foodListLabel;

    private ImageIcon emoji;
    private JLabel emojiLabel;

    private JPanel topPanel;
    private JPanel menuPanel;

    private ImageIcon happy;
    private ImageIcon netural;
    private ImageIcon sad;

    // Image Source : https://emojiisland.com/pages/free-download-emoji-icons-png
    private static final String SAD = "./images/Sad.png";
    private static final String HAPPY = "./images/Happy.png";
    private static final String NEUTRAL = "./images/Neutral.png";



    // Constructor sets up visual window and user.
    public WhatDidYouEatGUI() {
        super("What Did You Eat?");
        initializeFields();
        initializeUser();
        initializeGraphics();
        mainInterface();
    }

    // EFFECTS: initialize json writer and reader.
    // MODIFIES: this
    public void initializeFields() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: return get daily status string
    public String getDailyStatusText() {
        return "Daily Score: " + user.getDailyScore() + 
                                    "  |  Daily Consumed Calories: " + user.getDailyCaloriesConsumed() 
                                    + " / " + user.getDailyCaloriesGoal();
    }

    // MODIFIES: this
    // EFFECTS: initialize JFrame window where WhatDidYouEat will operate, and populates the tools to be used
    public void initializeGraphics() {
        dailyScoreLabel = new JLabel(getDailyStatusText());
        foodListLabel = new JLabel("You currently have " + (user.getFoodList()).getFoodList().size() + " items" + " in food list");
        dailyEmojiLabel = new JLabel();

        topPanelInitialize();
        menuPanelInitialize();
        
        add(topPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.SOUTH);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize top panel
    public void topPanelInitialize() {
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        dailyScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dailyEmojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        emojiLabel = new JLabel();
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(dailyScoreLabel);
        topPanel.add(dailyEmojiLabel);
        topPanel.add(emojiLabel);

        updateBackground();
    }

    // MODIFIES: this
    // EFFECTS: initialize menu panel
    public void menuPanelInitialize() {
        printButton = new JButton("View My Food List");
        addButton = new JButton("Add Food to My Food List");
        saveButton = new JButton("Save My Food List");

        logButton = new JButton("My day: Record Meal");
        printButton.setForeground(Color.BLUE);
        addButton.setForeground(Color.BLUE);
        saveButton.setForeground(Color.RED);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        menuPanel.add(foodListLabel);
        menuPanel.add(printButton);
        menuPanel.add(addButton);
        menuPanel.add(saveButton);

        menuPanel.add(logButton);
    }

    // MODIFIES: this
    // EFFECTS: initialize User by asking their calorie goal
    public void initializeUser() {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1500, 1, 5000, 100));

        int result = JOptionPane.showConfirmDialog(
                    null,
                    spinner,
                    "Enter your daily goal (in Kcal)",
                    JOptionPane.OK_CANCEL_OPTION
                    );
        int dailyGoal = (int) spinner.getValue();
        user = new User(dailyGoal);
        loadFoodListOption();
    }

    // MODIFIES: this
    // EFFECTS: asks user to load foodlist or not.
    public void loadFoodListOption() {
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
    public void loadFoodList() {
        try {
            FoodList fl = jsonReader.read();
            user.loadFoodList(fl);
            JOptionPane.showMessageDialog(null, "Loaded your food list from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: a helper method which declares and instaniates all options.
    public void mainInterface() {
        addButton.addActionListener(e -> addFood());
        printButton.addActionListener(e -> displayFoodList(1));
        saveButton.addActionListener(e -> saveFoodList());
        logButton.addActionListener(e -> logFood());
    }

    // MODIFIES: this
    // EFFECTS: add new food to user's food list
    public void addFood() {
        
    }

    // EFFECTS: If food list is empty, then print error message.
    //          If food list is not empty, creat String Array List and
    //          convert it into JList<String>.
    //          if mode == 1, then print the list and return null,
    //          if mode == 2, then return JList created.
    public JList<String> displayFoodList(int mode) {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: display all foods in the list, then find food in the given index number, then log food.
    public void logFood() {
        
    }

    // EFFECTS: saves the workroom to file
    public void saveFoodList() {
        try {
            jsonWriter.open();
            jsonWriter.write(user.getFoodList());
            jsonWriter.close();
            JOptionPane.showMessageDialog(
                this,
                "Saved your food list to " + JSON_STORE
            );
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(
                this,
                "Saved your food list to " + JSON_STORE
            );
        }
    }


    // EFFECTS: update background colour based on user's daily score
    // MODIFIES: this
    public void updateBackground() {
        
    }
}


