package seedu.duke;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Loads exchange rates from a JSON file.
 */
public class ExchangeRateStorage {
    private final String filePath;

    public ExchangeRateStorage(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Double> loadRates() {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new RuntimeException("Exchange rates JSON file not found.");
        }

        try (FileReader reader = new FileReader(file)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Double>>() {}.getType();

            return gson.fromJson(reader, type);

        } catch (IOException e) {
            throw new RuntimeException("Error reading exchange rates JSON file.");
        }
    }
}
