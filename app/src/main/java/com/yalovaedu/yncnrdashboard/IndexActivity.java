package com.yalovaedu.yncnrdashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.huawei.hms.ads.AdListener;
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

        Button onlinePlayers = findViewById(R.id.button);
        onlinePlayers.setOnClickListener(this);
        onlinePlayers.callOnClick(); //Default olarak açılması için

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:{
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, new OnlinePlayers(), null)
                        .setReorderingAllowed(true)
                        .addToBackStack("Name")
                        .commit();
                break;
            }
        }
    }
}