package com.vm.talk2me.network

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Van Manh on 23-thg 8-17.
 */

object NetworkState {

    fun isNetworkAvailable(context: Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (manager.activeNetworkInfo != null && manager.activeNetworkInfo.isConnectedOrConnecting) {
            true
        } else {
            false
        }
    }
}
