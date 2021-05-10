package com.example.dahlia_android.ui.help;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dahlia_android.R;

public class HelpActivity extends AppCompatActivity {

    //private Button button[] = new Button[15];
   // private TextView textView[];
    private Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);
        b11 = findViewById(R.id.button11);
        b12 = findViewById(R.id.button12);
        b13 = findViewById(R.id.button13);
        b14 = findViewById(R.id.button14);

        tv1 = findViewById(R.id.textView);

        b0.setText("Report a problem");
        b1.setText("Create a Dahlia Account");
        b2.setText("Sign in to Dahlia Account ");
        b3.setText("Edit a Profile");
        b4.setText("Search for Au Pair Friend or Group");
        b5.setText("Find Au Pair near by");
        b6.setText("Create a new Post");
        b7.setText("Add a new Au Pair Friend");
        b8.setText("Send a Message to a Friend");
        b9.setText("Creat a new Group");
        b10.setText("Join a Group");
        b11.setText("Check News Feed");
        b12.setText("Like a Post");
        b13.setText("Comment to a Post");
        b14.setText("Sign out");

        tv1.setText("1. Go to navigator bar by click    on the top left corner.\n" +
                "2. Click   on the navigator bar. \n" +
                "3. Click  \n" +
                "4. Enter you Username, Email, Password, Confirm Password, First Name, Last Name and Select your Au Par agency. \n" +
                "5. To create an account you must:\n" +
                "\t5.1 You fill all the information required on sign up page.\n" +
                "\t5.2 Your email has never signed up for a Dahlia account before.\n" +
                "\t5.3 Password and Confirm Password is match.\n" +
                "6. Click     button to finish creating account. \n");



    }
}
