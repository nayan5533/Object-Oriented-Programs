/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inventorymanagementprogram;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("weight", weight);
        obj.put("pricePerKg", pricePerKg);
        return obj;
    }
}

class Inventory {
    private List<InventoryItem> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(InventoryItem item) {
        items.add(item);
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        for (InventoryItem item : items) {
            totalValue += item.calculateValue();
        }
        return totalValue;
    }

    public JSONArray toJSON() {
        JSONArray array = new JSONArray();
        for (InventoryItem item : items) {
            array.add(item.toJSON());
        }
        return array;
    }
}

class InventoryFactory {
    public static Inventory createInventoryFromJSON(String filename) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filename));

        Inventory inventory = new Inventory();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            String name = (String) jsonObject.get("name");
            double weight = (double) jsonObject.get("weight");
            double pricePerKg = (double) jsonObject.get("pricePerKg");
            InventoryItem item = new InventoryItem(name, weight, pricePerKg);
            inventory.addItem(item);
        }
        return inventory;
    }
}

class InventoryManager {
    private List<Inventory> inventories;

    public InventoryManager() {
        inventories = new ArrayList<>();
    }

    public void addInventory(Inventory inventory) {
        inventories.add(inventory);
    }

    public void calculateInventoryPrice() {
        for (Inventory inventory : inventories) {
            System.out.println("Total value of inventory: " + inventory.calculateTotalValue());
        }
    }

    public void generateJSON() {
        for (Inventory inventory : inventories) {
            JSONArray jsonArray = inventory.toJSON();
            try (FileWriter file = new FileWriter("inventory_output.json")) {
                file.write(jsonArray.toJSONString());
                System.out.println("\nJSON inventory data written to inventory_output.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class InventoryManagementProgram {
    public static void main(String[] args) {
        try {
            InventoryManager manager = new InventoryManager();
            manager.addInventory(InventoryFactory.createInventoryFromJSON("inventory.json"));
            manager.calculateInventoryPrice();
            manager.generateJSON();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
