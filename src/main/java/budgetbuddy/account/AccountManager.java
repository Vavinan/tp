package budgetbuddy.account;

import java.util.ArrayList;

public class AccountManager {
    public ArrayList<Account> accounts;
    private int accountCount = 0;

    public AccountManager() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(String accountName, double initialBalance) {
        accountCount ++;
        accounts.add(new Account(accountCount, accountName, initialBalance));
    }
}
