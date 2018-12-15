package com.example.alexa.teammbafinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonChatSend;
    EditText editTextChatInput;
    ListView listViewChat;
    private FirebaseListAdapter<ChatMessage> adapter;

    private static int clickCount = 0;
    private String chatUID = UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        chatUID = UUID.randomUUID().toString();

        editTextChatInput = findViewById(R.id.editTextChatInput);

        buttonChatSend = findViewById(R.id.buttonChatSend);
        buttonChatSend.setOnClickListener(this);

        listViewChat = findViewById(R.id.listViewChat);

        FirebaseListOptions<ChatMessage> fbListOptions = new FirebaseListOptions
                .Builder<ChatMessage>().setQuery(FirebaseDatabase.getInstance().getReference
                ("chat"), ChatMessage.class).setLayout(R.layout.layout_chat).build();
        adapter = new FirebaseListAdapter<ChatMessage>(fbListOptions) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {

                //Get references to the views of layout_chat.xml
                TextView message_text, message_user, message_time;
                message_text = (TextView) v.findViewById(R.id.message_text);
                message_user = (TextView) v.findViewById(R.id.message_user);
                message_time = (TextView) v.findViewById(R.id.message_time);

                message_text.setText(model.message);
                message_user.setText(model.source);
                message_time.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.sentTime));
            }
        };

        listViewChat.setAdapter(adapter);
        listViewChat.forceLayout();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseDatabase fdb = FirebaseDatabase.getInstance();
        DatabaseReference myref = fdb.getReference("Chat");

        ChatMessage newMessage = new ChatMessage(editTextChatInput.getText().toString(), fAuth
                .getCurrentUser().getDisplayName(), chatUID );
        myref.push().setValue(newMessage);

        //Clearing the texbox
        editTextChatInput.setText("");

        if (clickCount == 1) {

        } else if (clickCount == 2) {

        } else if (clickCount == 3) {

        }


        listViewChat.forceLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater optionMenuInflater = getMenuInflater();
        optionMenuInflater.inflate(R.menu.mainmenu,menu);

        MenuItem addRecipe = menu.findItem(R.id.admin_add_recipe_menu_item);
        if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equalsIgnoreCase("admin@hc.com")) {
            addRecipe.setVisible(true);
        } else {
            addRecipe.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_menu_item:
                Intent homeintent = new Intent(ChatActivity.this, BottomNav.class );
                startActivity(homeintent);
                return true;
            case R.id.logout_menu_item:
                Intent logoutintent = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(logoutintent);
                return true;
            case R.id.admin_add_recipe_menu_item:
                Intent intentRecipes = new Intent(this, AddRecipestoDatabase.class);
                startActivity(intentRecipes);
                return true;
            default:
                return false;
        }


    }
}



