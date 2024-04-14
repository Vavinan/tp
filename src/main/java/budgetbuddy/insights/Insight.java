package budgetbuddy.insights;

import budgetbuddy.categories.Category;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.PieStyler;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.Window;

import static java.lang.Math.abs;

public class Insight {
    //@@author ShyamKrishna33
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

    //@@author
    private static void displayPieChart(Category[] categoryArray, Double[] incomeArray, Double[] expenseArray) {
        JFrame incomeFrame = new JFrame("Income Insights");
        incomeFrame.setLayout(new BorderLayout());
        JFrame expenseFrame = new JFrame("Expense Insights");
        expenseFrame.setLayout(new BorderLayout());

        JPanel incomePanel = new JPanel();
        incomeFrame.add(incomePanel, BorderLayout.CENTER);
        JPanel expensePanel = new JPanel();
        expenseFrame.add(expensePanel, BorderLayout.CENTER);

        PieChart incomeChart = new PieChartBuilder().width(800).height(600).title("Income Divide").build();

        // Customize Chart
        incomeChart.getStyler().setCircular(true);
        incomeChart.getStyler().setLegendVisible(true);
        incomeChart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);

        for (int i = 0; i < categoryArray.length; i++) {
            if (incomeArray[i] != 0) {
                incomeChart.addSeries(categoryArray[i].getCategoryName(), incomeArray[i]);
            }
        }

        // Show it
        incomePanel.add(new XChartPanel<>(incomeChart));
        incomeFrame.pack();
        incomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        incomeFrame.setVisible(true);

        PieChart expenseChart = new PieChartBuilder().width(800).height(600).title("Expense Divide").build();

        // Customize Chart
        expenseChart.getStyler().setCircular(true);
        expenseChart.getStyler().setLegendVisible(true);
        expenseChart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);

        for (int i = 0; i < categoryArray.length; i++) {
            if (expenseArray[i] != 0) {
                expenseChart.addSeries(categoryArray[i].getCategoryName(), expenseArray[i]);
            }
        }

        // Show it
        expensePanel.add(new XChartPanel<>(expenseChart));
        expenseFrame.pack();
        expenseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        expenseFrame.setVisible(true);

    }

    //@@author ShyamKrishna33
    private static int indexOf(Category[] array, Category target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    //@@author Vavinan
    public static void closeInsightFrames() {
        // Close any open insight frames here
        for (Window window : Window.getWindows()) {
            if (window instanceof JFrame) {
                String title = ((JFrame) window).getTitle();
                if (title != null && (title.equals("Income Insights") || title.equals("Expense Insights"))) {
                    window.dispose();
                }
            }
        }
    }
    //@@author 

}
