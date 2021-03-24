package com.example.projectct.InterfaceAPI;

import com.example.projectct.helpClass.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface JSONApi {
    @GET("/accounts/login_app/")
    public Call<Integer> getIDUser(@Body User data);
}
