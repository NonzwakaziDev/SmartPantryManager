package com.example.smartpantrymanager;

public class Ingredient {
    private int id;
    private String name;
    private String quantity;
    private String unit;

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

    public int getId() { return id; }
    public String getName() { return name; }
    public String getQuantity() { return quantity; }
    public String getUnit() { return unit; }
}
