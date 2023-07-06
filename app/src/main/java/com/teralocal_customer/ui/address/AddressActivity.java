package com.teralocal_customer.ui.address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.teralocal_customer.MainActivity;
import com.teralocal_customer.R;
import com.teralocal_customer.databinding.ActivityAddressBinding;
import com.teralocal_customer.ui.address.model.Address;
import com.teralocal_customer.ui.address.model.AddressResponseDetails;

public class AddressActivity extends AppCompatActivity {

    private static final String TAG = "AddressActivity";
    private ActivityAddressBinding activityAddressBinding;
    private AddressViewModel addressViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddressBinding = DataBindingUtil.setContentView(this, R.layout.activity_address);

        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        addressViewModel.addressResponseDetailsMutableLiveData.observe(this, addressResponseDetails -> {
            if (addressResponseDetails.getAddressSaved()) {
                startActivity(new Intent(this, MainActivity.class));
            } else {
                // TODO: 06/07/23 show error message
            }
        });
        activityAddressBinding.btnProceed.setOnClickListener(view -> addressViewModel.saveAddress(new Address()
                .setLine1(activityAddressBinding.edtAddressLine1.getText().toString())
                .setLine2(activityAddressBinding.edtAddressLine2.getText().toString())
                .setLine3(activityAddressBinding.edtAddressLine3.getText().toString())
                .setState(activityAddressBinding.edtState.getText().toString())
                .setCity(activityAddressBinding.edtCity.getText().toString())
                .setPincode(Integer.valueOf(activityAddressBinding.edtPincode.getText().toString()))
        ));
    }
}