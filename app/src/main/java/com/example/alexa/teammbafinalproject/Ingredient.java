package com.example.alexa.teammbafinalproject;

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
}
