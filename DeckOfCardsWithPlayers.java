/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.deckofcardswithplayers;
import java.lang.String;
/**
 *
 * @author nayan
 */
class Card {
    String suit;
    String rank;
    Card next;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.next = null;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}

class Player {
    String name;
    Card deck;
    Player next;

    public Player(String name) {
        this.name = name;
        this.deck = null;
        this.next = null;
    }

    public void receiveCard(String suit, String rank) {
        Card newCard = new Card(suit, rank);
        if (deck == null) {
            deck = newCard;
        } else {
            Card temp = deck;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newCard;
        }
    }

    public void sortDeckByRank() {
        if (deck == null || deck.next == null) {
            return;
        }
        Card current = deck;
        while (current != null) {
            Card index = current.next;
            while (index != null) {
                if (rankToValue(index.rank) < rankToValue(current.rank)) {
                    String tempSuit = current.suit;
                    String tempRank = current.rank;
                    current.suit = index.suit;
                    current.rank = index.rank;
                    index.suit = tempSuit;
                    index.rank = tempRank;
                }
                index = index.next;
            }
            current = current.next;
        }
    }

    private int rankToValue(String rank) {
        switch (rank) {
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "Jack": return 11;
            case "Queen": return 12;
            case "King": return 13;
            case "Ace": return 14;
            default: return 0;
        }
    }

    public void displayDeck() {
        Card temp = deck;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class Queue {
    Player front;
    Player rear;

    public Queue() {
        front = null;
        rear = null;
    }

    public void enqueue(String name) {
        Player newPlayer = new Player(name);
        if (rear == null) {
            front = newPlayer;
        } else {
            rear.next = newPlayer;
        }
        rear = newPlayer;
    }

    public Player dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        Player temp = front;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return temp;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

public class DeckOfCardsWithPlayers {
    public static void main(String[] args) {
        Queue playersQueue = new Queue();
        playersQueue.enqueue("Player 1");
        playersQueue.enqueue("Player 2");
        playersQueue.enqueue("Player 3");
        playersQueue.enqueue("Player 4");

        Queue cardsQueue = new Queue();

        // Initialize deck of cards
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cardsQueue.enqueue(suit, rank);
            }
        }

        // Shuffle cards
        shuffleQueue(cardsQueue);

        // Distribute cards to players
        while (!cardsQueue.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                Player currentPlayer = playersQueue.dequeue();
                currentPlayer.receiveCard(cardsQueue.front.suit, cardsQueue.front.rank);
                playersQueue.enqueue(currentPlayer.name);
                cardsQueue.dequeue();
            }
        }

        
        for (int i = 0; i < 4; i++) {
            Player currentPlayer = playersQueue.dequeue();
            System.out.println(currentPlayer.name + " received:");
            currentPlayer.displayDeck();
            System.out.println();
        }
    }

    
    public static void shuffleQueue(Queue queue) {
        if (queue.isEmpty()) {
            return;
        }
        Player[] playersArray = new Player[4];
        int index = 0;
        while (!queue.isEmpty()) {
            playersArray[index++] = queue.dequeue();
        }
        shuffleArray(playersArray);
        for (Player player : playersArray) {
            queue.enqueue(player.name);
        }
    }

    public static void shuffleArray(Player[] array) {
        int n = array.length;
        for (int i = n - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Player temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}

