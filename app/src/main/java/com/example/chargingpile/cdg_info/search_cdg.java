package com.example.chargingpile.cdg_info;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chargingpile.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class search_cdg extends AppCompatActivity {
    private ArrayList<cdg_info_datainfo> cdg_info_datainfo;
    private ListView show_lv;
    private EditText cpID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cdj);
        cdg_info_datainfo = new ArrayList<cdg_info_datainfo>();
        show_lv = findViewById(R.id.showscore_lv);
        cpID= findViewById(R.id.cpID);
        Log.d("data","查询");

    }


    private void search() {
        //创建一个子线程进行逻辑处理
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //get方式开始
                    OkHttpClient client =new OkHttpClient();
                    Request request=new Request.Builder().url("http://123.56.106.24:8082/chargepile/getBikeShedChargingpile?bikeshedId="+cpID.getText().toString()).build();
                    //使用newcall()创建一个call对象，并调用
                    Response response= client.newCall(request).execute();
                    //得到返回数据
                    String data= response.body().string();
                    //将返回数据添加到ui页面上
                    Log.d("data",data);

                    JSONArray jsonArray = new JSONArray(data);

                    for (int i = 0; i <jsonArray.length() ; i++) {

                        JSONObject jsonObj2 = jsonArray.getJSONObject(i);

                        cdg_info_datainfo mapx = new cdg_info_datainfo();
                        mapx.setBikeshedId(jsonObj2.getString("bikeshedId"));
                        mapx.setCpID(jsonObj2.getString("cpID"));
                        mapx.setGrp(jsonObj2.getString("grp"));
                        mapx.setNo(jsonObj2.getString("no"));
                        mapx.setUuid(jsonObj2.getString("uuid"));

                        cdg_info_datainfo.add(mapx);

                    }
                    showData();
                    //get方式结束

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

                cdg_info_adapter score_data_adapter = new cdg_info_adapter(search_cdg.this, 1,cdg_info_datainfo );
                show_lv.setAdapter(score_data_adapter);

//                text1.setText(data);
            }
        });

    }

    public void search_cpid(View view) {
        cdg_info_datainfo.clear();
        search();
    }
}