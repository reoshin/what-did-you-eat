// Reference: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
package persistence;
import model.FoodList;
import org.json.JSONObject;


import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFouundException if destination file cannot
    // be opened for writing
    public void open() throws FileAlreadyExistsException {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of foodlist to file
    public void write(FoodList fl) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        // stub
    }
}
