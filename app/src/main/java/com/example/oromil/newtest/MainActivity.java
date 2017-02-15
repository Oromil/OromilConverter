package com.example.oromil.newtest;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.oromil.newtest.fragments.Fragment1;
import com.example.oromil.newtest.fragments.Fragment2;
import com.example.oromil.newtest.network.ServerManager;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    DataManager dm;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment1 fragment1 = (Fragment1) getFragmentManager().findFragmentById(R.id.fragment);
        Fragment2 fragment2 = (Fragment2) getFragmentManager().findFragmentById(R.id.fragment2);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        swipe.setProgressBackgroundColorSchemeResource(R.color.myColor);
        swipe.setColorSchemeResources(R.color.myColor);

        //ServerManager.init();


        dm = new DataManager(this, fragment1.list, fragment2.editText, progressBar);
        dm.save();
        dm.setListener();
        swipe.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(false);
        dm.save();
//        swipe.setRefreshing(false);
    }
}
