package budgetbuddy.insights;

import budgetbuddy.categories.Category;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

//@@author ShyamKrishna33
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
        displayPieChart(categoryArray, incomeArray, expenseArray);
    }

    private static void displayPieChart(Category[] categoryArray, Double[] incomeArray, Double[] expenseArray) {
        JFrame incomeFrame = new JFrame("Income Insights");
        incomeFrame.setLayout(new BorderLayout());
        JFrame expenseFrame = new JFrame("Expense Insights");
        expenseFrame.setLayout(new BorderLayout());

        JPanel incomePanel = new JPanel();
        incomeFrame.add(incomePanel, BorderLayout.CENTER);
        JPanel expensePanel = new JPanel();
        expenseFrame.add(expensePanel, BorderLayout.CENTER);

        PieChart chart1 = new PieChartBuilder().width(800).height(600).title("Income Divide").build();

        // Customize Chart
        chart1.getStyler().setCircular(true);
        chart1.getStyler().setLegendVisible(true);
        chart1.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);

        for (int i = 0; i < categoryArray.length; i++) {
            if (incomeArray[i] != 0) {
                chart1.addSeries(categoryArray[i].getCategoryName(), incomeArray[i]);
            }
        }

        // Show it
        incomePanel.add(new XChartPanel<>(chart1));
        incomeFrame.pack();
        incomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        incomeFrame.setVisible(true);

        PieChart chart2 = new PieChartBuilder().width(800).height(600).title("Expense Divide").build();

        // Customize Chart
        chart2.getStyler().setCircular(true);
        chart2.getStyler().setLegendVisible(true);
        chart2.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);

        for (int i = 0; i < categoryArray.length; i++) {
            if (expenseArray[i] != 0) {
                chart2.addSeries(categoryArray[i].getCategoryName(), expenseArray[i]);
            }
        }

        // Show it
        expensePanel.add(new XChartPanel<>(chart2));
        expenseFrame.pack();
        expenseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        expenseFrame.setVisible(true);

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
