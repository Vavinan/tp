package budgetbuddy.account;

public class Account {
    private int accountId;
    private String accountName;
    private double balance;

    public Account(int accountId) {
        this.balance = 0.00;
        this.accountName = "";
        this.accountId = accountId;
    }

    public Account(int accountId, String accountName, double balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
