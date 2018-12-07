package com.example.alexa.teammbafinalproject;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;
import java.util.List;

public class Recipe {

    public String recipeID;

    public Image picture; //https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html

    public boolean isVegetarian;
    public boolean isDairyFree;
    public boolean isGlutenFree;
    public boolean isNutFree;

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
    public List<Image> stepImage;

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
