package model;

import java.util.ArrayList;

// Represents a list of food added by user
public class FoodList {
    private ArrayList<Food> foodList;
    
    // EFFECTS: construct foodList with no food inside
    public FoodList() {
        foodList = new ArrayList<Food>();
    }

    // EFFECTS: add food in the list. If there is food with the same name already exist, 
    //          delete previous one and replace by new food
    // MODIFIES: this
    public void addFood(Food food) { 
        // stub
    }

    // EFFECTS: find such food with the given foodName, and eats one time and returns calories consumed.
    // MODIFIES: Food
    public int eatFood() { // stub
        return 0;
    }

    // EFFECTS: find such food with given name and returns true if food is found
    public Boolean findFood(String foodName) { // stub
        return false;
    }
}
