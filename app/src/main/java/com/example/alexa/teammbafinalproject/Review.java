package com.example.alexa.teammbafinalproject;

import android.graphics.Bitmap;

import java.util.Date;

public class Review {

    String reviewID;
    String recipeName;
    String stars;
    String comment;
    String userID;
    String photo;
    String date;
    //Date reviewDate; removing this for now, dates can get complicated - Pat
    //Picture?

    public Review(String reviewID, String recipeName, String stars, String comment, String userID, String photo, String date) {
        this.reviewID = reviewID;
        this.recipeName = recipeName;
        this.stars = stars;
        this.comment = comment;
        this.userID = userID;
        this.photo = photo;
        this.date = date;
        //this.reviewDate = reviewDate; Removing this for now-Pat
    }

    public Review(){

    }
}
