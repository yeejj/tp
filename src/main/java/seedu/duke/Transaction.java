package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a financial transaction containing an auto-incremented ID, date,
 * description, amount, type (struct-like), and validated currency.
 */
public class Transaction {
    private static int nextId = 1;

    private int id;
    private LocalDate date;
    private String description;
    private double amount;
    private TransactionType type;
    private String currency;

    public Transaction(String dateStr, String description, Double amount, String typeStr, String currencyStr) {
        if (dateStr == null || description == null || amount == null || typeStr == null || currencyStr == null) {
            throw new IllegalArgumentException("Missing required transaction details.");
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }

        this.id = nextId++;
        assert this.id > 0 : "Auto-incremented ID must be positive.";

        this.date = parseDate(dateStr);
        this.description = description;
        this.amount = amount;
        this.type = new TransactionType(typeStr);
        this.currency = CurrencyValidator.validateAndGet(currencyStr);
    }

    private LocalDate parseDate(String dateStr) {
        assert dateStr != null : "Date string cannot be null.";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("DATE must be in DD/MM/YYYY format.");
        }
    }

    public int getId() {
        return id;
    }

    public void update(String dateStr, String description, Double amount, String typeStr, String currencyStr) {
        if (dateStr != null) {
            this.date = parseDate(dateStr);
        }
        if (description != null) {
            if (description.trim().isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty.");
            }
            this.description = description;
        }
        if (amount != null) {
            this.amount = amount;
        }
        if (typeStr != null) {
            this.type = new TransactionType(typeStr);
        }
        if (currencyStr != null) {
            this.currency = CurrencyValidator.validateAndGet(currencyStr);
        }
        assert this.description != null && !this.description.isEmpty() : "Description state is invalid after update.";
    }

    @Override
    public String toString() {
        assert date != null : "Date should not be null when formatting.";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("ID: %d | Date: %s | Desc: %s | Amount: %.2f | Type: %s | Currency: %s",
                id, date.format(formatter), description, amount, type.value, currency);
    }
}
