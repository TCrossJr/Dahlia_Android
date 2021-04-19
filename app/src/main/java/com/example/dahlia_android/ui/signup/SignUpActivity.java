package com.example.dahlia_android.ui.signup;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dahlia_android.R;
import com.example.dahlia_android.data.model.SignedUpUser;
import com.example.dahlia_android.ui.login.LoginActivity;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private SignUpViewModel signUpViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        final EditText agencyEditText = findViewById(R.id.agency);


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

                //Complete and destroy signup activity once successful
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
        agencyEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signUpViewModel.signUp(usernameEditText.getText().toString(),
                            userEmailEditText.getText().toString(),
                            password1EditText.getText().toString(),
                            password2EditText.getText().toString(),
                            firstNameEditText.getText().toString(),
                            lastNameEditText.getText().toString(),
                            agencyEditText.getText().toString());
                }
                return false;
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                signUpViewModel.signUp(usernameEditText.getText().toString(),
                        userEmailEditText.getText().toString(),
                        password1EditText.getText().toString(),
                        password2EditText.getText().toString(),
                        firstNameEditText.getText().toString(),
                        lastNameEditText.getText().toString(),
                        agencyEditText.getText().toString());
                storeCredentials(signUpViewModel.getUser());
            }
        });
        // TODO: For Testing RMV
        testSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEditText.setText("TestUser");
                userEmailEditText.setText("t76@test.com");
                password1EditText.setText("testUserpw1");
                password2EditText.setText("testUserpw1");
                firstNameEditText.setText("Tim");
                lastNameEditText.setText("Cross");
                agencyEditText.setText("Au Pair");
            }
        });
    }

    private void storeCredentials(SignedUpUser user) {
        if( user != null ) {
            String token = user.getNewUserToken();
            SharedPreferences preferences = getSharedPreferences("userToken",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("userToken", token );
            editor.apply();
            Log.d(TAG, "storeCredentials: Stored");
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Error storing credentials. Try Logging into App.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void updateUiWithUser(SignedUpUserView model) {
        String welcome = getString(R.string.prompt_welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showSignUpFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public void goLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}