package budgetbuddy.ui;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;
import budgetbuddy.transaction.type.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages Overall interaction between users and the program.
 * It handles all the input and output related tasks.
 */
public class UserInterface {

    public static final int START_INDEX = 0;

    public static final String HELP_BORDER = "```````````````````````````````````````````````````";

    private static final String LINE = "----------------------------------------------------";
    private static final String TABLE_BORDER = "____________________________________________________";
    private static final String ACCOUNT_TABLE_BORDER = "____________________________________________________";

    private static final String TAB_SPACE = "    ";
    public static Scanner in = new Scanner(System.in);

    /**
     * The function `listCategories` prints out the available categories along with
     * their corresponding
     * category names and numbers.
     */
    public static void listCategories() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Here are the available categories:");
        for (Category category : Category.values()) {
            System.out.println(TAB_SPACE + TAB_SPACE + category.getCategoryName() + ": " + category.getCategoryNum());
        }
        System.out.println(LINE);
    }

    /**
     * The function prompts the user to enter a number between 1 and 9 to categorize
     * a transaction and
     * returns the input as an integer.
     *
     * @return The method `getCategoryNum()` returns an integer value representing
     *         the category number
     *         input by the user.
     */
    public static int getCategoryNum() {
        System.out.println("In which category do you want to list this transaction? [Enter number between 1 and 9]");
        String input = in.nextLine();
        return Integer.parseInt(input);
    }

    /**
     * The function `printDeleteMessage` takes a transaction string and balance as
     * input, splits the
     * transaction string, and prints a message confirming the deletion of the
     * transaction along with
     * the updated account balance.
     *
     * @param transaction The `transaction` parameter is a string that contains
     *                    information about a
     *                    transaction, with different parts separated by the "|"
     *                    character.
     * @param balance     The `balance` parameter represents the updated account
     */
    public static void printDeleteMessage(String transaction, double balance) {
        String[] parts = transaction.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I have removed the following transaction from the history \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println("\n" + TAB_SPACE + "Your updated account balance is $" + balance);
        System.out.println(LINE);
    }

    //@@author Vavinan
    /**
     * The function `printAddMessage` takes a transaction string and balance amount,
     * formats and prints
     * the transaction details along with the updated balance.
     *
     * @param transaction The `transaction` parameter is a string that contains
     *                    information about a transaction.
     * @param balance     The `balance` parameter in the `printAddMessage` method
     *                    represents the updated
     *                    account balance after a transaction has been added.
     */
    public static void printAddMessage(String transaction, double balance) {
        String[] parts = transaction.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I have added the following transaction \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println("\n" + TAB_SPACE + "Your updated account balance is $" + balance);
        System.out.println(LINE);
    }

    /**
     * The function `printInvalidIndex` prints a message indicating an invalid index
     * along with the
     * valid index range.
     *
     * @param message The `message` parameter is a string that contains the error
     *                message to be
     *                displayed when an invalid index is provided.
     * @param id      The `id` parameter in the `printInvalidIndex` method
     *                represents the upper limit of the
     *                index range that is valid for the message being displayed.
     */
    public static void printInvalidIndex(String message, int id) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + message);
        System.out.println(TAB_SPACE + "Please use index within the range of: 1 to " + id);
        System.out.println(LINE);
    }

    /**
     * The function `printExceptionErrorMessage` prints an error message with a
     * given message and
     * provides guidance on checking command syntax.
     *
     * @param message The `message` parameter is a string that represents the error
     *                message associated
     *                with the exception that occurred.
     */
    public static void printExceptionErrorMessage(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "An error occurred with the message: " + message);
        System.out.println(TAB_SPACE + "Please check your command Syntax. \n" + TAB_SPACE +
                " If you need assistance use `help` command to know further about each command syntax.");
        System.out.println(LINE);
    }

    /**
     * The function `printInvalidInput` prints an error message surrounded by a line
     * to indicate
     * invalid input.
     *
     * @param message The `message` parameter is a String that contains the error
     *                message to be displayed.
     */
    public static void printInvalidInput(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Error occurred with message: " + message);
        System.out.println(LINE);
    }

    /**
     * The function `printInvalidAddSyntax` prints an error message along with a
     * reminder to ensure
     * correct argument entry.
     *
     * @param message The `message` parameter is a string that represents the error
     *                message to be displayed
     *                when the syntax for adding an item is invalid.
     */
    //@@author isaaceng7
    public static void printInvalidAddSyntax(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + message);
        System.out.println(TAB_SPACE + "Please ensure that you have entered all the arguments correctly.");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printTransactionTypeError` prints an error message for an
     * invalid transaction type
     * in Java.
     *
     * @param message The `message` parameter is a string that represents the
     *                specific error message related
     *                to an invalid transaction type.
     */
    //@@author isaaceng7
    public static void printTransactionTypeError(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid transaction type: " + message);
        System.out.println(TAB_SPACE + "Please enter Expense or Income only.");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printNumberFormatError` prints an error message related to
     * number format issues in
     * Java.
     *
     * @param message The `message` parameter is a string that represents the error
     *                message
     *                related to a number format issue.
     */
    //@@author isaaceng7
    public static void printNumberFormatError(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Error occurred with the input: " + message);
        System.out.println(TAB_SPACE + "Please enter a valid value.");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printEmptyArgumentError` prints an error message reminding the
     * user to include
     * proper argument in a command.
     *
     * @param message The `message` parameter is used to specify the type of
     *                argument that is
     *                missing in the command.
     */
    //@@author isaaceng7
    public static void printEmptyArgumentError(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Please include the " + message + "in the command.");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The `printAllTransactions` function prints a formatted list of transaction
     * details from an
     * ArrayList of Transaction objects.
     *
     * @param transactions The `printAllTransactions` method takes an `ArrayList` of
     *                     `Transaction`
     *                     objects as a parameter. It then iterates over each
     *                     `Transaction` object in the list and prints
     *                     out the details of each transaction in a formatted
     *                     table-like structure.
     */
    public static void printAllTransactions(ArrayList<Transaction> transactions) {
        int index = transactions.size();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Your Transaction history:");
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.printf(TAB_SPACE + TAB_SPACE + "%-5s %-10s %-20s %-20s %-30s %-15s %-15s %-15s%n", "ID", "Type",
                "Account Number", "Account Name", "Transaction", "Date", "Amount", "Category");
        for (int i = START_INDEX; i < index; i++) {
            Transaction transaction = transactions.get(i);
            int accountNumber = transaction.getAccountNumber();
            String accountName = transaction.getAccountName();
            String type = transaction.getTransactionType();
            String description = transaction.getDescription();
            LocalDate date = transaction.getDate();
            double amount = transaction.getAmount();
            String category = transaction.getCategory().getCategoryName();

            System.out.printf(TAB_SPACE + TAB_SPACE + "%-5d %-10s %-20d %-20.45s %-30.45s %-15s %-15.2f %-15s%n",
                    i + 1, type, accountNumber, accountName, description, date, amount, category);
        }
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.println(LINE);
    }

    /**
     * The function `printGoodBye` prints a farewell message with a reminder to keep
     * track of future
     * transactions.
     */
    public static void printGoodBye() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE +
                "Bye... Don't forget to keep track of your future transactions");
        System.out.println(LINE);
    }

    /**
     * The function `printNoCommandExists` prints a message indicating that no such
     * command exists.
     */
    public static void printNoCommandExists() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "No such command exists.");
        System.out.println(LINE);
    }

    //@@author Vavinan
    /**
     * The function `getEditInformation` prompts the user to edit transaction
     * details and returns the
     * updated information as a formatted string.
     *
     * @param string The `getEditInformation` method takes a `String` parameter
     *               named `string`, which
     *               represents the transaction information that needs to be edited.
     *               The method then prompts the user
     *               to enter new values for the transaction type, description,
     *               date, amount, and category.
     * @return The `getEditInformation` method is returning a formatted string that
     *         contains the edited
     *         transaction information.
     */
    public static String getEditInformation(String string) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Please edit the following transaction");
        System.out.println(string);
        System.out.println(LINE);
        System.out.print(TAB_SPACE + "Enter transaction type: ");
        String type = in.nextLine();
        System.out.print(TAB_SPACE + "Enter description: ");
        String description = in.nextLine();
        System.out.print(TAB_SPACE + "Enter transaction date: ");
        String date = in.nextLine();
        System.out.print(TAB_SPACE + "Enter transaction amount: ");
        String amount = in.nextLine();
        System.out.println(" ");
        for (Category category : Category.values()) {
            System.out.println(TAB_SPACE + TAB_SPACE + category.getCategoryName() + ": " + category.getCategoryNum());
        }
        System.out.println("In which category do you want to list this transaction? [Enter number between 1 and 9]");
        System.out.print(TAB_SPACE + "Enter Category: ");
        String category = in.next();
        in.nextLine();
        return type + " | " + description + " | " + date + " | " + amount + " | " + category;

    }
    //@@author

    /**
     * The function `printUpdatedTransaction` prints a success message along with
     * the updated
     * transaction details.
     *
     * @param t The parameter `t` is an object of the `Transaction` class. This
     *          method is designed to
     *          print a message indicating that the transaction was updated
     *          successfully, followed by
     *          the details of the updated transaction object `t`.
     */
    public static void printUpdatedTransaction(Transaction t) {
        System.out.println("\n" + TAB_SPACE + "Updated Successfully");
        System.out.println(t.toString());
        System.out.println(LINE);
    }

    /**
     * The function `printAllCommands` prints a list of available commands with
     * their syntax and
     * further help options.
     */
    public static void printAllCommands() {
        System.out.println(HELP_BORDER);
        System.out.printf("%-20s %-75s %-20s%n", "Command", "Syntax", "Further help");
        System.out.printf("%-20s %-75s %-20s%n", "Add", "add /t/[TYPE] /n/[DESCRIPTION] /d/[DD-MM-YYYY] " +
                "/$/[AMOUNT] /c/[CATEGORY]", "help add");
        System.out.printf("%-20s %-75s %-20s%n", "Edit", "edit [INDEX]", "help edit");
        System.out.printf("%-20s %-75s %-20s%n", "Delete", "delete [INDEX]", "help delete");
        System.out.printf("%-20s %-75s %-20s%n", "List", "list", "help list");
        System.out.println(HELP_BORDER);

    }

    /**
     * The function `printAddHelp` prints out helpful information and syntax
     * examples for adding
     * entries with different categories.
     */
    public static void printAddHelp() {
        System.out.println(HELP_BORDER);
        System.out.println("Method 1:");
        System.out.println(TAB_SPACE + "SYNTAX : add /t/[TYPE] /n/[DESCRIPTION] /d/[DD-MM-YYYY]" +
                "/$/[AMOUNT] \n");
        System.out.println("followed by choosing category from the given list:");

        for (Category category : Category.values()) {
            System.out.println(TAB_SPACE + TAB_SPACE + category.getCategoryName() + ": " + category.getCategoryNum());
        }

        System.out.println("\n Method 2");
        System.out.println(TAB_SPACE + " SYNTAX : add /t/[TYPE] /n/[DESCRIPTION] /d/[DD-MM-YYYY] " +
                "/$/[AMOUNT] /c/[CATEGORY] \n");
        System.out.println("Provide the category number along with the add command");
        System.out.println("\n ");
        System.out.println(HELP_BORDER);
    }

    /**
     * The function `printDeleteHelp` prints out syntax and guidelines for deleting
     * an item from a
     * transaction list.
     */
    public static void printDeleteHelp() {
        System.out.println(HELP_BORDER);
        System.out.println(TAB_SPACE + "SYNTAX : delete [INDEX] \n");
        System.out.println(TAB_SPACE + "Make sure the index is above 0 and below or equal to the size of " +
                "the transaction list");
        System.out.println(HELP_BORDER);
    }

    /**
     * The function `printEditHelp` provides guidance on how to use the "edit"
     * command in a Java
     * program for managing transactions.
     */
    public static void printEditHelp() {
        System.out.println(HELP_BORDER);
        System.out.println(TAB_SPACE + "SYNTAX : edit [INDEX] \n");
        System.out.println("Make sure the index is above 0 and below or equal to the size of the " +
                "transaction list");
        System.out.println("Then you will be asked to input the data for each parameters like");
        System.out.println("    Enter transaction type: [EXPENSE / INCOME] \n" +
                "    Enter description: [NEW DESCRIPTION] \n" +
                "    Enter transaction date: [NEW DATE] \n" +
                "    Enter transaction amount: [NEW AMOUNT] \n" + " \n");
        System.out.println("Then you will be given categories to choose from (like as add command). \n" +
                "    In which category do you want to list this transaction? [Enter number between 1 and " +
                "9]\n" +
                "    Enter Category: [NEW CATEGORY] ");
        System.out.println(HELP_BORDER);
    }

    /**
     * The function `printListHelp` prints out a help message with syntax and
     * available options for
     * viewing transactions.
     */
    public static void printListHelp() {
        System.out.println(HELP_BORDER);
        System.out.println(TAB_SPACE + "SYNTAX : list \n");
        System.out.println(TAB_SPACE + "This will give some available options to choose from:");
        System.out.println("What would you like to view?\n" +
                "    1. All Transactions\n" +
                "    2. Past Week Transactions\n" +
                "    3. Past Month Transactions\n" +
                "    4. Custom Date Transactions\n");
        System.out.println("From this you can choose 1-4 :");
        System.out.println("To print Custom date transaction: \n" +
                " 4\n" + "Start Date: [dd-MM-yyyy]\n" + "End Date: [dd-MM-yyyy] ");
        System.out.println(HELP_BORDER);
    }

    /**
     * The function `printUseAvailableHelp` prints out a list of available commands
     * for getting help
     * related to transactions and accounts.
     */
    public static void printUseAvailableHelp() {
        System.out.println(HELP_BORDER);
        System.out.println(TAB_SPACE + "Please use the following commands for help");
        System.out.println(TAB_SPACE + "To get idea about all commands use: help all");
        System.out.println(TAB_SPACE + "Add transaction: help add");
        System.out.println(TAB_SPACE + "Delete transaction: help delete");
        System.out.println(TAB_SPACE + "Search : help search");
        System.out.println(TAB_SPACE + "Edit transaction: help edit");
        System.out.println(TAB_SPACE + "List transaction: help list");
        System.out.println(TAB_SPACE + "Help related to accounts: help acc");
        System.out.println(HELP_BORDER);
    }

    //@@author isaaceng7
    /**
     * The function `printListOptions` displays a menu of transaction viewing
     * options.
     */
    public static void printListOptions() {
        System.out.println(LINE);
        System.out.println("What would you like to view?");
        System.out.println(TAB_SPACE + "1. All Transactions");
        System.out.println(TAB_SPACE + "2. Past Week Transactions");
        System.out.println(TAB_SPACE + "3. Past Month Transactions");
        System.out.println(TAB_SPACE + "4. Custom Date Transactions");
        System.out.println(TAB_SPACE + "5. Account Transactions");
        System.out.println(TAB_SPACE + "6. Category Transactions");
        System.out.println(LINE);
    }
    //@@author

    /**
     * The function `getListOption` reads a user input as a String and returns it.
     *
     * @return This method returns a String value that is read from the user input
     *         using the `Scanner` object `in`.
     */
    //@@author isaaceng7
    public static String getListOption() {
        String data = in.next();
        in.nextLine();
        return data;
    }

    //@@author

    /**
     * The function `getStartDate()` prompts the user for a start date input and
     * returns the entered
     * data as a String.
     *
     * @return The method `getStartDate()` returns a String value, which is the user
     *         input for the
     *         start date.
     */
    //@@author isaaceng7
    public static String getStartDate() {
        System.out.print("Start Date: ");
        String data = in.next();
        in.nextLine();
        return data;
    }

    //@@author

    /**
     * The function `getEndDate()` prompts the user for an end date input and
     * returns the entered data
     * as a String.
     *
     * @return The method `getEndDate()` is returning a String value that represents
     *         the end date
     *         entered by the user.
     */
    //@@author isaaceng7
    public static String getEndDate() {
        System.out.print("End Date: ");
        String data = in.next();
        in.nextLine();
        return data;
    }

    //@@author

    /**
     * The function `printAccountList` prints a list of account numbers and names in
     * a formatted table.
     *
     * @param accounts An ArrayList of Account objects. Each Account object
     *                 represents an account with
     *                 an account number and a name.
     */
    //@@author isaaceng7
    public static void printAccountList(ArrayList<Account> accounts) {
        int maxIndex = accounts.size();
        System.out.println(LINE);
        System.out.println("Please select an account to view...");
        System.out.println(TAB_SPACE + "Your accounts:");
        System.out.println(TAB_SPACE + ACCOUNT_TABLE_BORDER);
        System.out.printf(TAB_SPACE + TAB_SPACE + "%-20s %-30s", "Account Number",
                "Account Name");

        for (int i = START_INDEX; i < maxIndex; i++) {
            Account account = accounts.get(i);
            int accountNumber = account.getAccountNumber();
            String name = account.getName();

            System.out.printf("\n" +TAB_SPACE + TAB_SPACE + "%-20d %-30.45s", accountNumber, name);
        }
        System.out.println("\n" + TAB_SPACE + ACCOUNT_TABLE_BORDER);
        System.out.println(LINE);
    }
    //@@author

    /**
     * The function `getSelectedAccountNumber` prompts the user to select an account
     * number from a list
     * of accounts and returns the selected account number as a String.
     *
     * @param accounts An ArrayList of Account objects.
     * @return The method `getSelectedAccountNumber` returns a `String` value, which
     *         is the account
     *         number selected by the user from the list of accounts provided.
     */
    //@@author isaaceng7
    public static String getSelectedAccountNumber(ArrayList<Account> accounts) {
        printAccountList(accounts);
        System.out.print("Account number: ");
        String data = in.next();
        in.nextLine();
        return data;
    }

    //@@author

    /**
     * The function `getSelectedCategory` prompts the user to select a category
     * number and returns the
     * integer value entered by the user.
     *
     * @return The method `getSelectedCategory` is returning an integer value
     *         representing the selected
     *         category number entered by the user.
     */
    //@@author isaaceng7
    public static int getSelectedCategory() {
        System.out.print("Select a category number: ");
        String data = in.nextLine();
        return Integer.parseInt(data);
    }

    //@@author

    /**
     * The function `printPastTransactions` prints a formatted list of past
     * transactions based on a
     * specified duration.
     *
     * @param transactions transactions is an ArrayList that contains Transaction
     *                     objects.
     * @param duration     The `duration` parameter is a String that represents the
     *                     time period for
     *                     which the user want to display past transactions.
     */
    //@@author isaaceng7
    public static void printPastTransactions(ArrayList<Transaction> transactions, String duration) {
        int index = transactions.size();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Displaying transactions from the past " + duration + ":");
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.printf(TAB_SPACE + TAB_SPACE + "%-5s %-10s %-20s %-20s %-30s %-15s %-15s %-15s%n", "ID", "Type",
                "Account Number", "Account Name", "Transaction", "Date", "Amount", "Category");
        for (int i = START_INDEX; i < index; i++) {
            Transaction transaction = transactions.get(i);
            int accountNumber = transaction.getAccountNumber();
            String accountName = transaction.getAccountName();
            String type = transaction.getTransactionType();
            String description = transaction.getDescription();
            LocalDate date = transaction.getDate();
            double amount = transaction.getAmount();
            String category = transaction.getCategory().getCategoryName();

            System.out.printf(TAB_SPACE + TAB_SPACE + "%-5d %-10s %-20d %-20.45s %-30.45s %-15s %-15.2f %-15s%n",
                    i + 1, type, accountNumber, accountName, description, date, amount, category);
        }
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.println(LINE);

    }

    //@@author

    /**
     * The `printCustomDateTransactions` function prints a formatted list of
     * transactions within a
     * specified date range.
     *
     * @param transactions The `printCustomDateTransactions` method takes an
     *                     `ArrayList` of
     *                     `Transaction` objects as input. It then iterates over the
     *                     transactions in the list and prints
     *                     out specific details of each transaction in a formatted
     *                     table-like structure.
     */
    //@@author isaaceng7
    public static void printCustomDateTransactions(ArrayList<Transaction> transactions) {
        int index = transactions.size();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Displaying transactions of specified date range:");
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.printf(TAB_SPACE + TAB_SPACE + "%-5s %-10s %-20s %-20s %-30s %-15s %-15s %-15s%n", "ID", "Type",
                "Account Number", "Account Name", "Transaction", "Date", "Amount", "Category");
        for (int i = START_INDEX; i < index; i++) {
            Transaction transaction = transactions.get(i);
            int accountNumber = transaction.getAccountNumber();
            String accountName = transaction.getAccountName();
            String type = transaction.getTransactionType();
            String description = transaction.getDescription();
            LocalDate date = transaction.getDate();
            double amount = transaction.getAmount();
            String category = transaction.getCategory().getCategoryName();

            System.out.printf(TAB_SPACE + TAB_SPACE + "%-5d %-10s %-20d %-20.45s %-30.45s %-15s %-15.2f %-15s%n",
                    i + 1, type, accountNumber, accountName, description, date, amount, category);
        }
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printAccountTransactions` prints a formatted list of
     * transactions for a specific
     * account.
     *
     * @param transactions  An ArrayList of Transaction objects containing the
     *                      transaction details.
     * @param accountName   The `accountName` parameter in the
     *                      `printAccountTransactions` method is a
     *                      `String` that represents the name of the account for
     *                      which the user want to display transactions.
     * @param accountNumber The `accountNumber` parameter in the
     *                      `printAccountTransactions` method is
     *                      an integer value that represents the account number of
     *                      the account for which the user want to display
     *                      transactions.
     */
    //@@author isaaceng7
    public static void printAccountTransactions(ArrayList<Transaction> transactions, String accountName,
                                                int accountNumber) {
        int index = transactions.size();
        System.out.println(LINE);
        System.out
                .println(TAB_SPACE + "Displaying transactions of account: " + accountName + "(" + accountNumber + ")");
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.printf(TAB_SPACE + TAB_SPACE + "%-5s %-10s %-30s %-15s %-15s %-15s%n", "ID", "Type",
                "Transaction", "Date", "Amount", "Category");
        for (int i = START_INDEX; i < index; i++) {
            Transaction transaction = transactions.get(i);
            String type = transaction.getTransactionType();
            String description = transaction.getDescription();
            LocalDate date = transaction.getDate();
            double amount = transaction.getAmount();
            String category = transaction.getCategory().getCategoryName();

            System.out.printf(TAB_SPACE + TAB_SPACE + "%-5d %-10s %-30.45s %-15s %-15.2f %-15s%n",
                    i + 1, type, description, date, amount, category);
        }
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printCategoryTransactions` prints out a formatted list of
     * transactions belonging
     * to a specific category.
     *
     * @param transactions transactions is an ArrayList that contains Transaction
     *                     objects.
     * @param categoryName Category name is a string representing the name of a
     *                     category for which the
     *                     user want to display transactions.
     */
    //@@author isaaceng7
    public static void printCategoryTransactions(ArrayList<Transaction> transactions, String categoryName) {
        int index = transactions.size();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Displaying transactions of category: " + categoryName);
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.printf(TAB_SPACE + TAB_SPACE + "%-5s %-10s %-20s %-20s %-30s %-15s %-15s%n", "ID", "Type",
                "Account Number", "Account Name", "Transaction", "Date", "Amount");
        for (int i = START_INDEX; i < index; i++) {
            Transaction transaction = transactions.get(i);
            int accountNumber = transaction.getAccountNumber();
            String accountName = transaction.getAccountName();
            String type = transaction.getTransactionType();
            String description = transaction.getDescription();
            LocalDate date = transaction.getDate();
            double amount = transaction.getAmount();

            System.out.printf(TAB_SPACE + TAB_SPACE + "%-5d %-10s %-20d %-20.45s %-30.45s %-15s %-15.2f%n",
                    i + 1, type, accountNumber, accountName, description, date, amount);
        }
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printAddAccountMessage` takes a string representing an account,
     * splits it into
     * parts, and prints each part with proper formatting.
     *
     * @param account The parameter `account` is expected to contain account
     *                information separated
     *                by the pipe character `|`.
     */
    //@@author vibes-863
    public static void printAddAccountMessage(String account) {
        String[] parts = account.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I have added the following account \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printInvalidArgumentSyntax` prints an error message along with
     * a reminder to
     * ensure correct argument entry.
     *
     * @param message The `message` parameter is a string that contains the specific
     *                error message to
     *                be displayed when the argument syntax is invalid.
     */
    //@@author vibes-863
    public static void printInvalidArgumentSyntax(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + message);
        System.out.println(TAB_SPACE + "Please ensure that you have entered all the arguments correctly.");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printListOfAccounts` prints a formatted list of account details
     * including account
     * number, name, and balance.
     *
     * @param accounts An ArrayList of Account objects.
     */
    //@@author vibes-863
    public static void printListOfAccounts(ArrayList<Account> accounts) {
        int maxIndex = accounts.size();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Your accounts:");
        System.out.println(TAB_SPACE + ACCOUNT_TABLE_BORDER);
        System.out.printf(TAB_SPACE + TAB_SPACE + "%-20s %-30s %-15s ", "Account Number",
                "Account Name", "Balance");

        for (int i = START_INDEX; i < maxIndex; i++) {
            Account account = accounts.get(i);
            int accountNumber = account.getAccountNumber();
            String name = account.getName();
            double balance = account.getBalance();

            System.out.printf("\n" + TAB_SPACE + TAB_SPACE + "%-20d %-30.45s %-15.2f", accountNumber, name, balance);
        }
        System.out.println("\n" + TAB_SPACE + ACCOUNT_TABLE_BORDER);
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printDeleteAccountMessage` prints a message confirming the
     * deletion of an account
     * and lists the transactions related to that account that have also been
     * removed.
     *
     * @param account             The `account` parameter is a string that contains
     *                            account information.
     * @param transactionsRemoved The `transactionsRemoved` parameter is an
     *                            ArrayList of
     *                            Transaction objects that represent the
     *                            transactions related to the account
     */
    //@@author vibes-863
    public static void printDeleteAccountMessage(String account, ArrayList<Transaction> transactionsRemoved) {
        String[] parts = account.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I have removed the following account \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println("\n" + TAB_SPACE + "Transactions related to this account have also been removed:");
        System.out.println(TAB_SPACE + TABLE_BORDER);
        System.out.printf(TAB_SPACE + TAB_SPACE + "%-10s %-30s %-15s %-15s %-15s%n", "Type", "Transaction", "Date",
                "Amount", "Category");
        for (Transaction transaction : transactionsRemoved) {
            String type = transaction.getTransactionType();
            String description = transaction.getDescription();
            LocalDate date = transaction.getDate();
            double amount = transaction.getAmount();
            String category = transaction.getCategory().getCategoryName();

            System.out.printf(TAB_SPACE + TAB_SPACE + "%-10s %-30.45s %-15s %-15.2f %-15s%n", type, description, date,
                    amount, category);
        }
        System.out.println(TAB_SPACE + TABLE_BORDER);

        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `getNewAccountName` splits an account string into parts,
     * displays them for editing,
     * prompts the user to enter a new account name, and returns the trimmed input.
     *
     * @param account The `getNewAccountName` method takes a `String` parameter
     *                named `account`, which
     *                is expected to be in a specific format separated by the pipe
     *                character `|`.
     * @return The method `getNewAccountName` returns a new account name entered by
     *         the user.
     */
    //@@author vibes-863
    public static String getNewAccountName(String account) {
        String[] parts = account.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Please edit the following account \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println(LINE);
        System.out.print(TAB_SPACE + "Enter new account name: ");
        return in.nextLine().trim();
    }

    //@@author

    /**
     * The function `printUpdatedAccount` splits a given account string into parts,
     * prints a success
     * message, and then prints each part of the account details.
     *
     * @param account The `printUpdatedAccount` method takes a `String` parameter
     *                named `account`,
     *                which represents the account details.
     */
    //@@author vibes-863
    public static void printUpdatedAccount(String account) {
        String[] parts = account.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Updated Successfully! Updated account details: \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printCannotDeleteLastAccountMessage` prints a message
     * indicating that the last
     * account cannot be deleted.
     */
    //@@author vibes-863
    public static void printCannotDeleteLastAccountMessage() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "You cannot delete the last account.");
        System.out.println(TAB_SPACE + "Please edit the current account or add a new account before deleting " +
                "this one.");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printAccountHelp()` prints a help menu for account-related
     * commands in a Java
     * program.
     */
    //@@author Vavinan
    public static void printAccountHelp() {
        System.out.println(HELP_BORDER);
        System.out.printf("%-30s %-90s%n", "Command", "Syntax");
        System.out.printf("%-30s %-90s%n", "Add account", "add-acc /n/ [ACCOUNT_NAME] /$/ " +
                "[INITIAL_BALANCE]");
        System.out.printf("%-30s %-90s%n", "Delete Account", "delete-acc [ACCOUNT_NUMBER]");
        System.out.printf("%-30s %-90s%n", "Delete Account", "edit-acc [ACCOUNT_NUMBER]");
        System.out.printf("%-30s %-90s%n", "List all Accounts", "list-acc");
        System.out.println(HELP_BORDER);
    }

    /**
     * The function `printFileCorruptedError` prints an error message indicating
     * that the storage file
     * is corrupted and informs that a new file will be created.
     */
    //@@author ShyamKrishna33
    public static void printFileCorruptedError() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "The storage file is corrupted :(");
        System.out.println(TAB_SPACE + "So, a new file will be created!");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printSearchResults` prints search results of transactions with
     * specific formatting
     * and handles cases where no matching transactions are found.
     *
     * @param transactions The `transactions` parameter is an ArrayList of
     *                     Transaction objects. Each
     *                     Transaction object represents a specific transaction with
     *                     details such as transaction type,
     *                     account number, account name, description, date, amount,
     *                     and category.
     * @param indices      The `indices` parameter in the `printSearchResults`
     *                     method is an `ArrayList` of
     *                     `Integer` values. These indices represent the positions
     *                     of the matching transactions in the
     *                     original list of transactions. The method uses these
     *                     indices to display the search results with
     *                     the corresponding transaction number.
     */
    //@@author Vavinan
    public static void printSearchResults(ArrayList<Transaction> transactions, ArrayList<Integer> indices) {
        if (transactions.isEmpty()) {
            System.out.println("No matching Transactions found");
        } else {
            System.out.println("Search results:");
            System.out.println(TAB_SPACE + TABLE_BORDER);
            System.out.printf(TAB_SPACE + TAB_SPACE + "%-5s %-10s %-20s %-20s %-30s %-15s %-15s %-15s%n", "ID", "Type",
                    "Account Number", "Account Name", "Transaction", "Date", "Amount", "Category");
            int i = 0;
            for (Transaction t : transactions) {
                // System.out.println((indices.get(i) + 1) + ". " + transactions.get(i));
                System.out.printf(TAB_SPACE + TAB_SPACE + "%-5d %-10s %-20d %-20.45s %-30.45s %-15s %-15.2f %-15s%n",
                        indices.get(i) + 1, t.getTransactionType(), t.getAccountNumber(), t.getAccountName(),
                        t.getDescription(), t.getDate(), t.getAmount(), t.getCategory().getCategoryName());
                i++;
            }
            System.out.println(TAB_SPACE + TABLE_BORDER);
        }

    }

    /**
     * The function `printInvalidCategoryError` prints an error message for an
     * invalid category.
     */
    //@@author ShyamKrishna33
    public static void printInvalidCategoryError() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid Category");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printInvalidAccountNameError` prints an error message stating
     * that the Account
     * Name cannot be blank.
     */
    //@@author ShyamKrishna33
    private static void printInvalidAccountNameError() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Account Name cannot be blank");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `printInvalidAccountBalanceError` prints an error message the
     * invalid account balance.
     */
    //@@author ShyamKrishna33
    private static void printInvalidAccountBalanceError() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Account Balance cannot be a valid number");
        System.out.println(LINE);
    }

    //@@author

    /**
     * The function `getInitialAccountName` prompts the user to input a name for
     * their account and
     * validates it to ensure it is not empty.
     *
     * @return The method `getInitialAccountName()` returns a `String` value, which
     *         is the account name
     *         entered by the user.
     */
    //@@author ShyamKrishna33
    public static String getInitialAccountName() {
        System.out.println("Let's first create an account for you! What do you want to call it?");
        String accountName = in.nextLine();

        if (accountName.trim().isEmpty()) {
            printInvalidAccountNameError();
            accountName = getInitialAccountName();
        }
        return accountName;
    }

    //@@author

    /**
     * The function `getInitialAccountBalance` prompts the user to input an initial
     * account balance and
     * handles any input errors.
     *
     * @return The method `getInitialAccountBalance()` returns a `Double` value,
     *         which represents the
     *         initial account balance entered by the user.
     */
    //@@author ShyamKrishna33
    public static Double getInitialAccountBalance() {
        System.out.println("Great! What's the initial balance?");
        double initialBalance = 0;
        try {
            initialBalance = Double.parseDouble(UserInterface.in.nextLine().trim());
        } catch (NumberFormatException e) {
            printInvalidAccountBalanceError();
            getInitialAccountBalance();
        }
        return initialBalance;
    }

    public static void printSearchHelp(){
        System.out.println(HELP_BORDER);
        System.out.println(TAB_SPACE + "SYNTAX : search [KEYWORD] \n");
        System.out.println(TAB_SPACE + "The keyword can be anything representing description, date, " +
                "category or amount");
        System.out.println(HELP_BORDER);
    }

    /**
     * The function `printLoggerSetupError` prints an error message indicating that
     * there was an issue
     * setting up the logger.
     */
    public static void printLoggerSetupError() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Error setting up logger");
        System.out.println(LINE);
    }
}
