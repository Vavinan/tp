package budgetbuddy.transaction.type;

import budgetbuddy.categories.Category;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a transaction involving a financial account.
 * This is an abstract class and must be subclassed to provide specific transaction types.
 */
public abstract class Transaction {
    // The account number associated with the transaction
    private final int accountNumber;

    // The name of the account associated with the transaction
    private final String accountName;

    // A brief description of the transaction
    private final String description;

    // The amount of money involved in the transaction
    private final double amount;

    // The category of the transaction
    private Category category;

    // The date of the transaction
    private final LocalDate date;

    /**
     * Constructs a new Transaction object with the specified parameters.
     *
     * @param accountNumber The account number associated with the transaction.
     * @param accountName   The name of the account associated with the transaction.
     * @param description   A brief description of the transaction.
     * @param amount        The amount of money involved in the transaction.
     * @param date          The date of the transaction in the format "dd-mm-yyyy".
     */
    public Transaction(int accountNumber, String accountName, String description, double amount, String date) {
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

    /**
     * Parses the date string into a LocalDate object.
     *
     * @param by The date string in the format "dd-MM-yyyy".
     * @return The LocalDate object representing the date.
     */
    private LocalDate parseDate(String by) {
        return LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public Category getCategory() {
        return category;
    }

    public abstract String getTransactionType();

    /**
     * Returns a string representation of the transaction.
     */
    @Override
    public String toString() {
        return (" Account Number: " + getAccountNumber() + " | " +
                " Account Name: " + getAccountName() + " | " +
                " Transaction Type: " + getTransactionType() + " | " +
                " Description: " + getDescription() + " | " +
                " Date: " + getDate() + " | " +
                " Amount: " + getAmount() + " | " +
                " Category: " + getCategory().getCategoryName());
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
