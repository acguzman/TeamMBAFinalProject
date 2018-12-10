package com.example.alexa.teammbafinalproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment1 extends Fragment implements View.OnClickListener {

    Button buttonUpdateProfile;

    public ProfileFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_fragment1, container, false);

        buttonUpdateProfile = view.findViewById(R.id.buttonUpdateProfile);
        buttonUpdateProfile.setOnClickListener(this);

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
