package budgetbuddy.transaction;

import budgetbuddy.account.Account;

import budgetbuddy.account.AccountManager;


import budgetbuddy.categories.Category;
import budgetbuddy.exceptions.*;
import budgetbuddy.insights.Insight;
import budgetbuddy.parser.Parser;
import budgetbuddy.storage.DataStorage;
import budgetbuddy.transaction.type.Transaction;
import budgetbuddy.ui.UserInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a list of transactions and provides methods for managing them.
 */
public class TransactionList {

    public static final int DELETE_BEGIN_INDEX = 7;
    public static final int INDEX_OFFSET = 1;
    public static final int LOWER_BOUND = 0;
    public static final int EDIT_BEGIN_INDEX = 5;
    public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static final String ACCOUNT = "acc";
    public static final String ALL = "all";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String EDIT = "edit";
    public static final String LIST = "list";
    public static final String SEARCH = "search";
    private static final int DAYS_IN_WEEK = 7;
    private static final int DAYS_IN_MONTH = 30;
    private static final int DAYS_OFFSET = 1;

    private final ArrayList<Transaction> transactions;
    private final Parser parser;
    private final DataStorage dataStorage = new DataStorage();

    /**
     * Constructs a new TransactionList object with an empty list of transactions and a parser.
     */
    public TransactionList() {
        this.transactions = new ArrayList<>();
        this.parser = new Parser();
        LOGGER.log(Level.INFO, "TransactionList created with empty transactions and parser");

    }

