package com.example.chargingpile.cdg_info;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chargingpile.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class cdg_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdg_info);

    }

    public void add(View view) {
        startActivity(new Intent(this, add_cdg.class));
    }

    public void delete(View view) {
        startActivity(new Intent(this, del_cdg.class));
    }

    public void search(View view) {
        startActivity(new Intent(this, search_cdg.class));
    }

    public void change(View view) {
        startActivity(new Intent(this, change_cdg.class));
    }
}
