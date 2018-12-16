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

    EditText editTextNameUpdate, editTextUsernameUpdate, editTextPasswordOld, editTextPasswordConfirm;
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

        View view = inflater.inflate(R.layout.fragment_edit_login, container, false);

        mAuth = FirebaseAuth.getInstance();
        editTextNameUpdate = view.findViewById(R.id.editTextNameUpdate);
        editTextPasswordConfirm = view.findViewById(R.id.editTextPasswordConfirm);
        editTextPasswordOld = view.findViewById(R.id.editTextPasswordOld);
        editTextUsernameUpdate = view.findViewById(R.id.editTextUsernameUpdate);
        buttonLoginUpdate = view.findViewById(R.id.buttonLoginUpdate);
        buttonPasswordUpdate = view.findViewById(R.id.buttonPasswordUpdate);

        buttonPasswordUpdate.setOnClickListener(this);
        buttonLoginUpdate.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;


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

            String newPassword = editTextPasswordConfirm.getText().toString();
            String confirmPassword = editTextPasswordConfirm.getText().toString();

            if (newPassword.equals(confirmPassword)) {


                FirebaseUser currUser = mAuth.getCurrentUser();
                currUser.updatePassword(newPassword);
                mAuth.updateCurrentUser(currUser);
                Toast.makeText(getActivity(),"Password updated", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
