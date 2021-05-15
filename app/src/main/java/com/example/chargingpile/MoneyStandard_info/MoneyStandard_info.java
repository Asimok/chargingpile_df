package com.example.chargingpile.MoneyStandard_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chargingpile.R;

public class MoneyStandard_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_standard_info);
    }

    public void add(View view) {
        startActivity(new Intent(this, add_money.class));
    }

    public void del(View view) {
        startActivity(new Intent(this, del_money.class));
    }
    public void search(View view) {
        startActivity(new Intent(this, search_money.class));
    }
}