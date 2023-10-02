package com.techelevator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class VendingMachineProduct {
    // Instance Variables
    private String slotIdentifier;
    private String productName;
    private BigDecimal productPrice;
    private String snackType;
    private int productQuantityRemaining;

    // Constructor

    public VendingMachineProduct(String slotIdentifier, String productName, BigDecimal productPrice, String snackType, int productQuantityRemaining) {
        this.slotIdentifier = slotIdentifier;
        this.productName = productName;
        this.productPrice = productPrice;
        this.snackType = snackType;
        this.productQuantityRemaining = productQuantityRemaining;
    }

    // Methods

    @Override
    public String toString() {
        if (productQuantityRemaining == 0) {
            return slotIdentifier + " || " + productName + " || " + NumberFormat.getCurrencyInstance(Locale.US).format(productPrice) +
                    " || " + snackType + " || " +
                    " SOLD OUT";
        } else
            return slotIdentifier + " || " + productName + " || " + NumberFormat.getCurrencyInstance(Locale.US).format(productPrice) +
                    " || " + snackType + " || " +
                    productQuantityRemaining +
                    " Left in Stock";
    }

    // Getters and Setters

    public String getSlotIdentifier() {
        return slotIdentifier;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getSnackType() {
        return snackType;
    }

    public int getProductQuantityRemaining() {
        return productQuantityRemaining;
    }

    public void setProductQuantityRemaining(int productQuantityRemaining) {
        this.productQuantityRemaining = productQuantityRemaining;
    }
}
