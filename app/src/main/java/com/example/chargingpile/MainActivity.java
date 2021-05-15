package com.example.chargingpile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chargingpile.MoneyStandard_info.MoneyStandard_info;
import com.example.chargingpile.cam_info.cam_info;
import com.example.chargingpile.cdg_info.cdg_info;
import com.example.chargingpile.cp_info.cp_info;
import com.example.chargingpile.iccard_info.ic_info;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cp_info(View view) {
        startActivity(new Intent(this, cp_info.class));
    }

    public void cgz_info(View view) {
        startActivity(new Intent(this, cdg_info.class));
    }

    public void ic_card(View view) {
        startActivity(new Intent(this, ic_info.class));
    }

    public void cam_info(View view) {

        startActivity(new Intent(this, cam_info.class));
    }

    public void charge(View view) {
        startActivity(new Intent(this, MoneyStandard_info.class));
    }

    public void topic(View view) {
        startActivity(new Intent(this, login.class));
    }


}