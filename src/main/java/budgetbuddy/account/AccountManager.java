package budgetbuddy.account;

import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.exceptions.InvalidIndexException;
import budgetbuddy.parser.Parser;
import budgetbuddy.storage.DataStorage;
import budgetbuddy.ui.UserInterface;

import java.util.ArrayList;
import java.util.Random;

public class AccountManager {
    public static final int INDEX_OFFSET = 1;

    private final DataStorage dataStorage = new DataStorage();
    private final ArrayList<Account> accounts;
    private final ArrayList<Integer> existingAccountNumbers;

    public AccountManager() {
        this.accounts = new ArrayList<>();
        this.existingAccountNumbers = new ArrayList<>();
    }

    public AccountManager(ArrayList<Account> accounts, ArrayList<Integer> existingAccountNumbers) {
        this.accounts = accounts;
        this.existingAccountNumbers = existingAccountNumbers;
    }

    public void addAccount(String name, double initialBalance) {
        int newAccountNumber = generateAccountNumber();
        accounts.add(new Account(newAccountNumber, name, initialBalance));
        existingAccountNumbers.add(newAccountNumber);
    }

    public int generateAccountNumber() {
        Random random = new Random();
        boolean noMatchFound = true;
        int fourDigitNumber;
        do {
            fourDigitNumber = 1000 + random.nextInt(9000);
            for (int accountNumber: existingAccountNumbers) {
                if (accountNumber == fourDigitNumber) {
                    noMatchFound = false;
                    break;
                }
            }
        } while (!noMatchFound);

        return fourDigitNumber;
    }

    public void processAddAccount(String input)
            throws InvalidArgumentSyntaxException, NumberFormatException, EmptyArgumentException {

        String[] arguments = {"/n/", "/$/"};
        for (String argument: arguments) {
            if (!input.contains(argument)) {
                throw new InvalidArgumentSyntaxException("Invalid add account syntax.");
            }
        }
        String[] parsedData = Parser.parseAddAccount(input);
        addAccount(parsedData[0], Double.parseDouble(parsedData[1]));
        UserInterface.printAddAccountMessage(getAccount(accounts.size() - INDEX_OFFSET).toString());
    }

    public void removeAccount(String input)
            throws NumberFormatException, InvalidArgumentSyntaxException, EmptyArgumentException,
            InvalidIndexException {
        int accountNumber = Parser.parseRemoveAccount(input);
        Account accountRemoved = getAccountByAccountNumber(accountNumber);
        accounts.remove(accountRemoved);
        UserInterface.printDeleteAccountMessage(accountRemoved.toString());
    }

    public Account getAccount(int accountId){
        return accounts.get(accountId);
    }

    public Account getAccountByAccountNumber(int accountNumber) {
        for (Account account: accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        throw new IllegalArgumentException("Account not found.");
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void processEditAccount(String input) throws EmptyArgumentException, IllegalArgumentException {
        int accountNumber = Parser.parseEditAccount(input);
        Account account = getAccountByAccountNumber(accountNumber);
        String newName = UserInterface.getNewAccountName(account.toString());
        account.setName(newName);
        UserInterface.printUpdatedAccount(account.toString());
    }

    public void saveAccounts() {
        dataStorage.saveAccounts(accounts);
    }
}
