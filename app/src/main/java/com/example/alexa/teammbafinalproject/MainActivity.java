package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity implements View.OnClickListener {
    Button buttonLogin, buttonRegister;
    EditText editTextUsername, editTextPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextUsername = findViewById(R.id.editTextUsername);

        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {


        if (v==buttonRegister){

            Intent intentRegister = new Intent (this, Register.class);

            startActivity(intentRegister);



        }else if(v==buttonLogin){

            final Intent intentDiscover = new Intent(this, RecipeDiscoverFragment.class);

            mAuth.signInWithEmailAndPassword(editTextUsername.getText().toString(), editTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(MainActivity.this, "Login Successful " + user.getEmail(), Toast.LENGTH_SHORT).show();

                        startActivity(intentDiscover);

                    }else {
                        Toast.makeText(MainActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }
}
