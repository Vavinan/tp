package budgetbuddy;

import budgetbuddy.account.Account;
import budgetbuddy.account.AccountManager;
import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidAddTransactionSyntax;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.exceptions.InvalidEditTransactionData;
import budgetbuddy.exceptions.InvalidIndexException;
import budgetbuddy.exceptions.InvalidTransactionTypeException;
import budgetbuddy.transaction.TransactionList;
import budgetbuddy.ui.UserInterface;

import java.io.IOException;
import java.util.Scanner;

public class BudgetBuddy {

    /**
     * Main entry-point for the java.BudgetBuddy application.
     */
    public static void main(String[] args) {
        String logo = "BUDGET BUDDY";
        System.out.println("Hello from\n" + logo);


        TransactionList transactions = null;
        try {
            transactions = new TransactionList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        transactions.updateBalance(new Account(1));


        System.out.println("Let's first create an account for you! What do you want to call it?");
        Scanner in = UserInterface.in;
        String accountName = in.nextLine();
        System.out.println("Great! What's the initial balance?");
        double initialBalance = Double.parseDouble(in.nextLine());
        AccountManager accountManager = new AccountManager();
        accountManager.addAccount(accountName, initialBalance);

        System.out.println("What can I do for you?");


        boolean isRunning = true;

        while (isRunning) {
            String input = in.nextLine();
            try {
                switch (input.split(" ")[0]) {
                case "bye":
                    UserInterface.printGoodBye();
                    isRunning = false;
                    break;
                case "list":
                    transactions.processList(accountManager.accounts.get(0));
                    break;
                case "delete":
                    transactions.removeTransaction(input, accountManager.accounts.get(0));
                    break;
                case "add":
                    transactions.processTransaction(input, accountManager.accounts.get(0));
                    break;
                case "edit":
                    transactions.processEditTransaction(input, accountManager.accounts.get(0));
                    break;
                case "help":
                    transactions.helpWithUserCommands(input);
                    break;
                case "add-acc":
                    accountManager.processAddAccount(input);
                    break;
                case "list-acc":
                    UserInterface.printListOfAccounts(accountManager.accounts);
                    break;
                case "delete-acc":
                    accountManager.removeAccount(input);
                    break;
                default:
                    UserInterface.printNoCommandExists();
                }
                transactions.saveTransactionList();
            } catch (InvalidAddTransactionSyntax e) {
                UserInterface.printInvalidAddSyntax(e.getMessage());
            } catch (NumberFormatException e) {
                UserInterface.printNumberFormatError(e.getMessage());
            } catch (InvalidTransactionTypeException e) {
                UserInterface.printTransactionTypeError(e.getMessage());
            } catch (EmptyArgumentException e) {
                UserInterface.printEmptyArgumentError(e.getMessage());
            } catch (InvalidIndexException e) {
                UserInterface.printInvalidIndex("Given index id is out of bound",
                        Integer.parseInt(e.getMessage()));
            } catch (IndexOutOfBoundsException ignored){
                UserInterface.printInvalidInput("Please check your command syntax");
            } catch (InvalidEditTransactionData e){
                UserInterface.printInvalidInput(e.getMessage());
            } catch (InvalidArgumentSyntaxException e){
                UserInterface.printInvalidArgumentSyntax(e.getMessage());
            } catch (Exception e) {
                UserInterface.printUnknownError(e.getMessage());
            }
        }

    }
}
