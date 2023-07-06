package com.teralocal_customer.ui.login.models;

public class OTPVerificationDetails {

    private boolean isOTPSent;
    private boolean isOTPVerified;
    private String errorOTPSent;
    private String errorOTPVerification;

    public boolean isOTPSent() {
        return isOTPSent;
    }

    public OTPVerificationDetails setOTPSent(boolean OTPSent) {
        isOTPSent = OTPSent;
        return this;
    }

    public boolean isOTPVerified() {
        return isOTPVerified;
    }

    public OTPVerificationDetails setOTPVerified(boolean OTPVerified) {
        isOTPVerified = OTPVerified;
        return this;
    }

    public String getErrorOTPSent() {
        return errorOTPSent;
    }

    public OTPVerificationDetails setErrorOTPSent(String errorOTPSent) {
        this.errorOTPSent = errorOTPSent;
        return this;
    }

    public String getErrorOTPVerification() {
        return errorOTPVerification;
    }

    public OTPVerificationDetails setErrorOTPVerification(String errorOTPVerification) {
        this.errorOTPVerification = errorOTPVerification;
        return this;
    }
}
