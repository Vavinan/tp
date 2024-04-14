package budgetbuddy.transaction.type;

import budgetbuddy.account.Account;

public class Income extends Transaction {
    private static final String TRANSACTION_TYPE = "Income";

    //@@author vibes-863
    public Income(int accountNumber, String accountName, String description, double amount, String date,
                  Account account) {
        super(accountNumber, accountName, description, amount, date);
        assert this.getAmount() > 0: "Income amount must be positive";
        assert description != null && !description.isEmpty() : "Description cannot be null or empty";
        assert date != null : "Date cannot be null";
        assert account != null : "Account cannot be null";

        account.setBalance(account.getBalance() + this.getAmount());
    }

    public Income(int accountNumber, String accountName, String description, double amount, String date) {
        super(accountNumber, accountName, description, amount, date);
    }

    @Override
    public String getTransactionType() {
        return TRANSACTION_TYPE;
    }
}
