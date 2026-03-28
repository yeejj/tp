package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a financial transaction containing an auto-incremented ID, date,
 * description, amount, type, and validated currency.
 */
public class Transaction {
    private static int nextId = 1;

    private int id;
    private LocalDate date;
    private String description;
    private String currency;
    private final List<Posting> postings = new ArrayList<>();

    /**
     * Constructor used when loading transactions from storage.
     */
    private Transaction(int id, String dateStr, String description, List<String> postingStrings, String currencyStr) {
        // Centralized Validation
        if (dateStr == null || description == null || postingStrings == null || currencyStr == null) {
            throw new IllegalArgumentException("Missing required transaction details.");
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }

        this.id = id;
        this.date = parseDate(dateStr);
        this.description = description;
        this.currency = CurrencyValidator.validateAndGet(currencyStr);

        // Centralized Posting Parsing
        for (String pStr : postingStrings) {
            String[] parts = pStr.split("\\s+");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid posting format. Use: -p \"Account Amount\"");
            }
            try {
                double amount = Double.parseDouble(parts[1]);
                addPosting(parts[0], amount);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Amount must be a valid number: " + parts[1]);
            }
        }
    }

    public Transaction(String dateStr, String description, List<String> postingStrings, String currencyStr) {
        // Calls the Master Constructor above
        this(nextId++, dateStr, description, postingStrings, currencyStr);

        // Validation check for ID (post-increment)
        assert this.id > 0 : "Auto-incremented ID must be positive.";
    }

    public void addPosting(String account, double amount) {
        postings.add(new Posting(account, amount));
    }

    public boolean isBalanced() {
        double sum = 0;
        for (Posting p : postings) {
            sum += p.getInternalAmount();
            // Assets = Equity - Liabilities + (Income - Expenses)
            // The value for Liabilities and Expenses are multiplied by -1 inside the
            // .getInternalAmount logic
        }
        return Math.abs(sum) < 0.0001; // Avoid floating point precision issues
    }

    public List<Posting> getPostings() {
        return postings;
    }

    public static Transaction fromStorage(int id, String dateStr, String description,
            List<String> postingStrings, String currencyStr) {
        return new Transaction(id, dateStr, description, postingStrings, currencyStr);
    }

    public static void updateNextId(int nextIdValue) {
        if (nextIdValue > nextId) {
            nextId = nextIdValue;
        }
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

    public LocalDate getDate() {
        return date;
    }

    public String getDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public String getDescription() {
        return description;
    }

    public String getCurrency() {
        return currency;
    }

    public void update(String dateStr, String description, List<String> postingStrings, String currencyStr) {
        if (dateStr != null) {
            this.date = parseDate(dateStr);
        }
        if (description != null) {
            if (description.trim().isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty.");
            }
            this.description = description;
        }

        postings.clear();
        for (String pStr : postingStrings) {
            String[] parts = pStr.split("\\s+");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid posting format. Use: -p \"Account Amount\"");
            }
            try {
                double amount = Double.parseDouble(parts[1]);
                addPosting(parts[0], amount);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Amount must be a valid number: " + parts[1]);
            }
        }

        if (currencyStr != null) {
            this.currency = CurrencyValidator.validateAndGet(currencyStr);
        }
        assert this.description != null && !this.description.isEmpty() : "Description state is invalid after update.";
    }

    @Override
    public String toString() {
        assert date != null : "Date should not be null when formatting.";

        StringBuilder sb = new StringBuilder();

        // Header Line: ID, Date, and Description
        sb.append(String.format("ID: %d | Date: %s | Desc: %s | [%s]",
                id, getDateString(), description, currency));

        // Posting Lines: Indented for readability
        for (Posting p : postings) {
            sb.append("\n    "); // 4 spaces indentation
            // %-30s aligns the account name to the left with a width of 30
            // %10.2f aligns the number to the right
            sb.append(String.format("%-30s : %10.2f", p.getAccountName(), p.getAmount()));
        }

        return sb.toString();
    }
}
