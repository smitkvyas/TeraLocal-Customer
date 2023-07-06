package com.teralocal_customer.ui.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.teralocal_customer.MainActivity;
import com.teralocal_customer.R;
import com.teralocal_customer.ui.login.LoginActivity;
import com.teralocal_customer.ui.login.LoginViewModel;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        if (loginViewModel.hasLoggedInUser()) {
            loginViewModel.loginDetailsMutableLiveData.observe(this, loginDetails -> {
                if (loginDetails.isLoggedIn()) {
                    Toast.makeText(this, "Welcome back, " + loginDetails.getUsername(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                } else {
                    Toast.makeText(this, loginDetails.getNetworkErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            loginViewModel.refreshSession();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}