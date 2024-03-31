package budgetbuddy.storage;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Transaction;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DataStorageTest {
    @Test
    public void testSaveTransactions() {
        DataStorage dataStorage = new DataStorage();
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        Transaction t = new Expense("Groceries", 50.0, "25-03-2024", new Account(1));
        t.setCategory(Category.fromNumber(1));
        transactionArrayList.add(t);

        try {
            dataStorage.saveTransactions(transactionArrayList);

            File file = new File(DataStorage.STORAGE_FILE_PATH);
            assertTrue(file.exists()); // Check if file exists after saving transactions
        } catch (IOException e) {
            fail("Exception thrown while saving transactions: " + e.getMessage());
        }
    }
}
