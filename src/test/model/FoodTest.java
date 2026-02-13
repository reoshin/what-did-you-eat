package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FoodTest {
    private Food food1;
    private Food food2;
    private Food food3;

    @Before
    public void setUp() {
        food1 = new Food("Apple", 130);
        food2 = new Food("Yogurt", 200);
        food3 = new Food("Burger", 650);
    }

    @Test
    public void foodConstructorTest() {
        assertEquals(130, food1.getCalories());
        assertEquals(200, food2.getCalories());
        assertEquals(650, food3.getCalories());

        assertEquals(0, food1.getTimeConsumed());
        assertEquals("Apple", food1.getName());
    }

    @Test
    public void eatFoodTest() {
        food1.eatFood();
        assertEquals(1, food1.getTimeConsumed());
    }

    @Test
    public void eatMultipleFoodTest() {
        food1.eatFood();
        assertEquals(1, food1.getTimeConsumed());

        food2.eatFood();
        assertEquals(1, food2.getTimeConsumed());
    }

    @Test
    public void eatMultipleFoodMultipleTimesTest() {
        food1.eatFood();
        food1.eatFood();
        food1.eatFood();
        food1.eatFood();

        assertEquals(4, food1.getTimeConsumed());

        food2.eatFood();
        food2.eatFood();

        assertEquals(2, food2.getTimeConsumed());
    }
}
