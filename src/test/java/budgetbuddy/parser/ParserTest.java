package budgetbuddy.parser;
import budgetbuddy.account.Account;
import budgetbuddy.exceptions.*;
import org.junit.jupiter.api.Test;
import budgetbuddy.transaction.type.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseTransaction() throws InvalidTransactionTypeException, EmptyArgumentException,
            InvalidCategoryException, InvalidAddTransactionSyntax {
        Parser parser = new Parser();
        Account account = new Account(1);
        String input = "add /t/Income /n/Shopping /$/50 /d/14-03-2024 /c/1";
        Transaction transaction = parser.parseUserInputToTransaction(input , account);
        assertEquals("Shopping", transaction.getDescription());
        assertEquals(50.0f, transaction.getAmount(), 0.001);
        assertEquals(LocalDate.parse("14-03-2024", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                transaction.getDate());
        assertEquals("Dining", transaction.getCategory().getCategoryName());
    }

    @Test
    public void testParseTransactionType() throws InvalidEditTransactionData, InvalidEditTransactionData,
            InvalidCategoryException {
        Parser parser = new Parser();
        Account account = new Account(1);

        // Test case for valid income transaction
        String incomeTransactionString = "income | test1 | 10-11-2022 | 1000.00 | 1";
        Transaction incomeTransaction = parser.parseEditTransaction(incomeTransactionString, account);
        assertEquals("test1", incomeTransaction.getDescription());
        assertEquals(1000.00, incomeTransaction.getAmount(), 0.001);
        assertEquals("Dining", incomeTransaction.getCategory().getCategoryName());
        assertEquals(LocalDate.parse("10-11-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                incomeTransaction.getDate());

        // Test case for valid expense transaction
        String expenseTransactionString = "expense | Grocery | 12-11-2022 | 50.00 | 2";
        Transaction expenseTransaction = parser.parseEditTransaction(expenseTransactionString, account);
        assertEquals("Grocery", expenseTransaction.getDescription());
        assertEquals(-50.00, expenseTransaction.getAmount(), 0.001);
        assertEquals("Groceries", expenseTransaction.getCategory().getCategoryName());
        assertEquals(LocalDate.parse("12-11-2022", DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                expenseTransaction.getDate());

    }




}
