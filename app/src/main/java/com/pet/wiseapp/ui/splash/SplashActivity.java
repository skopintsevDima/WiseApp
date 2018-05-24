package com.pet.wiseapp.ui.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.TextView;

import com.pet.wiseapp.R;
import com.pet.wiseapp.ui.home.HomeActivity;
import com.pet.wiseapp.utils.SplashScreenLoader;

public class SplashActivity extends AppCompatActivity {

    private AppCompatTextView tvGreetingTitle;
    private AppCompatImageView ivSplashImage;

    private String loadingIndicator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initUI();
    }

    private void initUI() {
        tvGreetingTitle = findViewById(R.id.tv_greeting_title);
        ivSplashImage = findViewById(R.id.iv_splash_image);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new SplashScreenLoader(new SplashScreenLoader.SplashScreenLoaderCallback() {
            @Override
            public void onProgressUpdate() {
                loadingIndicator += ".";
                if (loadingIndicator.length() > 3)
                    loadingIndicator = ".";
                String greeting = getString(R.string.greeting) + loadingIndicator;
                tvGreetingTitle.setText(greeting);
            }

            @Override
            public void onLoadingCompleted() {
                Intent startHomeIntent = new Intent(SplashActivity.this, HomeActivity.class);
                startHomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(startHomeIntent);
            }
        }).execute();

    }
}
