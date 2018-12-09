package com.example.alexa.teammbafinalproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class GetCookingFragment extends Fragment implements View.OnClickListener {

    Button buttonGetCookingAvocadoFettucine, buttonGetCookingMushroomFrittata, buttonGetCookingKoreanSpicyChx;

    public GetCookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        buttonGetCookingAvocadoFettucine = getView().findViewById(R.id.buttonGetCookingAvocadoFettucine);
        buttonGetCookingKoreanSpicyChx = getView().findViewById(R.id.buttonGetCookingKoreanSpicyChx);
        buttonGetCookingMushroomFrittata = getView().findViewById(R.id.buttonGetCookingMushroomFrittata);

        buttonGetCookingAvocadoFettucine.setOnClickListener(this);
        buttonGetCookingKoreanSpicyChx.setOnClickListener(this);
        buttonGetCookingMushroomFrittata.setOnClickListener(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_cooking, container, false);
    }

    @Override
    public void onClick(View v) {

        if(v == buttonGetCookingAvocadoFettucine){

            Intent intentRecipeStepBase = new Intent(getActivity(),RecipeStepBase.class);
            startActivity(intentRecipeStepBase);

        }else if(v == buttonGetCookingKoreanSpicyChx){

        }else if(v == buttonGetCookingMushroomFrittata){

        }

    }
}
