package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User testUser;

    @Before
    public void setUp() {
        testUser = new User(1200);
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

        // another add food
        testUser.addFood("French Fries", 250);
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
}
