package com.ahsailabs.kasirsederhana.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.ahsailabs.kasirsederhana.R;
import com.ahsailabs.kasirsederhana.configs.Config;
import com.ahsailabs.kasirsederhana.ui.login.models.LoginData;

/**
 * Created by ahmad s on 2019-10-04.
 */
public class SessionUtil {
    public static boolean isLoggedIn(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(Config.DATA_ISLOGGEDIN,false);
    }

    public static void login(Context context, LoginData loginData) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putString(Config.DATA_TOKEN, loginData.getApiToken());
        editor.putString(Config.DATA_NAME, loginData.getName());
        editor.putString(Config.DATA_USERNAME, loginData.getEmail());
        editor.putBoolean(Config.DATA_ISLOGGEDIN, true);
        editor.apply();
    }

    public static void logout(Activity activity) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.remove(Config.DATA_TOKEN);
        editor.remove(Config.DATA_NAME);
        editor.remove(Config.DATA_USERNAME);
        editor.remove(Config.DATA_ISLOGGEDIN);
        editor.apply();
    }

    public static LoginData getLoginData(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(Config.APP_PREFERENCES, Context.MODE_PRIVATE);
        LoginData loginData = new LoginData();
        loginData.setApiToken(sharedPreferences.getString(Config.DATA_TOKEN, ""));
        loginData.setEmail(sharedPreferences.getString(Config.DATA_USERNAME, ""));
        loginData.setName(sharedPreferences.getString(Config.DATA_NAME, ""));
        return loginData;
    }
}
