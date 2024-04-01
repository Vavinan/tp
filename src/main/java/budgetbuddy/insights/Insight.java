package budgetbuddy.insights;

import budgetbuddy.categories.Category;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


public class Insight {

    public static void displayCategoryInsight(ArrayList<Transaction> transactionArrayList) {
        Category[] categoryArray = Category.values();
        Double[] expenseArray =  new Double[categoryArray.length];
        Double[] incomeArray =  new Double[categoryArray.length];

        for (int i = 0; i < categoryArray.length; i++) {
            expenseArray[i] =  0.0;
            incomeArray[i] =  0.0;
        }
        for(Transaction t : transactionArrayList){
            Category category = t.getCategory();
            int index = indexOf(categoryArray, category);
            if (t instanceof Expense) {
                expenseArray[index] += abs(t.getAmount());
            } else if (t instanceof Income){
                incomeArray[index] += abs(t.getAmount());
            }
        }
    }


    private static int indexOf(Category[] array, Category target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

}