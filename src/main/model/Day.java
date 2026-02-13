package model;

import java.util.ArrayList;

// represents nth day in a week with daily score and daily food log
public class Day {
    private ArrayList<Food> dailyFoodLog;
    private double dailyScore;
    private int day;

    // REQUIRES: 1 <= day <= 7
    // EFFECTS: construct day with given day and empty food list and initial dailyScore.
    public Day(int day) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: add given food into a food list of a day
    public void addFood(Food food) {
        // stub
    }

    public ArrayList<Food> getFoodList() { // stub
        return null;
    }

    public int getDay() { // stub
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: sets dailyScore as score given
    public void setDailyScore(double score) {
        // stub
    }
}
