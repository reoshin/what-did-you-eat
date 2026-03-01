// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Food;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFood(String foodName, int calories, Food food) {
        assertEquals(foodName, food.getName());
        assertEquals(calories, food.getCalories());
    }
}
