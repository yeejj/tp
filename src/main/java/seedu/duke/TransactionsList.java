package seedu.duke;

import java.util.ArrayList;

/**
 * Stores and manages a collection of Transaction objects, providing operations
 * to add, list, edit, delete, and clear.
 */
public class TransactionsList {
    private ArrayList<Transaction> transactions;

    public TransactionsList() {
        this.transactions = new ArrayList<>();
        assert this.transactions != null : "Transactions list initialization failed.";
    }

    public void addTransaction(Transaction transaction) {
        assert transaction != null : "Cannot add a null transaction.";
        transactions.add(transaction);
        assert transactions.contains(transaction) : "Transaction was not successfully added.";
    }

    public void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction t : transactions) {
            System.out.println(t.toString());
        }
    }

    public void deleteTransaction(int id) {
        int initialSize = transactions.size();
        boolean removed = transactions.removeIf(t -> t.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("Transaction ID not found.");
        }
        assert transactions.size() == initialSize - 1 : "Transaction list size did not decrease by exactly one.";
    }

    public void clearTransactions() {
        transactions.clear();
        assert transactions.isEmpty() : "Transactions list should be empty after clear.";
        System.out.println("All transactions have been cleared.");
    }

    public void editTransaction(int id, String dateStr, String desc, Double amount, String type, String currency) {
        for (Transaction t : transactions) {
            if (t.getId() == id) {
                t.update(dateStr, desc, amount, type, currency);
                return;
            }
        }
        throw new IllegalArgumentException("Transaction ID not found.");
    }
}
