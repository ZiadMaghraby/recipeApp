package com.example.recipeapp;

import java.util.ArrayList;
import java.util.List;

// This class is the manager for the shopping list items. It has simple functions like add and remove.
public class ShoppingManager {
    // This list holds the name of all items for shopping.
    // IMPORTANT: It saves data only in phone memory (RAM), so need saving to SharedPreferences for permanent saving.
    private static final List<String> shoppingList = new ArrayList<>();

    // Function for adding a new item to the list.
    public static void addItem(String item) {
        shoppingList.add(item);
    }

    // Function for removing a specific item from the list.
    public static void removeItem(String item) {
        shoppingList.remove(item);
    }

    // Function for deleting all items from the list.
    public static void clearAll() {
        shoppingList.clear();
    }

    // Function for getting the complete list of items.
    public static List<String> getList() {
        return shoppingList;
    }
}