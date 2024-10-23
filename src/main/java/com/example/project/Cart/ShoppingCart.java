package com.example.project.Cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    // Make it be called from other classes
    private static ShoppingCart INSTANCE;

    public static ShoppingCart getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ShoppingCart();
        }
        return INSTANCE;
    }

    // Map for entries
    private Map<String,CartEntry> entries;

    public ShoppingCart(){
        this.entries = new HashMap<>();
    }

    public void addItem(String itemName){
        CartEntry itemEntry = entries.get(itemName.toUpperCase());
        if(itemEntry!= null){
            itemEntry.increaseQuantity();
        }else {
            Item2 item = Item2.valueOf(itemName);
            CartEntry entry = new CartEntry(item,1);
            entries.put(itemName.toUpperCase(),entry);
        }
    }

    public void removeItem(String itemName){
        CartEntry itemEntry = entries.get(itemName.toUpperCase());
        if (itemEntry != null){
            itemEntry.decreaseQuantity();
        }
    }

    public int getQuantity(String itemName){
        CartEntry itemEntry = entries.get(itemName.toUpperCase());
        if(itemEntry != null){
            return itemEntry.getQuantity();
        }
        return 0;
    }

    public double getTotalAmount() {
        double total = 0;
        for (CartEntry itemEntry: entries.values()){
            double entryCost = itemEntry.getItem().getPrice()* itemEntry.getQuantity();
            total = total + entryCost;

        }
        return total;
    }

    public List<CartEntry> getEntries(){
        return new ArrayList<>(entries.values());
    }

}
