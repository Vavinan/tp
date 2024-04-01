package budgetbuddy.account;

public class Account {
    private final int accountNumber;
    private final String name;
    private double balance;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.name = "";
        this.balance = 0.00;
    }

    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  (" Account Number: " + getAccountNumber() + " | " +
                " Name: " + getName() + " | " +
                " Balance: " + getBalance()) ;
    }
}
