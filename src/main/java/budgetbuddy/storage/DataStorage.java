package budgetbuddy.storage;

import budgetbuddy.account.Account;
import budgetbuddy.account.AccountManager;
import budgetbuddy.categories.Category;
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

public class DataStorage {
    public static final String TRANSACTIONS_FILE_PATH = "./data/transactions.txt";
    public static final String ACCOUNTS_FILE_PATH = "./data/accounts.txt";
    public static final String FOLDER_PATH = "./data";

    public void saveAccounts(ArrayList<Account> accounts) {
        try {
            File f = new File(ACCOUNTS_FILE_PATH);
            if (!f.exists()) {
                createDataFolderIfNotExists();
                if (!f.createNewFile()) {
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
        }
    }

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
                + " ," + t.getAccountName()+ "\n";
    }

    // description, categoryNum, type, date, amount, accountNumber, accountName
    private Transaction processData(String s) {
        String[] transactionInfo = s.split(" ,");
        int categoryNum = Integer.parseInt(transactionInfo[1]);

        assert transactionInfo.length ==  7: "Invalid transaction information format";
        assert transactionInfo[2].equals("Income") || transactionInfo[2].equals("Expense") : "Invalid transaction type";

        switch (transactionInfo[2]) {
        case "Income":
            Income incomeObj = new Income(Integer.parseInt(transactionInfo[5]), transactionInfo[6], transactionInfo[0],
                    Double.parseDouble(transactionInfo[4]), transactionInfo[3]);
            incomeObj.setCategory(Category.fromNumber(categoryNum));
            return incomeObj;
        case "Expense":
            Expense expenseObj = new Expense(Integer.parseInt(transactionInfo[5]), transactionInfo[6],
                    transactionInfo[0], -Double.parseDouble(transactionInfo[4]), transactionInfo[3]);
            expenseObj.setCategory(Category.fromNumber(categoryNum));
            return expenseObj;
        default:
            return null;
        }
    }

    private static void createDataFolderIfNotExists() throws IOException {
        Path dataFolderPath = Paths.get(FOLDER_PATH);
        if (!Files.exists(dataFolderPath)) {
            Files.createDirectories(dataFolderPath);
        }
    }

    public ArrayList<Account> readAccountFile() throws IOException {
        File f = new File(ACCOUNTS_FILE_PATH);
        Scanner s = new Scanner(f);

        ArrayList<Account> accounts = new ArrayList<>();
        while (s.hasNext()) {
            String[] accountInfo = s.nextLine().split(" ,");
            assert accountInfo.length == 3 : "Invalid account information format";
            accounts.add(new Account(Integer.parseInt(accountInfo[0]), accountInfo[1],
                    Double.parseDouble(accountInfo[2])));
        }
        return accounts;
    }

    public ArrayList<Transaction> readTransactionFile() throws IOException {
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
        while(s.hasNext()) {
            transactionList.add(processData(s.nextLine()));
        }
        return transactionList;
    }

    public AccountManager loadAccounts() {
        try {
            File f = new File(ACCOUNTS_FILE_PATH);
            if (!f.exists()) {
                createDataFolderIfNotExists();
                if(!f.createNewFile()) {
                    throw new IOException("Failed to create file");
                }
                return createNewAccountManager();
            }
            ArrayList<Account> accounts = readAccountFile();
            ArrayList<Integer> existingAccountNumbers = new ArrayList<>();
            for (Account account : accounts) {
                existingAccountNumbers.add(account.getAccountNumber());
            }
            return new AccountManager(accounts, existingAccountNumbers);
        } catch (IOException e) {
            System.out.println("Error reading accounts file. Creating new account manager.");
            return createNewAccountManager();
        }
    }

    private AccountManager createNewAccountManager() {
        System.out.println("Let's first create an account for you! What do you want to call it?");
        String accountName = UserInterface.in.nextLine();
        System.out.println("Great! What's the initial balance?");
        double initialBalance = Double.parseDouble(UserInterface.in.nextLine());
        AccountManager accountManager = new AccountManager();
        accountManager.addAccount(accountName, initialBalance);
        return accountManager;
    }

    public TransactionList loadTransactions() {
        try {
            ArrayList<Transaction> transactions = readTransactionFile();
            return new TransactionList(transactions);
        } catch (IOException e) {
            System.out.println("Error reading transactions file. Creating new transaction list.");
            return new TransactionList();
        }
    }
}
