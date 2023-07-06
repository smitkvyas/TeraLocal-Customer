package com.teralocal_customer.ui.login.repo;

import com.teralocal_customer.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginNetworkInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = BuildConfig.BASE_URL;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}