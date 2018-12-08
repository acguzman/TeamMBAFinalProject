package com.example.alexa.teammbafinalproject;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;
import java.util.List;

public class Recipe {

    public String recipeID;

    public String recipeName;

    public String recipeDescription;

    public Bitmap picture; //https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html

    public boolean isVegetarian;
    public boolean isVegan;
    public boolean isDairyFree;
    public boolean isGlutenFree;
    public boolean isNutFree;

    public Recipe(String recipeID, String recipeName, String recipeDescription, Bitmap picture,
                  boolean isVegetarian, boolean isVegan, boolean isDairyFree, boolean
                          isGlutenFree, boolean isNutFree, List<String> stepID, List<String>
                          stepName, List<Bitmap> stepImage, List<List<Ingredient>>
                          stepIngredients, List<List<String>> stepIngredientMeasurementAmount,
                  List<List<String>> stepIngredientPreparationType) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.picture = picture;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.isDairyFree = isDairyFree;
        this.isGlutenFree = isGlutenFree;
        this.isNutFree = isNutFree;
        this.stepID = stepID;
        this.stepName = stepName;
        this.stepImage = stepImage;
        this.stepIngredients = stepIngredients;
        this.stepIngredientMeasurementAmount = stepIngredientMeasurementAmount;
        this.stepIngredientPreparationType = stepIngredientPreparationType;
    }

    public Recipe() {}

    /** The sample ingredients should look like the following example:
     * 2 Avocados, pitted
     * 1 Tablespoon Nutritional yeast
     * 1 Tablespoon Extra virgin olive oil
     * 1 Tablespoon Fresh lemon juice
     * 1 Clove Garlic, minced
     * 1 Teaspoon Salt
     * ¼ Teaspoon Freshly ground black pepper
     * ¾ Pound Fettucine
     */
    public String ingredientSummary;

    // Unique ID for each step
    public List<String> stepID;

    //Customer-friendly name for each step
    public List<String> stepName;

    //Image associated with each step
    public List<Bitmap> stepImage;

    /**
     * A list of ingredients associated with each step.
     * i.e. Steps 1 might have 5 ingredients associated with it
     * So the outer list's first element would be a list of 5 elements.
     */
    public List<List<Ingredient>> stepIngredients;

    /**
     * A list of ingredients associated with each step.
     * i.e. Steps 1 might have 5 ingredients associated with it
     * So the outer list's first element would be the amount for
     * each of the 5 ingredients.
     */
    public List<List<String>> stepIngredientMeasurementAmount;

    /**
     * A list of ingredients associated with each step.
     * i.e. Steps 1 might have 5 ingredients associated with it
     * So the outer list's first element would be the preparation
     * type for each of the 5 ingredients.
     */
    public List<List<String>> stepIngredientPreparationType;

    ////Functions

    /**
     * To get the Step name, given a step number (e.g. 1/2/3/4/../10)
     * @param stepNumber
     * @return
     */
    public String getStepName(int stepNumber){
        return  stepName.get(stepNumber);
    }
}
