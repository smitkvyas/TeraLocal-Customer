package com.teralocal_customer.ui.address;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.teralocal_customer.ui.address.model.Address;
import com.teralocal_customer.ui.address.model.AddressResponseDetails;
import com.teralocal_customer.ui.address.repo.AddressRepo;

public class AddressViewModel extends ViewModel {

    private static final String TAG = "AddressViewModel";
    public MutableLiveData<AddressResponseDetails> addressResponseDetailsMutableLiveData = new MutableLiveData<>();
    private AddressRepo addressRepo = new AddressRepo();

    public void saveAddress(Address address) {
        Log.d(TAG, "saveAddress() called with: address = [" + address + "]");
        addressRepo.saveAddress(address, addressResponseDetailsMutableLiveData);
    }
}
