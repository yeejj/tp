package seedu.duke;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests currency conversion behaviour.
 */
public class CurrencyConverterTest {

    @Test
    public void testConversionSuccess() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("SGD", 1.0);
        rates.put("USD", 0.74);
        rates.put("EUR", 0.68);

        CurrencyConverter converter = new CurrencyConverter(rates);

        double result = converter.convert(100, "USD", "SGD");

        assertTrue(result > 100);
    }

    @Test
    public void testInvalidCurrency() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("SGD", 1.0);

        CurrencyConverter converter = new CurrencyConverter(rates);

        assertThrows(IllegalArgumentException.class, () ->
                converter.convert(100, "USD", "SGD"));
    }
}
