package com.starwarcasting.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * This class is used to check internet connection only
 */
public class NetworkUtils {

    /**
     * This is used to check internet connection is available or not
     * @param context Context fo the current activity or fragment from where this method is called
     * @return true if internet connection is available otherwise false
     */
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
