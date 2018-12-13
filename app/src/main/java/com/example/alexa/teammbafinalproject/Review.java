package com.example.alexa.teammbafinalproject;

import java.util.Date;

public class Review {

    String recipeID;
    int stars;
    String comment;
    String userID;
    //Date reviewDate; removing this for now, dates can get complicated - Pat
    //Picture?

    public Review(String recipeID, int stars, String comment, String userID) {
        this.recipeID = recipeID;
        this.stars = stars;
        this.comment = comment;
        this.userID = userID;
        //this.reviewDate = reviewDate; Removing this for now-Pat
    }
}
