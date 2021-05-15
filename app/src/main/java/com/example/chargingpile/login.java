package com.example.chargingpile;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class login extends AppCompatActivity {
    private EditText password, telNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        password = findViewById(R.id.password);
        telNumber = findViewById(R.id.telNumber);

    }

    public void add(View view) {


        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("telNumber", telNumber.getText().toString());

            jsonObject.put("password", password.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient client1 = new OkHttpClient();

                    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                    RequestBody formBody = FormBody.create(mediaType, jsonObject.toString());

                    Request request1 = new Request.Builder()
                            .url("http://123.56.106.24:8082/manage/login")
                            .post(formBody)
                            .build();

                    Response response1 = client1.newCall(request1).execute();
                    //得到返回数据
                    String data1 = response1.body().string();
                    //将返回数据添加到ui页面上
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

                    if (status.equals("1")) {
                        Toast.makeText(login.this, "登录成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, MainActivity.class));
                    } else {
                        Toast.makeText(login.this, "登录失败", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}