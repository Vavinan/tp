package budgetbuddy.parser;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;

import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidAddTransactionSyntax;
import budgetbuddy.exceptions.InvalidArgumentSyntaxException;
import budgetbuddy.exceptions.InvalidCategoryException;
import budgetbuddy.exceptions.InvalidEditTransactionData;
import budgetbuddy.exceptions.InvalidTransactionTypeException;
import budgetbuddy.transaction.TransactionList;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;
import budgetbuddy.ui.UserInterface;

public class Parser {

    public static final int ADD_COMMAND_INDEX = 3;
    public static final int HELP_BEGIN_INDEX = 4;
    private static final int ADD_ACC_COMMAND_INDEX = 7;

    /**
     * The function `parseAccountNumber` extracts and returns an account number from a given input
     * string following a specific syntax.
     *
     * @param input The input string that containes the account number to be parsed.
     * @return The method is returning an integer value, which is the account number parsed from the
     * input string.
     */
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

    /**
     * The function `parseUserInputToTransaction` takes user input, parses it to create a transaction
     * object (either income or expense), and handles various exceptions related to invalid input.
     *
     * @param input takes a user input that contain transaction details in a
     * specific format.
     * @param account The `account` parameter in this method represents the account to which the transaction belongs.
     * @return The method `parseUserInputToTransaction` is returning a `Transaction` object, which can
     * be either an `Income` or an `Expense` object based on the type provided in the input.
     */
    public Transaction parseUserInputToTransaction(String input, Account account)
            throws InvalidTransactionTypeException, NumberFormatException,
            EmptyArgumentException, InvalidCategoryException, InvalidAddTransactionSyntax {
        String data = input.substring(ADD_COMMAND_INDEX + 1);
        String[] parseData = data.split("/");
        String type = null;
        String description = null;
        String date = null;
        String amount = null;
        int category = -1;
        for (int i = 0; i < parseData.length - 1; i++) {
            switch (parseData[i].trim()) {
            case "t":
                type = parseData[i + 1].trim();
                break;
            case "n":
                description = parseData[i + 1].trim();
                break;
            case "$":
                if (TransactionList.isNotDouble(parseData[i + 1].trim())) {
                    throw new NumberFormatException(parseData[i + 1].trim());
                } else {
                    amount = parseData[i + 1].trim();
                    break;
                }
            case "d":
                date = parseData[i + 1].trim();
                break;
            case "c":
                category = Integer.parseInt(parseData[i + 1].trim());
                break;
            default:
                break;
            }
        }
        assert amount != null;
        assert type != null;

        if (category == -1) {
            UserInterface.listCategories();
            category = UserInterface.getCategoryNum();
        }

        if (category < 1 || category > 9) {
            throw new InvalidCategoryException("Category Index out of bounds");
        }

        if (Double.parseDouble(amount) < 0) {
            throw new InvalidAddTransactionSyntax("Amount cannot be negative");
        }

        if (description.trim().isEmpty() || type.trim().isEmpty()) {
            throw new EmptyArgumentException("data for the arguments ");
        } else if (type.equalsIgnoreCase("income")) {
            Income income = new Income(account.getAccountNumber(), account.getName(), description,
                    Double.parseDouble(amount), date, account);
            income.setCategory(Category.fromNumber(category));

            return income;
        } else if (type.equalsIgnoreCase("expense")) {
            Expense expense = new Expense(account.getAccountNumber(), account.getName(), description,
                    Double.parseDouble(amount), date, account);
            expense.setCategory(Category.fromNumber(category));
            return expense;
        } else {
            throw new InvalidTransactionTypeException(type);
        }
    }

    //@@author Vavinan
    /**
     * The `parseEditTransaction` function in Java parses a new transaction string and creates either
     * an Income or Expense object based on the transaction type, validating the category number and
     * throwing exceptions for invalid data.
     *
     * @param newTransaction The `newTransaction` string is expected to be in a specific format
     * @param account The `account` parameter in the `parseEditTransaction` method represents the
     * account to which the transaction belongs.
     * @return The `parseEditTransaction` method is returning a `Transaction` object, which can be
     * either an `Income` or `Expense` object based on the type provided in the `newTransaction`
     * string.
     */
    public Transaction parseEditTransaction(String newTransaction, Account account) throws InvalidEditTransactionData,
            InvalidCategoryException {
        String[] parts = newTransaction.split(" \\| ");

        String type = parts[0].trim();
        String description = parts[1].trim();
        String date = parts[2].trim();
        String amount = parts[3].trim();
        String category = parts[4].trim();
        int categoryValue = Integer.parseInt(category);
        if (categoryValue <= 0 || categoryValue > 9) {
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

    public String parseHelpCommand(String input) {
        return input.substring(HELP_BEGIN_INDEX).trim();
    }
    //@@author

    /**
     * The `parseAddAccount` function in Java parses the input and returns the account name and
     *  balance of the new account that is to added.
     *
     * @param input It contains details about account name and initial balance
     * @return  A string array that contains the account name and initial balance
     */
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

        if (name == null || name.isEmpty()) {
            throw new EmptyArgumentException("name ");
        }

        if (initialBalance == null) {
            throw new EmptyArgumentException("initial balance ");
        }

        if (TransactionList.isNotDouble(initialBalance)) {
            throw new NumberFormatException(initialBalance);
        }

        return new String[]{name, initialBalance};
    }

    /**
     * The `parseRemoveAccount` function in Java parses the input and returns the account
     * number that is to be deleted
     * @param input It contains details about account number that is to be deleted
     * @return  A integer value representing the account number
     */
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

    /**
     * The `parseEditAccount` function in Java parses the input and returns the account
     * number that is to be edited
     * @param input It contains details about account number  that is to be edited
     * @return  A integer value representing the account number
     */
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
