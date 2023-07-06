package com.teralocal_customer;

import android.app.Application;

public class TeraLocalCustomerApplication extends Application {

    public static boolean isFlowTest() {
        return BuildConfig.FLAVOR.toLowerCase().equalsIgnoreCase("flowtest");
    }
}
