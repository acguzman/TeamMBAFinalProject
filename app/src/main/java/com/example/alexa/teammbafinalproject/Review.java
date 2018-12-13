package com.example.alexa.teammbafinalproject;

import java.util.Date;

public class Review {

    String reviewID;
    String recipeName;
    int stars;
    String comment;
    String userID;
    //Date reviewDate; removing this for now, dates can get complicated - Pat
    //Picture?

    public Review(String reviewID, String recipeName, int stars, String comment, String userID) {
        this.reviewID = reviewID;
        this.recipeName = recipeName;
        this.stars = stars;
        this.comment = comment;
        this.userID = userID;
        //this.reviewDate = reviewDate; Removing this for now-Pat
    }

    public Review(){

    }
}
