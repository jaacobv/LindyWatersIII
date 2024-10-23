package com.example.project.Cart;

public class CartEntry {
    private Item2 item;

    private int quantity;

    public CartEntry(Item2 item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item2 getItem() {
        return item;
    }

    public void increaseQuantity(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        if(this.quantity>0){
            this.quantity--;
        }
    }
}

