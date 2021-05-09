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

    private Button aboutButton, homeButton, profileButton, friendsButton, messageButton,
            postButton, likeButton, commentButton, groupButton, searchButton, findaupairButton;
    private TextView aboutTextView, homeTextView, profileTextView, friendsTextView, messageTextView,
            likeTextView, commentTextView, postTextView, groupTextView, searchTextView, findaupairTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutButton = findViewById(R.id.button);
        aboutTextView = findViewById(R.id.textView);

        homeButton = findViewById(R.id.button2);
        homeTextView = findViewById(R.id.textView2);

        profileButton = findViewById(R.id.button3);
        profileTextView = findViewById(R.id.textView3);

        friendsButton = findViewById(R.id.button4);
        friendsTextView = findViewById(R.id.textView4);

        messageButton = findViewById(R.id.button5);
        messageTextView = findViewById(R.id.textView5);

        postButton = findViewById(R.id.button6);
        postTextView = findViewById(R.id.textView6);

        likeButton = findViewById(R.id.button7);
        likeTextView = findViewById(R.id.textView7);

        commentButton = findViewById(R.id.button8);
        commentTextView = findViewById(R.id.textView8);

        groupButton = findViewById(R.id.button9);
        groupTextView = findViewById(R.id.textView9);

        searchButton = findViewById(R.id.button10);
        searchTextView = findViewById(R.id.textView10);

        findaupairButton = findViewById(R.id.button11);
        findaupairTextView = findViewById(R.id.textView11);

        aboutButton.setText("About Dahlia");
        aboutTextView.setText("The Dahlia social network system is for au pair users. Dahlia helps you to connect to Au Pair friends " +
                "and lets you create a profile, create posts, join a group, send messages, and search for and find nearby Au Pair. " +
                "It helps you further enjoy the Au Pair community while you are a member of it.");
        showText(aboutButton, aboutTextView);

        homeButton.setText("Home Feed");
        homeTextView.setText("Your home page is what you first see when you sign into Dahlia. " +
                "It shows your News Feed which includes posts from yours Au Pair friends. " +
                "You also can create a new post on your home page by clicking the + button. ");
        showText(homeButton, homeTextView);

        profileButton.setText("Profile");
        profileTextView.setText("A profile is your personal account, where you can provide your personal interests and information about yourself. ");
        showText(profileButton, profileTextView);

        friendsButton.setText("Friend");
        friendsTextView.setText("Friending on Dahlia helps you to connect to your Au Pair friends." +
                " Adding a friend means you may see the other Au Pair friends’ posts in News Feed. ");
        showText(friendsButton, friendsTextView);

        messageButton.setText("Message");
        messageTextView.setText("Messaging on Dahlia lets you instantly send message to your Au Pair friends. ");
        showText(messageButton, messageTextView);

        postButton.setText("Post");
        postTextView.setText("Posts that you see in Home Feed are meant to keep you connected to " +
                "the Au Pair community and things that you care about. You can have your posts liked or commented on by your Au Pair friends. ");
        showText(postButton, postTextView);

        likeButton.setText("Like");
        likeTextView.setText("Clicking Like below a post on Dahlia is way to let people know that you enjoy your friend’s post without leaving a comment. " +
                "Anyone who can see the post also can see that you liked it.");
        showText(likeButton, likeTextView);

        commentButton.setText("Comment");
        commentTextView.setText("Comment lets you respond to a post on Dahlia. You can comment below a post and type what you what say and anyone" +
                " who can see the post can also see your comment.  ");
        showText(commentButton, commentTextView);

        groupButton.setText("Group");
        groupTextView.setText("Groups are a function to share specific interest with selected Au Pair friends. " +
                "You can create group for your clubs, an event, a trip, or anything.");
        showText(groupButton, groupTextView);

        searchButton.setText("Search");
        searchTextView.setText("Search helps you find friends and groups that you are looking for on Dahlia. ");
        showText(searchButton, searchTextView);

        findaupairButton.setText("Au Pair Near By");
        findaupairTextView.setText("Au Pair near by helps you search for Au Pair friends that live near your search area. ");
        showText(findaupairButton, findaupairTextView);
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
