import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Inventory inventory = new Inventory();

        // To add new products into the inventory
        System.out.println("Do you want to add a product? (Y/N)");
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        if (response.toUpperCase().equals("Y")) {
            inventory.addToInventory();
        }

        // To display the all details of inventory
        System.out.println("Do you want to display the inventory? (Y/N)");
        if (sc.nextLine().toUpperCase().equals("Y")) {
            Display_inventory.displayInventory();
        }

        // To search the medicine
        System.out.println("Do you want to search a product? (Y/N)");
        if (sc.nextLine().toUpperCase().equals("Y")) {
            Display_inventory displaySearch = new Display_inventory();
            displaySearch.search();
        }

        // To buy the medicine and to maintain the stock into the Pharmacy
        Sales sales = new Sales();
        System.out.println("Do you want to buy products? (Y/N)");
        if (sc.nextLine().toUpperCase().equals("Y")) {
            sales.buyProducts();
        }
        // To check the expired date of products and to check the stock below threshold
        Notification notification = new Notification();
        notification.CompareDate();
        notification.CompareStock();

        // To generate sales record for the user
        sales.generateSalesRecord();
        sales.generateBill();

        /* generating barchart */
        GraphCharts example = new GraphCharts("Bar Chart");
        example.setSize(1000, 500);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);
    }
}
