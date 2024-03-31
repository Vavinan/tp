package budgetbuddy.account;

import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.parser.Parser;
import budgetbuddy.ui.UserInterface;

import java.util.ArrayList;

public class AccountManager {
    public ArrayList<Account> accounts;
    private int accountCount = 0;

    public AccountManager() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(String name, double initialBalance) {
        accountCount ++;
        accounts.add(new Account(accountCount, name, initialBalance));
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
