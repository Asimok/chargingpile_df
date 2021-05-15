package com.example.chargingpile.cp_info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chargingpile.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class search_cp extends AppCompatActivity {
    private ArrayList<cp_info_datainfo> cp_info_datainfo;
    private ListView show_lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cp);
        cp_info_datainfo = new ArrayList<cp_info_datainfo>();
        show_lv = findViewById(R.id.showscore_lv);
        Log.d("data","查询");
        search();
    }


    private void search() {
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
                    Log.d("data",data);
                    JSONArray jsonArray = new JSONArray(data);
                    for (int i = 0; i <jsonArray.length() ; i++) {

                        JSONObject jsonObj2 = jsonArray.getJSONObject(i);

                        cp_info_datainfo mapx = new cp_info_datainfo();
                        mapx.setBikeShedId(jsonObj2.getString("bikeShedId"));
                        mapx.setBikeshedName(jsonObj2.getString("bikeshedName"));
                        mapx.setLatitude(jsonObj2.getString("latitude"));
                        mapx.setLongitude(jsonObj2.getString("longitude"));
                        mapx.setType(jsonObj2.getString("type"));

                        cp_info_datainfo.add(mapx);

                    }
                    showData();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showData() {
        //切换回主线程处理，安卓不允许在子线程设置ui界面，所以需要回到主线程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cp_info_adapter score_data_adapter = new cp_info_adapter(search_cp.this, 1, cp_info_datainfo);
                show_lv.setAdapter(score_data_adapter);

//                text1.setText(data);
            }
        });

    }
}