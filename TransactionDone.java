/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.transactiondone;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author nayan
 */


class Card {
    String suit;
    String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}

public class TransactionDone {
    private ArrayList<Card> deck;

    public TransactionDone() {
        deck = new ArrayList<>();

        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public ArrayList<Card>[] distributeCards(int players, int cardsPerPlayer) {
        if (players * cardsPerPlayer > deck.size()) {
            throw new IllegalArgumentException("Not enough cards in the deck to distribute to players");
        }

        ArrayList<Card>[] playerHands = new ArrayList[players];

        // Initialize player hands
        for (int i = 0; i < players; i++) {
            playerHands[i] = new ArrayList<>();
        }

        // Distribute cards to players
        int cardIndex = 0;
        for (int i = 0; i < cardsPerPlayer; i++) {
            for (int j = 0; j < players; j++) {
                playerHands[j].add(deck.get(cardIndex++));
            }
        }

        return playerHands;
    }

    public static void main(String[] args) {
        TransactionDone transactionDone = new TransactionDone();
        transactionDone.shuffle();

        int players = 4;
        int cardsPerPlayer = 9;

        ArrayList<Card>[] playerHands = transactionDone.distributeCards(players, cardsPerPlayer);

        System.out.println("Cards received by the players:");
        for (int i = 0; i < players; i++) {
            System.out.println("Player " + (i + 1) + ":");
            for (Card card : playerHands[i]) {
                System.out.println(card);
            }
            System.out.println();
        }
    }
}


