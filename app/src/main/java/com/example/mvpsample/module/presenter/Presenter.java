package com.example.mvpsample.module.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mvpsample.Injection;
import com.example.mvpsample.data.source.DataSource;
import com.example.mvpsample.data.source.RemoteDataSource;
import com.example.mvpsample.data.source.Repository;
import com.example.mvpsample.domain.GetUsers;
import com.example.mvpsample.domain.UseCase;
import com.example.mvpsample.domain.UseCaseHandler;
import com.example.mvpsample.module.contract.Contract;

public class Presenter implements Contract.Presenter {

    private static final String TAG = Presenter.class.getSimpleName();
    private Contract.View view;
    private UseCaseHandler mUseCaseHandler;
    private GetUsers mGet;

    public Presenter(@NonNull Contract.View view) {
        this.view = view;
        view.setPresenter(this);
        init();
    }

    private void init() {
        mUseCaseHandler = UseCaseHandler.getInstance();
        mGet = new GetUsers(Injection.provideRepo(view.getActivity()));
    }

    @Override
    public void getUsers() {
        mUseCaseHandler.execute(mGet, new GetUsers.RequestValues(), new UseCase.UseCaseCallback<GetUsers.ResponseValue, GetUsers.ResponseError>() {
            @Override
            public void onSuccess(GetUsers.ResponseValue response) {
                view.showUsers(response.getUsers());
            }

            @Override
            public void onError(GetUsers.ResponseError error) {
                Log.d(TAG, "onError:" + error );
            }
        });
    }
}
