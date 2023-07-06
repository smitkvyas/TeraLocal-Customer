package com.teralocal_customer.location_check;

import android.content.Context;
import android.content.SharedPreferences;

import com.teralocal_customer.TeraLocalCustomerApplication;

public class PrefManager {


    private final SharedPreferences sharedPreferences;

    public PrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(TeraLocalCustomerApplication.class.getName(), Context.MODE_PRIVATE);
    }

    public void setLoginDetails(String userName, String token, String refreshToken, long tokenValidTill) {
        sharedPreferences.edit()
                .putString("user_name", userName)
                .putString("token", token)
                .putString("refresh_token", refreshToken)
                .putLong("valid_till", tokenValidTill).apply();
    }

    public String getRefreshToken() {
        return sharedPreferences.getString("refresh_token", "");
    }

    public String getLastLoggedInUserName() {
        return sharedPreferences.getString("user_name", "");
    }
}
