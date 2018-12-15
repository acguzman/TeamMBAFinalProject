package com.example.alexa.teammbafinalproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetCookingFragment extends Fragment implements View.OnClickListener {

    Button buttonGetCookingAvocadoFettucine, buttonGetCookingMushroomFrittata,
            buttonGetCookingKoreanSpicyChx;
    TextView textViewRecipeAvocadoLabel, textViewRecipeKoreanChxLabel, textViewRecipeMushroomLabel;

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

        buttonGetCookingAvocadoFettucine.setOnClickListener(this);
        buttonGetCookingKoreanSpicyChx.setOnClickListener(this);
        buttonGetCookingMushroomFrittata.setOnClickListener(this);

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
