package seedu.duke;

/**
 * A struct-like object that holds and enforces the transaction type (only debit
 * or credit).
 */
public class TransactionType {
    public final String value;

    public TransactionType(String value) {
        assert value != null : "Transaction type cannot be null.";
        if (!value.equalsIgnoreCase("debit") && !value.equalsIgnoreCase("credit")) {
            throw new IllegalArgumentException("Type must be strictly 'debit' or 'credit'.");
        }
        this.value = value.toLowerCase();
    }
}
