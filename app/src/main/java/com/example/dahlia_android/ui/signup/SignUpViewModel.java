package com.example.dahlia_android.ui.signup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.SharedPreferences;
import android.util.Patterns;

import com.example.dahlia_android.R;
import com.example.dahlia_android.data.SignUpRepository;
import com.example.dahlia_android.data.SignUpResult;
import com.example.dahlia_android.data.model.SignedUpUser;

public class SignUpViewModel extends ViewModel {

    private MutableLiveData<SignUpFormState> signUpFormState = new MutableLiveData<>();
    private MutableLiveData<com.example.dahlia_android.ui.signup.SignUpResult> signUpResult = new MutableLiveData<com.example.dahlia_android.ui.signup.SignUpResult>();
    private SignUpRepository signUpRepository;
    private SignedUpUser _user;

    SignUpViewModel(SignUpRepository signUpRepository) {
        this.signUpRepository = signUpRepository;
    }

    LiveData<SignUpFormState> getSignUpFormState() {
        return signUpFormState;
    }

    LiveData<com.example.dahlia_android.ui.signup.SignUpResult> getSignUpResult() {
        return signUpResult;
    }

    public SignedUpUser getUser() {
        return this._user;
    }

    public void signUp(String username, String userEmail, String password1, String password2, String firstName, String lastName, String agency) {
        // can be launched in a separate asynchronous job
        SignUpResult<SignedUpUser> result = signUpRepository.signup(username, userEmail, password1, password2, firstName, lastName, agency);

        if (result instanceof SignUpResult.Success) {
            SignedUpUser data = ((SignUpResult.Success<SignedUpUser>) result).getData();
            if( data == null ) {
                this.signUpResult.setValue(new com.example.dahlia_android.ui.signup.SignUpResult(R.string.prompt_signup_same_user));
            }
            this._user = data;
//            _user = new SignedUpUser(data);
            this.signUpResult.setValue(new com.example.dahlia_android.ui.signup.SignUpResult(new SignedUpUserView(data.getUserName())));
        } else {
            this.signUpResult.setValue(new com.example.dahlia_android.ui.signup.SignUpResult(R.string.prompt_signup_failed));
        }
    }

    public void signUpDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            signUpFormState.setValue(new SignUpFormState(R.string.prompt_invalid_username, null));
        } else if (!isPasswordValid(password)) {
            signUpFormState.setValue(new SignUpFormState(null, R.string.prompt_invalid_password));
        } else {
            signUpFormState.setValue(new SignUpFormState(true));
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
}