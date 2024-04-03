package budgetbuddy.parser;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;
import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.exceptions.InvalidEditTransactionData;
import budgetbuddy.exceptions.InvalidTransactionTypeException;

import budgetbuddy.transaction.TransactionList;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;

public class Parser {

    public static final int ADD_COMMAND_INDEX = 3;
    public static final int HELP_BEGIN_INDEX = 4;
    private static final int ADD_ACC_COMMAND_INDEX = 7;

    public static int parseAccountNumber(String input) throws InvalidArgumentSyntaxException {
        String data = input.substring(ADD_COMMAND_INDEX + 1);
        String[] parseData = data.split("/");
        for (int i = 0; i < parseData.length - 1; i++) {
            if (parseData[i].trim().equals("a")) {
                return Integer.parseInt(parseData[i + 1].trim());
            }
        }
        throw new InvalidArgumentSyntaxException("Invalid add syntax.");
    }

    public Transaction parseTransaction(String input, Account account)
            throws InvalidTransactionTypeException, NumberFormatException, EmptyArgumentException {
        String data = input.substring(ADD_COMMAND_INDEX + 1);
        String[] parseData = data.split("/");
        String type = null;
        String description = null;
        String date = null;
        String amount = null;
        String category = null;
        for(int i = 0; i < parseData.length-1; i++) {
            switch (parseData[i].trim()) {
            case "t":
                type = parseData[i + 1].trim();
                break;
            case "n":
                description = parseData[i + 1].trim();
                break;
            case "$":
                if (TransactionList.isNotDouble(parseData[i + 1].trim())) {
                    throw new NumberFormatException(parseData[i+1].trim());
                } else {
                    amount = parseData[i + 1].trim();
                    break;
                }
            case "d":
                date = parseData[i + 1].trim();
                break;
            case "c":
                category = parseData[i + 1].trim();
                break;
            default:
                break;
            }
        }
        assert amount != null;
        assert type != null;
        if(description.trim().isEmpty() || type.trim().isEmpty()){
            throw new EmptyArgumentException("data for the arguments ");
        } else if (type.equalsIgnoreCase("income")) {
            Income income = new Income(account.getAccountNumber(), account.getName(), description,
                    Double.parseDouble(amount), date, account);
            if (category != null){
                income.setCategory(Category.fromNumber(Integer.parseInt(category)));
            }
            return income;
        } else if (type.equalsIgnoreCase("expense")) {
            Expense expense = new Expense(account.getAccountNumber(), account.getName(), description,
                    Double.parseDouble(amount), date, account);
            if (category != null){
                expense.setCategory(Category.fromNumber(Integer.parseInt(category)));
            }
            return expense;
        } else {
            throw new InvalidTransactionTypeException(type);
        }
    }

    //@@author Vavinan
    public Transaction parseTransactionType(String newTransaction, Account account) throws InvalidEditTransactionData {
        String[] parts = newTransaction.split(" \\| ");

        String type = parts[0].trim();
        String description = parts[1].trim();
        String date = parts[2].trim();
        String amount = parts[3].trim();
        String category = parts[4].trim();
        int categoryValue = Integer.parseInt(category);
        if(categoryValue <=0 || categoryValue>9){
            throw new InvalidEditTransactionData("Choose category number from the list 1-9");
        }
        if (type.equalsIgnoreCase("income")) {
            Income income = new Income(account.getAccountNumber(), account.getName(), description,
                    Double.parseDouble(amount), date, account);
            income.setCategory(Category.fromNumber(categoryValue));
            return income;
        } else if (type.equalsIgnoreCase("expense")) {
            Expense expense = new Expense(account.getAccountNumber(), account.getName(), description,
                    Double.parseDouble(amount), date, account);
            expense.setCategory(Category.fromNumber(categoryValue));
            return expense;
        } else {
            throw new InvalidEditTransactionData(" One or more data is wrong. ");
        }
    }

    public String parseHelpCommand(String input){
        return input.substring(HELP_BEGIN_INDEX).trim();
    }
    //@@author

    public static String[] parseAddAccount(String input) throws NumberFormatException, EmptyArgumentException {
        String data = input.substring(ADD_ACC_COMMAND_INDEX + 1).trim();
        String[] parseData = data.split("/");
        String name = null;
        String initialBalance = null;
        for (int i = 0; i < parseData.length - 1; i++) {
            if (parseData[i].trim().equals("n")) {
                name = parseData[i + 1].trim();
            } else if (parseData[i].trim().equals("$")) {
                initialBalance = parseData[i + 1].trim();
            }
        }

        if (name == null || name.isEmpty()){
            throw new EmptyArgumentException("name ");
        }

        if (initialBalance == null) {
            throw new EmptyArgumentException("initial balance ");
        }

        if (TransactionList.isNotDouble(initialBalance)) {
            throw new NumberFormatException(initialBalance);
        }

        return new String[] {name, initialBalance};
    }

    public static int parseRemoveAccount(String input)
            throws NumberFormatException, EmptyArgumentException {
        if (input.trim().length() < 11) {
            throw new EmptyArgumentException("delete-acc index ");
        }
        String data = input.substring(11).trim();
        if (TransactionList.isNotInteger(data)) {
            throw new NumberFormatException(data);
        }
        return Integer.parseInt(data);
    }


    public static int parseEditAccount(String input) throws EmptyArgumentException {
        if (input.trim().length() < 9) {
            throw new EmptyArgumentException("edit-acc index ");
        }
        String data = input.substring(9).trim();
        if (TransactionList.isNotInteger(data)) {
            throw new NumberFormatException(data);
        }
        return Integer.parseInt(data);
    }
}
