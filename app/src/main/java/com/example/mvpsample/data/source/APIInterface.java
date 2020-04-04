package com.example.mvpsample.data.source;

import com.example.mvpsample.data.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("users")
    Call<List<UserModel>> getUsers();

}
