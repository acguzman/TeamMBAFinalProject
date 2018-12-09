package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeStepBase extends Activity implements View.OnClickListener {

    Button floatingActionButtonNext, floatingActionButtonChat;
    TextView textViewRecipeName, textViewStepName,textViewStepIngredientList;
    EditText editTextRecipeName;
    Drawable imageStepPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_base);

        floatingActionButtonChat = (Button) findViewById(R.id.floatingActionButtonChat);
        floatingActionButtonNext = (Button) findViewById(R.id.floatingActionButtonNext);
        textViewRecipeName = (TextView) findViewById(R.id.textViewRecipeName);
        textViewStepName = (TextView) findViewById(R.id.textViewStepName);
        textViewStepIngredientList = (TextView) findViewById(R.id.textViewStepIngredientList);
        editTextRecipeName = (EditText) findViewById(R.id.editTextRecipeName);

        floatingActionButtonNext.setOnClickListener(this);
        floatingActionButtonChat.setOnClickListener(this);

        autoPopulate();

        Toast.makeText(this, editTextRecipeName.getText().toString(), Toast.LENGTH_LONG).show();
    }

    private void autoPopulate() {
        //       textViewStepName.setText()
    }

    //   @Override
    public void onClick(View v) {
        if(v == floatingActionButtonChat){

        }else if(v == floatingActionButtonNext){

        }
    }
}