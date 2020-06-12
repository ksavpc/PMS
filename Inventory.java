import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// All about the inveentory in this section about maintenance of Pharmacy Management System 
public class Inventory {
    static ArrayList<String> MedicineID = new ArrayList<>();
    static ArrayList<String> ProductName = new ArrayList<>();
    static ArrayList<String> Stock = new ArrayList<>();
    static ArrayList<String> Price = new ArrayList<>();
    static ArrayList<String> ExpiryDate = new ArrayList<>();
    static File Inventoryfile;

    // TO read the inventory
    public static void readFile() throws FileNotFoundException {
        Inventoryfile = new File("Medicine_Inventory.csv");
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

    // It helps to add medicine into the inventory
    public void addToInventory() throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of Products to add to the inventory:");
        int noOfProducts = sc.nextInt();
        String[] addedData = new String[noOfProducts];
        System.out.println("Enter the MedicineID,ProductName,Stock,Price,ExpiryDate:");
        for (int i = 0; i < noOfProducts; i++) {
            addedData[i] = sc.next();
        }
        Inventory.readFile();
        FileWriter out = new FileWriter(Inventoryfile);

        int cnt = 0;
        for (String i : MedicineID) {

            out.write(i + "," + ProductName.get(cnt) + "," + Stock.get(cnt) + "," + Price.get(cnt) + ","
                    + ExpiryDate.get(cnt) + "\n");
            cnt++;
        }
        for (int i = 0; i < noOfProducts; i++) {
            out.write(addedData[i] + "\n");
        }
        out.close();
    }

}
