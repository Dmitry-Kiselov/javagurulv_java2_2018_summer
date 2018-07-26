package lv.javaguru.java2;

import lv.javaguru.java2.database.Database;
import lv.javaguru.java2.database.JDBCDatabaseImpl;
import lv.javaguru.java2.servises.AddProductService;
import lv.javaguru.java2.servises.PrintProductService;
import lv.javaguru.java2.servises.ProductValidator;
import lv.javaguru.java2.servises.ProductValidatorImpl;
import lv.javaguru.java2.servises.RemoveProductService;
import lv.javaguru.java2.views.AddProductView;
import lv.javaguru.java2.views.ConsoleView;
import lv.javaguru.java2.views.ExitView;
import lv.javaguru.java2.views.PrintProductListView;
import lv.javaguru.java2.views.RemoveProductView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShoppingListApplication {

    public static void main(String[] args) {
        // Use cases:
        // 1. Add product to list
        // 2. Remove product from list
        // 3. Print shopping list to console
        // 4. Exit

        Database database = new JDBCDatabaseImpl();

        ProductValidator productValidator = new ProductValidatorImpl(database);

        AddProductService addProductService = new AddProductService(productValidator, database);
        RemoveProductService removeProductService = new RemoveProductService(database);
        PrintProductService printProductService = new PrintProductService(database);

        AddProductView addProductView = new AddProductView(addProductService);
        RemoveProductView removeProductView = new RemoveProductView(removeProductService);
        PrintProductListView printProductListView = new PrintProductListView(printProductService);

        Map<Integer, ConsoleView> menuMap = new HashMap<>();
        menuMap.put(1, addProductView);
        menuMap.put(2, removeProductView);
        menuMap.put(3, printProductListView);
        menuMap.put(4, new ExitView());

        while (true) {
            printProgramMenu();
            int menuItem = getFromUserMenuItemToExecute();
            ConsoleView consoleView = menuMap.get(menuItem);
            consoleView.execute();
        }

    }

    private static void printProgramMenu() {
        System.out.println("Program Menu:");
        System.out.println("1. Add product to list");
        System.out.println("2. Remove product from list");
        System.out.println("3. Print list to console");
        System.out.println("4. Exit");
    }

    private static int getFromUserMenuItemToExecute() {
        System.out.print("Please enter menu item number to execute:");
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

}
