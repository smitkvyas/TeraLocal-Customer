package com.teralocal_customer.ui.login.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.teralocal_customer.BuildConfig;
import com.teralocal_customer.TeraLocalCustomerApplication;
import com.teralocal_customer.ui.login.models.OTPVerificationDetails;

import java.util.Objects;

public class OTPVerificationRepo {

    private static final String TAG = "OTPVerificationRepo";

    public void sendOTP(String countryCode, String mobileNumber, MutableLiveData<OTPVerificationDetails> verificationDetailsMutableLiveData) {
        Log.d(TAG, "sendOTP() called with: countryCode = [" + countryCode + "], mobileNumber = [" + mobileNumber + "], verificationDetailsMutableLiveData = [" + verificationDetailsMutableLiveData + "]");
        if (TeraLocalCustomerApplication.isFlowTest()) {
            verificationDetailsMutableLiveData.postValue(new OTPVerificationDetails()
                    .setOTPSent(true).setOTPVerified(false));
        } else {
            // TODO: 05/07/23 add api call or call firebase function
        }
    }

    public void verifyOTP(String otp, MutableLiveData<OTPVerificationDetails> verificationDetailsMutableLiveData) {
        if (TeraLocalCustomerApplication.isFlowTest()) {
            if (otp.equalsIgnoreCase(String.valueOf(BuildConfig.OTP))) {
                verificationDetailsMutableLiveData.postValue(Objects.requireNonNull(verificationDetailsMutableLiveData.getValue()).setOTPVerified(true));
            } else {
                // TODO: 05/07/23 create api call to verify otp or call firebase function
            }
        }
    }
}
