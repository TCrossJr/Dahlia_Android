package com.example.dahlia_android.ui.messages;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.friends.FriendsList;
import com.example.dahlia_android.ui.friends.FriendsViewModel;
import com.example.dahlia_android.ui.friends.FriendsViewModelFactory;
import com.example.dahlia_android.ui.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class CreateMessageActivity extends AppCompatActivity {
    private FriendsViewModel friendsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        // TODO: Finish Create Message
        friendsViewModel =
                new ViewModelProvider(this, new FriendsViewModelFactory())
                        .get(FriendsViewModel.class);
        final Spinner messageToSpinner = findViewById(R.id.user_to_message);
//        final EditText messageUserTo = findViewById(R.id.user_to_message);
        final EditText messageText = findViewById(R.id.text_message);
        final ImageView messageMedia = findViewById(R.id.media_image);
        final Button messageAddMedia = findViewById(R.id.media_add);
        final Button messageSend = findViewById(R.id.send_message);


        final FriendsList friends = friendsViewModel.getFriends();
        final ArrayAdapter<User> userToAdapter = new ArrayAdapter<User>(
                this, android.R.layout.simple_spinner_item,
                friends != null ? new ArrayList<User>() : null ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView initial = (TextView)super.getView(position, convertView, parent);
                if(initial.getText().toString().isEmpty()) {
                    initial.setTextColor(Color.GRAY);
                }
                return initial;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView initial = (TextView)super.getDropDownView(position, convertView, parent);
                if(initial.getText().toString().isEmpty()) {
                    initial.setVisibility(View.GONE);
                } else {
                    initial.setVisibility(View.VISIBLE);
                }
                return initial;
            }
        };
    }
}
