package com.teralocal_customer.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.teralocal_customer.MainActivity;
import com.teralocal_customer.R;
import com.teralocal_customer.databinding.ActivityOtpVerificationBinding;
import com.teralocal_customer.ui.login.models.OTPVerificationDetails;

import java.util.Objects;

public class OtpVerificationActivity extends AppCompatActivity {

    private static final String TAG = "OtpVerificationActivity";
    private ActivityOtpVerificationBinding activityOtpVerificationBinding;
    private OtpVerificationViewModel otpVerificationViewModel;
    private VERIFICATION_STAGE verificationStage = VERIFICATION_STAGE.NONE;

    enum VERIFICATION_STAGE {
        NONE, OTP_SENT, OTP_VERIFIED, OTP_VERIFICATION_ERROR
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityOtpVerificationBinding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verification);

        activityOtpVerificationBinding.edtOTP.setEnabled(false);
        otpVerificationViewModel = new ViewModelProvider(this).get(OtpVerificationViewModel.class);

        otpVerificationViewModel.verificationDetailsMutableLiveData.observe(this, otpVerificationDetails -> {
            if (otpVerificationDetails.isOTPVerified()) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else if (otpVerificationDetails.isOTPSent()) {
                verificationStage = VERIFICATION_STAGE.OTP_SENT;
                activityOtpVerificationBinding.edtOTP.setEnabled(true);
                Toast.makeText(this, "OTP sent, please enter", Toast.LENGTH_SHORT).show();
            } else {
                switch (verificationStage) {
                    case NONE: {
                        Toast.makeText(this, otpVerificationDetails.getErrorOTPSent(), Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default: {
                        Toast.makeText(this, otpVerificationDetails.getErrorOTPVerification(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        activityOtpVerificationBinding.btnProceed.setOnClickListener(view -> {
            switch (verificationStage) {
                case NONE: {
                    otpVerificationViewModel.sendOTP(Objects.requireNonNull(activityOtpVerificationBinding.edtMobileNumber.getText()).toString());
                    break;
                }
                case OTP_SENT: {
                    otpVerificationViewModel.verifyOTP(Objects.requireNonNull(activityOtpVerificationBinding.edtOTP.getText()).toString());
                    break;
                }
            }
        });
    }
}