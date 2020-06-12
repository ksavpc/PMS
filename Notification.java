import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Notification {
    ArrayList<String> Stock = new ArrayList<>();
    ArrayList<String> ExpiryDate = new ArrayList<>();
    static int lineCountInventory, thresholdForStock;
    static ArrayList<String> ProductName = new ArrayList<>();

    public void readFile() throws FileNotFoundException {
        File Inventory_file = new File("Medicine_Inventory.csv");
        Scanner inputFile = new Scanner(Inventory_file);
        while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            lineCountInventory++;
            String[] arr = line.split(",");
            ProductName.add(arr[1]);
            Stock.add(arr[2]);
            if (arr[2].contains("limit")) {
                String[] array = arr[2].split("=");
                thresholdForStock = Integer.parseInt(array[1].substring(0, array[1].length() - 1));
            }
            ExpiryDate.add(arr[4]);
        }
    }

    public void CompareDate() throws Exception {
        readFile();
        int check = 0;
        String pattern = "yyyy/MM/dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        Date currentDate = simpleDateFormat.parse(date);
        Date[] dates = new Date[lineCountInventory - 1];
        for (int i = 1; i < lineCountInventory; i++) {
            dates[i - 1] = simpleDateFormat.parse(ExpiryDate.get(i));
        }
        System.out.println("**********************Checking the Expiry Date************************** ");

        for (int i = 1; i < lineCountInventory; i++) {
            if (dates[i - 1].compareTo(currentDate) < 0) {
                System.out.println(ProductName.get(i) + " is expired!!");
                check++;
            }
        }
        if (check == 0) {
            System.out.println("None are expried");
        }

    }

    public void CompareStock() {
        int check = 0;
        System.out.println("**********************Checking Stock Availability************************** ");
        for (int i = 1; i < lineCountInventory; i++) {
            if (Integer.parseInt(Stock.get(i)) < thresholdForStock) {
                System.out.println(ProductName.get(i) + " to be added!!");
                check++;
            }
        }
        if (check == 0) {
            System.out.println("Stock is fine.");
        }
    }

}
