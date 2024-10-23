package com.example.project.Cart;

public enum Item2 {
    BLACKSHIRT("Black T-Shirt","Shirt.jpg",13.99),
    BLUESHIRT("Blue T-Shirt","Shirt1.jpg",13.99),
    REDSHIRT("Red T-Shirt","Shirt3.jpg",13.99);


    private String name;
    private String imageFile;
    private  double price;


    Item2(String name, String imageFile, double price){
        this.name = name;
        this.imageFile = imageFile;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public String getImageFile(){
        return imageFile;
    }

    public double getPrice(){
        return this.price;
    }







}
