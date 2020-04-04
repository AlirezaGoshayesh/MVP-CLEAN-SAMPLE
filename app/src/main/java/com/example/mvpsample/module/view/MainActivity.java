package com.example.mvpsample.module.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import com.example.mvpsample.R;
import com.example.mvpsample.data.model.UserModel;
import com.example.mvpsample.module.contract.Contract;
import com.example.mvpsample.module.presenter.Presenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private final static String TAG = MainActivity.class.getSimpleName();
    private Contract.Presenter mPresenter;
    private RecyclerView rv;
    private MAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Presenter(this);
        initView();
        mPresenter.getUsers();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showUsers(List<UserModel> users) {
        adapter = new MAdapter(users, this);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        mPresenter = presenter;
    }

}
