package com.teralocal_customer;

public class AppData {
    private static AppData appData;

    public static AppData getInstance() {
        if (appData == null) {
            appData = new AppData();
        }
        return appData;
    }

    private AppData() {
    }
}
