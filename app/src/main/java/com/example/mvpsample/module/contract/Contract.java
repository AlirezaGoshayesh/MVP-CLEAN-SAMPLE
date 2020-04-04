package com.example.mvpsample.module.contract;

import android.app.Activity;

import com.example.mvpsample.data.model.UserModel;

import java.util.List;

public interface Contract {

    interface View {

        Activity getActivity();
        void showUsers(List<UserModel> users);
        void setPresenter(Presenter presenter);
    }

    interface Presenter {

        void getUsers();
    }
}
