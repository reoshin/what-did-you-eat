// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
package persistence;

import model.Food;
import model.FoodList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FoodList fl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            FoodList fl = reader.read();
            assertEquals(0, fl.getFoodListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFoodList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFoodList.json");
        try {
            FoodList fl = reader.read();
            List<Food> foods = fl.getFoodList();
            assertEquals(3, foods.size());
            checkFood("Burger", 550, foods.get(0));
            checkFood("Cake", 750, foods.get(1));
            checkFood("Burrito", 1100, foods.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}