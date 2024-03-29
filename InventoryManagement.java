/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inventorymanagement;

import org.json.simple.*;
import org.json.simple.JSONArrays;
import org.json.simple.parser.*;
import org.json.simple.parser.ParserException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



/**
 *
 * @author nayan
 */

class InventoryItem {
    private String name;
    private double weight;
    private double pricePerKg;

    public InventoryItem(String name, double weight, double pricePerKg) {
        this.name = name;
        this.weight = weight;
        this.pricePerKg = pricePerKg;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public double calculateValue() {
        return weight * pricePerKg;
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        try {
            
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("inventory.json"));

           
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String name = (String) jsonObject.get("name");
                double weight = (double) jsonObject.get("weight");
                double pricePerKg = (double) jsonObject.get("pricePerKg");

                InventoryItem item = new InventoryItem(name, weight, pricePerKg);
                double value = item.calculateValue();

                System.out.println("Item: " + name + ", Value: " + value);
            }

           
            JSONArray inventoryArray = new JSONArray();
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String name = (String) jsonObject.get("name");
                double weight = (double) jsonObject.get("weight");
                double pricePerKg = (double) jsonObject.get("pricePerKg");

                InventoryItem item = new InventoryItem(name, weight, pricePerKg);
                double value = item.calculateValue();

                JSONObject inventoryObject = new JSONObject();
                inventoryObject.put("name", name);
                inventoryObject.put("weight", weight);
                inventoryObject.put("pricePerKg", pricePerKg);
                inventoryObject.put("value", value);

                inventoryArray.add(inventoryObject);
            }

            
            try (FileWriter file = new FileWriter("inventory_output.json")) {
                file.write(inventoryArray.toJSONString());
                System.out.println("\nJSON inventory data written to inventory_output.json");
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
