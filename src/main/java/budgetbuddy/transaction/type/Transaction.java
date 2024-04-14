package budgetbuddy.transaction.type;

import budgetbuddy.categories.Category;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {
    private final int accountNumber;
    private final String accountName;
    private final String description;
    private final double amount;
    private Category category;
    private final LocalDate date;

    public Transaction(int accountNumber, String accountName, String description, double amount,String date) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.description = description;
        this.amount = amount;
        this.date = parseDate(date);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    private LocalDate parseDate(String by) {
        return LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
  
    public Category getCategory() {
        return category;
    }

    public abstract String getTransactionType();

    @Override
    public String toString() {
        return  (" Account Number: " + getAccountNumber() + " | " +
                " Account Name: " + getAccountName() + " | " +
                " Transaction Type: " + getTransactionType() + " | " +
                " Description: " + getDescription() + " | " +
                " Date: " + getDate() + " | " +
                " Amount: " + getAmount() + " | " +
                " Category: " + getCategory().getCategoryName()) ;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
