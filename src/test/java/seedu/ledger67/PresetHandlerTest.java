package seedu.ledger67;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

public class PresetHandlerTest {
    @Test
    public void init_handlerProcessing_returnsValidList() {
        // Ensures the static logic is initialized and processes a basic valid request
        List<Posting> postings = PresetHandler.generatePostings("INCOME 100");
        assertNotNull(postings);
        assertFalse(postings.isEmpty());
    }
    @Test
    public void generatePostings_dailyExpense_createsCorrectPostings() {
        List<Posting> result = PresetHandler.generatePostings("DAILYEXPENSE 50.00");
        assertEquals(2, result.size());

        // Expense part
        assertEquals("Expenses", result.get(0).getAccountName());
        assertEquals(50.00, result.get(0).getAmount());

        // Asset part
        assertEquals("Assets:Cash", result.get(1).getAccountName());
        assertEquals(-50.00, result.get(1).getAmount());
    }

    @Test
    public void generatePostings_income_createsCorrectPostings() {
        List<Posting> result = PresetHandler.generatePostings("INCOME 1000");
        assertEquals("Assets:Bank", result.get(0).getAccountName());
        assertEquals(1000.0, result.get(0).getAmount());
        assertEquals("Income", result.get(1).getAccountName());
        assertEquals(-1000.0, result.get(1).getAmount());
    }

    @Test
    public void generatePostings_invalidAmount_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PresetHandler.generatePostings("INCOME abc");
        });
    }

    @Test
    public void generatePostings_unknownType_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PresetHandler.generatePostings("STOLEN_MONEY 100");
        });
    }

    @Test
    public void generatePostings_malformedInput_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PresetHandler.generatePostings("DAILYEXPENSE"); // Missing amount
        });
    }
}
