package com.danirosas.itunesintegrationapp.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.danirosas.itunesintegrationapp.R;
import com.danirosas.itunesintegrationapp.view.fragment.AlbumsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addingFragment();


    }

    private void addingFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        AlbumsFragment fragment = AlbumsFragment.newInstance();
        transaction.add(R.id.container, fragment).commit();
    }
}
