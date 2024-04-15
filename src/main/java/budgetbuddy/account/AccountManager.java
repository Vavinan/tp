package budgetbuddy.account;

import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.exceptions.InvalidIndexException;
import budgetbuddy.parser.Parser;
import budgetbuddy.storage.DataStorage;
import budgetbuddy.transaction.TransactionList;
import budgetbuddy.transaction.type.Transaction;
import budgetbuddy.ui.UserInterface;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the accounts in the budget buddy system.
 */
public class AccountManager {
    public static final int INDEX_OFFSET = 1;
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final DataStorage dataStorage = new DataStorage();
    private final ArrayList<Account> accounts;
    private final ArrayList<Integer> existingAccountNumbers;


    /**
     * Creates an AccountManager with empty account and account number lists.
     */
    public AccountManager() {
        this.accounts = new ArrayList<>();
        this.existingAccountNumbers = new ArrayList<>();
        LOGGER.log(Level.INFO, "AccountManager created with empty account and account number lists");
    }

    /**
     * Creates an AccountManager with the given account and account number lists.
     *
     * @param accounts               the list of accounts
     * @param existingAccountNumbers the list of existing account numbers
     */
    public AccountManager(ArrayList<Account> accounts, ArrayList<Integer> existingAccountNumbers) {
        assert accounts != null : "Accounts list cannot be null";
        assert existingAccountNumbers != null : "Existing account numbers list cannot be null";
        this.accounts = accounts;
        this.existingAccountNumbers = existingAccountNumbers;
        LOGGER.log(Level.INFO, "AccountManager created with specified account and account number lists");
    }

    /**
     * Adds an account with the given name and initial balance.
     *
     * @param name           the name of the account
     * @param initialBalance the initial balance of the account
     */
    public void addAccount(String name, double initialBalance) {
        assert name != null : "Name cannot be null";
        int newAccountNumber = generateAccountNumber();
        accounts.add(new Account(newAccountNumber, name, initialBalance));
        existingAccountNumbers.add(newAccountNumber);
        LOGGER.log(Level.INFO, "Account added");
    }

    /**
     * Generates a unique four-digit account number.
     *
     * @return the generated account number
     */
    public int generateAccountNumber() {
        Random random = new Random();
        boolean noMatchFound = true;
        int fourDigitNumber;
        do {
            fourDigitNumber = 1000 + random.nextInt(9000);
            for (int accountNumber : existingAccountNumbers) {
                if (accountNumber == fourDigitNumber) {
                    noMatchFound = false;
                    LOGGER.log(Level.WARNING, "Account number already exists. Generating new account number.");
                    break;
                }
            }
        } while (!noMatchFound);

        LOGGER.log(Level.INFO, "Account number generated");
        return fourDigitNumber;
    }

    /**
     * Processes the addition of an account from the given input.
     *
     * @param input the input string
     * @throws InvalidArgumentSyntaxException if the input syntax is invalid
     * @throws NumberFormatException          if the input contains an invalid number
     * @throws EmptyArgumentException         if the input is empty
     */
    public void processAddAccount(String input)
            throws InvalidArgumentSyntaxException, NumberFormatException, EmptyArgumentException {
        assert input != null : "Input cannot be null";
        LOGGER.log(Level.INFO, "Processing add account command");
        String[] arguments = {"/n/", "/$/"};
        for (String argument : arguments) {
            if (!input.contains(argument)) {
                LOGGER.log(Level.WARNING, "Invalid add account syntax.");
                throw new InvalidArgumentSyntaxException("Invalid add account syntax.");
            }
        }
        String[] parsedData = Parser.parseAddAccount(input);
        addAccount(parsedData[0], Double.parseDouble(parsedData[1]));
        UserInterface.printAddAccountMessage(getAccount(accounts.size() - INDEX_OFFSET).toString());
        LOGGER.log(Level.INFO, "Account added successfully");
    }

    /**
     * Removes an account with the given input and updates the transaction list.
     *
     * @param input        the input string
     * @param transactions the transaction list
     * @throws NumberFormatException          if the input contains an invalid number
     * @throws InvalidArgumentSyntaxException if the input syntax is invalid
     * @throws EmptyArgumentException         if the input is empty
     * @throws InvalidIndexException          if the input contains an invalid index
     */
    public void removeAccount(String input, TransactionList transactions)
            throws NumberFormatException, InvalidArgumentSyntaxException, EmptyArgumentException,
            InvalidIndexException {
        assert input != null : "Input cannot be null";
        assert transactions != null : "Transactions cannot be null";
        LOGGER.log(Level.INFO, "Processing remove account command");
        int accountNumber = Parser.parseRemoveAccount(input);
        Account accountRemoved = getAccountByAccountNumber(accountNumber);
        if (accounts.size() == 1) {
            UserInterface.printCannotDeleteLastAccountMessage();
            LOGGER.log(Level.WARNING, "Cannot delete last account.");
            return;
        }
        accounts.remove(accountRemoved);
        existingAccountNumbers.remove(Integer.valueOf(accountNumber));
        ArrayList<Transaction> transactionsRemoved = transactions.removeTransactionsByAccountNumber(accountNumber);
        UserInterface.printDeleteAccountMessage(accountRemoved.toString(), transactionsRemoved);
        LOGGER.log(Level.INFO, "Account removed successfully");
    }

    /**
     * Returns the account with the given account ID.
     *
     * @param accountId the account ID
     * @return the account
     */
    public Account getAccount(int accountId) {
        assert accountId >= 0 : "Account ID cannot be negative";
        return accounts.get(accountId);
    }

    /**
     * Returns the account with the given account number.
     *
     * @param accountNumber the account number
     * @return the account
     * @throws IllegalArgumentException if the account is not found
     */
    public Account getAccountByAccountNumber(int accountNumber) {
        assert accountNumber > 0 : "Account number must be positive";
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        LOGGER.log(Level.WARNING, "Account not found.");
        throw new IllegalArgumentException("Account not found.");
    }

    /**
     * Returns the list of accounts.
     *
     * @return the list of accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * Processes the editing of an account from the given input.
     *
     * @param input the input string
     * @throws EmptyArgumentException   if the input is empty
     * @throws IllegalArgumentException if the input is invalid
     */
    public void processEditAccount(String input) throws EmptyArgumentException, IllegalArgumentException,
            InvalidArgumentSyntaxException {
        assert input != null : "Input cannot be null";
        LOGGER.log(Level.INFO, "Processing edit account command");
        int accountNumber = Parser.parseEditAccount(input);
        Account account = getAccountByAccountNumber(accountNumber);
        String newName = UserInterface.getNewAccountName(account.toString());
        account.setName(newName);
        UserInterface.printUpdatedAccount(account.toString());
        LOGGER.log(Level.INFO, "Account edited successfully");
    }

    /**
     * Saves the accounts to the data storage.
     */
    public void saveAccounts() {
        assert accounts != null : "Accounts list cannot be null";
        dataStorage.saveAccounts(accounts);
    }

    /**
     * Returns the list of existing account numbers.
     *
     * @return the list of existing account numbers
     */
    public ArrayList<Integer> getExistingAccountNumbers() {
        return existingAccountNumbers;
    }
}
