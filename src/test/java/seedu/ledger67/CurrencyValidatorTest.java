package seedu.ledger67;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CurrencyValidatorTest {
    @Test
    public void init_checkApprovedCurrencies_exists() {
        // Verification that the validator initializes and recognizes the core currency
        assertNotNull(CurrencyValidator.validateAndGet("SGD"));
    }
    @Test
    public void validateAndGet_validCurrencies_returnsNormalizedString() {
        assertEquals("SGD", CurrencyValidator.validateAndGet("sgd"));
        assertEquals("USD", CurrencyValidator.validateAndGet("  USD  "));
        assertEquals("EUR", CurrencyValidator.validateAndGet("Eur"));
    }

    @Test
    public void validateAndGet_invalidCurrency_throwsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CurrencyValidator.validateAndGet("GBP");
        });
        assertTrue(exception.getMessage().contains("Currency must be one of"));
    }

    @Test
    public void validateAndGet_emptyString_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            CurrencyValidator.validateAndGet("");
        });
    }

    @Test
    public void validateAndGet_null_throwsAssertionError() {
        // Assertions must be enabled (-ea) for this to trigger
        try {
            CurrencyValidator.validateAndGet(null);
        } catch (AssertionError e) {
            assertEquals("Currency cannot be null.", e.getMessage());
        }
    }
}
