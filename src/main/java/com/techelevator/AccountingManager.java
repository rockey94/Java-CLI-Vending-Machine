package com.techelevator;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;


public class AccountingManager {


    private static Scanner userInput = new Scanner(System.in);
    private BigDecimal currentMoney = new BigDecimal(0.00);
    private int productsPurchasedCount = 0;

    protected LogManager log = new LogManager();

    public AccountingManager() {
        String logLocation = "src/log.txt";
        log.copyTransactionToLog(logLocation);
    }

    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    public void feedMoney() {
        System.out.println("Please feed money in whole dollar amounts only");
        String userDollarInputString = userInput.nextLine();
        while (!userDollarInputString.matches("[0-9]+")) {
            System.out.println("Please enter a dollar amount in whole dollars.");
            userDollarInputString = userInput.nextLine();
        }

        Integer inputMoneyWholeDollar = Integer.parseInt(userDollarInputString);

        BigDecimal inputMoney = new BigDecimal(inputMoneyWholeDollar);
        currentMoney = currentMoney.add(inputMoney);

        log.logFeedMoney(inputMoney, currentMoney);
    }

    public void finishTransaction() {
        BigDecimal changeTotal = new BigDecimal(currentMoney.toString());
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        final BigDecimal QUARTER_VALUE = new BigDecimal("0.25");
        final BigDecimal DIME_VALUE = new BigDecimal("0.10");
        final BigDecimal NICKEL_VALUE = new BigDecimal("0.05");

        while (currentMoney.compareTo(QUARTER_VALUE) >= 0) {
            currentMoney = currentMoney.subtract(QUARTER_VALUE);
            quarterCount++;
        }
        while (currentMoney.compareTo(DIME_VALUE) >= 0) {
            currentMoney = currentMoney.subtract(DIME_VALUE);
            dimeCount++;
        }
        while (currentMoney.compareTo(NICKEL_VALUE) >= 0) {
            currentMoney = currentMoney.subtract(NICKEL_VALUE);
            nickelCount++;
        }

        log.logGiveChange(changeTotal, currentMoney);
        System.out.println("Your change total is " + NumberFormat.getCurrencyInstance(Locale.US).format(changeTotal) + ". This converts into " + quarterCount + " quarter(s), " + dimeCount + " dime(s), and " + nickelCount + " nickel(s).");

    }

    public void purchaseItemAugustSpecial(BigDecimal itemPrice, String itemName, String itemLocation) {

        if ((productsPurchasedCount + 1) % 2 == 0) {
            System.out.println("$$$$$$$$$$$$$ BOGODO! One Dollar Off Purchase $$$$$$$$$$$$$");
            BigDecimal dollarOff = new BigDecimal("1");
            currentMoney = currentMoney.subtract(itemPrice.subtract(dollarOff));

            log.logMakePurchase(itemPrice.subtract(dollarOff), currentMoney, itemName, itemLocation);
            productsPurchasedCount++;
        } else if ((productsPurchasedCount % 2) == 0 || productsPurchasedCount == 0) {
            currentMoney = currentMoney.subtract(itemPrice);
            productsPurchasedCount++;

            log.logMakePurchase(itemPrice, currentMoney, itemName, itemLocation);
        }
    }
}
