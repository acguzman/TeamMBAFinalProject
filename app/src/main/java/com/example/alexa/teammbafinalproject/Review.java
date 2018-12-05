package com.example.alexa.teammbafinalproject;

import java.util.Date;

public class Review {

    String recipeID;
    int stars;
    String comment;
    String userID;
    Date reviewDate;
    //Picture?

    public Review(String recipeID, int stars, String comment, String userID, Date reviewDate) {
        this.recipeID = recipeID;
        this.stars = stars;
        this.comment = comment;
        this.userID = userID;
        this.reviewDate = reviewDate;
    }
}
