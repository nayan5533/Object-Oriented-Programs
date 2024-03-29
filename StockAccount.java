/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stockaccount;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nayan
 */


class CompanyShares {
    private String stockSymbol;
    public int numberOfShares;
    private String dateTime;

    public CompanyShares(String stockSymbol, int numberOfShares, String dateTime) {
        this.stockSymbol = stockSymbol;
        this.numberOfShares = numberOfShares;
        this.dateTime = dateTime;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public String getDateTime() {
        return dateTime;
    }
}

public class StockAccount {
    public List<CompanyShares> companySharesList;

    public StockAccount() {
        companySharesList = new ArrayList<>();
    }

    
    public void buy(String stockSymbol, int numberOfShares, String dateTime) {
        
        boolean found = false;
        for (CompanyShares cs : companySharesList) {
            if (cs.getStockSymbol().equals(stockSymbol)) {
                cs.numberOfShares += numberOfShares;
                found = true;
                break;
            }
        }

        
        if (!found) {
            companySharesList.add(new CompanyShares(stockSymbol, numberOfShares, dateTime));
        }

        System.out.println(numberOfShares + " shares of " + stockSymbol + " bought successfully.");
    }

    
    public void sell(String stockSymbol, int numberOfShares, String dateTime) {
        
        for (CompanyShares cs : companySharesList) {
            if (cs.getStockSymbol().equals(stockSymbol)) {
                
                if (cs.getNumberOfShares() >= numberOfShares) {
                    cs.numberOfShares -= numberOfShares;
                    System.out.println(numberOfShares + " shares of " + stockSymbol + " sold successfully.");
                    return;
                } else {
                    System.out.println("Not enough shares of " + stockSymbol + " available to sell.");
                    return;
                }
            }
        }
        System.out.println("No shares of " + stockSymbol + " available to sell.");
    }

    
    public void display() {
        System.out.println("\nCompany Shares:");
        for (CompanyShares cs : companySharesList) {
            System.out.println("Stock Symbol: " + cs.getStockSymbol() + ", Number of Shares: " + cs.getNumberOfShares() + ", Date/Time: " + cs.getDateTime());
        }
    }

    public static void main(String[] args) {
        StockAccount stockAccount = new StockAccount();

        
        stockAccount.buy("AAPL", 10, "2024-03-30 09:30:00");
        stockAccount.buy("GOOGL", 20, "2024-03-30 10:00:00");
        stockAccount.buy("AAPL", 5, "2024-03-31 11:00:00");
        stockAccount.sell("AAPL", 8, "2024-03-31 12:30:00");

        
        stockAccount.display();
    }
}
