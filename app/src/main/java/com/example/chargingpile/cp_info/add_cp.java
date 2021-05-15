package com.example.chargingpile.cp_info;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chargingpile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class add_cp extends AppCompatActivity {
    private EditText bikeShedId, longitude, latitude, bikeshedName, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cp);

        bikeShedId = findViewById(R.id.bikeShedId);
        longitude = findViewById(R.id.longitude);
        latitude = findViewById(R.id.latitude);
        bikeshedName = findViewById(R.id.bikeshedName);
        type = findViewById(R.id.type);
    }

    public void add(View view) {
        String bikeShedId1 = bikeShedId.getText().toString();
        String longitude1 = longitude.getText().toString();
        String latitude1 = latitude.getText().toString();
        String bikeshedName1 = bikeshedName.getText().toString();
        String type1 = type.getText().toString();

        JSONObject jsonObject=new JSONObject();

        try {
            jsonObject.put("bikeShedId",bikeShedId1);
            jsonObject.put("longitude",longitude1);
            jsonObject.put("latitude",latitude1);
            jsonObject.put("bikeshedName",bikeshedName1);
            jsonObject.put("type",Integer.parseInt(type1));

        } catch (JSONException e) {
            e.printStackTrace();
        }




        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient client1 = new OkHttpClient();

                    MediaType mediaType=MediaType.parse("application/json; charset=utf-8");
                    RequestBody formBody= FormBody.create(mediaType, jsonObject.toString());

                    Request request1 = new Request.Builder()
                            .url("http://123.56.106.24:8082/bikeshed/insertInfo")
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
        //切换回主线程处理，安卓不允许在子线程设置ui界面，所以需要回到主线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Log.d("data", data);
                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(data);
                    String status = jsonObj.getString("status");
                    Log.d("data", status);
                    Toast.makeText(add_cp.this, "status: " + status, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                text1.setText(data);
            }
        });

    }



}