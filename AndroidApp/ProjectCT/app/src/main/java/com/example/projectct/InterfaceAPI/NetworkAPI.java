package com.example.projectct.InterfaceAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkAPI {
    private static NetworkAPI mInstance;
    private static final String BASE_URL = "http://10.206.4.111:8000"
            ;
    private Retrofit mRetrofit;

    private NetworkAPI() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkAPI getInstance() {
        if (mInstance == null){
            mInstance = new NetworkAPI();
        }
        return mInstance;
    }

    public JSONApi getJSONApi() {
        return mRetrofit.create(JSONApi.class);
    }
}

