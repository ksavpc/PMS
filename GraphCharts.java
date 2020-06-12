import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphCharts extends JFrame {
    static int noOfSalesData;

    public GraphCharts(String appTitle) throws FileNotFoundException {
        super(appTitle);

        // Create Dataset
        CategoryDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createBarChart("Sales Record ", // Chart Title
                "Fields", // Category axis
                "Sales in Rs", // Value axis
                dataset, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public String[][] scanningData_Sales() throws FileNotFoundException {
        File Sales = new File("Sales.csv");
        Scanner inputFile = new Scanner(Sales);
        Scanner input = new Scanner(Sales);
        while (inputFile.hasNext()) {
            inputFile.nextLine();
            noOfSalesData++;
        }
        String[][] SalesData = new String[noOfSalesData - 1][3];

        for (int i = 0; i < noOfSalesData; i++) {
            String line = input.nextLine();
            String[] arr = line.split(",");
            if (i == 0)
                continue;

            for (int j = 0; j < 3; j++) {
                SalesData[i - 1][j] = arr[j];

            }
        }

        inputFile.close();
        input.close();
        return SalesData;
    }

    private CategoryDataset createDataset() throws FileNotFoundException {
        String[][] data = scanningData_Sales();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String[] salesDatum : data) {
            dataset.addValue(Double.parseDouble(salesDatum[2]), "Sales", (salesDatum[1] + ":" + salesDatum[0]));
        }

        return dataset;
    }

}