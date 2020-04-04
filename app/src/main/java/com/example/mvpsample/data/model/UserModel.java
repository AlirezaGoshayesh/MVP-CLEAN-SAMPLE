package com.example.mvpsample.data.model;

import com.google.gson.annotations.SerializedName;

public class UserModel {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    public String getName() {
        return name;
    }

}
