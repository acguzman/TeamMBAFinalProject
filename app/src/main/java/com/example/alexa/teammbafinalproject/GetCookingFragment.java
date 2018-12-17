package com.example.alexa.teammbafinalproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetCookingFragment extends Fragment implements View.OnClickListener {

    Button buttonGetCookingAvocadoFettucine, buttonGetCookingMushroomFrittata,
            buttonGetCookingKoreanSpicyChx;
    TextView textViewRecipeAvocadoLabel, textViewRecipeKoreanChxLabel, textViewRecipeMushroomLabel,
              textViewDiscover;
    ImageButton imageButtonAvocado,imageButtonKoreanChx,imageButtonMushroom;

    FirebaseDatabase database;

    DatabaseReference myRef;

    public GetCookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View currView = inflater.inflate(R.layout.fragment_get_cooking, container, false);

        buttonGetCookingAvocadoFettucine = currView.findViewById(R.id
                .buttonGetCookingAvocadoFettucine);
        buttonGetCookingKoreanSpicyChx = currView.findViewById(R.id.buttonGetCookingKoreanSpicyChx);
        buttonGetCookingMushroomFrittata = currView.findViewById(R.id
                .buttonGetCookingMushroomFrittata);

        textViewRecipeAvocadoLabel = currView.findViewById(R.id.textViewRecipeAvocadoLabel);
        textViewRecipeKoreanChxLabel = currView.findViewById(R.id.textViewRecipeKoreanChxLabel);
        textViewRecipeMushroomLabel = currView.findViewById(R.id.textViewRecipeMushroomLabel);
        textViewDiscover = currView.findViewById(R.id.textViewDiscover);

        imageButtonAvocado = currView.findViewById(R.id.imageButtonAvocado);
        imageButtonKoreanChx = currView.findViewById(R.id.imageButtonKoreanChx);
        imageButtonMushroom = currView.findViewById(R.id.imageButtonMushroom);

        buttonGetCookingAvocadoFettucine.setOnClickListener(this);
        buttonGetCookingKoreanSpicyChx.setOnClickListener(this);
        buttonGetCookingMushroomFrittata.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

            String findUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();

            myRef.orderByChild("email").equalTo(findUser).addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User findUserRecipe = dataSnapshot.getValue(User.class);

                    if (findUserRecipe.favorites == null) {
                        textViewDiscover.setVisibility(View.VISIBLE);
                        findUserRecipe.favorites = new ArrayList<String>();
                    } else { }

                    if (findUserRecipe.favorites.contains(textViewRecipeAvocadoLabel.getText().toString())) {
                        textViewRecipeAvocadoLabel.setVisibility(View.VISIBLE);
                        buttonGetCookingAvocadoFettucine.setVisibility(View.VISIBLE);
                        imageButtonAvocado.setVisibility(View.VISIBLE);
                    } else {
                    }

                    if (findUserRecipe.favorites.contains(textViewRecipeKoreanChxLabel.getText().toString())) {
                        textViewRecipeKoreanChxLabel.setVisibility(View.VISIBLE);
                        buttonGetCookingKoreanSpicyChx.setVisibility(View.VISIBLE);
                        imageButtonKoreanChx.setVisibility(View.VISIBLE);
                    } else {
                    }

                    if (findUserRecipe.favorites.contains(textViewRecipeMushroomLabel.getText().toString())) {
                        textViewRecipeMushroomLabel.setVisibility(View.VISIBLE);
                        buttonGetCookingMushroomFrittata.setVisibility(View.VISIBLE);
                        imageButtonMushroom.setVisibility(View.VISIBLE);
                    } else {
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {                }
            });

        // Inflate the layout for this fragment
        return currView;

    }




    @Override
    public void onClick(View v) {

        if (v == buttonGetCookingAvocadoFettucine) {

            Intent intentRecipeStepBase = new Intent(getActivity(), RecipeStepBase.class);
            intentRecipeStepBase.putExtra("passedRecipeName", textViewRecipeAvocadoLabel.getText().toString());
            startActivity(intentRecipeStepBase);

        } else if (v == buttonGetCookingKoreanSpicyChx) {
            Intent intentRecipeStepBase = new Intent(getActivity(), RecipeStepBase.class);
            intentRecipeStepBase.putExtra("passedRecipeName", textViewRecipeKoreanChxLabel.getText().toString());
            startActivity(intentRecipeStepBase);

        } else if (v == buttonGetCookingMushroomFrittata) {
            Intent intentRecipeStepBase = new Intent(getActivity(), RecipeStepBase.class);
            intentRecipeStepBase.putExtra("passedRecipeName", textViewRecipeMushroomLabel.getText().toString());
            startActivity(intentRecipeStepBase);
        }

    }
}
