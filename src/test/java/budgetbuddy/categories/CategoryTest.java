package budgetbuddy.categories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    public void testGetCategoryNum() {
        assertEquals(1, Category.DINING.getCategoryNum());
        assertEquals(2, Category.GROCERIES.getCategoryNum());
        assertEquals(3, Category.UTILITIES.getCategoryNum());
        assertEquals(4, Category.TRANSPORTATION.getCategoryNum());
        assertEquals(5, Category.HEALTHCARE.getCategoryNum());
        assertEquals(6, Category.ENTERTAINMENT.getCategoryNum());
        assertEquals(7, Category.RENT.getCategoryNum());
        assertEquals(8, Category.SALARY.getCategoryNum());
        assertEquals(9, Category.OTHERS.getCategoryNum());
    }

    @Test
    public void testFromNumber() {
        assertEquals(Category.DINING, Category.fromNumber(1));
        assertEquals(Category.GROCERIES, Category.fromNumber(2));
        assertEquals(Category.UTILITIES, Category.fromNumber(3));
        assertEquals(Category.TRANSPORTATION, Category.fromNumber(4));
        assertEquals(Category.HEALTHCARE, Category.fromNumber(5));
        assertEquals(Category.ENTERTAINMENT, Category.fromNumber(6));
        assertEquals(Category.RENT, Category.fromNumber(7));
        assertEquals(Category.SALARY, Category.fromNumber(8));
        assertEquals(Category.OTHERS, Category.fromNumber(9));
        assertNull(Category.fromNumber(10)); // Non-existing number
    }
}
