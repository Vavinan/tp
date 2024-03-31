package budgetbuddy.account;

import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.parser.Parser;
import budgetbuddy.ui.UserInterface;

import java.util.ArrayList;
import java.util.Random;

public class AccountManager {
    public ArrayList<Account> accounts;
    private ArrayList<Integer> existingIdList;
    private int accountCount = 0;

    public AccountManager() {
        this.accounts = new ArrayList<>();
        this.existingIdList = new ArrayList<>();
    }

    public void addAccount(String name, double initialBalance) {
        accountCount ++;
        int newId = generateId();
        accounts.add(new Account(newId, name, initialBalance));
        existingIdList.add(newId);
    }

    public int generateId() {
        Random random = new Random();
        boolean noMatchFound = true;
        int fourDigitNumber;
        do {
            fourDigitNumber = 1000 + random.nextInt(9000);
            for (int id: existingIdList) {
                if (id == fourDigitNumber) {
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
}
