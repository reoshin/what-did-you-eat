// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

package persistence;

import model.Food;
import model.FoodList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a reader that reads foodlist from JSON data stoted in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads foodlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodList read() throws IOException { // stub
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException { // stub
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses foodlist from JSON object and returns it
    private FoodList parseFoodList(JSONObject jsonObject) { // stub
        FoodList fl = new FoodList();
        addFoods(fl, jsonObject);
        return fl;
    }

    // MODIFIES: fl
    // EFFECTS: parses foods from JSON object and adds them to foodlist
    private void addFoods(FoodList fl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("food");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(fl, nextFood);
        }
    }

    // MODIFIES: fl
    // EFFECTS: parses food from JSON object and adds it to foodlist
    private void addFood(FoodList fl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int calories = jsonObject.getInt("calories");
        Food food = new Food(name, calories);
        fl.addFood(food);
    }
}
