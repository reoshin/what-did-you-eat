// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Food;
import model.FoodList;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            FoodList fl = new FoodList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFoodList.json");
            writer.open();
            writer.write(fl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFoodList.json");
            fl = reader.read();
            assertEquals(0, fl.getFoodListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFoodList() {
        try {
            FoodList fl = new FoodList();
            fl.addFood(new Food("Burger", 550));
            fl.addFood(new Food("Cake", 750));
            fl.addFood(new Food("Burrito", 1100));
        
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFoodList.json");
            writer.open();
            writer.write(fl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFoodList.json");
            fl = reader.read();
            List<Food> foods = fl.getFoodList();
            assertEquals(3, foods.size());
            checkFood("Burger", 550, foods.get(0));
            checkFood("Cake", 750, foods.get(1));
            checkFood("Burrito", 1100, foods.get(2));
    
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
