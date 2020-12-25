package com.yalovaedu.yncnrdashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.banner.BannerView;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener{
    BannerView bannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        // Obtain BannerView.
        bannerView = findViewById(R.id.hw_banner_view);
        AdParam adParam = new AdParam.Builder().build();
        bannerView.loadAd(adParam);

        Button onlinePlayers = findViewById(R.id.ActivePlayers);
        onlinePlayers.setOnClickListener(this);
        onlinePlayers.callOnClick(); //Default olarak açılması için

        Button top15Scores = findViewById(R.id.Top15Scores);
        top15Scores.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ActivePlayers:{
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, new OnlinePlayers(), null)
                        .setReorderingAllowed(true)
                        .addToBackStack("Name")
                        .commit();
                break;
            }
            case R.id.Top15Scores: {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, new Top15Scores(), null)
                        .setReorderingAllowed(true)
                        .addToBackStack("Name")
                        .commit();
                break;
            }
        }
    }
}