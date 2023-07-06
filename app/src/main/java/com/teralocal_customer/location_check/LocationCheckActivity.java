package com.teralocal_customer.location_check;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class LocationCheckActivity extends AppCompatActivity {

    private static final String TAG = "LocationCheckActivity";
    private boolean isPermitted;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            for (int i = 0; i < grantResults.length; i++) {
                isPermitted = grantResults[i] == PackageManager.PERMISSION_GRANTED;
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    /*new GpsTracker(this).showSettingsAlert();*/
                } else {
                    getLocation();
                }
            }
        }

    }

    public void getLocation() {
        GpsTracker gpsTracker = new GpsTracker(this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Log.i(TAG, "getLocation: " + latitude);
            Log.i(TAG, "getLocation: " + longitude);
        } else {
            gpsTracker.showSettingsAlert();
        }
    }
}
