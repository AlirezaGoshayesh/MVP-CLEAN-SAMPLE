package com.example.mvpsample.data.source;

import android.content.Context;

import com.example.mvpsample.data.model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RemoteDataSource implements DataSource.Remote {

    private final static String TAG = RemoteDataSource.class.getSimpleName();
    private static RemoteDataSource INSTANCE;
    private static Context mContext;

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }
        return INSTANCE;
    }

    public RemoteDataSource(Context context) {
        mContext = context;
    }

    @Override
    public void getUsers(final DataSource.getUsersCallback callback) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<UserModel>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                callback.callback(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
