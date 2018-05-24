package com.pet.wiseapp.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;

import com.pet.wiseapp.R;
import com.pet.wiseapp.ui.about.AboutActivity;

import java.lang.reflect.Array;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    private AppCompatTextView tvAphorism;

    private String[] aphorisms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
        loadAphorisms();
        setNextAphorism();
    }

    private void initUI() {
        tvAphorism = findViewById(R.id.tv_aphorism);
    }

    private void loadAphorisms() {
        aphorisms = getResources().getStringArray(R.array.aphorisms_array);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_generate_aphorism:
                setNextAphorism();
                break;
            case R.id.mi_about:
                Intent showAboutIntent = new Intent(this, AboutActivity.class);
                startActivity(showAboutIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNextAphorism() {
        int nextAphorismIndex = new Random().nextInt(aphorisms.length);
        String nextAphorism = aphorisms[nextAphorismIndex];
        tvAphorism.setText(nextAphorism);
    }
}
