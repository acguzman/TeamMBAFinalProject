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
public class GetCookingFragment extends Fragment implements View.OnClickListener{

    public Button buttonGetCookingAvocadoFettucine;
    public Button buttonGetCookingKoreanSpicyChx;
    public Button buttonGetCookingMushroomFrittata;

    public GetCookingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonGetCookingAvocadoFettucine = getView().findViewById(R.id.buttonGetCookingAvocadoFettucine);

        buttonGetCookingAvocadoFettucine.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_cooking, container, false);

    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */


    @Override
    public void onClick(View v) {

        if(v == buttonGetCookingAvocadoFettucine) {
            Intent intentRecipeStepBase = new Intent(getActivity().getApplication(),RecipeStepBase.class);
            startActivity(intentRecipeStepBase);

        }
    }
}
