package com.example.alexa.teammbafinalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList <Review> reviews = new ArrayList<>();
    private Context mContext;

    RecyclerViewAdapter(ArrayList<Review> reviews, Context mContext) {
        this.reviews = reviews;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_recipe_review,viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textViewReview1.setText(reviews.get(i).userID);
        viewHolder.textViewReview4.setText(reviews.get(i).comment);
        //in the canvas video they add an onclicklistener here that displays toast, don't think that is nessesary for us
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewReview1, textViewReview2, textViewReview3, textViewReview4;
        RatingBar ratingViewReview;
        RelativeLayout review_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewReview1 = itemView.findViewById(R.id.textViewReview1);
            textViewReview2 = itemView.findViewById(R.id.textViewReview2);
            textViewReview3 = itemView.findViewById(R.id.textViewReview3);
            textViewReview4 = itemView.findViewById(R.id.textViewReview4);
            ratingViewReview = itemView.findViewById(R.id.ratingViewReview);
            review_layout = itemView.findViewById(R.id.review_Layout);
        }
    }
}
