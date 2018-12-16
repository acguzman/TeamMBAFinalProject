package com.example.alexa.teammbafinalproject;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

    private StorageReference storageRef, pathReference;
    private FirebaseStorage storage;

    public List<String> newRecipeIds = new ArrayList<String>() {{}};
    public List<String> newRecipeImagePaths = new ArrayList<String>() {{}};
    private List<String> userRecipeIds = new ArrayList<String>();
    private List<String> userRecipeImagePaths = new ArrayList<String>();

    public List<ImageButton> imageButtonList = new ArrayList<ImageButton>();
    public ImageButton imageButton01;
    ImageButton img;


    LinearLayout linearLayoutNewRecipes, linearLayoutOldRecipes;

    public RecipeDiscoverFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //the below was mStorageRef and I changed to storageRef based on line 119
        storageRef = FirebaseStorage.getInstance().getReference();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_recipe_discover, container, false);

        try {

            FirebaseDatabase fdb = FirebaseDatabase.getInstance();
            fdb.getReference("Recipe").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Recipe temp = dataSnapshot.getValue(Recipe.class);
                    newRecipeIds.add(temp.recipeName);
                    newRecipeImagePaths.add(temp.picture);
                    userRecipeIds.add(temp.recipeName);
                    userRecipeImagePaths.add(temp.picture);
                    UpdateViewWithRecipe(view, temp);
                    UpdateViewWithNewRecipes(view, temp);
//                    for (DataSnapshot d : dataSnapshot.getChildren()) {
//                        Recipe temp1 = d.getValue(Recipe.class);
//                        newRecipeIds.add(temp1.recipeName);
//                        newRecipeImagePaths.add(temp1.picture);
//                        userRecipeIds.add(temp1.recipeName);
//                        userRecipeImagePaths.add(temp1.picture);
//                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            });

        } catch (Throwable e) {
            Log.e(TAG, "GetImages: " + e.getMessage(), e);
        }

        return view;
    }


    public void UpdateViewWithRecipe(View view, Recipe newRecipe){

        // Get the application context
        Context mContext = view.getContext();

        linearLayoutOldRecipes = view.findViewById(R.id.LinearLayoutOldRecipes);

//        for (int i = 0; i < newRecipeIds.size(); i++) {
        CardView newCardView = new CardView(mContext);
        newCardView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f)
        );

        RelativeLayout newRelativeLayout = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        newRelativeLayout.setLayoutParams(rlp);

        ImageButton newImageButton = new ImageButton(mContext);
        newImageButton.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, getResources().getDisplayMetrics()),
                        1.0f)
        );
        newImageButton.setBackgroundResource(android.R.color.transparent);
        newImageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView newTextView = new TextView(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        newTextView.setLayoutParams(rlp);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        newTextView.setPadding(padding, padding, padding, padding);
        newTextView.setTextSize(18);
        newTextView.setTypeface(newTextView.getTypeface(), Typeface.BOLD);
        newTextView.setText(newRecipe.recipeName);
        newTextView.setBackgroundResource(R.drawable.scrim);

        newImageButton.setTag(newRecipe.recipeName);
        newImageButton.setOnClickListener(this);
        imageButtonList.add(newImageButton);
        LoadRecipeImage(newImageButton, newRecipe.picture);

        newRelativeLayout.addView(newImageButton);
        newRelativeLayout.addView(newTextView);
        newCardView.addView(newRelativeLayout);
        linearLayoutOldRecipes.addView(newCardView);
//        }
    }



    public void UpdateViewWithNewRecipes(View view, Recipe newRecipe){

        // Get the application context
        Context mContext = view.getContext();

        linearLayoutNewRecipes = view.findViewById(R.id.LinearLayoutNewRecipes);

//        for (int i = 0; i < newRecipeIds.size(); i++) {
        CardView newCardView = new CardView(mContext);
        newCardView.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        1.0f)
        );

        RelativeLayout newRelativeLayout = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        newRelativeLayout.setLayoutParams(rlp);

        ImageButton newImageButton = new ImageButton(mContext);
        newImageButton.setLayoutParams(
                new LinearLayout.LayoutParams(
                        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250f, getResources().getDisplayMetrics()),
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        1.0f)
        );
        newImageButton.setBackgroundResource(android.R.color.transparent);
        newImageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);

        TextView newTextView = new TextView(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f);
        //(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, getResources().getDisplayMetrics()),
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        newTextView.setLayoutParams(rlp);
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        newTextView.setPadding(padding, padding, padding, padding);
        newTextView.setTextSize(18);
        newTextView.setTypeface(newTextView.getTypeface(), Typeface.BOLD);
        newTextView.setText(newRecipe.recipeName);
        newTextView.setBackgroundResource(R.drawable.scrim);

        newImageButton.setTag(newRecipe.recipeName);
        newImageButton.setOnClickListener(this);
        imageButtonList.add(newImageButton);
        LoadRecipeImage(newImageButton, newRecipe.picture);

        newRelativeLayout.addView(newImageButton);
        newRelativeLayout.addView(newTextView, rlp);
        newCardView.addView(newRelativeLayout);
        linearLayoutNewRecipes.addView(newCardView);
