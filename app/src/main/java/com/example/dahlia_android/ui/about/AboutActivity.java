package com.example.dahlia_android.ui.about;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dahlia_android.R;

public class AboutActivity extends AppCompatActivity {

    private Button aboutButton, feedButton, profileButton, friendsButton, messageButton,  postButton, groupButton, findaupairButton, reportButton;
    private TextView aboutTextView, feedTextView, profileTextView, friendsTextView, messageTextView, postTextView, groupTextView, findaupairTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutButton = findViewById(R.id.button);
        feedButton = findViewById(R.id.button2);
        profileButton = findViewById(R.id.button3);
        friendsButton = findViewById(R.id.button4);
        messageButton = findViewById(R.id.button5);
        postButton = findViewById(R.id.button6);
        groupButton = findViewById(R.id.button7);
        findaupairButton = findViewById(R.id.button8);
        reportButton = findViewById(R.id.button9);

        aboutTextView = findViewById(R.id.textView);
        feedTextView = findViewById(R.id.textView2);
        profileTextView = findViewById(R.id.textView3);
        friendsTextView = findViewById(R.id.textView4);
        messageTextView = findViewById(R.id.textView5);
        postTextView = findViewById(R.id.textView6);
        groupTextView = findViewById(R.id.textView7);
        findaupairTextView = findViewById(R.id.textView8);

        aboutButton.setText("About Dahlia");
        feedButton.setText("News Feed");
        profileButton.setText("Profile");
        postButton.setText("Post");
        groupButton.setText("Group");
        findaupairButton.setText("Find Au Pair Near By");
        reportButton.setText("Report a Problem");

        aboutTextView.setText("..............");
        feedTextView.setText(".............");
        profileTextView.setText("............");
        postTextView.setText(".........");
        groupTextView.setText("Groups are a function to shared interest with certain Au Pair friends. You can create group for your club, an event, a trip or anything.");
        findaupairTextView.setText("............");

        showText(aboutButton, aboutTextView);
        showText(feedButton, feedTextView);
        showText(profileButton, profileTextView);
        showText(postButton, postTextView);
        showText(groupButton, groupTextView);
        showText(findaupairButton,findaupairTextView);

        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Report", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showText(Button b, TextView tv) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setVisibility((tv.getVisibility()==View.GONE)
                        ? View.VISIBLE: View.GONE);
            }
        });
    }

}
