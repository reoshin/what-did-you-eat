package model;

// represent User with daily calories goal (in kcal), daily calories consumed (in kcal),
// success status and daily score (out of 100)
public class User {
    private int dailyCaloriesGoal;
    private int dailyScore;
    private int dailyCaloriesConsumed;
    private static int INITIAL_DAILYSCORE = 50;
    private FoodList foodList;

    // REQUIRES: dailyCaloriesGoal >= 0
    // EFFECTS: Constructs user with given dailyCaloriesGoal, initial dailyScore,
    //          and 0 kcal dailyCaloriesConsumed.
    public User(int dailyCaloriesGoal) {
        this.dailyCaloriesGoal = dailyCaloriesGoal;
        this.dailyScore = INITIAL_DAILYSCORE;
        this.dailyCaloriesConsumed = 0;
        foodList = new FoodList();
    }

    // REQUIRES: calories > 0
    // MODIFIES: foodList
    // EFFECTS: add food to a foodlist with given foodname and calories.
    public void addFood(String foodName, int calories) {
        foodList.addFood(new Food(foodName, calories));
    }

    // REQUIRES: n > 0 and there should be at least one Food in foodList
    // EFFECTS: return nth Food in foodList
    public Food findNthFood(int n) {
        return foodList.getNthFood(n);
    }


    // REQUIRES: there should be a food with given name
    // EFFECTS: find food with foodName from FoodList and record as it has eaten
    //          and add the calories of that food into daily, update daily score and return true.
    //          If can't find such food, return false and do nothing.
    public void recordFood(String foodName) {
        this.dailyCaloriesConsumed += foodList.eatFood(foodName);
        updateDailyScore();
    }
    
    // REQUIRES: dailyCaloriesConsumed > 0
    // MODIFIES: this
    // EFFECTS: set the score as INITIAL_DAILYSCORE + 50 * (dailyCaloriesConsumed / dailyCaloriesGoal)
    //          if dailyCaloriesConsumed <= dailyCaloriesGoal,
    //          otherwise, set the score as INITIAL_SCORE - 50 * 
    //          (dailyCaloriesConsumed - dailyCaloriesGoal) / dailyCaloriesGoal)
    public void updateDailyScore() {
        if (dailyCaloriesConsumed <= dailyCaloriesGoal) {
            double score = INITIAL_DAILYSCORE + 50.0 * ((double) dailyCaloriesConsumed / dailyCaloriesGoal);
            dailyScore = (int) Math.round(score);
        } else {
            double score = INITIAL_DAILYSCORE - 50.0
                            * ((double) (dailyCaloriesConsumed - dailyCaloriesGoal) / dailyCaloriesGoal);
            dailyScore = (int) Math.round(score);
        }
    }

    // EFFECTS: return user's sucess status by determining following criteria
    //          return ":D" if 75 <= dailyScore <= 100
    //          return ":)" if 50 <= dailyScore < 75
    //          return ":/" if 25 < dailyScore < 50
    //          otherwise return ":("
    public String getSuccessStatus() {
        if (dailyScore >= 75) {
            return ":D";
        } else if (dailyScore >= 50) {
            return ":)";
        } else if (dailyScore > 25) {
            return ":/";
        }
        return ":(";
    }

    public int getDailyCaloriesGoal() {
        return dailyCaloriesGoal;
    }

    public int getDailyScore() {
        return dailyScore;
    }

    public int getDailyCaloriesConsumed() {
        return dailyCaloriesConsumed;
    }

    public FoodList getFoodList() {
        return foodList;
    }

    public int getFoodListSize() {
        return foodList.getFoodListSize();
    }
}
