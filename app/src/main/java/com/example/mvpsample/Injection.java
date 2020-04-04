package com.example.mvpsample;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.mvpsample.data.source.RemoteDataSource;
import com.example.mvpsample.data.source.Repository;

public class Injection {

    public static Repository provideRepo(@NonNull Context context) {
        return Repository.getInstance(RemoteDataSource.getInstance(context));
    }
}
