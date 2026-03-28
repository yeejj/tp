package seedu.duke;

public class Posting {
    private final String accountName; // e.g., "Expenses:Food"
    private final double amount; // The raw number from the user

    public Posting(String accountName, double amount) {
        this.accountName = accountName;
        this.amount = amount;
    }

    /**
     * This replaces the logic that was in the Account class.
     * It looks at the prefix of the string to decide the math.
     */
    public double getInternalAmount() {
        String root = accountName.split(":")[0].toLowerCase();
        if (root.equals("assets") || root.equals("expenses")) {
            return amount; // Positive = Debit
        } else {
            return -amount; // Positive = Credit (mapped to negative for zero-sum)
        }
    }

    public String getAccountName() {
        return accountName;
    }

    public double getAmount() {
        return amount;
    }
}
