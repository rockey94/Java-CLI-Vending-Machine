package com.techelevator;

import java.io.File;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Scanner;

public class VendingMachineCLI {
    public static void main(String[] args) {
        // Inventory Related Variables
        LinkedHashMap<String, VendingMachineProduct> inventoryListMap = new LinkedHashMap<>();

        // Accounting Related Variables
        AccountingManager accountingClass = new AccountingManager();

        // booleans used to switch between menus
        boolean usingMenu1 = true;
        boolean inputMoneyOption = false;
        boolean fileDoesntExist = true;
        // Scanners
        Scanner userInput = new Scanner(System.in);


        // File reader and converter for Inventory
        while (fileDoesntExist) {
            System.out.println("Please enter the file path for the vending machine inventory: ");
            String filePath = userInput.nextLine();
            File vendingMachineInventory = new File(filePath);
            if (vendingMachineInventory.exists()) {
                inventoryListMap.putAll(InventoryManager.createInventoryMap(vendingMachineInventory));
                fileDoesntExist = false;
            } else {
                System.out.println("Not a valid file path.");
            }
        }

        // Menu 1
        while (usingMenu1) {
            System.out.println("------------------------------------------------------------\n(1) Display Vending Machine Items \n(2) Purchase\n(3) Exit\n------------------------------------------------------------\nPlease select an option: ");
            String menu1Choice = userInput.nextLine();
            switch (menu1Choice) {
                case "1": // Display Vending Machine Items
                    InventoryManager.printCurrentInventory(inventoryListMap);
                    break;

                case "2": // Purchase
                    //Menu 2
                    usingMenu1 = false;

                    while (!usingMenu1 || inputMoneyOption) {
                        inputMoneyOption = false;
                        System.out.println("------------------------------------------------------------\nCurrent Money Provided " + NumberFormat.getCurrencyInstance(Locale.US).format(accountingClass.getCurrentMoney())
                                + "\n(1) Feed Money \n(2) Select Product\n(3) Finish Transaction\n------------------------------------------------------------\nPlease select an option: ");
                        String usingMenu2Choice = userInput.nextLine();

                        switch (usingMenu2Choice) {
                            case "1": // Feed Money
                                accountingClass.feedMoney();
                                inputMoneyOption = true;
                                break;
                            case "2": // Select Product
                                InventoryManager.printCurrentInventory(inventoryListMap);
                                System.out.println("------------------------------------------------------------\nPlease enter a code to select an item\n------------------------------------------------------------");
                                String requestedItem = userInput.nextLine().toUpperCase();
                                if (!inventoryListMap.containsKey(requestedItem)) {
                                    System.out.println("This product code does not exist. Please make another selection.");
                                } else if (inventoryListMap.get(requestedItem).getProductQuantityRemaining() == 0) {
                                    System.out.println("Sorry, this item is currently sold out. Please make another selection.");
                                } else if (accountingClass.getCurrentMoney().compareTo(inventoryListMap.get(requestedItem).getProductPrice()) < 0) {
                                    System.out.println("Insufficient funds to make selected purchase. PLease add more money or select a different item.");
                                } else if (inventoryListMap.containsKey(requestedItem)) {
                                    int removeItem = inventoryListMap.get(requestedItem).getProductQuantityRemaining() - 1;
                                    inventoryListMap.get(requestedItem).setProductQuantityRemaining(removeItem);

                                    switch (inventoryListMap.get(requestedItem).getSnackType()) {
                                        case "Munchy":
                                            System.out.println("Crunch Crunch, Yum!");
                                            break;
                                        case "Candy":
                                            System.out.println("Yummy Yummy, So Sweet!");
                                            break;
                                        case "Drink":
                                            System.out.println("Glug Glug, Yum!");
                                            break;
                                        case "Gum":
                                            System.out.println("Chew Chew, Yum!");
                                            break;
                                    }
                                    accountingClass.purchaseItemAugustSpecial(inventoryListMap.get(requestedItem).getProductPrice(), inventoryListMap.get(requestedItem).getProductName(), inventoryListMap.get(requestedItem).getSlotIdentifier());
                                }
                                break;
                            case "3": // Finish Transaction
                                accountingClass.finishTransaction();
                                usingMenu1 = true;
                        }
                    }
                    break;
                case "3": // Exit
                    accountingClass.log.close();
                    System.out.println("Thank you for using the vending machine, have a great day!");
                    System.exit(1);
            }
        }
    }
}