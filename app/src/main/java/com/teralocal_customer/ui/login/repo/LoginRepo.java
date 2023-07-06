package com.teralocal_customer.ui.login.repo;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.teralocal_customer.BuildConfig;
import com.teralocal_customer.TeraLocalCustomerApplication;
import com.teralocal_customer.location_check.PrefManager;
import com.teralocal_customer.ui.login.models.LoginDetails;

import java.util.Calendar;

public class LoginRepo {

    public void performLogin(Context applicationContext, String userName, String token, MutableLiveData<LoginDetails> loginDetailsMutableLiveData) {
        if (!TeraLocalCustomerApplication.isFlowTest()) {
            LoginService loginService = LoginNetworkInstance.getRetrofitInstance().create(LoginService.class);
            loginService.performLogin();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 180);
        long tokenValidTill = calendar.getTimeInMillis();
        saveTokenDetails(applicationContext, userName, BuildConfig.TOKEN, BuildConfig.REFRESH_TOKEN, tokenValidTill);

        loginDetailsMutableLiveData.postValue(new LoginDetails()
                .setLoggedIn(true).setUsername(userName)
                .setToken(token)
                .setRefreshToken(BuildConfig.REFRESH_TOKEN).setValidTill(tokenValidTill));
    }

    private void saveTokenDetails(Context applicationContext, String userName, String token, String refreshToken, long tokenValidTill) {
        new PrefManager(applicationContext).setLoginDetails(userName, token, refreshToken, tokenValidTill);
    }

    public void refreshSession(Context applicationContext, MutableLiveData<LoginDetails> loginDetailsMutableLiveData) {
        if (TeraLocalCustomerApplication.isFlowTest()) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 180);

            String userName = new PrefManager(applicationContext.getApplicationContext()).getLastLoggedInUserName();
            long tokenValidTill = calendar.getTimeInMillis();

            saveTokenDetails(applicationContext, userName, BuildConfig.TOKEN, BuildConfig.REFRESH_TOKEN, tokenValidTill);
            loginDetailsMutableLiveData.postValue(new LoginDetails()
                    .setLoggedIn(true).setUsername(userName)
                    .setToken(BuildConfig.TOKEN)
                    .setRefreshToken(BuildConfig.REFRESH_TOKEN).setValidTill(tokenValidTill));
        } else {
            LoginService loginService = LoginNetworkInstance.getRetrofitInstance().create(LoginService.class);
            loginService.refreshSession();
        }
    }

    public boolean hasLoggedInUser(Context applicationContext) {
        return !(new PrefManager(applicationContext).getRefreshToken().isEmpty());
    }
}
