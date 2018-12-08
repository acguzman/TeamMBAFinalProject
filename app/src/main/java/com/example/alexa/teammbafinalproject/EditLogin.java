package com.example.alexa.teammbafinalproject;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditLogin extends Fragment implements View.OnClickListener {

    EditText editTextNameUpdate, editTextUsernameUpdate, editTextPasswordOld, editTextPasswordUpdate, editTextPasswordConfirm;
    Button buttonLoginUpdate, buttonPasswordUpdate;
    User user;

    private FirebaseAuth mAuth;

    public EditLogin() {
        // Required empty public constructor
//        FirebaseUser currUser = auth.getCurrentUser();
//        currUser.updatePassword("");
//        auth.updateCurrentUser(currUser);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        editTextNameUpdate = getView().findViewById(R.id.editTextNameUpdate);
        editTextPasswordConfirm = getView().findViewById(R.id.editTextPasswordConfirm);
        editTextPasswordOld = getView().findViewById(R.id.editTextPasswordOld);
        editTextUsernameUpdate = getView().findViewById(R.id.editTextUsernameUpdate);
        editTextPasswordUpdate = getView().findViewById(R.id.editTextPasswordUpdate);
        buttonLoginUpdate = getView().findViewById(R.id.buttonLoginUpdate);
        buttonPasswordUpdate = getView().findViewById(R.id.buttonPasswordUpdate);

        buttonPasswordUpdate.setOnClickListener(this);
        buttonLoginUpdate.setOnClickListener(this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_login, container, false);


        //
    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Users");

        if (v == buttonLoginUpdate) {

            String editName = editTextNameUpdate.getText().toString();
            final String editUsername = editTextUsernameUpdate.getText().toString();


            myRef.orderByChild("name").equalTo(editName).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    String usernamekey = dataSnapshot.getKey();
                    myRef.child(usernamekey).child("username").setValue(editUsername);

                    Toast.makeText(getActivity(), "Username Updated", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        } else if (v == buttonPasswordUpdate) {

            String newPassword = editTextPasswordUpdate.getText().toString();
            String confirmPassword = editTextPasswordConfirm.getText().toString();

            if (newPassword==confirmPassword) {


                FirebaseUser currUser = mAuth.getCurrentUser();
                currUser.updatePassword(newPassword);
                mAuth.updateCurrentUser(currUser);


            } else {
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
