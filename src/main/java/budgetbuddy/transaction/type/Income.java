package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;

/**
 * Represents an income transaction in the budget buddy system.
 * An income transaction increases the balance of an account.
 */
public class Income extends Transaction {
    private static final String TRANSACTION_TYPE = "Income";

    //@@author vibes-863

    /**
     * Creates an income transaction with the given account number, account name, description, amount, date,
     * and account.
     * The balance of the account is increased by the amount of the income.
     *
     * @param accountNumber the account number
     * @param accountName   the name of the account
     * @param description   the description of the income
     * @param amount        the amount of the income
     * @param date          the date of the income
     * @param account       the account the income is made to
     */
    public Income(int accountNumber, String accountName, String description, double amount, String date,
                  Account account) {
        super(accountNumber, accountName, description, amount, date);
        assert this.getAmount() > 0 : "Income amount must be positive";
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert date != null : "Date cannot be null";
        assert account != null : "Account cannot be null";

        account.setBalance(account.getBalance() + this.getAmount());
    }

    /**
     * Creates an income transaction with the given account number, account name, description, amount, and date.
     *
     * @param accountNumber the account number
     * @param accountName   the name of the account
     * @param description   the description of the income
     * @param amount        the amount of the income
     * @param date          the date of the income
     */
    public Income(int accountNumber, String accountName, String description, double amount, String date) {
        super(accountNumber, accountName, description, amount, date);
    }

    /**
     * Returns the type of the transaction.
     *
     * @return the type of the transaction
     */
    @Override
    public String getTransactionType() {
        return TRANSACTION_TYPE;
    }
}
