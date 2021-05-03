package com.example.dahlia_android.ui.signup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.R;
import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpViewModel = new ViewModelProvider(this, new SignUpViewModelFactory())
                .get(SignUpViewModel.class);

        final EditText usernameEditText = findViewById(R.id.user_name);
        final EditText userEmailEditText = findViewById(R.id.user_email);
        final EditText password1EditText = findViewById(R.id.password1);
        final EditText password2EditText = findViewById(R.id.password2);
        final EditText firstNameEditText = findViewById(R.id.first_name);
        final EditText lastNameEditText = findViewById(R.id.last_name);
        final TextView loginTextButton = findViewById(R.id.go_login);
        //final EditText agencyEditText = findViewById(R.id.agency);
        String[] agency = {"Select Au Pair Agency"};
        final Spinner agencySpinner = findViewById(R.id.agencyS);


        //agencySpinner drop down elements
        final List<String> agencyList = new ArrayList<String>();
        agencyList.add("Select Au Pair Agency");
        agencyList.add("Au Pair in America AIFS");
        agencyList.add("Cultural Care Au Pair");
        agencyList.add("AuPairCare");
        agencyList.add("EurAuPair Intercultural Child Care Program");
        agencyList.add("Agent Au Pair");
        agencyList.add("goAUPAIR");
        agencyList.add("Au Pair Foundation");
        agencyList.add("Au Pair 4 Me");
        agencyList.add("InterExchange Au Pair USA");
        agencyList.add("USAupair, Inc.");
        agencyList.add("Cultural HomeStay International Au Pair USA");
        agencyList.add("Other");

        //create adapter for agencySpinner
        final ArrayAdapter<String> agencyDataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, agencyList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView initial = (TextView)super.getView(position, convertView, parent);
                if(initial.getText().toString() == "Select Au Pair Agency") {
                    initial.setTextColor(Color.GRAY);
                }
                return initial;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView initial = (TextView)super.getDropDownView(position, convertView, parent);
                if(initial.getText().toString() == "Select Au Pair Agency") {
                    initial.setVisibility(View.GONE);
                } else {
                    initial.setVisibility(View.VISIBLE);
                }
                return initial;
            }
        };

        //set drop down menu style
        //agencySpinner.setPrompt("Select Au Pair Agency");
        agencyDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attaching data adapter to spinner
        agencySpinner.setAdapter(agencyDataAdapter);

        final Button signUpButton = findViewById(R.id.signup);
        final Button testSignUp = findViewById(R.id.test_signup);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        signUpViewModel.getSignUpFormState().observe(this, new Observer<SignUpFormState>() {
            @Override
            public void onChanged(@Nullable SignUpFormState signUpFormState) {
                if (signUpFormState == null) {
                    return;
                }
                signUpButton.setEnabled(signUpFormState.isDataValid());
                if (signUpFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(signUpFormState.getUsernameError()));
                }
                if (signUpFormState.getPasswordError() != null) {
                    password1EditText.setError(getString(signUpFormState.getPasswordError()));
                }
                // TODO: Add checks for other input fields
            }
        });

        signUpViewModel.getSignUpResult().observe(this, new Observer<SignUpResult>() {
            @Override
            public void onChanged(@Nullable SignUpResult signUpResult) {
                if (signUpResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (signUpResult.getError() != null) {
                    showSignUpFailed(signUpResult.getError());
                }
                if (signUpResult.getSuccess() != null) {
                    updateUiWithUser(signUpResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO: Add data for SignUp
                signUpViewModel.signUpDataChanged(usernameEditText.getText().toString(),
                        password1EditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        password1EditText.addTextChangedListener(afterTextChangedListener);
        password2EditText.addTextChangedListener(afterTextChangedListener);
        password2EditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signUpViewModel.signUp(usernameEditText.getText().toString(),
                            userEmailEditText.getText().toString(),
                            password1EditText.getText().toString(),
                            password2EditText.getText().toString(),
                            firstNameEditText.getText().toString(),
                            lastNameEditText.getText().toString(),
                            agency[0]);
                }
                return false;
            }
        });

        agencySpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm =
                        (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                return false;
            }
        });

        agencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals(0)) {
                } else {
                    agency[0] = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agency[0] == "Select Au Pair Agency") {
                    Toast.makeText(getApplicationContext(),"Please Select Au Pair Agency", Toast.LENGTH_SHORT).show();
                } else {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    signUpViewModel.signUp(usernameEditText.getText().toString(),
                            userEmailEditText.getText().toString(),
                            password1EditText.getText().toString(),
                            password2EditText.getText().toString(),
                            firstNameEditText.getText().toString(),
                            lastNameEditText.getText().toString(),
                            agency[0]);

                    setupProfile(signUpViewModel.getUser());
                }
            }
        });
        loginTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        // TODO: For Testing RMV
        testSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEditText.setText("NUser");
                userEmailEditText.setText("n@test.com");
                password1EditText.setText("testUserpw1");
                password2EditText.setText("testUserpw1");
                firstNameEditText.setText("Tim");
                lastNameEditText.setText("Cross");
                agency[0] = "Au Pair";
            }
        });
    }

    private void setupProfile(SignedUpUser user) {
        if( user != null ) {
            String token = user.getNewUserToken();
            SharedPreferences preferences = getSharedPreferences("userToken",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("userToken", token );
            editor.apply();
            Log.d(TAG, "storeCredentials: Stored");
        }
        else {
            Log.d(TAG, "storeCredentials: Failed");
        }
    }

    private void updateUiWithUser(SignedUpUserView model) {
        String welcome = getString(R.string.prompt_welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience -> Set nav_header_main username to users name
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showSignUpFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}