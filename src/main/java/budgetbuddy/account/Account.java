package budgetbuddy.account;

public class Account {
    private final int id;
    private final String name;
    private double balance;

    public Account(int accountId) {
        this.id = accountId;
        this.name = "";
        this.balance = 0.00;
    }

    public Account(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  (" ID: " + getId() + " | " +
                " Name: " + getName() + " | " +
                " Balance: " + getBalance()) ;
    }
}
