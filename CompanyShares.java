/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.companyshares;

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

public class CompanyShares {
    private Node head;

    public CompanyShares() {
        head = null;
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

    
    public void display() {
        Node temp = head;
        System.out.println("\nCompany Shares:");
        while (temp != null) {
            System.out.println("Stock Symbol: " + temp.stockSymbol + ", Number of Shares: " + temp.numberOfShares + ", Date/Time: " + temp.dateTime);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        CompanyShares companyShares = new CompanyShares();

        
        companyShares.addCompanyShares("AAPL", 100, "2024-03-30 09:30:00");
        companyShares.addCompanyShares("GOOGL", 200, "2024-03-30 10:00:00");
        companyShares.addCompanyShares("MSFT", 150, "2024-03-31 11:00:00");

   
        companyShares.display();
    }
}

