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


    public Review(String reviewID, String recipeName, String stars, String comment, String userID, String photo, String date) {
        this.reviewID = reviewID;
        this.recipeName = recipeName;
        this.stars = stars;
        this.comment = comment;
        this.userID = userID;
        this.photo = photo;
        this.date = date;
    }

    public Review(){

    }
}
