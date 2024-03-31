package budgetbuddy.categories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    @Test
    public void testGetCategoryName() {
        assertEquals("Dining", Category.DINING.getCategoryName());
        assertEquals("Groceries", Category.GROCERIES.getCategoryName());
        assertEquals("Utilities", Category.UTILITIES.getCategoryName());
        assertEquals("Transportation", Category.TRANSPORTATION.getCategoryName());
        assertEquals("Healthcare", Category.HEALTHCARE.getCategoryName());
        assertEquals("Entertainment", Category.ENTERTAINMENT.getCategoryName());
        assertEquals("Rent", Category.RENT.getCategoryName());
        assertEquals("Salary", Category.SALARY.getCategoryName());
        assertEquals("Others", Category.OTHERS.getCategoryName());
    }
}
