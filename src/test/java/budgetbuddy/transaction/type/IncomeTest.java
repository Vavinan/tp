package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;
import budgetbuddy.transaction.type.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IncomeTest {
    private Income income;
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(1, "Test Account", 0.0);
        income = new Income(1, "Test Account", "Test Income", 100.0, "01-01-2023", account);
    }

    @Test
    void incomeIncreasesAccountBalance() {
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void incomeHasCorrectType() {
        assertEquals("Income", income.getTransactionType());
    }

    @Test
    void incomeWithNegativeAmountThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Income(1, "Test Account", "Test Income", -100.0, "01-01-2023", account));
    }

    @Test
    void incomeWithNullDescriptionThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Income(1, "Test Account", null, 100.0, "01-01-2023", account));
    }

    @Test
    void incomeWithEmptyDescriptionThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Income(1, "Test Account", "", 100.0, "01-01-2023", account));
    }

    @Test
    void incomeWithNullAccountThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Income(1, "Test Account", "Test Income", 100.0, "01-01-2023", null));
    }
}