// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
package persistence;

import model.Food;
import model.FoodList;
import model.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONObject;

// Represents a reader that reads foodlist from JSON data stoted in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        // stub
    }

    // EFFECTS: reads foodlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodList read() throws IOException { // stub
        return null; 
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException { // stub
        return null; 
    }

    // EFFECTS: parses foodlist from JSON object and returns it
    private FoodList parseFoodList(JSONObject jsonObject) { // stub
        return null;
    }

    // MODIFIES: fl
    // EFFECTS: parses foods from JSON object and adds them to foodlist
    private void addFoods(FoodList fl, JSONObject jsonObject) {
        // stub
    }

    // MODIFIES: fl
    // EFFECTS: parses food from JSON object and adds it to foodlist
    private void addFood(FoodList fl, JSONObject jsonObject) {
        // stub
    }
}
