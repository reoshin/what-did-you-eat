package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class FoodListTest {
    private Food food1;
    private Food food2;
    private Food food3;
    
    private FoodList testFoodList;

    @Before
    public void setUp() {
        food1 = new Food("Apple", 130);
        food2 = new Food("Yogurt", 200);
        food3 = new Food("Burger", 650);

        testFoodList = new FoodList();
    }

    @Test
    public void foodListConstructorTest() {
        ArrayList<Food> currentFoodList = testFoodList.getFoodList();
        assertTrue(currentFoodList.isEmpty());
    }

    @Test
    public void addFoodTest() {
        testFoodList.addFood(food1);
        ArrayList<Food> currentFoodList = testFoodList.getFoodList();
        assertEquals(currentFoodList.get(0), food1);

        // duplicate test
        testFoodList.addFood(food1);
        currentFoodList = testFoodList.getFoodList();
        assertEquals(food1, currentFoodList.get(0));
        assertEquals(1, testFoodList.getFoodListSize());

        // another food test
        testFoodList.addFood(food2);
        currentFoodList = testFoodList.getFoodList();
        assertEquals(food1, currentFoodList.get(0));
        assertEquals(food2, currentFoodList.get(1));
    }

    @Test
    public void eatFoodTest() {
        testFoodList.addFood(food1);
        testFoodList.addFood(food2);
        testFoodList.addFood(food3);

        assertEquals(130, testFoodList.eatFood("Apple"));
        
        // Eat same food one more time
        testFoodList.eatFood("Apple");
        assertEquals(2, food1.getTimeConsumed());

        assertEquals(650, testFoodList.eatFood("Burger"));
        assertEquals(1, food3.getTimeConsumed());
    }

    @Test
    public void findFoodTest() {
        // when there is nothing in a list
        assertNull(testFoodList.findFood("Apple"));
        testFoodList.addFood(food1);

        // find food1
        assertEquals(food1, testFoodList.findFood("Apple"));
    }
}
