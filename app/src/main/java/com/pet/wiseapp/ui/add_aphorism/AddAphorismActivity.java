package com.pet.wiseapp.ui.add_aphorism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.pet.wiseapp.R;
import com.pet.wiseapp.db.DbUtils;

public class AddAphorismActivity extends AppCompatActivity {

    AppCompatEditText etAphorism;
    AppCompatButton btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_aphorism);
        initUI();
    }

    private void initUI() {
        etAphorism = findViewById(R.id.et_aphorism);
        btnOk = findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View btn) {
                String newAphorismText = etAphorism.getText().toString().trim();
                if (!newAphorismText.isEmpty()){
                    DbUtils.addNewAphorism(newAphorismText);
                    finish();
                } else {
                    Toast.makeText(
                            AddAphorismActivity.this,
                            R.string.aphorism_text_cannot_be_empty,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
