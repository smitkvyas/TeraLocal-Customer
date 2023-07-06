package com.teralocal_customer.ui.login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.teralocal_customer.ui.login.models.OTPVerificationDetails;
import com.teralocal_customer.ui.login.repo.OTPVerificationRepo;

public class OtpVerificationViewModel extends AndroidViewModel {

    private static final String TAG = "OtpVerificationViewMode";
    private OTPVerificationRepo otpVerificationRepo = new OTPVerificationRepo();
    public MutableLiveData<OTPVerificationDetails> verificationDetailsMutableLiveData = new MutableLiveData<OTPVerificationDetails>();

    public OtpVerificationViewModel(@NonNull Application application) {
        super(application);
    }

    public void sendOTP(String mobileNumber) {
        Log.d(TAG, "sendOTP() called with: mobileNumber = [" + mobileNumber + "]");
        otpVerificationRepo.sendOTP("91", mobileNumber, verificationDetailsMutableLiveData);
    }

    public void verifyOTP(String otp) {
        otpVerificationRepo.verifyOTP(otp,verificationDetailsMutableLiveData);
    }
}
