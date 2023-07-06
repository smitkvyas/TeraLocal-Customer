package com.teralocal_customer.ui.login.repo;

import retrofit2.Call;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/login")
    Call<Object> performLogin();

    @POST("/refreshSession")
    Call<Object> refreshSession();
}
