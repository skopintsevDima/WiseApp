package com.pet.wiseapp.ui.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuItem;

import com.pet.wiseapp.R;
import com.pet.wiseapp.db.DbUtils;
import com.pet.wiseapp.ui.about.AboutActivity;
import com.pet.wiseapp.ui.add_aphorism.AddAphorismActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    private AppCompatTextView tvAphorism;

    private List<String> aphorisms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
    }

    private void initUI() {
        tvAphorism = findViewById(R.id.tv_aphorism);
    }

    private void loadAphorisms() {
        aphorisms = new ArrayList<>();

        // Load aphorisms from predefined aphorisms array
        String[] predefinedAphorisms = getResources().getStringArray(R.array.aphorisms_array);
        aphorisms.addAll(Arrays.asList(predefinedAphorisms));

        // Load all user's aphorisms
        String[] userAphorisms =  DbUtils.loadAllAphorisms();
        aphorisms.addAll(Arrays.asList(userAphorisms));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAphorisms();
        setNextAphorism();
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
            case R.id.mi_add_aphorism:
                Intent showAddAphorismIntent = new Intent(this, AddAphorismActivity.class);
                startActivity(showAddAphorismIntent);
                break;
            case R.id.mi_about:
                Intent showAboutIntent = new Intent(this, AboutActivity.class);
                startActivity(showAboutIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNextAphorism() {
        int nextAphorismIndex = new Random().nextInt(aphorisms.size());
        String nextAphorism = aphorisms.get(nextAphorismIndex);
        tvAphorism.setText(nextAphorism);
    }
}
