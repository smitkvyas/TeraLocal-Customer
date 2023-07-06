package com.teralocal_customer.ui.address.model;

public class AddressResponseDetails {

    private Boolean isAddressSaved;
    private Boolean isError;
    private String errorMessage;


    public Boolean getAddressSaved() {
        return isAddressSaved;
    }

    public AddressResponseDetails setAddressSaved(Boolean addressSaved) {
        isAddressSaved = addressSaved;
        return this;
    }

    public Boolean getError() {
        return isError;
    }

    public AddressResponseDetails setError(Boolean error) {
        isError = error;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public AddressResponseDetails setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
