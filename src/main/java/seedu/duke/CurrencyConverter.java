package seedu.duke;

import java.util.Map;

/**
 * Handles currency conversion using exchange rates.
 */
public class CurrencyConverter {
    private final Map<String, Double> rates;

    public CurrencyConverter(Map<String, Double> rates) {
        this.rates = rates;
    }

    public double convert(double amount, String from, String to) {
        if (!rates.containsKey(from) || !rates.containsKey(to)) {
            throw new IllegalArgumentException("Unsupported currency.");
        }

        double fromRate = rates.get(from);
        double toRate = rates.get(to);

        double amountInSGD = amount / fromRate;
        return amountInSGD * toRate;
    }
}
