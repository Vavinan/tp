package budgetbuddy.account;

import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.exceptions.InvalidIndexException;
import budgetbuddy.transaction.TransactionList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountManagerTest {

    @Test
    void addingAccountShouldIncreaseExistingAccountNumbersSize() {
        AccountManager accountManager = new AccountManager();
        accountManager.addAccount("Test Account", 1000.00);
        assertEquals(1, accountManager.getExistingAccountNumbers().size());
    }

    @Test
    void addingAccountShouldGenerateUniqueAccountNumber() {
        AccountManager accountManager = new AccountManager();
        accountManager.addAccount("Test Account", 1000.00);
        accountManager.addAccount("Test Account 2", 2000.00);
        assertNotEquals(accountManager.getAccount(0).getAccountNumber(), accountManager.getAccount(1).getAccountNumber());
    }

    @Test
    void removingAccountShouldDecreaseExistingAccountNumbersSize() throws InvalidIndexException, InvalidArgumentSyntaxException, EmptyArgumentException {
        AccountManager accountManager = new AccountManager();
        accountManager.addAccount("Test Account", 1000.00);
        accountManager.addAccount("Test Account2", 1000.00);
        accountManager.removeAccount("delete-acc " + String.valueOf(accountManager.getAccounts().get(1).getAccountNumber()), new TransactionList());
        assertEquals(1, accountManager.getExistingAccountNumbers().size());
    }

    @Test
    void getAccountByAccountNumberShouldReturnCorrectAccount() {
        AccountManager accountManager = new AccountManager();
        accountManager.addAccount("Test Account", 1000.00);
        int accountNumber = accountManager.getAccount(0).getAccountNumber();
        assertEquals("Test Account", accountManager.getAccountByAccountNumber(accountNumber).getName());
    }

    @Test
    void getAccountByInvalidAccountNumberShouldThrowException() {
        AccountManager accountManager = new AccountManager();
        accountManager.addAccount("Test Account", 1000.00);
        assertThrows(IllegalArgumentException.class, () -> accountManager.getAccountByAccountNumber(9999));
    }


}