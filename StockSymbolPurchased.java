/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stocksymbolpurchased;

/**
 *
 * @author nayan
 */
class Node {
    String stockSymbol;
    int numberOfShares;
    String dateTime;
    Node next;

    public Node(String stockSymbol, int numberOfShares, String dateTime) {
        this.stockSymbol = stockSymbol;
        this.numberOfShares = numberOfShares;
        this.dateTime = dateTime;
        this.next = null;
    }
}

class Stack {
    private Node top;

    public Stack() {
        this.top = null;
    }

    public void push(String stockSymbol, int numberOfShares, String dateTime) {
        Node newNode = new Node(stockSymbol, numberOfShares, dateTime);
        newNode.next = top;
        top = newNode;
    }

    public Node pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Node temp = top;
        top = top.next;
        return temp;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class StockSymbolPurchased {
    private Node head;
    private Stack transactionStack;

    public StockSymbolPurchased() {
        head = null;
        transactionStack = new Stack();
    }

    
    public void addCompanyShares(String stockSymbol, int numberOfShares, String dateTime) {
        Node newNode = new Node(stockSymbol, numberOfShares, dateTime);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    
    public void displayCompanyShares() {
        Node temp = head;
        System.out.println("\nCompany Shares:");
        while (temp != null) {
            System.out.println("Stock Symbol: " + temp.stockSymbol + ", Number of Shares: " + temp.numberOfShares + ", Date/Time: " + temp.dateTime);
            temp = temp.next;
        }
    }

    
    public void buy(String stockSymbol, int numberOfShares, String dateTime) {
        transactionStack.push(stockSymbol, numberOfShares, dateTime);
        addCompanyShares(stockSymbol, numberOfShares, dateTime);
        System.out.println(numberOfShares + " shares of " + stockSymbol + " bought successfully.");
    }

    
    public void sell(String stockSymbol, int numberOfShares, String dateTime) {
        transactionStack.push(stockSymbol, -numberOfShares, dateTime);
        Node temp = head;
        while (temp != null) {
            if (temp.stockSymbol.equals(stockSymbol)) {
                temp.numberOfShares -= numberOfShares;
                System.out.println(numberOfShares + " shares of " + stockSymbol + " sold successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("No shares of " + stockSymbol + " available to sell.");
    }

    
    public void displayTransactions() {
        System.out.println("\nTransaction History:");
        while (!transactionStack.isEmpty()) {
            Node transaction = transactionStack.pop();
            if (transaction.numberOfShares > 0) {
                System.out.println("Bought " + transaction.numberOfShares + " shares of " + transaction.stockSymbol + " on " + transaction.dateTime);
            } else {
                System.out.println("Sold " + (-transaction.numberOfShares) + " shares of " + transaction.stockSymbol + " on " + transaction.dateTime);
            }
        }
    }

    public static void main(String[] args) {
        StockSymbolPurchased stockSymbolPurchased = new StockSymbolPurchased();

        
        stockSymbolPurchased.buy("AAPL", 10, "2024-03-30 09:30:00");
        stockSymbolPurchased.buy("GOOGL", 20, "2024-03-30 10:00:00");
        stockSymbolPurchased.sell("AAPL", 5, "2024-03-31 11:00:00");

        
        stockSymbolPurchased.displayCompanyShares();

        
        stockSymbolPurchased.displayTransactions();
    }
}

