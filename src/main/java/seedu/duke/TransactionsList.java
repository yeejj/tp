package seedu.duke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

/**
 * Stores and manages all transactions in memory, and saves them using Storage.
 */
public class TransactionsList {
    private static final Logger logger = Logger.getLogger("TransactionsList");
    private final List<Transaction> transactions;
    private final Storage storage;

    private CurrencyConverter converter;
    private String displayCurrency = "SGD";
    private boolean autoConvertDisplay = false;

    public TransactionsList(Storage storage) {
        this.storage = storage;
        this.transactions = new ArrayList<>(storage.load());
    }

    public void setCurrencyConverter(CurrencyConverter converter) {
        this.converter = converter;
    }

    public void setDisplayCurrency(String displayCurrency) {
        this.displayCurrency = CurrencyValidator.validateAndGet(displayCurrency);
    }

    public String getDisplayCurrency() {
        return displayCurrency;
    }

    public void setAutoConvertDisplay(boolean autoConvertDisplay) {
        this.autoConvertDisplay = autoConvertDisplay;
    }

    public void addTransaction(Transaction t) {
        logger.info("Adding transactions: " + t);
        if (t.isBalanced()) {
            transactions.add(t);
            save();
        } else {
            throw new IllegalArgumentException("Transaction is unbalanced!");
        }
    }

    public double getAccountBalance(String accountName) {
        double total = 0;
        for (Transaction t : transactions) {
            for (Posting p : t.getPostings()) {
                if (p.getAccountName().startsWith(accountName)) {
                    total += p.getAmount();
                }
            }
        }
        return total;
    }

    public void listTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        transactions.stream()
                .sorted(Comparator.comparing(Transaction::getDate))
                .forEach(this::printTransactionWithDisplayAmount);
    }

    private void printTransactionWithDisplayAmount(Transaction transaction) {
        if (converter == null || !autoConvertDisplay) {
            System.out.println(transaction);
            return;
        }

        List<Posting> postings = transaction.getPostings();

        for (Posting posting : postings) {
            double converted = converter.convert(
                    posting.getAmount(),
                    transaction.getCurrency(),
                    displayCurrency);
            if (transaction.getCurrency().equals(displayCurrency)) {
                System.out.println(transaction);
            } else {
                System.out.printf(
                        "%s | Display: %.2f %s%n",
                        transaction,
                        converted,
                        displayCurrency);
            }
        }
    }

    public void deleteTransaction(int id) {
        logger.info("Deleting transaction with ID: " + id);
        Transaction transaction = findById(id);
        transactions.remove(transaction);
        save();
    }

    public void clearTransactions() {
        logger.info("Clearing all transactions.");
        transactions.clear();
        save();
        System.out.println("All transactions have been cleared.");
    }

    public void editTransaction(int id, String date, String desc, List<String> postingStrings, String currency) {
        logger.info("Editing transactions with ID: " + id);
        Transaction transaction = findById(id);
        transaction.update(date, desc, postingStrings, currency);
        save();
    }

    public Transaction getTransactionById(int id) {
        return findById(id);
    }

    private Transaction findById(int id) {
        return transactions.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Transaction ID not found."));
    }

    private void save() {
        storage.save(transactions);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
