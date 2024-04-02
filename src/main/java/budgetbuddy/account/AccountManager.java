package budgetbuddy.account;

import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.exceptions.InvalidIndexException;
import budgetbuddy.parser.Parser;
import budgetbuddy.ui.UserInterface;

import java.util.ArrayList;
import java.util.Random;

public class AccountManager {
    public static final int INDEX_OFFSET = 1;
    public static final int LOWER_BOUND = 0;

    private final ArrayList<Account> accounts;
    private final ArrayList<Integer> existingAccountNumbers;
    private int accountCount = 0;

    public AccountManager() {
        this.accounts = new ArrayList<>();
        this.existingAccountNumbers = new ArrayList<>();
    }

    public void addAccount(String name, double initialBalance) {
        accountCount ++;
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
        UserInterface.printAddAccountMessage(accounts.get(accountCount - 1).toString());
    }

    public void removeAccount(String input)
            throws NumberFormatException, InvalidArgumentSyntaxException, EmptyArgumentException,
            InvalidIndexException {
        int accountNumber = Parser.parseRemoveAccount(input);
        Account accountRemoved = getAccountByAccountNumber(accountNumber);
        accounts.remove(accountRemoved);
        accountCount--;
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
}
