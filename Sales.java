
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// It is all about sales....................
public class Sales {
    static ArrayList<String> MedicineID = new ArrayList<>();
    static ArrayList<String> ProductName = new ArrayList<>();
    static ArrayList<String> Stock = new ArrayList<>();
    static ArrayList<String> Price = new ArrayList<>();
    static ArrayList<String> ExpiryDate = new ArrayList<>();
    static String CustName, TodayDate;
    static int TotalSales, noOfProducts;
    static String[] nameOfProduct;
    static int[] amount, individualPrice;

    public String readFile() throws IOException {
        File Sales = new File("Sales.csv");
        Scanner inputFile = new Scanner(Sales);
        String saveFile = "";
        while (inputFile.hasNext()) {
            saveFile += inputFile.nextLine() + "\n";
        }
        return saveFile;
    }

    public void readInventoryData() {
        MedicineID = Display_inventory.getMedicineID();
        ProductName = Display_inventory.getProductName();
        Stock = Display_inventory.getStock();
        Price = Display_inventory.getPrice();
        ExpiryDate = Display_inventory.getExpiryDate();
    }

    public void buyProducts() throws IOException {
        readInventoryData();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        CustName = sc.next();
        System.out.println("Enter today's date:");
        TodayDate = sc.next();
        System.out.println("Enter the number of products to buy:");
        noOfProducts = sc.nextInt();
        nameOfProduct = new String[noOfProducts];
        amount = new int[noOfProducts];
        individualPrice = new int[noOfProducts];
        for (int i = 0; i < noOfProducts; i++) {
            System.out.println("Enter the name of product " + (i + 1) + ":");
            nameOfProduct[i] = sc.next();
        }
        int cnt = 0;
        int checkForStock = 0;

        for (String i : ProductName) {
            int stock;
            for (int j = 0; j < noOfProducts; j++) {
                if (i.equalsIgnoreCase(nameOfProduct[j])) {
                    System.out.println("Enter the amount of medicine you want for " + i + ":");
                    amount[j] = sc.nextInt();
                    stock = Integer.parseInt(Stock.get(cnt));
                    stock -= amount[j];
                    individualPrice[j] = Integer.parseInt(Price.get(cnt));
                    TotalSales += amount[j] * individualPrice[j];
                    if (stock > 0) {

                        Stock.set(cnt, "" + stock);
                        checkForStock *= 0;
                    } else {
                        checkForStock += 1;

                    }

                }

            }
            cnt++;
        }

        if ((checkForStock > 0)) {
            System.out.println("Out of the stock for this product!!");
        } else {
            System.out.println("Go for buying!!");
        }

        cnt = 0;
        FileWriter out = new FileWriter("Medicine_Inventory.csv");
        for (String i : MedicineID) {

            out.write(i + "," + ProductName.get(cnt) + "," + Stock.get(cnt) + "," + Price.get(cnt) + ","
                    + ExpiryDate.get(cnt) + "\n");
            cnt++;
        }

        out.close();
    }

    public void generateBill() throws IOException {
        FileWriter out = new FileWriter("Bills of "+CustName);
        out.write("Name:" + CustName + "\nDate:" + TodayDate + "\n====================BILL==================");
        out.write("\nNo.ProductName\tQuantity\tPrice\ttotal\n");
        for (int i = 0; i < noOfProducts; i++) {
            out.write((i + 1) + "  " + nameOfProduct[i] + ((nameOfProduct[i].length() > 7) ? "\t" : "\t\t") + amount[i]
                    + "\t\t\t" + individualPrice[i] + "\t\t" + (amount[i] * individualPrice[i]) + "\n");
        }
        out.write("\n=========================================");
        out.write("\nGrand Total\t\t\t\t\t" + TotalSales);
        out.close();

    }

    public void generateSalesRecord() throws IOException {
        File sales = new File("Sales.csv");
        String saveFile = readFile();
        FileWriter out = new FileWriter(sales);
        out.write(saveFile);
        out.write(TodayDate + "," + CustName + "," + TotalSales);
        out.close();

    }

}
