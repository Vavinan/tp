package budgetbuddy.account;

/**
 * Represents an account in the budget buddy system.
 */
public class Account {
    private final int accountNumber;
    private String name;
    private double balance;

    /**
     * Creates an account with the given account number, and default name and balance.
     *
     * @param accountNumber the account number
     */
    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.name = "";
        this.balance = 0.00;
    }

    /**
     * Creates an account with the given account number, name and balance.
     *
     * @param accountNumber the account number
     * @param name          the name
     * @param balance       the balance
     */
    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the account to the given amount.
     *
     * @param balance the new balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns the account number.
     *
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns the name of the account.
     *
     * @return the name of the account
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the account to the given name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the account, including the account number, name, and balance.
     *
     * @return a string representation of the account
     */
    @Override
    public String toString() {
        return (" Account Number: " + getAccountNumber() + " | " +
                " Name: " + getName() + " | " +
                " Balance: " + getBalance());
    }
}
