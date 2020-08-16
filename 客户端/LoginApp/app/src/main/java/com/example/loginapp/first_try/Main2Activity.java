package com.example.loginapp.first_try;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.loginapp.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Button login, cancle;
    EditText user, pwd;
    RadioButton man, woman;
    String username, password, sex;
    User u;

    private String url = "http://192.168.0.167:8088/android/LoginServlet";//服务器接口地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.btn_login_main);
        cancle = (Button) findViewById(R.id.btn_cancel);

        user = (EditText) findViewById(R.id.et_username);
        pwd = (EditText) findViewById(R.id.et_password);

        man = (RadioButton) findViewById(R.id.rb_man);
        woman = (RadioButton) findViewById(R.id.rb_woman);

        login.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_main:
                /**
                 * 开辟一个子线程访问网络，否则会抛出异常
                 */
                new Thread() {
                    @Override
                    public void run() {
                        username = user.getText().toString().trim();
                        password = pwd.getText().toString().trim();
                        if(man.isClickable()) {
                            sex = "男";
                        } else if(woman.isClickable()) {
                            sex = "女";
                        }
                        u = new User(username,password,sex);

                        NameValuePair pair1 = new BasicNameValuePair("username", username);
                        NameValuePair pair2 = new BasicNameValuePair("password", password);
                        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                        pairList.add(pair1);
                        pairList.add(pair2);
                        try {
                            HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList);
                            // URl是接口地址
                            HttpPost httpPost = new HttpPost(url);
                            // 将请求体内容加入请求中
                            httpPost.setEntity(requestHttpEntity);
                            // 需要客户端对象来发送请求
                            HttpClient httpClient = new DefaultHttpClient();
                            // 发送请求
                            HttpResponse response = httpClient.execute(httpPost);
                            // 显示响应
                            if(getInfo(response)) {
                                Intent intent=new Intent(Main2Activity.this,infoActivity.class);
                                intent.putExtra("user",u);
                                startActivity(intent);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }.start();
                break;
        }
    }

    // 收取数据
    private static boolean getInfo(HttpResponse response) throws Exception {

        HttpEntity httpEntity = response.getEntity();
        InputStream inputStream = httpEntity.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String result = "";
        String line = "";
        while (null != (line = reader.readLine())){
            result += line;
        }
        if(result.equals("success")) {
            return true;
        }
        return false;
    }
}