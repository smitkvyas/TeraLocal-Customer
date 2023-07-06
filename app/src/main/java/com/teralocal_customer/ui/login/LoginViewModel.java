package com.teralocal_customer.ui.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.teralocal_customer.ui.login.models.LoginDetails;
import com.teralocal_customer.ui.login.repo.LoginRepo;

public class LoginViewModel extends AndroidViewModel {

    private static final String TAG = "LoginViewModel";

    private LoginRepo loginRepo = new LoginRepo();
    public MutableLiveData<LoginDetails> loginDetailsMutableLiveData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void saveUserLogin(String userName, String token) {
        loginRepo.performLogin(getApplication().getApplicationContext(), userName, token, loginDetailsMutableLiveData);
    }

    public void refreshSession() {
        Log.d(TAG, "refreshSession() called");
        loginRepo.refreshSession(getApplication().getApplicationContext(), loginDetailsMutableLiveData);
    }

    public boolean hasLoggedInUser() {
        return loginRepo.hasLoggedInUser(getApplication().getApplicationContext());
    }
}
