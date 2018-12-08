package com.example.alexa.teammbafinalproject;

class User {

    public String name;
    public String username;

    public boolean isVegetarian;
    public boolean isVegan;
    public boolean isDairyFree;
    public boolean isGlutenFree;
    public boolean isNutFree;

    public User(String name, String username, boolean isVegetarian, boolean isVegan, boolean
            isDairyFree, boolean isGlutenFree, boolean isNutFree) {
        this.name = name;
        this.username = username;
        this.isVegetarian = isVegetarian;
        this.isVegan = isVegan;
        this.isDairyFree = isDairyFree;
        this.isGlutenFree = isGlutenFree;
        this.isNutFree = isNutFree;
    }

    public User() {
    }
}
