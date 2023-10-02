package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AccountingManagerTest {

    @Test
    public void feedMoney_returns_1000(){

        //Arrange
        String expectedValue = "1000";
        System.setIn(new ByteArrayInputStream(expectedValue.getBytes()));
        Scanner test = new Scanner(System.in);
        AccountingManager tester = new AccountingManager();

        //Act
        tester.feedMoney();

        //Assert
        Assert.assertEquals(expectedValue, tester.getCurrentMoney().toString());
    }
}
