package com.vm.talk2me.config

import android.content.Context
import android.content.SharedPreferences

import com.vm.talk2me.Constants
import com.vm.talk2me.R

/**
 * Created by VanManh on 02-Sep-17.
 */

object Config {

    fun getCurrTheme(context: Context): Int {
        val preferences = context.getSharedPreferences(Constants.SHARE_NAME, Context.MODE_PRIVATE)
        return preferences.getInt(Constants.SHARE_THEME_KEY, R.style.AppTheme)
    }

    fun setCurrTheme(context: Context, currTheme: Int): Boolean {
        try {
            val preferences = context.getSharedPreferences(Constants.SHARE_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.remove(Constants.SHARE_THEME_KEY).commit()
            editor.putInt(Constants.SHARE_THEME_KEY, currTheme).commit()
            return true
        } catch (e: Exception) {
            return false
        }

    }
}
