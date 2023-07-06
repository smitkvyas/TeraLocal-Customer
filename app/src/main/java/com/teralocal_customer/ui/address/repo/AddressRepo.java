package com.teralocal_customer.ui.address.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.teralocal_customer.TeraLocalCustomerApplication;
import com.teralocal_customer.ui.address.model.Address;
import com.teralocal_customer.ui.address.model.AddressResponseDetails;

public class AddressRepo {

    private static final String TAG = "AddressRepo";


    public void saveAddress(Address address, MutableLiveData<AddressResponseDetails> addressResponseDetailsMutableLiveData) {
        Log.d(TAG, "saveAddress() called with: address = [" + address + "], addressResponseDetailsMutableLiveData = [" + addressResponseDetailsMutableLiveData + "]");
        if (TeraLocalCustomerApplication.isFlowTest()) {
            addressResponseDetailsMutableLiveData
                    .postValue(new AddressResponseDetails().setAddressSaved(true).setError(false));
        } else {

        }
    }
}
