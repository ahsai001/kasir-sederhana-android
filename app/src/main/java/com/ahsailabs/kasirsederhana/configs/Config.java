package com.ahsailabs.kasirsederhana.configs;

/**
 * Created by ahmad s on 24/09/20.
 */
public class Config {
    private static final String BASE_URL = "http://172.16.100.129:8000/api/";

    public static String getLoginUrl(){
        return BASE_URL+"login";
    }
    public static String getLogoutUrl(){
        return BASE_URL+"logout";
    }

    public static final String APP_PREFERENCES = "kasir_sederhana_preferences";
    public static final String DATA_TOKEN = "api_token";
    public static final String DATA_NAME = "data_name";
    public static final String DATA_USERNAME = "data_username";
    public static final String DATA_ISLOGGEDIN = "data_isloggedin";

    public static String getCategoryListUrl() {
        return BASE_URL+"categories";
    }

    public static String getAddCategoryUrl() {
        return BASE_URL+"categories";
    }
}
