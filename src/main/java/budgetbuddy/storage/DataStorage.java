package budgetbuddy.storage;

import budgetbuddy.account.Account;
import budgetbuddy.account.AccountManager;
import budgetbuddy.categories.Category;
import budgetbuddy.exceptions.FileCorruptedException;
import budgetbuddy.exceptions.InvalidCategoryException;
import budgetbuddy.transaction.TransactionList;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;
import budgetbuddy.ui.UserInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * This class provides methods for storing and retrieving data related to transactions and accounts.
 * It includes methods for saving and loading transactions and accounts to/from files.
 */
public class DataStorage {
    public static final String TRANSACTIONS_FILE_PATH = "./data/transactions.txt";
    public static final String ACCOUNTS_FILE_PATH = "./data/accounts.txt";
    public static final String FOLDER_PATH = "./data";

    private static final Logger logger = Logger.getLogger(AccountManager.class.getName());
    /**
     * Writes the provided string to a file at the given file path.
     *
     * @param stringToWrite The string to write to the file.
     * @param filePath      The path of the file to write to.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    //@@author ShyamKrishna33
    private static void writeToFile(String stringToWrite, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(stringToWrite);
        fw.close();
    }

    private static String getStringToWrite(Transaction t) {
        LocalDate date = t.getDate();
        String stringDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return t.getDescription() + " ," + t.getCategory().getCategoryNum() + " ,"
                + t.getTransactionType() + " ," + stringDate + " ," + t.getAmount() + " ," + t.getAccountNumber()
                + " ," + t.getAccountName() + "\n";
    }

    private static void createDataFolderIfNotExists() throws IOException {
        Path dataFolderPath = Paths.get(FOLDER_PATH);
        if (!Files.exists(dataFolderPath)) {
            Files.createDirectories(dataFolderPath);
        }
    }
    //@@author

    /**
     * Saves the list of accounts to the file in the ACCOUNT_FILE_PATH.
     *
     * @param accounts The list of accounts to save.
     */
    public void saveAccounts(ArrayList<Account> accounts) {
        logger.log(Level.INFO, "Saving accounts to file");
        try {
            File f = new File(ACCOUNTS_FILE_PATH);
            if (!f.exists()) {
                logger.log(Level.WARNING, "File does not exist. Creating new file.");
                createDataFolderIfNotExists();
                if (!f.createNewFile()) {
                    logger.log(Level.SEVERE, "Failed to create file");
                    throw new IOException("Failed to create file");
                }
            }
            FileWriter fw = new FileWriter(ACCOUNTS_FILE_PATH, false);
            for (Account account : accounts) {
                String stringToWrite = account.getAccountNumber() + " ," + account.getName() + " ,"
                        + account.getBalance() + "\n";
                writeToFile(stringToWrite, ACCOUNTS_FILE_PATH);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving accounts.");
            logger.log(Level.SEVERE, "Error saving accounts");
        }
        logger.log(Level.INFO, "Accounts saved to file");
    }

    /**
     * Saves the list of transactions to a file.
     *
     * @param transactionArrayList The list of transactions to save.
     * @throws IOException If an I/O error occurs while saving the transactions.
     */
    //@@author ShyamKrishna33
    public void saveTransactions(ArrayList<Transaction> transactionArrayList) throws IOException {
        File f = new File(TRANSACTIONS_FILE_PATH);

        assert f.exists() : "File does not exist";
        FileWriter fw = new FileWriter(TRANSACTIONS_FILE_PATH, false);

        for (Transaction transaction : transactionArrayList) {
            if (transaction == null) {
                break;
            }
            String stringToWrite = getStringToWrite(transaction);
            writeToFile(stringToWrite, TRANSACTIONS_FILE_PATH);
        }
    }

    /**
     * Parses a string representing transaction data into a Transaction object.
     *
     * @param s                      The string representing the transaction data.
     * @param existingAccountNumbers A list of existing account numbers.
     * @return The parsed Transaction object.
     * @throws FileCorruptedException    If the file containing transaction data is corrupted.
     * @throws InvalidCategoryException If the category specified in the transaction data is invalid.
     */
    private Transaction parseDataToTransaction(String s, ArrayList<Integer> existingAccountNumbers)
            throws FileCorruptedException, InvalidCategoryException {
        String[] transactionInfo = s.split(" ,");
        int categoryNum;
        try {
            categoryNum = Integer.parseInt(transactionInfo[1]);
        } catch (NumberFormatException e) {
            throw new FileCorruptedException("Invalid type for category number");
        }

        if (categoryNum < 1 || categoryNum > 9) {
            throw new FileCorruptedException("Invalid category number");
        }

        if (transactionInfo.length != 7) {
            throw new FileCorruptedException("Invalid transaction information format");
        }

        if (!transactionInfo[2].equals("Income") && !transactionInfo[2].equals("Expense")) {
            throw new FileCorruptedException("Invalid transaction type");
        }

        double amount;

        try {
            amount = Double.parseDouble(transactionInfo[4]);
        } catch (NumberFormatException e) {
            throw new FileCorruptedException("Invalid type for transaction amount");
        }

        if (!existingAccountNumbers.contains(Integer.parseInt(transactionInfo[5]))) {
            throw new FileCorruptedException("Invalid account number");
        }

        switch (transactionInfo[2]) {
        case "Income":
            Income incomeObj = new Income(Integer.parseInt(transactionInfo[5]), transactionInfo[6], transactionInfo[0],
                    amount, transactionInfo[3]);
            incomeObj.setCategory(Category.fromNumber(categoryNum));
            return incomeObj;
        case "Expense":
            Expense expenseObj = new Expense(Integer.parseInt(transactionInfo[5]), transactionInfo[6],
                    transactionInfo[0], -amount, transactionInfo[3]);
            expenseObj.setCategory(Category.fromNumber(categoryNum));
            return expenseObj;
        default:
            return null;
        }
    }
    //@@author

    /**
     * Reads account data from the accounts file and returns a list of Account objects.
     *
     * @param existingAccountNumbers A list of existing account numbers.
     * @return The list of Account objects read from the file.
     * @throws IOException           If an I/O error occurs while reading the file.
     * @throws FileCorruptedException If the file containing account data is corrupted.
     */
    public ArrayList<Account> readAccountFile(ArrayList<Integer> existingAccountNumbers)
            throws IOException, FileCorruptedException {
        File f = new File(ACCOUNTS_FILE_PATH);
        Scanner s = new Scanner(f);

        ArrayList<Account> accounts = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] accountInfo = line.split(" ,");
            int accountNumber;
            double balance;
            String accountName = accountInfo[1].trim();

            if (accountInfo.length != 3) {
                throw new FileCorruptedException("Invalid account information format");
            }

            try {
                accountNumber = Integer.parseInt(accountInfo[0]);
            } catch (NumberFormatException e) {
                throw new FileCorruptedException("Invalid type for account number");
            }

            try {
                balance = Double.parseDouble(accountInfo[2]);
            } catch (NumberFormatException e) {
                throw new FileCorruptedException("Invalid type for account balance");
            }

            if (accountNumber < 1000 || accountNumber > 9999) {
                throw new FileCorruptedException("Invalid account number");
            }

            if (accountName.isEmpty()) {
                throw new FileCorruptedException("Invalid account name");
            }

            if (existingAccountNumbers.contains(accountNumber)) {
                throw new FileCorruptedException("Duplicate account number");
            }

            accounts.add(new Account(accountNumber, accountInfo[1], balance));
            existingAccountNumbers.add(accountNumber);
        }
        return accounts;
    }

    /**
     * Reads transaction data from the transactions file and returns a list of Transaction objects.
     *
     * @param existingAccountNumbers A list of existing account numbers.
     * @return The list of Transaction objects read from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public ArrayList<Transaction> readTransactionFile(ArrayList<Integer> existingAccountNumbers) throws IOException {
        createDataFolderIfNotExists();
        File f = new File(TRANSACTIONS_FILE_PATH);
        if (!f.exists()) {
            if (!f.createNewFile()) {
                throw new IOException("Failed to create file");
            }
        }

        assert f.exists() : "File does not exist";

        Scanner s = new Scanner(f);
        ArrayList<Transaction> transactionList = new ArrayList<>();
        try {
            while (s.hasNext()) {
                String line = s.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                transactionList.add(parseDataToTransaction(line, existingAccountNumbers));
            }
        } catch (FileCorruptedException | InvalidCategoryException e) {
            UserInterface.printFileCorruptedError();
            FileWriter fw = new FileWriter(TRANSACTIONS_FILE_PATH, false);
            return new ArrayList<>();
        }
        return transactionList;
    }

    /**
     * Loads the accounts from the accounts file and returns an AccountManager object.
     *
     * @return The loaded AccountManager object.
     */
    public AccountManager loadAccounts() {
        try {
            File f = new File(ACCOUNTS_FILE_PATH);
            if (!f.exists()) {
                createDataFolderIfNotExists();
                if (!f.createNewFile()) {
                    throw new IOException("Failed to create file");
                }
                return createNewAccountManager();
            }
            ArrayList<Integer> existingAccountNumbers = new ArrayList<>();
            ArrayList<Account> accounts = null;
            try {
                accounts = readAccountFile(existingAccountNumbers);
            } catch (FileCorruptedException e) {
                UserInterface.printFileCorruptedError();
                FileWriter fw = new FileWriter(ACCOUNTS_FILE_PATH, false);
                return createNewAccountManager();
            }
            if (accounts.isEmpty()) {
                return createNewAccountManager();
            }
            return new AccountManager(accounts, existingAccountNumbers);
        } catch (IOException e) {
            UserInterface.printFileCorruptedError();
            return createNewAccountManager();
        }
    }

    private AccountManager createNewAccountManager() {
        String accountName = UserInterface.getInitialAccountName();
        Double initialBalance = UserInterface.getInitialAccountBalance();
        AccountManager accountManager = new AccountManager();
        accountManager.addAccount(accountName, initialBalance);
        return accountManager;
    }

    /**
     * Loads the transactions from the transactions file and returns a TransactionList object.
     *
     * @param existingAccountNumbers A list of existing account numbers.
     * @return The loaded TransactionList object.
     */
    public TransactionList loadTransactions(ArrayList<Integer> existingAccountNumbers) {
        try {
            ArrayList<Transaction> transactions = readTransactionFile(existingAccountNumbers);
            return new TransactionList(transactions);
        } catch (IOException e) {
            return new TransactionList();
        }
    }
}
