package com.example.alexa.teammbafinalproject;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;
import java.util.List;

public class Recipe {

    //Will possibly be created by the push functionality of firbaseDB.
    public String recipeID;

    public String recipeName;

    public String recipeDescription;

    public String picture; //https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html

    public boolean isVegetarian;
    public boolean isVegan;
    public boolean isDairyFree;
    public boolean isGlutenFree;
    public boolean isNutFree;

    public String ingredientSummary;

    public String numberOfSteps;

    public Recipe(String recipeID, String recipeName, String recipeDescription, String picture,
                  boolean isVegetarian, boolean isVegan, boolean isDairyFree, boolean
                          isGlutenFree, boolean isNutFree, String ingredientSummary, String numberOfSteps, List<String> stepID,
                          List<String> stepName, List<String> stepImage, List<String> stepIngredients) {

        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.picture = picture;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.isDairyFree = isDairyFree;
        this.isGlutenFree = isGlutenFree;
        this.isNutFree = isNutFree;
        this.ingredientSummary = ingredientSummary;
        this.numberOfSteps = numberOfSteps;
        this.stepID = stepID;
        this.stepName = stepName;
        this.stepImage = stepImage;
        this.stepIngredients = stepIngredients;
    }

    public Recipe() {}


    // Unique ID for each step
    public List<String> stepID;

    //Customer-friendly name for each step
    public List<String> stepName;

    //Image associated with each step
    public List<String> stepImage;

    /**
     * A list of ingredients associated with each step.
     * i.e. Steps 1 might have 5 ingredients associated with it
     * So the outer list's first element would be a list of 5 elements.
     */
    public List<String> stepIngredients;

    /**
     * A list of ingredients associated with each step.
     * i.e. Steps 1 might have 5 ingredients associated with it
     * So the outer list's first element would be the amount for
     * each of the 5 ingredients.
     */

    /**
     * To get the Step name, given a step number (e.g. 1/2/3/4/../10)
     * @param stepNumber
     * @return
     */
    public String getStepName(int stepNumber){
        return  stepName.get(stepNumber);
    }
    public String getStepIngredients(int stepNumber){
        return  stepIngredients.get(stepNumber);
    }

    public String getStepImage(int stepNumber) {
        return stepImage.get(stepNumber).toString();
    }

};

