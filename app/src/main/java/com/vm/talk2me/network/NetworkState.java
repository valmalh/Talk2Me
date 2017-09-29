package com.vm.talk2me.network;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Van Manh on 23-thg 8-17.
 */

public class NetworkState {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
