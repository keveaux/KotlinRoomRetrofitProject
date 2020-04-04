package com.i.kotlinroomretrofitproject;

import com.i.kotlinroomretrofitproject.Retrofit.RetrofitClient;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://staging-api.ictlife.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
