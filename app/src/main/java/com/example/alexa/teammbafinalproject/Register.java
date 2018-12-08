package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends Activity implements View.OnClickListener {

   Button buttonRegisterNew;
   EditText editTextName, editTextEmail, editTextUsernameNew, editTextPasswordNew;
   CheckBox checkBoxVeg, checkBoxVegan, checkBoxGluten, checkBoxNut, checkBoxDairy;
   private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegisterNew = findViewById(R.id.buttonRegisterNew);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsernameNew = findViewById(R.id.editTextUsernameNew);
        editTextPasswordNew = findViewById(R.id.editTextPasswordNew);

        checkBoxVegan = findViewById(R.id.checkBoxVegan);
        checkBoxVeg = findViewById(R.id.checkBoxVeg);
        checkBoxDairy = findViewById(R.id.checkBoxDairy);
        checkBoxGluten = findViewById(R.id.checkBoxGluten);
        checkBoxNut = findViewById(R.id.checkBoxNut);

        buttonRegisterNew.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("com.example.alexa.teammbafinalproject.User");


        if (v==buttonRegisterNew){

            final Intent intentNewUser = new Intent (this, BottomNav.class);

            mAuth.createUserWithEmailAndPassword(editTextEmail.getText().toString(), editTextPasswordNew.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this, "Welcome New com.example.alexa.teammbafinalproject.User", Toast.LENGTH_SHORT).show();
                        User newuser = new User (editTextUsernameNew.getText().toString(),
                                editTextName.getText().toString(),checkBoxVeg.isChecked(), checkBoxVegan.isChecked(), checkBoxGluten.isChecked(), checkBoxDairy.isChecked(), checkBoxNut.isChecked());

                        myRef.push().setValue(newuser);

                        startActivity(intentNewUser);

                    }else {
                        Toast.makeText(Register.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
}
