package com.example.dahlia_android.ui.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.dahlia_android.R;
import com.example.dahlia_android.data.DataRepository;
import com.example.dahlia_android.data.Result;
import com.example.dahlia_android.ui.user.User;
import com.example.dahlia_android.ui.user.UserResult;
import com.example.dahlia_android.ui.user.UserView;

public class LoginViewModel extends ViewModel {

    public LiveData<User> user;
    private SavedStateHandle savedStateHandle;
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private MutableLiveData<UserResult> userResult = new MutableLiveData<>();
    private DataRepository dataRepository;

    public LoginViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<User> loginResult = dataRepository.login(username, password);

        if (loginResult instanceof Result.Success) {
            User data = ((Result.Success<User>) loginResult).getData();
            this.loginResult.setValue(new LoginResult(new LoggedInUserView(data.getEmail())));
        } else {
            this.loginResult.setValue(new LoginResult(R.string.prompt_login_failed));
        }
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.prompt_invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.prompt_invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    public LiveData<User> getUser() {
        return user;
    }

    public LiveData<UserResult> getUserResults() {
        return userResult;
    }

    // TODO: Implement stored user
    public void loadUser() {
        Result<User> userResult = dataRepository.loadUser();

        if(userResult instanceof Result.Success) {
            User data = ((Result.Success<User>) userResult).getData();
            this.userResult.setValue(new UserResult(new UserView((data.getUser()))));
        } else {
            this.userResult.setValue(new UserResult(R.string.prompt_user_load_failed));
        }
    }

    public int getUserID() {
        return dataRepository.getUser().getUserID();
    }
}