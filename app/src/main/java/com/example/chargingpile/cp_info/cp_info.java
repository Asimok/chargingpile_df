package com.example.chargingpile.cp_info;

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

public class cp_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cp_info);
//        submit(1);

    }
    public void add(View view) {
        startActivity(new Intent(this, add_cp.class));
    }

    public void delete(View view) {
        startActivity(new Intent(this, del_cp.class));
    }
    public void search(View view) {
        startActivity(new Intent(this, search_cp.class));
    }
    public void change(View view) {
        startActivity(new Intent(this, updata_cp.class));
    }

    private void submit(int bikeshedId) {
        //创建一个子线程进行逻辑处理
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //get方式开始
                    OkHttpClient client =new OkHttpClient();
                    Request request=new Request.Builder().url("http://123.56.106.24:8082/bikeshed/allbikeshedinfo").build();
                    //使用newcall()创建一个call对象，并调用
                   Response response= client.newCall(request).execute();
                    //得到返回数据
                   String data= response.body().string();
                    //将返回数据添加到ui页面上
                    showData(data);

                    //get方式结束


                    //post方式开始
//                    OkHttpClient client1 = new OkHttpClient();
//                    FormBody formBody = new FormBody.Builder().add("bikeshedId", bikeshedId).add("pas", "123456").build();
//                    Request request1 = new Request.Builder()
//                            .url("http://123.56.106.24:8082/bikeshed/allInfo?bikeshedId=260")
//                            .post(formBody)
//                            .build();
//
//                    Response response1 = client1.newCall(request1).execute();
//                    //得到返回数据
//                    String data1 = response1.body().string();
//                    //将返回数据添加到ui页面上
//                    showData(data1);
                    //post方式结束


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
                Toast.makeText(cp_info.this, "你好2", Toast.LENGTH_SHORT).show();
                Log.d("data",data);
                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(data);
                    String total = jsonObj.getString("total");
                } catch (JSONException e) {
                    e.printStackTrace();
                }



//                text1.setText(data);
            }
        });

    }


}