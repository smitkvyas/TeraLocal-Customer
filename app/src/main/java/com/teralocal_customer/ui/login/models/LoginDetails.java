package com.teralocal_customer.ui.login.models;

public class LoginDetails {

    private String username;
    private boolean isLoggedIn;
    private String networkErrorMessage;
    private String validationErrors;
    private String token;
    private String refreshToken;
    private Long validTill;

    public String getUsername() {
        return username;
    }

    public LoginDetails setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public LoginDetails setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public String getNetworkErrorMessage() {
        return networkErrorMessage;
    }

    public LoginDetails setNetworkErrorMessage(String networkErrorMessage) {
        this.networkErrorMessage = networkErrorMessage;
        return this;
    }

    public String getValidationErrors() {
        return validationErrors;
    }

    public LoginDetails setValidationErrors(String validationErrors) {
        this.validationErrors = validationErrors;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginDetails setToken(String token) {
        this.token = token;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public LoginDetails setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Long getValidTill() {
        return validTill;
    }

    public LoginDetails setValidTill(Long validTill) {
        this.validTill = validTill;
        return this;
    }
}
