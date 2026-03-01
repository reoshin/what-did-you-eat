package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a food having name, the number of times eaten and calories (in kcal)
public class Food implements Writable{
    private String foodName;
    private int calories;
    private int timeConsumed;

    // REQUIRES : foodName has a non-zero length
    //            calories >= 0
    // EFFECTS: construct Food object with given foodName, calories and
    //          set initial timeConsumed as 0, and add this food to FoodList.

    public Food(String foodName, int calories) { 
        this.foodName = foodName;
        this.calories = calories;
        this.timeConsumed = 0;
    }

    public int getCalories() { // stub
        return calories;
    }

    public String getName() { // stub
        return foodName;
    }
    
    public int getTimeConsumed() {
        return timeConsumed;
    }

    // MODIFIES: this
    // EFFECTS: increases timeConsumed by 1
    public void eatFood() {
        timeConsumed++;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", foodName);
        json.put("calories", calories);
        return json;
    }
}
