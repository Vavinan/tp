package budgetbuddy.storage;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;
import budgetbuddy.exceptions.InvalidCategoryException;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Transaction;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class DataStorageTest {
    @Test
    public void testSaveTransactions() throws IOException, InvalidCategoryException {
        DataStorage dataStorage = new DataStorage();
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        Transaction t = new Expense(1, "test","Groceries", 50.0,
                "25-03-2024", new Account(1));
        t.setCategory(Category.fromNumber(1));
        transactionArrayList.add(t);

        try {
            dataStorage.readTransactionFile(existingAccountNumbers);
            dataStorage.saveTransactions(transactionArrayList);

            File file = new File(DataStorage.TRANSACTIONS_FILE_PATH);
            assertTrue(file.exists()); // Check if file exists after saving transactions
        } catch (IOException e) {
            fail("Exception thrown while saving transactions: " + e.getMessage());
        } finally {
            FileWriter fw = new FileWriter(DataStorage.TRANSACTIONS_FILE_PATH, false);
        }
    }

    @Test
    public void testReadFromFile() throws IOException, InvalidCategoryException {
        DataStorage dataStorage = new DataStorage();
        ArrayList<Transaction> expectedTransactions = new ArrayList<>();
        Transaction t = new Expense( 1, "test","Groceries", 50.0,
                "25-03-2024", new Account(1));
        t.setCategory(Category.fromNumber(1));
        expectedTransactions.add(t);
        dataStorage.readTransactionFile(existingAccountNumbers);
        dataStorage.saveTransactions(expectedTransactions);
        try {
            ArrayList<Transaction> actualTransactions = dataStorage.readTransactionFile(existingAccountNumbers);

            // Check if read transactions match the expected transactions
            assertEquals(expectedTransactions.size(), actualTransactions.size());
            for (int i = 0; i < expectedTransactions.size(); i++) {
                assertEquals(expectedTransactions.get(i).getDescription(), actualTransactions.get(i).getDescription());
                assertEquals(expectedTransactions.get(i).getAmount(),
                        actualTransactions.get(i).getAmount(), 0.001);
                assertEquals(expectedTransactions.get(i).getDate(), actualTransactions.get(i).getDate());
                assertEquals(expectedTransactions.get(i).getTransactionType(),
                        actualTransactions.get(i).getTransactionType());
                assertEquals(expectedTransactions.get(i).getCategory(), actualTransactions.get(i).getCategory());
            }
        } catch (IOException e) {
            fail("Exception thrown while reading from file: " + e.getMessage());
        } finally {
            FileWriter fw = new FileWriter(DataStorage.TRANSACTIONS_FILE_PATH, false);
        }
    }
}
