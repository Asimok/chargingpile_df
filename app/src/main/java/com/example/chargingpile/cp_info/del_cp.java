package com.example.chargingpile.cp_info;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chargingpile.R;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class del_cp extends AppCompatActivity {
    private EditText bikeShedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_cp);

        bikeShedId = findViewById(R.id.bikeShedId);

    }

    public void add(View view) {
        String bikeShedId1 = bikeShedId.getText().toString();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient client =new OkHttpClient();
                    Request request=new Request.Builder().url("http://123.56.106.24:8082/bikeshed/deleteInfo?bikeshedId="+bikeShedId1).build();
                    //使用newcall()创建一个call对象，并调用
                    Response response= client.newCall(request).execute();
                    //得到返回数据
                    String data= response.body().string();
                    //将返回数据添加到ui页面上
                    showData(data);

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
                    Toast.makeText(del_cp.this, "status: " + status, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                text1.setText(data);
            }
        });

    }
}