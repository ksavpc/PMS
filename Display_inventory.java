
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Display_inventory {
    static ArrayList<String> MedicineID = new ArrayList<>();
    static ArrayList<String> ProductName = new ArrayList<>();
    static ArrayList<String> Stock = new ArrayList<>();
    static ArrayList<String> Price = new ArrayList<>();
    static ArrayList<String> ExpiryDate = new ArrayList<>();

    // reads the inventory file
    public static void readFile() throws FileNotFoundException {
        File Inventoryfile = new File("Medicine_Inventory.csv");
        Scanner inputFile = new Scanner(Inventoryfile);
        while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            String[] arr = line.split(",");
            MedicineID.add(arr[0]);
            ProductName.add(arr[1]);
            Stock.add(arr[2]);
            Price.add(arr[3]);
            ExpiryDate.add(arr[4]);
        }
    }

    // Displaying the inventory
    public static void displayInventory() throws FileNotFoundException {
        Display_inventory.readFile();
        System.out.println("\t\t\t\t\tHere are the details of medicine in the Pharmacy:");
        System.out.println("==========================================================================");
        int cnt = 0;
        for (String i : MedicineID) {

            System.out.println(i + ((i.length() > 5) ? "\t\t" : "\t\t\t\t") + ProductName.get(cnt)
                    + ((ProductName.get(cnt).length() < 8) ? "\t\t\t" : "\t\t") + Stock.get(cnt)
                    + ((Stock.get(cnt).length() > 8) ? "\t" : "\t\t\t\t\t") + Price.get(cnt)
                    + ((ExpiryDate.get(cnt).contains("ExpiryDate")) ? "\t\t" : "\t\t\t") + ExpiryDate.get(cnt));
            cnt++;
        }
        System.out.println("==========================================================================");
    }

    // Search the medicine with Unique ID
    public void search() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=================Searching Here=================");
        System.out.println("Enter the MedicineID of the medicine you want to search:");
        String MED_ID = sc.nextLine();

        int cnt = 0;
        int check = 0;
        for (String i : MedicineID) {
            if (i.equals(MED_ID)) {
                System.out.println("Medicine ID:\t" + i + "\nProduct Name:\t" + ProductName.get(cnt)
                        + "\nStock amount:\t" + Stock.get(cnt) + "\nPrice:\t\t\t" + Price.get(cnt) + "\nExpiry Date:\t"
                        + ExpiryDate.get(cnt));
                check *= 0;
                break;
            } else {
                check += 1;
            }
            cnt++;
        }

        if (check > 0) {
            System.out.println("Sorry, we don't have that item!!");
        }
    }

    public static ArrayList<String> getMedicineID() {
        return MedicineID;
    }

    public static ArrayList<String> getProductName() {
        return ProductName;
    }

    public static ArrayList<String> getStock() {
        return Stock;
    }

    public static ArrayList<String> getPrice() {
        return Price;
    }

    public static ArrayList<String> getExpiryDate() {
        return ExpiryDate;
    }
}
