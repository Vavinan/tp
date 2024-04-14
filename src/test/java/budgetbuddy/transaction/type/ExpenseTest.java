package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;
import budgetbuddy.transaction.type.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {
    private Expense expense;
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account(1, "Test Account", 0.0);
        expense = new Expense(1, "Test Account", "Test Expense", 100.0, "01-01-2023", account);
    }

    @Test
    void expenseDecreasesAccountBalance() {
        assertEquals(-100.0, account.getBalance());
    }

    @Test
    void expenseHasCorrectType() {
        assertEquals("Expense", expense.getTransactionType());
    }

    @Test
    void expenseWithNullDescriptionThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Expense(1, "Test Account", null, 100.0, "01-01-2023", account));
    }

    @Test
    void expenseWithEmptyDescriptionThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Expense(1, "Test Account", "", 100.0, "01-01-2023", account));
    }

    @Test
    void expenseWithNullAccountThrowsAssertionError() {
        assertThrows(AssertionError.class, () -> new Expense(1, "Test Account", "Test Expense", 100.0, "01-01-2023", null));
    }
}