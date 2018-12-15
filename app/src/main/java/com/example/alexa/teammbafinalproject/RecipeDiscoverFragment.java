package com.example.alexa.teammbafinalproject;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the interface
 * to handle interaction events. factory method to
 * create an instance of this fragment.
 */
public class RecipeDiscoverFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private StorageReference storageRef, pathReference;
    private FirebaseStorage storage;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<String> recipeIDS = new ArrayList<String>();

    public ImageButton imageButton01;
    ImageButton img;

    //Temp Code
    public List<String> recipeIds = new ArrayList<String>() {{
        add("111");
        add("222");
        add("333");
    }};

    LinearLayout linearLayoutNewRecipes, linearLayoutOldRecipes;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public RecipeDiscoverFragment() {
        // Required empty public constructor
        GetImages(true);
    }

    /**
     * Gets Images dynamically from the firebase storage database
     * @param storeLocally - If true it will execute code to store the image locally on the phone
     *                     Else it would update the images on the app, when the user opens the app.
     */
    private void GetImages(boolean storeLocally) {
        try {
            FirebaseDatabase fdb = FirebaseDatabase.getInstance();

            fdb.getReference("Recipe").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    long count = dataSnapshot.getChildrenCount();
                    for (DataSnapshot d: dataSnapshot.getChildren()) {
                        recipeIDS.add(d.getKey());
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {}
            });

            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference pathReference = storageRef.child("-LTF48csLNdqe7jBLzyT/showcase/AvocadoFettucineComplete.jpg");

            if(storeLocally) {
                File localFile = File.createTempFile("images", "jpg");

                pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {


                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Local temp file has been created

                        int ia=1;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        Log.e(TAG, "onFailure() returned: " + exception.getMessage(), exception);
                    }
                });
            } else {
                final long ONE_MEGABYTE = 1024 * 1024;
                pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Data for "images/island.jpg" is returns, use this as needed
                        Drawable src = new BitmapDrawable(getResources(),
                                BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                         if(img==null) {
                             img = getView().findViewById(R.id.imageButton02);
                             img.setBackground(src);
                         } else {
                             img.setBackground(src);
                        }
                        int ia=1;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        Log.e(TAG, "onFailure: " + exception.getMessage(), exception);
                    }
                });
            }
        } catch (Throwable e) {
            Log.e(TAG, "GetImages: AAYUSH "+ e.getMessage(), e);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //thie below was mStorageRef and I changed to storageRef based on line 119
        storageRef = FirebaseStorage.getInstance().getReference();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe_discover, container, false);
        imageButton01 = view.findViewById(R.id.imageButton01);
        imageButton01.setOnClickListener(this);
        img = view.findViewById(R.id.imageButton02);

//        FirebaseAuth fAuth = FirebaseAuth.getInstance();
//        String currEmail = fAuth.getCurrentUser().getEmail();
//        fdb.orderByChild("name").equalTo(currEmail).

        FirebaseDatabase myFdb = FirebaseDatabase.getInstance();
        DatabaseReference fdb = myFdb.getReference("Recipe");
        fdb.orderByChild("recipeID").limitToFirst(10).addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                /*LinearLayout LinearLayoutOldRecipes = getView().findViewById(R.id.LinearLayoutOldRecipes);
                // Get the application context
                Context mContext = getView().getContext();

                ArrayList<Recipe> newRecipes = new ArrayList<Recipe>();
                for (DataSnapshot childDataSnapShot : dataSnapshot.getChildren()) {
                    newRecipes.add(childDataSnapShot.getValue(Recipe.class));
                    CardView newCard = new CardView(mContext);
                    RelativeLayout relLayout = new RelativeLayout(mContext);
                    ImageButton newImageButton = new ImageButton(mContext);
                    TextView newTextView = new TextView(mContext);
                    relLayout.addView(newImageButton);
                    relLayout.addView(newTextView);
                    newCard.addView(relLayout);
                }*/

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

        GetImages(false);

        return view;
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if (true) {
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

        } else { //TODO delete once done testing
            Context mContext = getView().getContext();
            Intent intentRecipes = new Intent(mContext, MainActivity.class);
            startActivity(intentRecipes);
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
