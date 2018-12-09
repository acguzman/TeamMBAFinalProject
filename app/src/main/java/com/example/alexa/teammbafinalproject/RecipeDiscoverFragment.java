package com.example.alexa.teammbafinalproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the interface
 * to handle interaction events. factory method to
 * create an instance of this fragment.
 */
public class RecipeDiscoverFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public ImageButton imageButton01;

    //Temp Code
    public List<String> recipeIds = new ArrayList<String>() {{add("111"); add("222"); add("333");}};

    LinearLayout linearLayoutNewRecipes, linearLayoutOldRecipes;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public RecipeDiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe_discover, container, false);
        imageButton01 = view.findViewById(R.id.imageButton01);
        imageButton01.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if(true) {
            v.getId();

            String temIdToPass = recipeIds.get(1);
            // Code to transition to the Recipe Description Page

//            BottomNavigationView mainNav = getView().findViewById(R.id.main_nav);
            FragmentManager curr = getFragmentManager();
            FragmentTransaction fragmentTransaction = curr.beginTransaction();
//            RecipieDescriptionFragment rdf = new RecipieDescriptionFragment();
//            fragmentTransaction.replace(R.id.main_frame, rdf );
            fragmentTransaction.replace(R.id.main_frame, new RecipieDescriptionFragment());
//            fragmentTransaction.show(rdf );
            fragmentTransaction.commit();

        }
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        if (linearLayoutNewRecipes == null) {
//            linearLayoutNewRecipes = view.findViewById(R.id.LinearLayoutNewRecipes);
//        }
//        if (linearLayoutOldRecipes == null) {
//            linearLayoutOldRecipes = view.findViewById(R.id.LinearLayoutOldRecipes);
//        }
//
//        //Get info to populate from the Firebase database
//        List<String> newRecipeList = new ArrayList<String>();
//        newRecipeList.add("com.example.alexa.teammbafinalproject.Recipe 01");// Dummy variables
//        newRecipeList.add("com.example.alexa.teammbafinalproject.Recipe 02");// Dummy variables
//        newRecipeList.add("com.example.alexa.teammbafinalproject.Recipe 03");// Dummy variables
//        newRecipeList.add("com.example.alexa.teammbafinalproject.Recipe 04");// Dummy variables
//        newRecipeList.add("com.example.alexa.teammbafinalproject.Recipe 05");// Dummy variables
//        newRecipeList.add("com.example.alexa.teammbafinalproject.Recipe 06");// Dummy variables
//
//        //
//        int noOfNewRecipes = newRecipeList.size();
//
//        List<Button> newRecipeButtons = new ArrayList<Button>();
//
//        for (int i = 0; i < noOfNewRecipes; i++) {
//            Button tempButton = new Button(getActivity());
//            tempButton.setText(newRecipeList.get(i));
//            tempButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
//                    .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            newRecipeButtons.add(tempButton);
//            linearLayoutNewRecipes.addView(tempButton);
//        }
//
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
