package ui;

import model.Event;
import model.EventLog;
import model.Food;
import model.FoodList;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@ExcludeFromJacocoGeneratedReport

// represents application's main window.
public class WhatDidYouEatGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 500;
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
    private JScrollPane consumptionPanel;

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
        initializeQuitOptions();

    }

    // EFFECTS: set the quit option
    // MODIFIES: this
    public void initializeQuitOptions() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int input = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to quit?", 
                        "Exit Confirmation", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (input == JOptionPane.YES_OPTION) {
                    EventLog events = EventLog.getInstance();
                    for (Event event : events) {
                        System.out.println(event.getDescription());
                    }
                    System.exit(0);
                }
            }
        });
    }

    // EFFECTS: initialize json writer and reader.
    // MODIFIES: this
    public void initializeFields() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: return get daily status string
    public String getDailyStatusText() {
        return "Daily Score: " + user.getDailyScore() 
                                + "  |  Daily Consumed Calories: " + user.getDailyCaloriesConsumed() 
                                + " / " + user.getDailyCaloriesGoal();
    }

    // MODIFIES: this
    // EFFECTS: initialize JFrame window where WhatDidYouEat will operate, and populates the tools to be used
    public void initializeGraphics() {
        dailyScoreLabel = new JLabel(getDailyStatusText());
        foodListLabel = new JLabel("You currently have " + (user.getFoodList()).getFoodList().size() 
                                    + " items" + " in food list");
        dailyEmojiLabel = new JLabel();

        topPanelInitialize();
        menuPanelInitialize();
        listPanelInitialize();
        
        add(topPanel, BorderLayout.NORTH);
        add(consumptionPanel);
        add(menuPanel, BorderLayout.SOUTH);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initialize list panel
    public void listPanelInitialize() {
        consumptionPanel = new JScrollPane();
        JList<String> consumptionJList = displayFoodList(3);
        consumptionPanel.setViewportView(consumptionJList);

        JLabel title = new JLabel("My Day: What Did I Eat Today?");
        consumptionPanel.setColumnHeaderView(title);
    }

    // MODIFIES: this
    // EFFECTS: update list panel after user's food consumption
    public void updateListPanel() {
        JList<String> consumptionJList = displayFoodList(3);
        consumptionPanel.setViewportView(consumptionJList);
        consumptionPanel.revalidate();
        consumptionPanel.repaint();
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
                    this,
                    new Object[] {"Enter your daily goal (in Kcal):", spinner},
                    "Welcome To What Did You Eat",
                    JOptionPane.DEFAULT_OPTION
                    );
        int dailyGoal = (int) spinner.getValue();
        user = new User(dailyGoal);
        EventLog.getInstance().logEvent(new Event("Daily calories goal has been set to " + dailyGoal + " calories"));
        loadFoodListOption();
    }

    // MODIFIES: this
    // EFFECTS: asks user to load foodlist or not.
    public void loadFoodListOption() {
        Object[] options = {"Yes", "No"};

        int choice = JOptionPane.showOptionDialog(null, 
                    "Would you like to load the data?", 
                    "Data Reader", 
                    JOptionPane.YES_NO_CANCEL_OPTION, 
                    JOptionPane.QUESTION_MESSAGE,
                    null, 
                    options, 
                    options[0]);
        if (choice == JOptionPane.YES_OPTION) {
            EventLog.getInstance().logEvent(new Event("User choose to load data."));
            loadFoodList();
        } else {
            JOptionPane.showMessageDialog(null, "Starting without loading data.");
            EventLog.getInstance().logEvent(new Event("User choose not to load data."));
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
            EventLog.getInstance().logEvent(new Event("Loaded your food list from " + JSON_STORE));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE);
            EventLog.getInstance().logEvent(new Event("Error: failed to load data from file " + JSON_STORE));
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
        JTextField foodNameField = new JTextField();
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(500, 1, 5000, 50));
        JPanel addFoodPanel = new JPanel(new GridLayout(2, 2));

        addFoodPanel.add(new JLabel("Food name: "));
        addFoodPanel.add(foodNameField);
        addFoodPanel.add(new JLabel("Calories (in Kcal): "));
        addFoodPanel.add(spinner);

        int result = JOptionPane.showConfirmDialog(this, addFoodPanel, "Add Food",
                    JOptionPane.OK_CANCEL_OPTION);
        
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String foodName = foodNameField.getText();
        int foodCalories = (int) spinner.getValue();
        if (foodName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a food name.");
            return;
        }   

        user.addFood(foodName, foodCalories);

        JOptionPane.showMessageDialog(this, "Successfully added " + foodName + " to my food list!");
        EventLog.getInstance().logEvent(new Event(foodName + " has been added to my food list."));
        foodListLabel.setText("You currently have " + (user.getFoodList()).getFoodList().size() 
                                                    + " items" + " in food list");
    }

    // EFFECTS: If food list is empty, then print error message.
    //          If food list is not empty, creat String Array List and
    //          convert it into JList<String>.
    //          if mode == 1, then print the list and return null,
    //          if mode == 2, then return JList created, 
    //          if mode == 3, then return JList without food has never been consumed.
    public JList<String> displayFoodList(int mode) {
        ArrayList<Food> list = (user.getFoodList()).getFoodList();
        if (list.isEmpty() && ! (mode == 3)) {
            JOptionPane.showMessageDialog(this, "Your list is empty. Please start by adding new food!");
            return null;
        } else {
            String[] foodArray = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                Food f = list.get(i);
                if (mode == 3) {
                    if (f.getTimeConsumed() > 0) {
                        foodArray[i] = f.getName() + " (" + f.getCalories() + "kcal) " + f.getTimeConsumed() + " time";
                    }
                } else {
                    foodArray[i] = (i + 1) + ". " + f.getName() + " (" + f.getCalories() + " kcal)";
                }
            }
            JList<String> foodJList = new JList<>(foodArray);
            if (mode == 1) {
                JOptionPane.showMessageDialog(this, foodJList, "My Food List", JOptionPane.INFORMATION_MESSAGE);
                return null;
            }
            return foodJList;
        }
    }

    // MODIFIES: this
    // EFFECTS: display all foods in the list, then find food in the given index number, then log food.
    public void logFood() {
        ArrayList<Food> list = (user.getFoodList()).getFoodList();
        JList<String> foodJList = displayFoodList(2);
        if (! (foodJList == null)) {
            int result = JOptionPane.showConfirmDialog(this, foodJList,
                                                "Select a food to record",
                                                JOptionPane.OK_CANCEL_OPTION,
                                                JOptionPane.PLAIN_MESSAGE);

            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            int selectedIndex = foodJList.getSelectedIndex();

            if (selectedIndex < 0) {
                JOptionPane.showMessageDialog(this, "Please select a food");
                return;
            }

            Food selectedFood = list.get(selectedIndex);
            user.recordFood(selectedFood.getName());
            

            JOptionPane.showMessageDialog(this, selectedFood.getName() + " has been recorded for today!");
            EventLog.getInstance().logEvent(new Event(selectedFood.getName() + " has been added to my day."));
            

            dailyScoreLabel.setText(getDailyStatusText());
            updateBackground();
            updateListPanel();
        }
    }

    // EFFECTS: saves the workroom to file
    public void saveFoodList() {
        try {
            jsonWriter.open();
            jsonWriter.write(user.getFoodList());
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved your food list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: update background colour based on user's daily score
    // MODIFIES: this
    public void updateBackground() {
        if (user.getDailyScore() > 70) {
            emoji = new ImageIcon(HAPPY);
            topPanel.setBackground(Color.GREEN);
        } else if (user.getDailyScore() <= 25) {
            emoji = new ImageIcon(SAD);
            topPanel.setBackground(Color.RED);
        } else {
            emoji = new ImageIcon(NEUTRAL);
            topPanel.setBackground(Color.YELLOW);
        }
        emojiLabel.setIcon(emoji);
        topPanel.revalidate();
        topPanel.repaint();
    }
}