//        }
    }

    public void depricatedCode() {
/*
        FirebaseDatabase myFdb = FirebaseDatabase.getInstance();
        DatabaseReference fdb = myFdb.getReference("Recipe");
        fdb.orderByChild("recipeID").limitToFirst(10).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                *//*LinearLayout LinearLayoutOldRecipes = getView().findViewById(R.id.LinearLayoutOldRecipes);
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
                }*//*
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


        GetImages(false);*/
    }

    /**
     * Gets Images dynamically from the firebase storage database
     * @param storeLocally - If true it will execute code to store the image locally on the phone
     *                     Else it would update the images on the app, when the user opens the app.
     */
    private void GetImages(boolean storeLocally) {
        try {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference pathReference = storageRef.child("-LTF48csLNdqe7jBLzyT/showcase/AvocadoFettucineComplete.jpg");

            if (storeLocally) {
                // TODO: commented as image loading is fast enough, so no need to pre-download.
                /*File localFile = File.createTempFile("images", "jpg");
                pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Local temp file has been created

                        int ia = 1;
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                        Log.e(TAG, "onFailure() returned: " + exception.getMessage(), exception);
                    }
                });*/
            } else {
                final long ONE_MEGABYTE = 1024 * 1024;
                pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {

                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Data for image is returned, use this as needed
                        Drawable src = new BitmapDrawable(getResources(),
                                BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                        if(img==null) {
                            Log.e(TAG, "UNABLE TO INITIALIZE THE img variable", null);
                        } else {
                            img.setBackground(src);
                            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        }
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
            Log.e(TAG, "GetImages: "+ e.getMessage(), e);
        }
    }

    private void LoadRecipeImage(final ImageButton imageButtonPhoto,String imageSource) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child(imageSource);

        final long ONE_MEGABYTE = 1024 * 1024;
        pathReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Drawable src = new BitmapDrawable(getResources(),
                        BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                if(imageButtonPhoto==null) {
                    Log.e(TAG, "onSuccess: Image not initialized.", null);
                } else {
                    imageButtonPhoto.setImageDrawable(src);
                    //imageStepPhoto.setBackground(src);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.e(TAG, "onFailure: " + exception.getMessage(), exception);
            }
        });
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if (true) {
            String itemIdToPass = v.getTag().toString();
            Bundle bundle = new Bundle();
            bundle.putString("passedRecipeName", itemIdToPass);
            // Code to transition to the Recipe Description Page

//            BottomNavigationView mainNav = getView().findViewById(R.id.main_nav);
            FragmentManager curr = getFragmentManager();
            FragmentTransaction fragmentTransaction = curr.beginTransaction();
//            RecipieDescriptionFragment rdf = new RecipieDescriptionFragment();
//            fragmentTransaction.replace(R.id.main_frame, rdf );
            RecipieDescriptionFragment rdFragment = new RecipieDescriptionFragment();
            rdFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.main_frame, rdFragment);
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
