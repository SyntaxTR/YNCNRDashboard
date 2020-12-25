package com.yalovaedu.yncnrdashboard;

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.banner.BannerView;

public class IndexActivity extends AppCompatActivity {

=======
>>>>>>> Stashed changes
    BannerView bannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        // Obtain BannerView.
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

        BannerView bannerView = findViewById(R.id.hw_banner_view);
        bannerView.setAdListener(adListener);
        AdParam adParam = new AdParam.Builder().build();
        bannerView.loadAd(adParam);
    }
    private AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
        }

        @Override
        public void onAdFailed(int errorCode) {
            Toast.makeText(IndexActivity.this, "Ad load failed with error code: " + errorCode,
                    Toast.LENGTH_SHORT).show();
            Log.d("ERROR", "Ad load failed with error code: " + errorCode);
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            Log.d("TAG", "onAdClosed");
        }

        @Override
        public void onAdClicked() {
            Log.d("TAG", "onAdClicked");
            super.onAdClicked();
        }

        @Override
        public void onAdOpened() {
            Log.d("TAG", "onAdOpened");
            super.onAdOpened();
        }
    };

=======
>>>>>>> Stashed changes
}