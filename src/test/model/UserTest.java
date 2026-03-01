package model;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserTest {
    private User testUser;
    private FoodList foodList;

    @BeforeEach
    public void setUp() {
        testUser = new User(1200);
        foodList = new FoodList();
    }

    @Test
    public void userConstructorTest() {
        assertEquals(":)", testUser.getSuccessStatus());
        assertEquals(1200, testUser.getDailyCaloriesGoal());
        assertEquals(50, testUser.getDailyScore());
        assertEquals(0, testUser.getDailyCaloriesConsumed());
    }

    @Test
    public void addFoodTest() {
        testUser.addFood("Burrito", 750);
        FoodList currentFoodList = testUser.getFoodList();
        Food currentFood = currentFoodList.getNthFood(0);
        assertEquals("Burrito", currentFood.getName());

        Food currentFood2 = testUser.findNthFood(0);
        assertEquals("Burrito", currentFood2.getName());

        // another add food
        testUser.addFood("French Fries", 250);
        assertEquals(2, testUser.getFoodListSize());
        currentFoodList = testUser.getFoodList();
        currentFood = currentFoodList.getNthFood(1);
        assertEquals("French Fries", currentFood.getName());
    }

    @Test
    public void recordFoodTest() {
        testUser.addFood("Burrito", 750);
        testUser.addFood("French Fries", 250);

        testUser.recordFood("Burrito");
        assertEquals(750, testUser.getDailyCaloriesConsumed());
        assertEquals(81, testUser.getDailyScore());

        testUser.recordFood("French Fries");
        assertEquals(1000, testUser.getDailyCaloriesConsumed());
        assertEquals(92, testUser.getDailyScore());

        testUser.recordFood("French Fries");
        assertEquals(1250, testUser.getDailyCaloriesConsumed());
        assertEquals(48, testUser.getDailyScore());
    }

    @Test
    public void getSuccessStatusTest() {
        testUser.addFood("Burrito", 750);
        testUser.addFood("French Fries", 250);
        // 50
        assertEquals(":)", testUser.getSuccessStatus());
        
        // 82
        testUser.recordFood("Burrito");
        assertEquals(":D", testUser.getSuccessStatus());

        // 92
        testUser.recordFood("French Fries");
        assertEquals(":D", testUser.getSuccessStatus());

        // 48
        testUser.recordFood("French Fries");
        assertEquals(":/", testUser.getSuccessStatus());

        // 17
        testUser.recordFood("Burrito");
        assertEquals(":(", testUser.getSuccessStatus());

    }

    @Test
    public void loadFoodListTest() {
        foodList.addFood(new Food("Cookie", 230));
        testUser.loadFoodList(foodList);
        assertEquals("Cookie", ((testUser.getFoodList().getNthFood(0)).getName()));
    }
}