    /**
     * Constructs a new TransactionList object with the specified list of transactions and a parser.
     *
     * @param transactions The list of transactions.
     */
    public TransactionList(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
        this.parser = new Parser();
        LOGGER.log(Level.INFO, "TransactionList created with transactions and parser");

    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void printTransactions() {
        UserInterface.printAllTransactions(transactions);
    }

    /**
     * Removes a transaction from the list based on the user input.
     *
     * @param input          The user input specifying the transaction to be removed.
     * @param accountManager The account manager for retrieving account information.
     * @throws EmptyArgumentException If the input string is empty or does not contain the required parameter.
     * @throws NumberFormatException  If the index parsed from the input string is not a valid integer.
     * @throws InvalidIndexException  If the index is out of bounds or invalid.
     */
    public void removeTransaction(String input, AccountManager accountManager) throws EmptyArgumentException,
            NumberFormatException, InvalidIndexException {
        if (input.trim().length() < DELETE_BEGIN_INDEX) {
            LOGGER.log(Level.WARNING, "Index id is not given for delete command");
            throw new EmptyArgumentException("delete index");
        }
        String data = input.substring(DELETE_BEGIN_INDEX).trim();
        int id = Integer.parseInt(data) - INDEX_OFFSET;
        int size = transactions.size();
        if (id >= LOWER_BOUND && id < size) {
            String itemRemoved = transactions.get(id).toString();
            Account account = accountManager.getAccountByAccountNumber(transactions.get(id).getAccountNumber());
            assert itemRemoved != null : "String representation of item to remove is null";
            account.setBalance(account.getBalance() - transactions.get(id).getAmount());
            transactions.remove(id);
            assert transactions.size() == size - 1 : "Transaction list size did not decrease after removal";
            UserInterface.printDeleteMessage(itemRemoved, account.getBalance());
            LOGGER.log(Level.INFO, "Transaction is removed successfully");

        } else {
            LOGGER.log(Level.WARNING, "Invalid index for delete command");
            throw new InvalidIndexException(String.valueOf(size));
        }
    }

    public static boolean isNotInteger(String data) {
        try {
            Integer.parseInt(data);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isNotDouble(String data) {
        try {
            Double.parseDouble(data);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    void addTransaction(Transaction t) {
        transactions.add(t);
    }

    /**
     * Processes a transaction based on the user input and adds it to the transaction list.
     *
     * @param input   The user input specifying the transaction details.
     * @param account The account associated with the transaction.
     * @throws InvalidTransactionTypeException If the transaction type is invalid.
     * @throws InvalidAddTransactionSyntax     If the syntax for adding a transaction is invalid.
     * @throws EmptyArgumentException          If the input string is empty or missing required arguments.
     * @throws InvalidCategoryException        If the category specified in the transaction is invalid.
     */
    public void processTransaction(String input, Account account)
            throws InvalidTransactionTypeException, InvalidAddTransactionSyntax, EmptyArgumentException,
            InvalidCategoryException {
        // Check for syntax for add transaction
        String[] arguments = {"/a/", "/t/", "/n/", "/$/", "/d/"};
        for (String argument : arguments) {
            if (!input.contains(argument)) {
                LOGGER.log(Level.WARNING, "Invalid add transaction syntax");
                throw new InvalidAddTransactionSyntax("Invalid add syntax.");
            }
        }

        Transaction t = parser.parseUserInputToTransaction(input, account);
        assert t != null : "Parsed transaction is null";
        addTransaction(t);
        assert transactions.get(transactions.size() - 1) != null : "Added transaction is null after adding to the list";
        String fetchData = String.valueOf(transactions.get(transactions.size() - 1));
        UserInterface.printAddMessage(fetchData, account.getBalance());
        LOGGER.log(Level.INFO, "Transaction added successfully");

    }

    /**
     * Saves the current list of transactions to a data storage.
     *
     * @throws IOException If an I/O error occurs while saving the transactions.
     */
    public void saveTransactionList() throws IOException {
        dataStorage.saveTransactions(transactions);
    }

    /**
     * Retrieves past transactions based on the specified duration.
     *
     * @param transactions The list of transactions to filter.
     * @param duration     The duration of past transactions to retrieve ("week" or "month").
     * @return An ArrayList containing past transactions based on the specified duration.
     */
    //@@author isaaceng7
    public static ArrayList<Transaction> getPastTransactions(ArrayList<Transaction> transactions, String duration) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = null;
        switch (duration) {
        case "week":
            startDate = today.minusDays(DAYS_IN_WEEK + DAYS_OFFSET);
            break;
        case "month":
            startDate = today.minusDays(DAYS_IN_MONTH + DAYS_OFFSET);
            break;
        default:
            break;
        }
        ArrayList<Transaction> pastTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().isAfter(startDate)) {
                pastTransactions.add(transaction);
            }
        }
        LOGGER.log(Level.INFO, "Past transactions loaded successfully");
        return pastTransactions;
    }

    /**
     * Retrieves transactions within a custom date range.
     *
     * @param transactions The list of transactions to filter.
     * @return An ArrayList containing transactions within the specified custom date range.
     */
    public static ArrayList<Transaction> getCustomDateTransactions(ArrayList<Transaction> transactions) {
        String start = UserInterface.getStartDate();
        String end = UserInterface.getEndDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(start, formatter).minusDays(DAYS_OFFSET);
        LocalDate endDate = LocalDate.parse(end, formatter).plusDays(DAYS_OFFSET);
        ArrayList<Transaction> customDateTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getDate().isAfter(startDate) && transaction.getDate().isBefore(endDate)) {
                customDateTransactions.add(transaction);
            }
        }
        LOGGER.log(Level.INFO, "Custom date transactions loaded successfully");

        return customDateTransactions;
    }

