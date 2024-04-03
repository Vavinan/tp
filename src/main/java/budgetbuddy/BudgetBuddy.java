package budgetbuddy;

import budgetbuddy.account.Account;
import budgetbuddy.account.AccountManager;
import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidAddTransactionSyntax;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.exceptions.InvalidEditTransactionData;
import budgetbuddy.exceptions.InvalidIndexException;
import budgetbuddy.exceptions.InvalidTransactionTypeException;
import budgetbuddy.parser.Parser;
import budgetbuddy.storage.DataStorage;
import budgetbuddy.transaction.TransactionList;
import budgetbuddy.ui.UserInterface;

import java.util.Scanner;

public class BudgetBuddy {
    private final AccountManager accountManager;
    private final TransactionList transactions;

    public BudgetBuddy() {
        DataStorage dataStorage = new DataStorage();
        this.accountManager = dataStorage.loadAccounts();
        this.transactions = dataStorage.loadTransactions();
    }

    /**
     * Main entry-point for the java.BudgetBuddy application.
     */

    public static void main(String[] args){
        new BudgetBuddy().run();
    }

    public void run() {
        Scanner in = UserInterface.in;
        String logo = "BUDGET BUDDY";

        System.out.println("Hello from\n" + logo);
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
                    transactions.processList();
                    break;
                case "delete":
                    transactions.removeTransaction(input, accountManager);
                    break;
                case "add":
                    int accountNumber = Parser.parseAccountNumber(input);
                    Account account = accountManager.getAccountByAccountNumber(accountNumber);
                    transactions.processTransaction(input, account);
                    break;
                case "edit":
                    transactions.processEditTransaction(input, accountManager);
                    break;
                case "help":
                    transactions.helpWithUserCommands(input);
                    break;
                case "add-acc":
                    accountManager.processAddAccount(input);
                    break;
                case "insights":
                    transactions.displayInsights();
                    break;
                case "list-acc":
                    UserInterface.printListOfAccounts(accountManager.getAccounts());
                    break;
                case "delete-acc":
                    accountManager.removeAccount(input, transactions);
                    break;
                case "edit-acc":
                    accountManager.processEditAccount(input);
                    break;
                default:
                    UserInterface.printNoCommandExists();
                }
                transactions.saveTransactionList();
                accountManager.saveAccounts();

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
