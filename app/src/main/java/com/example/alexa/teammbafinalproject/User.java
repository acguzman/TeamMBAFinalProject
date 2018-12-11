package com.example.alexa.teammbafinalproject;

public class User {

    String name;
    String username;
    Boolean Veg;
    Boolean Vegan;
    Boolean Gluten;
    Boolean Dairy;
    Boolean Nut;
    String email;

    public User(String name, String username, String email, Boolean veg, Boolean vegan, Boolean gluten, Boolean dairy, Boolean nut) {
        this.name = name;
        this.username = username;
        this.email = email;
        Veg = veg;
        Vegan = vegan;
        Gluten = gluten;
        Dairy = dairy;
        Nut = nut;
    }

    public User() {
    }
}
