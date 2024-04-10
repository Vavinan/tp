package budgetbuddy.ui;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;
import budgetbuddy.transaction.type.Transaction;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    public static final int START_INDEX = 0;

    public static final String HELP_BORDER = "```````````````````````````````````````````````````";

    private static final String LINE = "----------------------------------------------------";
    private static final String TABLE_BORDER = "____________________________________________________";
    private static final String ACCOUNT_TABLE_BORDER = "____________________________________________________";

    private static final String TAB_SPACE = "    ";
    public static Scanner in = new Scanner(System.in);

    public static void listCategories() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Here are the available categories:");
        for (Category category : Category.values()) {
            System.out.println(TAB_SPACE + TAB_SPACE + category.getCategoryName() + ": " + category.getCategoryNum());
        }
        System.out.println(LINE);
    }

    public static int getCategoryNum() {
        System.out.println("In which category do you want to list this transaction? [Enter number between 1 and 9]");
        String input = in.nextLine();
        return Integer.parseInt(input);
    }


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

    public static void printInvalidIndex(String message, int id) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + message);
        System.out.println(TAB_SPACE + "Please use index within the range of: 1 to " + id);
        System.out.println(LINE);
    }

    public static void printExceptionErrorMessage(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "An error occurred with the message: " + message);
        System.out.println(TAB_SPACE + "Please check your command Syntax. \n" + TAB_SPACE +
                " If you need assistance use `help` command to know further about each command syntax.");
        System.out.println(LINE);
    }

    public static void printInvalidInput(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Error occurred with message: " + message);
        System.out.println(LINE);
    }

    //@@author isaaceng7
    public static void printInvalidAddSyntax(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + message);
        System.out.println(TAB_SPACE + "Please ensure that you have entered all the arguments correctly.");
        System.out.println(LINE);
    }

    public static void printTransactionTypeError(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid transaction type: " + message);
        System.out.println(TAB_SPACE + "Please enter Expense or Income only.");
        System.out.println(LINE);
    }

    public static void printNumberFormatError(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Error occurred with the input: " + message);
        System.out.println(TAB_SPACE + "Please enter a valid value.");
        System.out.println(LINE);
    }

    public static void printEmptyArgumentError(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Please include the " + message + "in the command.");
        System.out.println(LINE);
    }

    //@@author
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


    public static void printGoodBye() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE +
                "Bye... Don't forget to keep track of your future transactions");
        System.out.println(LINE);
    }

    public static void printNoCommandExists() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "No such command exists.");
        System.out.println(LINE);
    }

    //@@author Vavinan
    public static String getEditInformation(String string) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Please edit the following transaction");
        System.out.println(string);
        System.out.println(LINE);
        System.out.print(TAB_SPACE + "Enter transaction type: ");
        String type = in.next();
        System.out.print(TAB_SPACE + "Enter description: ");
        String description = in.next();
        System.out.print(TAB_SPACE + "Enter transaction date: ");
        String date = in.next();
        System.out.print(TAB_SPACE + "Enter transaction amount: ");
        String amount = in.next();
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

    public static void printUpdatedTransaction(Transaction t) {
        System.out.println("\n" + TAB_SPACE + "Updated Successfully");
        System.out.println(t.toString());
        System.out.println(LINE);
    }

    public static void printAllCommands(){
        System.out.println(HELP_BORDER);
        System.out.printf("%-20s %-75s %-20s%n", "Command", "Syntax", "Further help");
        System.out.printf("%-20s %-75s %-20s%n","Add","add /t/[TYPE] /n/[DESCRIPTION] /d/[DD-MM-YYYY] " +
                "/$/[AMOUNT] /c/[CATEGORY]", "help add");
        System.out.printf("%-20s %-75s %-20s%n", "Edit", "edit [INDEX]", "help edit");
        System.out.printf("%-20s %-75s %-20s%n", "Delete", "delete [INDEX]", "help delete");
        System.out.printf("%-20s %-75s %-20s%n", "List", "list", "help list");
        System.out.println(HELP_BORDER);

    }

    public static void printAddHelp(){
        System.out.println(HELP_BORDER);
        System.out.println("Method 1:");
        System.out.println(TAB_SPACE + "SYNTAX : add /t/[TYPE] /n/[DESCRIPTION] /d/[DD-MM-YYYY]" +
                "/$/[AMOUNT] \n");
        System.out.println("followed by choosing category from the given list:");

        for(Category category : Category.values()) {
            System.out.println(TAB_SPACE + TAB_SPACE + category.getCategoryName() + ": " + category.getCategoryNum());
        }

        System.out.println("\n Method 2");
        System.out.println(TAB_SPACE + " SYNTAX : add /t/[TYPE] /n/[DESCRIPTION] /d/[DD-MM-YYYY] " +
                "/$/[AMOUNT] /c/[CATEGORY] \n");
        System.out.println("Provide the category number along with the add command");
        System.out.println("\n ");
        System.out.println(HELP_BORDER);
    }

    public static void printDeleteHelp(){
        System.out.println(HELP_BORDER);
        System.out.println(TAB_SPACE + "SYNTAX : delete [INDEX] \n");
        System.out.println( TAB_SPACE + "Make sure the index is above 0 and below or equal to the size of " +
                "the transaction list");
        System.out.println(HELP_BORDER);
    }

    public static void printEditHelp(){
        System.out.println(HELP_BORDER);
        System.out.println(TAB_SPACE + "SYNTAX : edit [INDEX] \n");
        System.out.println("Make sure the index is above 0 and below or equal to the size of the " +
                "transaction list");
        System.out.println("Then you will be asked to input the data for each parameters like");
        System.out.println("    Enter transaction type: [EXPENSE / INCOME] \n" +
                "    Enter description: [NEW DESCRIPTION] \n" +
                "    Enter transaction date: [NEW DATE] \n" +
                "    Enter transaction amount: [NEW AMOUNT] \n" + " \n");
        System.out.println("Then you will be given categories to choose from (like as add command). \n"+
                "    In which category do you want to list this transaction? [Enter number between 1 and " +
                "9]\n" +
                "    Enter Category: [NEW CATEGORY] ");
        System.out.println(HELP_BORDER);
    }

    public static void printListHelp(){
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
                " 4\n" +"Start Date: [dd-MM-yyyy]\n" + "End Date: [dd-MM-yyyy] ");
        System.out.println(HELP_BORDER);
    }

    public static void printUseAvailableHelp(){
        System.out.println(HELP_BORDER);
        System.out.println(TAB_SPACE + "Please use the following commands for help");
        System.out.println(TAB_SPACE + "To get idea about all commands use: help all");
        System.out.println(TAB_SPACE + "Add transaction: help add");
        System.out.println(TAB_SPACE + "Delete transaction: help delete");
        System.out.println(TAB_SPACE + "Edit transaction: help edit");
        System.out.println(TAB_SPACE + "List transaction: help list");
        System.out.println(TAB_SPACE + "Help related to accounts: help acc");
        System.out.println(HELP_BORDER);
    }

    //@@author isaaceng7
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
    public static String getListOption() {
        String data = in.next();
        in.nextLine();
        return data;
    }

    public static String getStartDate() {
        System.out.print("Start Date: ");
        String data = in.next();
        in.nextLine();
        return data;
    }

    public static String getEndDate() {
        System.out.print("End Date: ");
        String data = in.next();
        in.nextLine();
        return data;
    }

    public static void printAccountList(ArrayList<Account> accounts) {
        int maxIndex = accounts.size();
        System.out.println(LINE);
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

    public static String getSelectedAccountNumber(ArrayList<Account> accounts) {
        printAccountList(accounts);
        System.out.print("Select an account number: ");
        String data = in.next();
        in.nextLine();
        return data;
    }

    public static int getSelectedCategory() {
        System.out.print("Select a category number: ");
        String data = in.nextLine();
        return Integer.parseInt(data);
    }


    public static void printPastTransactions(ArrayList<Transaction> transactions, String duration) {
        int index = transactions.size();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Displaying transactions from the past " +  duration + ":");
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

    public static void printAccountTransactions(ArrayList<Transaction> transactions, String accountName,
                                                int accountNumber) {
        int index = transactions.size();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Displaying transactions of account: " + accountName + "(" + accountNumber +")");
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

    public static void printInvalidArgumentSyntax(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + message);
        System.out.println(TAB_SPACE + "Please ensure that you have entered all the arguments correctly.");
        System.out.println(LINE);
    }

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

            System.out.printf("\n" +TAB_SPACE + TAB_SPACE + "%-20d %-30.45s %-15.2f", accountNumber, name, balance);
        }
        System.out.println("\n" + TAB_SPACE + ACCOUNT_TABLE_BORDER);
        System.out.println(LINE);
    }

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

            System.out.printf(TAB_SPACE + TAB_SPACE + "%-10s %-30.45s %-15s %-15.2f %-15s%n",type, description, date,
                    amount, category);
        }
        System.out.println(TAB_SPACE + TABLE_BORDER);

        System.out.println(LINE);
    }

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

    public static void printUpdatedAccount(String account) {
        String[] parts = account.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Updated Successfully! Updated account details: \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println(LINE);
    }

    public static void printCannotDeleteLastAccountMessage() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "You cannot delete the last account.");
        System.out.println(TAB_SPACE + "Please edit the current account or add a new account before deleting " +
                "this one.");
        System.out.println(LINE);
    }

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


    //@@author ShyamKrishna33
    public static void printFileCorruptedError() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "The storage file is corrupted :(");
        System.out.println(TAB_SPACE + "So, a new file will be created!");
        System.out.println(LINE);
    }
    public static void printSearchResults(ArrayList<Transaction> transactions, ArrayList<Integer> indices) {
        if(transactions.isEmpty()) {
            System.out.println("No matching Transactions found");
        } else{
            System.out.println("Search results:");
            System.out.println(TAB_SPACE + TABLE_BORDER);
            System.out.printf(TAB_SPACE + TAB_SPACE + "%-5s %-10s %-20s %-20s %-30s %-15s %-15s %-15s%n", "ID", "Type",
                    "Account Number", "Account Name", "Transaction", "Date", "Amount", "Category");
            int i = 0;
            for (Transaction t : transactions) {
                //System.out.println((indices.get(i) + 1) + ". " + transactions.get(i));
                System.out.printf(TAB_SPACE + TAB_SPACE + "%-5d %-10s %-20d %-20.45s %-30.45s %-15s %-15.2f %-15s%n",
                        indices.get(i)+1, t.getTransactionType(),t.getAccountNumber(),t.getAccountName(),
                        t.getDescription(), t.getDate(), t.getAmount(), t.getCategory().getCategoryName());
                i++;
            }
            System.out.println(TAB_SPACE + TABLE_BORDER);
        }

    }

    public static void printInvalidCategoryError() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid Category");
        System.out.println(LINE);
    }

    private static void printInvalidAccountNameError() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Account Name cannot be blank");
        System.out.println(LINE);
    }

    private static void printInvalidAccountBalanceError() {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Account Balance cannot be a valid number");
        System.out.println(LINE);
    }

    public static String getInitialAccountName() {
        System.out.println("Let's first create an account for you! What do you want to call it?");
        String accountName = in.nextLine();

        if (accountName.trim().isEmpty()) {
            printInvalidAccountNameError();
            accountName = getInitialAccountName();
        }
        return accountName;
    }

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
}
