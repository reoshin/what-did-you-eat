package model;

// Represents a food having name, the number of times eaten and calories (in kcal)

public class Food {
    private String foodName;
    private int calories;
    private int timeConsumed;

    // REQUIRES : foodName has a non-zero length
    //            calories >= 0
    // EFFECTS: construct Food object with given foodName, calories and
    //          set initial timeConsumed as 0, and add this food to FoodList.

    public Food(String foodName, int calories) { // stub
    }

    public int getCalories() { // stub
        return 0;
    }

    public String getName() { // stub
        return "";
    }
    
    public int getTimeConsumed() {
        // stub
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: increases timeConsumed by 1
    public void eatFood() {
        // stub
    }
}
