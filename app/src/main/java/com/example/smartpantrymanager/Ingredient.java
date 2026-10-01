package com.example.smartpantrymanager;
//class
public class Ingredient {
    private int id;
    private String name;
    private String quantity;
    private String unit;
//constructors
    public Ingredient(int id, String name, String quantity, String unit) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Ingredient(String name, String quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
//getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getQuantity() { return quantity; }
    public String getUnit() { return unit; }
}
