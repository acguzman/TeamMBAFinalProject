package com.example.alexa.teammbafinalproject;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment1 extends Fragment implements View.OnClickListener {

    Button buttonUpdateProfile;
    TextView textViewProfile;
    private FirebaseAuth mAuth;

    public ProfileFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_fragment1, container, false);

        buttonUpdateProfile = view.findViewById(R.id.buttonUpdateProfile);
        textViewProfile = view.findViewById(R.id.textViewProfile);
        buttonUpdateProfile.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");

        myRef.orderByChild("email").equalTo(mAuth.getCurrentUser().toString()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User profile = dataSnapshot.getValue(User.class);
                String profileKey = dataSnapshot.getKey();
                String vegTrue = String.valueOf(profile.Veg);
                String dairyTrue = String.valueOf(profile.Dairy);
                String veganTrue = String.valueOf(profile.Vegan);
                String nutTrue = String.valueOf(profile.Nut);
                String glutenTrue = String.valueOf(profile.Gluten);

                textViewProfile.setText("Name: " + profile.name + "\n" + "Username: " + profile.username + "\n" + "Vegan: " + veganTrue + "\n" +
                "Vegetarian: " + vegTrue + "\n" + "Dairy Free: " + dairyTrue + "\n" + "Gluten Free: " + glutenTrue + "\n" + "Nut Free: " + nutTrue + "\n");

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



        return view;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        FragmentManager curr = getFragmentManager();
        FragmentTransaction fragmentTransaction = curr.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, new EditLogin());
        fragmentTransaction.commit();
    }
}
