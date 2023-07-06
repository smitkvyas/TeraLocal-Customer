package com.teralocal_customer.ui.login;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.teralocal_customer.R;
import com.teralocal_customer.databinding.ActivityLoginBinding;
import com.teralocal_customer.location_check.LocationCheckActivity;

public class LoginActivity extends LocationCheckActivity {

    private static final String TAG = "LoginActivity";
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private final int REQ_ONE_TAP = 100;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.loginDetailsMutableLiveData.observe(this, loginDetails -> {
            if (loginDetails.isLoggedIn()) {
                startActivity(new Intent(this, OtpVerificationActivity.class));
                finish();
            } else {
                activityLoginBinding.setLoginDetails(loginDetails);
            }
        });

        activityLoginBinding.imgGoogleIcon.setOnClickListener(view -> {
            oneTapClient = Identity.getSignInClient(this);
            signInRequest = BeginSignInRequest.builder()
                    .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions
                            .builder().setSupported(true).build())
                    .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions
                            .builder().setSupported(true).setServerClientId(getString(R.string.default_web_client_id))
                            .setFilterByAuthorizedAccounts(false).build())
                    .setAutoSelectEnabled(false).build();

            oneTapClient.beginSignIn(signInRequest)
                    .addOnSuccessListener(this, result -> {
                        try {
                            startIntentSenderForResult(
                                    result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
                                    null, 0, 0, 0);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e(TAG, "Couldn't start One Tap UI: " + e.getLocalizedMessage());
                        }
                    })
                    .addOnFailureListener(this, e -> Log.d(TAG, e.getLocalizedMessage()));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_ONE_TAP) {
            try {
                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                String idToken = credential.getGoogleIdToken();
                String username = credential.getId();
                /*String password = credential.getPassword();*/
                if (idToken != null) {
                    loginViewModel.saveUserLogin(username, idToken);
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}