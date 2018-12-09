package com.example.alexa.teammbafinalproject;

import java.util.ArrayList;

public class Ingredient {

    public String ingredientID;
    public String ingredientName;
    public String measurementUnit;

    Ingredient(String measurement, String ingredient) {
        this.measurementUnit = measurement;
        this.ingredientName = ingredient;
    }

    public Ingredient(String measurementType, String ingredient, String preparationType) {
        this.measurementUnit = measurementType;
        this.ingredientName = ingredient;
    }

    public Ingredient(String rawinput) {
        String[] parts = rawinput.split(",");
        if (parts.length > 1) {
            this.measurementUnit = parts[1];
            this.ingredientName = parts[2];
        } else {
            this.measurementUnit = "";
            this.ingredientName = "";
        }
    }
}
