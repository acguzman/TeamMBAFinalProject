package com.example.alexa.teammbafinalproject;

import java.util.ArrayList;
import java.util.List;

public class User {

    public List<String> favorites;
    public String name;
    public String username;
    public Boolean Veg;
    public Boolean Vegan;
    public Boolean Gluten;
    public Boolean Dairy;
    public Boolean Nut;
    public String email;


    public User(String name, String username, String email, Boolean veg, Boolean vegan,
                Boolean gluten, Boolean dairy, Boolean nut, List<String> favorites) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.Veg = veg;
        this.Vegan = vegan;
        this.Gluten = gluten;
        this.Dairy = dairy;
        this.Nut = nut;
        this.favorites = favorites;


    }

    public User() {
    }

    /**
     * To get the Step name, given a step number (e.g. 1/2/3/4/../10)
     * @param recipeNumber
     * @return
     */
    public String getRecipeName(int recipeNumber){
        return  favorites.get(recipeNumber);}

}
