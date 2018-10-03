package com.starwarcasting.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtils {

    public static boolean isConnected(Context context) {
        boolean mobileConnected = false;
        boolean wifiConnected = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        if (manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
            //For 3G check
            mobileConnected = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .isConnected();
        }
        if (manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null) {
            //For WiFi Check
            wifiConnected = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .isConnected();
        }
        return (mobileConnected || wifiConnected);
    }
}
