/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stockreport;
import java.util.*;
/**
 *
 * @author nayan
 */


class Stock {
    private String name;
    private int numberOfShares;
    private double sharePrice;

    public Stock(String name, int numberOfShares, double sharePrice) {
        this.name = name;
        this.numberOfShares = numberOfShares;
        this.sharePrice = sharePrice;
    }

    public double calculateValue() {
        return numberOfShares * sharePrice;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public double getSharePrice() {
        return sharePrice;
    }
}

class StockPortfolio {
    private List<Stock> stocks;

    public StockPortfolio() {
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        for (Stock stock : stocks) {
            totalValue += stock.calculateValue();
        }
        return totalValue;
    }

    public void printStockReport() {
        System.out.println("Stock Report:");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Stock Name", "Number of Shares", "Share Price", "Total Value");
        for (Stock stock : stocks) {
            System.out.printf("%-15s %-15d %-15.2f %-15.2f%n", stock.getName(), 
                    stock.getNumberOfShares(), stock.getSharePrice(), stock.calculateValue());
        }
        System.out.println("---------------------------------------------");
        System.out.printf("%-45s %.2f%n", "Total Value of Stock", calculateTotalValue());
    }
}

public class StockReport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StockPortfolio portfolio = new StockPortfolio();

        System.out.print("Enter the number of stocks: ");
        int numStocks = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < numStocks; i++) {
            System.out.println("Enter details for Stock " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Number of Shares: ");
            int numberOfShares = scanner.nextInt();
            System.out.print("Share Price: ");
            double sharePrice = scanner.nextDouble();
            scanner.nextLine(); 

            Stock stock = new Stock(name, numberOfShares, sharePrice);
            portfolio.addStock(stock);
        }

        portfolio.printStockReport();
        scanner.close();
    }
}

