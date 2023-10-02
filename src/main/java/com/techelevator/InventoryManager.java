package com.techelevator;

import com.techelevator.VendingMachineProduct;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryManager {

    // Methods
    public static Map<String, VendingMachineProduct> createInventoryMap(File vendingMachineInventory) {
        LinkedHashMap<String, VendingMachineProduct> inventoryListMap = new LinkedHashMap<>();
        try {
            Scanner vendingMachineInventoryScanner = new Scanner(vendingMachineInventory);
            while (vendingMachineInventoryScanner.hasNextLine()) {
                String[] currentLineItem = vendingMachineInventoryScanner.nextLine().split(",");
                inventoryListMap.put(currentLineItem[0], (new VendingMachineProduct(currentLineItem[0], currentLineItem[1], new BigDecimal(currentLineItem[2]), currentLineItem[3], 5)));
            }
        } catch (Exception e) {
            System.out.println("Please submit a valid working file. WARNING: IMPORTING VALID ITEMS UP TO ERROR. YOUR VENDING MACHINE WILL NOT BE STOCKED AS INTENDED");
        }
        return inventoryListMap;
    }

    public static void printCurrentInventory(LinkedHashMap<String, VendingMachineProduct> inventoryListMap) {
        System.out.println("------------------------------------------------------------");
        for (Map.Entry<String, VendingMachineProduct> currentProduct : inventoryListMap.entrySet()) {
            System.out.println(currentProduct.getValue().toString());
        }
    }

}
