package com.example.dahlia_android.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dahlia_android.R;
import com.example.dahlia_android.ui.friends.FriendsResult;
import com.example.dahlia_android.ui.friends.FriendsViewModel;
import com.example.dahlia_android.ui.friends.FriendsViewModelFactory;
import com.example.dahlia_android.ui.groups.GroupsResult;
import com.example.dahlia_android.ui.groups.GroupsViewModel;
import com.example.dahlia_android.ui.groups.GroupsViewModelFactory;
import com.example.dahlia_android.ui.home.HomeFeedResult;
import com.example.dahlia_android.ui.home.HomeFeedViewModel;
import com.example.dahlia_android.ui.home.HomeFeedViewModelFactory;
import com.example.dahlia_android.ui.messages.MessagesResult;
import com.example.dahlia_android.ui.messages.MessagesViewModel;
import com.example.dahlia_android.ui.messages.MessagesViewModelFactory;
import com.example.dahlia_android.ui.signup.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private HomeFeedViewModel homeFeedViewModel;
    private FriendsViewModel friendsViewModel;
    private GroupsViewModel groupsViewModel;
    private MessagesViewModel messagesViewModel;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final TextView signUpTextButton = findViewById(R.id.register);
        final CheckBox rememberOption = findViewById(R.id.remember);
        final Button testUser = findViewById(R.id.test_user); // TODO: RMV
        loadingProgressBar = findViewById(R.id.loading);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    loadUserData();
                    updateUiWithUser(loginResult.getSuccess());
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
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO: Think we need to add storing preferences(or change to ViewModel, DataSource, and DataRepository) here also. This gets called if Enter is pressed if complete
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loadingProgressBar.setVisibility(View.VISIBLE);
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                SharedPreferences preferences = getSharedPreferences("prefs", MODE_PRIVATE);
                boolean remember = preferences.getBoolean("remember", false ) ;
                if( remember ) {
                    String userName = preferences.getString("username", "");
                    usernameEditText.setText(userName);
                    String creds = preferences.getString("creds", "");
                    passwordEditText.setText(creds);
                }

                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
        signUpTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        rememberOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( buttonView.isChecked() ) {
                    SharedPreferences preferences = getSharedPreferences("remember", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "yes" );
                    editor.apply();
                }
                else if( !buttonView.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("remember", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "no" );
                    editor.apply();
                }
            }
        });

        // TODO: RMV
        testUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEditText.setText("tac11170@vtc.edu");
                passwordEditText.setText("testAdminpw1");
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        // set header username TODO: set nav_header
/*        TextView username = findViewById(R.id.userName);
        username.setText(model.getDisplayName());*/
        String welcome = getString(R.string.prompt_welcome) + model.getDisplayName();
        Toast.makeText(this, welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(this, errorString, Toast.LENGTH_LONG).show();
    }

    private void loadUserData() {
        /* HomeFeed */
        loadingProgressBar.setVisibility(View.VISIBLE);
        homeFeedViewModel = new ViewModelProvider(this, new HomeFeedViewModelFactory())
                .get(HomeFeedViewModel.class);
        homeFeedViewModel.loadFeed();
        homeFeedViewModel.getFeedResult().observe(this, new Observer<HomeFeedResult>() {
            @Override
            public void onChanged(HomeFeedResult homeFeedResult) {
                if (homeFeedResult == null) {
                    return;
                }
                // do nothing first try, second try in HomeFeedFragment, if null then send error

                // no UI update needed
            }
        });

        /* Friends */
        friendsViewModel = new ViewModelProvider(this, new FriendsViewModelFactory())
                .get(FriendsViewModel.class);
        friendsViewModel.loadFriends();
        friendsViewModel.getFriendsResult().observe(this, new Observer<FriendsResult>() {
            @Override
            public void onChanged(FriendsResult friendsResult) {
                if (friendsResult == null) {
                    return;
                }
                    // do nothing first try, second try in FriendsFragment, if null then send error

                    // no UI update needed
            }
        });
        /* Groups */
        groupsViewModel = new ViewModelProvider(this, new GroupsViewModelFactory())
                .get(GroupsViewModel.class);
        groupsViewModel.loadGroups();
        groupsViewModel.getGroupsResult().observe(this, new Observer<GroupsResult>() {
            @Override
            public void onChanged(GroupsResult groupsResult) {
                if (groupsResult == null) {
                    return;
                }
                // do nothing first try, second try in GroupsFragment, if null then send error

                // no UI update needed
            }
        });

        /* Messages */
        messagesViewModel = new ViewModelProvider(this, new MessagesViewModelFactory())
                .get(MessagesViewModel.class);
        messagesViewModel.loadMessages();
        messagesViewModel.getMessagesResult().observe(this, new Observer<MessagesResult>() {
            @Override
            public void onChanged(MessagesResult messagesResult) {
                if (messagesResult == null) {
                    return;
                }
                // do nothing first try, second try in MessagesFragment, if null then send error

                // no UI update needed
            }
        });
    }
}