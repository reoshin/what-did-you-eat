package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Food;
import model.User;

public class WhatDidYouEat {
    private User user;
    private Scanner input;

    // EFFECTS: runs What Did You Eat application
    public WhatDidYouEat() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: handle user's input
    public void runApp() {
        boolean keepGoing = true;
        String letter = null;

        newUser();

        while (keepGoing) {
            displayMenu();
            letter = input.next();
            letter = letter.toLowerCase();

            if (letter.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(letter);
            }
        }
    }
    
    // EFFECTS: initialize new user with given daily calories goal
    // MODIFIES: user
    private void newUser() {
        input = new Scanner(System.in);
        System.out.println("Welcome to What Did You Eat!");
        System.out.println("Enter Your daily goal (in kcal)");
        int dailyGoal = input.nextInt();
        while (dailyGoal <= 0) {
            System.out.println("Calories can't be either zero or negative number. Please enter calories again.");
            dailyGoal = input.nextInt();
        }
        user = new User(dailyGoal);
    }
    

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nYour Daily Score: " + user.getDailyScore());
        System.out.println("\n" + user.getSuccessStatus());
        System.out.println("\ta -> add food");
        System.out.println("\tl -> load my food list");
        System.out.println("\te -> log food");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: call each method for user's input
    private void processCommand(String command) {
        if (command.equals("a")) {
            addFood();
        } else if (command.equals("l")) {
            loadFoodList();
        } else if (command.equals("e")) {
            logFood();
        } else {
            System.out.println("Error!");
        }
    }

    // MODIFIES: user
    // EFFECTS: add new food to user's food list
    private void addFood() {
        input.nextLine();
        System.out.println("Enter name of food");
        String foodName = input.nextLine();
    
        System.out.println("Enter calories of food (in kcal)");
        int foodCalories = input.nextInt();
        while (foodCalories <= 0) {
            System.out.println("Calories can't be either zero or negative number. Please enter calories again.");
            foodCalories = input.nextInt();
        }
        user.addFood(foodName, foodCalories);

        System.out.println("Successfully added " + foodName + " to my food list!");
    }

    // EFFECTS: display all foods in the list. If food list is empty, then print error message.
    private void loadFoodList() {
        ArrayList<Food> list = (user.getFoodList()).getFoodList();
        if (list.isEmpty()) {
            System.out.println("Your list is empty. Please start by adding new food!");
        } else {
            int index = 0;
            System.out.println("You currently have " + list.size() + " items" + " in food list");
            for (Food currentFood : list) {
                index++;
                System.out.println("\n" + index + ". " + currentFood.getName() 
                                    + " (" + currentFood.getCalories() + " kcal)");
            }
        }
    }

    // MODIFIES: user
    // EFFECTS: display all foods in the list, then find food in the given index number, then log food.
    private void logFood() {
        loadFoodList();
        if (! (user.getFoodList()).getFoodList().isEmpty()) {
            System.out.println("Select food number to log");
            int num = input.nextInt() - 1;
            while (num < 0 || num > user.getFoodListSize()) {
                System.out.println("Please select number from 1 ~ " + user.getFoodListSize());
                num = input.nextInt() - 1;
            }
            String foodName = (user.findNthFood(num)).getName();
            user.recordFood(foodName);
        }
    }
}
