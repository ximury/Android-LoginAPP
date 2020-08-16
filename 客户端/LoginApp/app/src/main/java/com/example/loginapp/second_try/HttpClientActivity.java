package com.example.loginapp.second_try;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.loginapp.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientActivity extends AppCompatActivity {
    private TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client);
        tvContent=findViewById(R.id.tv_content2);
        requestUsingHttpClient();
    }

    // 同样的消息机制
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                tvContent.setText(msg.obj.toString());
            }
        }
    };

    private void requestUsingHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient client = new DefaultHttpClient(); // HttpClient 是一个接口，无法实例化，所以我们通常会创建一个DefaultHttpClient实例
                HttpGet get = new HttpGet("https://www.baidu.com"); // 发起GET请求就使用HttpGet，发起POST请求则使用HttpPost，这里我们先使用HttpGet
                try {
                    HttpResponse httpResponse = client.execute(get); // 调用HttpClient对象的execute()方法
                    // 状态码200说明响应成功
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = httpResponse.getEntity(); // 取出报文的具体内容
                        String response = EntityUtils.toString(entity, "utf-8"); // 报文编码

                        // 发送消息
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = response;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
