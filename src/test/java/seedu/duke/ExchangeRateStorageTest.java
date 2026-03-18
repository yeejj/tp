package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests loading exchange rates from a JSON file.
 */
public class ExchangeRateStorageTest {

    @Test
    public void testLoadRatesFromJson() throws Exception {
        String testFile = "data/test-rates.json";

        File file = new File(testFile);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        FileWriter writer = new FileWriter(testFile);
        writer.write("{\"SGD\":1.0,\"USD\":0.74,\"EUR\":0.68}");
        writer.close();

        ExchangeRateStorage storage = new ExchangeRateStorage(testFile);
        Map<String, Double> rates = storage.loadRates();

        assertEquals(1.0, rates.get("SGD"));
        assertEquals(0.74, rates.get("USD"));
        assertEquals(0.68, rates.get("EUR"));

        file.delete();
    }
}
