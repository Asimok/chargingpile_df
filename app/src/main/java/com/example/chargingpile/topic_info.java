package com.example.chargingpile;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chargingpile.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class topic_info extends AppCompatActivity {
    private EditText bsid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_info);

        bsid = findViewById(R.id.bsId);

    }

    public void add(View view) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://123.56.106.24:8082/manage/writeintotop?content=" +
                            bsid.getText().toString() ).build();
                    Response response = client.newCall(request).execute();
                    String data1 = response.body().string();
                    showData(data1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showData(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Log.d("data", data);
                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(data);
                    String status = jsonObj.getString("status");
                    Log.d("data", status);
                    Toast.makeText(topic_info.this, "status: " + status, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}