    /**
     * Retrieves transactions associated with a specific account number.
     *
     * @param transactions   The list of transactions to filter.
     * @param accountNumber  The account number for which transactions are to be retrieved.
     * @return An ArrayList containing transactions associated with the specified account number.
     */
    public static ArrayList<Transaction> getAccountTransactions(ArrayList<Transaction> transactions,
                                                                int accountNumber) {
        ArrayList<Transaction> accountTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccountNumber() == accountNumber) {
                accountTransactions.add(transaction);
            }
        }
        LOGGER.log(Level.INFO, "Transaction based on account loaded successfully");
        return accountTransactions;
    }

    /**
     * Retrieves transactions associated with a specific category.
     *
     * @param transactions The list of transactions to filter.
     * @param category     The category for which transactions are to be retrieved.
     * @return An ArrayList containing transactions associated with the specified category.
     */
    public static ArrayList<Transaction> getCategoryTransactions(ArrayList<Transaction> transactions,
                                                                 Category category) {
        ArrayList<Transaction> categoryTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getCategory() == category) {
                categoryTransactions.add(transaction);
            }
        }
        LOGGER.log(Level.INFO, "Transactions based on a category loaded successfully");

        return categoryTransactions;
    }

    /**
     * Processes the user-selected list option to list a specific set of transactions
     * and perform the corresponding action.
     *
     * @param accounts        The list of accounts.
     * @param accountManager  The account manager for retrieving account information.
     * @throws InvalidIndexException   If the selected option index is invalid.
     * @throws InvalidCategoryException If the selected category is invalid.
     */
    public void processList(ArrayList<Account> accounts, AccountManager accountManager) throws InvalidIndexException,
            InvalidCategoryException {
        UserInterface.printListOptions();
        String data = UserInterface.getListOption().trim();
        int option = Integer.parseInt(data);
        switch (option) {
        // 1 - ALL TRANSACTIONS
        case 1:
            printTransactions();
            break;
        // 2 - PAST WEEK TRANSACTIONS
        case 2:
            ArrayList<Transaction> pastWeekTransactions = getPastTransactions(transactions, "week");
            UserInterface.printPastTransactions(pastWeekTransactions, "week");
            break;
        // 3 - PAST MONTH TRANSACTIONS
        case 3:
            ArrayList<Transaction> pastMonthTransactions = getPastTransactions(transactions, "month");
            UserInterface.printPastTransactions(pastMonthTransactions, "month");
            break;
        // 4 - CUSTOM DATE TRANSACTIONS
        case 4:
            ArrayList<Transaction> customDateTransactions = getCustomDateTransactions(transactions);
            UserInterface.printCustomDateTransactions(customDateTransactions);
            break;
        // 5 - ACCOUNT TRANSACTIONS
        case 5:
            String accountData = UserInterface.getSelectedAccountNumber(accounts);
            int accountNumber = Integer.parseInt(accountData);
            Account account = accountManager.getAccountByAccountNumber(accountNumber);
            String accountName = account.getName();
            ArrayList<Transaction> accountTransactions = getAccountTransactions(transactions, accountNumber);
            UserInterface.printAccountTransactions(accountTransactions, accountName, accountNumber);
            break;
        // 6 - CATEGORY TRANSACTIONS
        case 6:
            UserInterface.listCategories();
            int input = UserInterface.getSelectedCategory();
            Category categorySelected = Category.fromNumber(input);
            String categoryName = categorySelected.getCategoryName();
            ArrayList<Transaction> categoryTransactions = getCategoryTransactions(transactions, categorySelected);
            UserInterface.printCategoryTransactions(categoryTransactions, categoryName);
            break;
        default:
            LOGGER.log(Level.WARNING, "Invalid index for 'list' command");
            throw new InvalidIndexException("6");
        }

    }

    /**
     * Processes the user input for editing a transaction and updates the transaction accordingly.
     *
     * @param input           The user input specifying the transaction index to be edited.
     * @param accountManager  The account manager for retrieving account information.
     * @throws EmptyArgumentException      If the input string is empty or missing required arguments.
     * @throws NumberFormatException       If the index parsed from the input string is not a valid integer.
     * @throws InvalidIndexException       If the index is out of bounds or invalid.
     * @throws InvalidEditTransactionData  If the data for editing the transaction is invalid.
     * @throws InvalidCategoryException    If the specified category is invalid.
     */
    //@@author Vavinan
    public void processEditTransaction(String input, AccountManager accountManager) throws EmptyArgumentException,
            NumberFormatException, InvalidIndexException, InvalidEditTransactionData, InvalidCategoryException, InvalidArgumentSyntaxException {
        if (input.trim().length() < EDIT_BEGIN_INDEX) {
            LOGGER.log(Level.WARNING, "Index id is missing for edit command");
            throw new EmptyArgumentException("edit index ");
        }
        String data = input.substring(EDIT_BEGIN_INDEX).trim();

        if (isNotInteger(data)) {
            LOGGER.log(Level.WARNING, "Given index id for 'edit' command is not an integer");
            throw new NumberFormatException(data);
        }
        int index = Integer.parseInt(data) - INDEX_OFFSET;
        if ((index >= LOWER_BOUND) && (index < transactions.size())) {
            Transaction transaction = transactions.get(index);
            Account account = accountManager.getAccountByAccountNumber(transaction.getAccountNumber());
            String newTransaction = UserInterface.getEditInformation(transaction.toString());
            Transaction t = parser.parseEditTransaction(newTransaction, account);
            transactions.set(index, t);
            UserInterface.printUpdatedTransaction(t);
            LOGGER.log(Level.INFO, "Transaction is edited successfully");

        } else {
            LOGGER.log(Level.WARNING, "Given index id for 'edit' command is not valid");
            throw new InvalidIndexException(String.valueOf(transactions.size()));
        }
    }

    /**
     * Provides help messages for user commands.
     *
     * @param input The user input specifying the command for which help is requested.
     */
    public void helpWithUserCommands(String input) {
        String helpCommand = parser.parseHelpCommand(input);
        switch (helpCommand.toLowerCase()) {
        case ALL:
            UserInterface.printAllCommands();
            break;
        case ADD:
            UserInterface.printAddHelp();
            break;
        case DELETE:
            UserInterface.printDeleteHelp();
            break;
        case EDIT:
            UserInterface.printEditHelp();
            break;
        case LIST:
            UserInterface.printListHelp();
            break;
        case ACCOUNT:
            UserInterface.printAccountHelp();
            break;

        case SEARCH:
            UserInterface.printSearchHelp();
            break;
        default:
            UserInterface.printUseAvailableHelp();
            break;
        }
    }
    //@@author

    /**
     * Displays insights based on transaction categories.
     */
    //@@author ShyamKrishna33
    public void displayInsights() {
        Insight.displayCategoryInsight(transactions);
    }

    public ArrayList<Transaction> removeTransactionsByAccountNumber(int accountNumber) {
        ArrayList<Transaction> transactionsToRemove = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAccountNumber() == accountNumber) {
                transactionsToRemove.add(transaction);
            }
        }
        transactions.removeAll(transactionsToRemove);
        LOGGER.log(Level.INFO, "Transactions were removed successfully from the specified account number");
        return transactionsToRemove;
    }

    /**
     * Searches transactions based on a keyword and prints search results.
     *
     * @param input The user input specifying the keyword to search for transactions.
     */
    public void searchTransactions(String input) {
        try {
            String keyword = input.split(" ")[1];
            ArrayList<Transaction> searchResults = new ArrayList<>();
            ArrayList<Integer> indices = new ArrayList<>();
            int index = 0;
            for (Transaction transaction : transactions) {
                if (transaction.getDescription().toLowerCase().contains(keyword.toLowerCase()) ||
                        String.valueOf(transaction.getAmount()).contains(keyword) ||
                        transaction.getCategory().getCategoryName().toLowerCase()
                                .contains(keyword.toLowerCase()) ||
                        transaction.getDate().toString().contains(keyword)) {
                    searchResults.add(transaction);
                    indices.add(index);
                }
                index++;
            }
            LOGGER.log(Level.INFO, "Transactions are filtered out for 'search' command");
            UserInterface.printSearchResults(searchResults, indices);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.WARNING, "Keyword is not provided for search command");
            UserInterface.printInvalidInput("Please enter a keyword to search for transactions.");
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Search command failed");
            UserInterface.printExceptionErrorMessage(e.getMessage());
        }
    }
}
