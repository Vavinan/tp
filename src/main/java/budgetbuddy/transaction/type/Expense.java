package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;

/**
 * Represents an expense transaction in the budget buddy system.
 * An expense transaction decreases the balance of an account.
 */
public class Expense extends Transaction {
    private static final String TRANSACTION_TYPE = "Expense";

    /**
     * Creates an expense transaction with the given account number, account name, description, amount, date,
     * and account.
     * The amount is automatically negated to represent an expense.
     * The balance of the account is decreased by the amount of the expense.
     *
     * @param accountNumber the account number
     * @param accountName   the name of the account
     * @param description   the description of the expense
     * @param amount        the amount of the expense
     * @param date          the date of the expense
     * @param account       the account the expense is made from
     */
    public Expense(int accountNumber, String accountName, String description, double amount, String date,
                   Account account) {
        super(accountNumber, accountName, description, -amount, date);
        assert this.getAmount() < 0 : "Expense amount must be positive";
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert date != null : "Date cannot be null";
        assert account != null : "Account cannot be null";

        account.setBalance(account.getBalance() + this.getAmount());
    }

    /**
     * Creates an expense transaction with the given account number, account name, description, amount, and date.
     * The amount is automatically negated to represent an expense.
     *
     * @param accountNumber the account number
     * @param accountName   the name of the account
     * @param description   the description of the expense
     * @param amount        the amount of the expense
     * @param date          the date of the expense
     */
    public Expense(int accountNumber, String accountName, String description, double amount, String date) {
        super(accountNumber, accountName, description, -amount, date);
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
