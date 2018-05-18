package seaver.test.com.may18test;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FirstActivity extends AppCompatActivity {
private TextView json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        json=(TextView)findViewById(R.id.json);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://www.baidu.com").build();
                    Log.d("FirstActivity","Just a test");
                    Response response = client.newCall(request).execute();
                    String str=response.body().string();
                    show(str);
                }catch (Exception e){
                    e.printStackTrace();
                }
                }
        }).start();
    }
    public void show(final String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                json.setText(str);
            }
        });
    }
}
