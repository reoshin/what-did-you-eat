package model;

// represent User with daily calories goal (in kcal), daily calories consumed (in kcal),
// success status and daily score (out of 100)
public class User {
    private int dailyCaloriesGoal;
    private double dailyScore;
    private int dailyCaloriesConsumed;
    private static double INITIAL_DAILYSCORE = 50.0;

    // REQUIRES: dailyCaloriesGoal >= 0
    // EFFECTS: Constructs user with given dailyCaloriesGoal, initial dailyScore,
    //          and 0 kcal dailyCaloriesConsumed.
    public User(int dailyCaloriesGoal) {
        this.dailyCaloriesGoal = dailyCaloriesGoal;
        this.dailyScore = INITIAL_DAILYSCORE;
        this.dailyCaloriesConsumed = 0;
    }

    // EFFECTS: find food with foodName from FoodList and record as it has eaten
    //          and add the calories of that food into daily, update daily score and return true.
    //          If can't find such food, return false and do nothing.
    public Boolean eatFood(String foodName) {
        return false;
    }
    
    // REQUIRES: dailyCaloriesConsumed > 0
    // MODIFIES: this
    // EFFECTS: set the score as INITIAL_SCORE + (50 * (dailyCaloriesConsumed / dailyCaloriesGoal))
    //          if dailyCaloriesConsumed <= dailyCaloriesGoal,
    //          otherwise, set the score as INITIAL_SCORE - (50 * (dailyCaloriesGoal / dailyCaloriesConsumed))
    public void updateDailyScore() {
        // stub
    }

    // EFFECTS: return user's sucess status by determining following criteria
    //          return ":D" if 75 <= dailyScore <= 100
    //          return ":)" if 50 <= dailyScore < 75
    //          return ":/" if 25 < dailyScore < 50
    //          otherwise return ":("
    public String getSuccessStatus() {
        return "";
    }
}
