package com.example.mvpsample.data.source;

import com.example.mvpsample.data.model.UserModel;

import java.util.List;

public interface DataSource {

    interface Remote {

        void getUsers(getUsersCallback callback);
    }

    interface getUsersCallback {

        void callback(List<UserModel> users);

        void onFailure();
    }
}
