package model;

import java.util.ArrayList;

// Represents a list of food added by user
public class FoodList {
    private ArrayList<Food> foodList;
    
    // EFFECTS: construct foodList with no food inside
    public FoodList() {
        foodList = new ArrayList<Food>();
    }

    // EFFECTS: add food in the list if food is not in a list already
    // MODIFIES: this
    public void addFood(Food food) { 
        if (! foodList.contains(food)) {
            foodList.add(food);
        }
    }

    // EFFECTS: find such food with the given foodName, and eats one time and returns calories consumed.
    // REQUIRES: there should be food with given name in FoodList
    // MODIFIES: Food
    public int eatFood(String foodName) {
        Food currentFood = findFood(foodName);
        currentFood.eatFood();
        return currentFood.getCalories();
    }

    // EFFECTS: find such food with given name, otherwise return null
    public Food findFood(String foodName) {
        for (Food currentFood : foodList) {
            if (foodName.equals(currentFood.getName())) {
                return currentFood;
            }
        }
        return null;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public Food getNthFood(int index) {
        return foodList.get(index);
    }

    public int getFoodListSize() {
        return foodList.size();
    }
}
