package com.example.chargingpile.iccard_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chargingpile.R;

public class ic_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ic_info);
    }

    public void band(View view) {
        startActivity(new Intent(this, band_ic.class));
    }

    public void money(View view) {
        startActivity(new Intent(this, money_ic.class));
    }

}