package com.example.chargingpile.MoneyStandard_info;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chargingpile.R;
import com.example.chargingpile.cdg_info.cdg_info_datainfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class search_money extends AppCompatActivity {
    private ArrayList<money_datainfo> money_datainfo;
    private ListView show_lv;
    private EditText cpID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_money);
        money_datainfo = new ArrayList<money_datainfo>();
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
                    Request request=new Request.Builder().url("http://123.56.106.24:8082/manage/selectMoneyStandard?bsid="+cpID.getText().toString()).build();
                    //使用newcall()创建一个call对象，并调用
                    Response response= client.newCall(request).execute();
                    //得到返回数据
                    String data= response.body().string();
                    //将返回数据添加到ui页面上
                    Log.d("data",data);

                    JSONArray jsonArray = new JSONArray(data);

                    for (int i = 0; i <jsonArray.length() ; i++) {

                        JSONObject jsonObj2 = jsonArray.getJSONObject(i);

                        money_datainfo mapx = new money_datainfo();
                        mapx.setUuid(jsonObj2.getString("uuid"));
                        mapx.setPrice(jsonObj2.getString("price"));
                        mapx.setTime(jsonObj2.getString("time"));
                        mapx.setBsId(jsonObj2.getString("bsId"));

                        money_datainfo.add(mapx);

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

                money_info_adapter score_data_adapter = new money_info_adapter(search_money.this, 1,money_datainfo );
                show_lv.setAdapter(score_data_adapter);

//                text1.setText(data);
            }
        });

    }

    public void search_cpid(View view) {
        money_datainfo.clear();
        search();
    }
}