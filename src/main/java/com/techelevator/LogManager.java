package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogManager {
    private PrintWriter copyTransactionToLog;

    public void copyTransactionToLog(String logLocation) {
        try {
            copyTransactionToLog = new PrintWriter(new FileWriter(logLocation, true), true);
            copyTransactionToLog.append("----------------------------------------------------------------------------\nSTART OF NEW TRANSACTION\n----------------------------------------------------------------------------\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logFeedMoney(BigDecimal inputMoney, BigDecimal currentMoney) {

        copyTransactionToLog.append(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a").format(LocalDateTime.now()) + " FEED MONEY: " + NumberFormat.getCurrencyInstance(Locale.US).format(inputMoney) + " " + NumberFormat.getCurrencyInstance(Locale.US).format(currentMoney) + "\n");
        copyTransactionToLog.flush();
    }

    public void logMakePurchase(BigDecimal productPrice, BigDecimal currentMoney, String productName, String slotIdentifier) {

        copyTransactionToLog.append(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a").format(LocalDateTime.now()) + " " + productName + " " + slotIdentifier + " " + NumberFormat.getCurrencyInstance(Locale.US).format(productPrice) + " " + NumberFormat.getCurrencyInstance(Locale.US).format(currentMoney) + "\n");
        copyTransactionToLog.flush();
    }

    public void logGiveChange(BigDecimal changeTotal, BigDecimal currentMoney) {

        copyTransactionToLog.append(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a").format(LocalDateTime.now()) + " GIVE CHANGE: " + NumberFormat.getCurrencyInstance(Locale.US).format(changeTotal) + " " + NumberFormat.getCurrencyInstance(Locale.US).format(currentMoney) + "\n");
        copyTransactionToLog.flush();
    }

    public void close() {
        copyTransactionToLog.append("----------------------------------------------------------------------------\nCLOSE OF TRANSACTION\n----------------------------------------------------------------------------\n");
        copyTransactionToLog.close();
    }
}
