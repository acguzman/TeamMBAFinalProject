package com.example.alexa.teammbafinalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditLogin extends Fragment {

    FirebaseAuth auth;

    public EditLogin() {
        // Required empty public constructor
//        FirebaseUser currUser = auth.getCurrentUser();
//        currUser.updatePassword("");
//        auth.updateCurrentUser(currUser);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        auth = FirebaseAuth.getInstance();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_login, container, false);
    }

}
