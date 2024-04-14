package budgetbuddy.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {

    @Test
    void creatingAccountWithValidParametersShouldSucceed() {
        Account account = new Account(1234, "Test Account", 1000.00);
        assertEquals(1234, account.getAccountNumber());
        assertEquals("Test Account", account.getName());
        assertEquals(1000.00, account.getBalance());
    }

    @Test
    void settingBalanceShouldUpdateBalance() {
        Account account = new Account(1234, "Test Account", 1000.00);
        account.setBalance(2000.00);
        assertEquals(2000.00, account.getBalance());
    }

    @Test
    void settingNameShouldUpdateName() {
        Account account = new Account(1234, "Test Account", 1000.00);
        account.setName("Updated Account");
        assertEquals("Updated Account", account.getName());
    }

    @Test
    void toStringShouldReturnCorrectFormat() {
        Account account = new Account(1234, "Test Account", 1000.00);
        String expectedString = " Account Number: 1234 |  Name: Test Account |  Balance: 1000.0";
        assertEquals(expectedString, account.toString());
    }
}
