import entity.Product;

import java.util.Scanner;

public class Main {

    static MyProduct myProduct = new MyProduct();
	
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            showMenu();
            Scanner sc = new Scanner(System.in);
            int chooseNumber = Integer.parseInt(sc.nextLine());
            switch (chooseNumber) {
                case 1:
                    System.out.println("Enter product's information(Code, Name, Quantity, Saled, Price): ");
                    String[] productArr = sc.nextLine().split(",");
                    Product product = new Product(productArr[0].trim(), productArr[1].trim(), Integer.parseInt(productArr[2].trim()),
                            Integer.parseInt(productArr[3].trim()), Double.parseDouble(productArr[4].trim()));
                    myProduct.insert(product);
                    break;
                case 2:
                    System.out.printf("%-10s%-20s%-10s%-10s%-10s%n", "Code", "Name", "Quantity" , "Saled" , "Price");
                    myProduct.inOrder();
                    break;
                case 3:
                    System.out.printf("%-10s%-20s%-10s%-10s%-10s%n", "Code", "Name", "Quantity" , "Saled" , "Price");
                    myProduct.BFT();
                    break;
                case 4:
                    System.out.println("Enter product's code: ");
                    String pCode = sc.nextLine();
                    System.out.printf("%-10s%-20s%-10s%-10s%-10s%n", "Code", "Name", "Quantity" , "Saled" , "Price");
                    myProduct.search(pCode);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Total number of products are: " + myProduct.size());
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }
    }

    public static void showMenu() throws NumberFormatException {
        String menu = "Product list: \n" +
                "1. Insert a new product\n" +
                "2. In-order travel\n" +
                "3. Breath first travel\n" +
                "4. Search by a product code\n" +
                "5. Delete by a product code\n" +
                "6. Simple balancing\n" +
                "7. Count number of products\n" +
                "0. Exit\n" +
                "Your choice: ";
        System.out.println(menu);
    }
}
