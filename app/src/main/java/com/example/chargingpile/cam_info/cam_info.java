package com.example.chargingpile.cam_info;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chargingpile.R;

public class cam_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_info);
    }

    public void add(View view) {
        startActivity(new Intent(this, add_cam.class));
    }

    public void del(View view) {
        startActivity(new Intent(this, del_cam.class));
    }
    public void search(View view) {
//        startActivity(new Intent(this, search_cam.class));
    }
}