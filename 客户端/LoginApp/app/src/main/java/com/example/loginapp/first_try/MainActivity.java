package com.example.loginapp.first_try;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.loginapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button login,cancle;
    EditText user,pwd;
    RadioButton man,woman;
    String username,password,sex;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.btn_login_main);
        cancle=(Button)findViewById(R.id.btn_cancel);

        user=(EditText)findViewById(R.id.et_username);
        pwd=(EditText)findViewById(R.id.et_password);

        man=(RadioButton)findViewById(R.id.rb_man);
        woman=(RadioButton)findViewById(R.id.rb_woman);

        login.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_main:
                username = user.getText().toString().trim();
                password = pwd.getText().toString().trim();
                if(man.isClickable()) {
                    sex = "男";
                } else if(woman.isClickable()) {
                    sex = "女";
                    u = new User(username,password,sex);
                }
                u = new User(username,password,sex);
                checkLogin(u);
                break;
            case R.id.btn_cancel:
                break;
        }
    }

    private void checkLogin(User u) {

        if(u.getUsername().equals("WZJ") && u.getPassword().equals("wzj") && u.getSex().equals("男")) {
            Intent intent=new Intent(this,infoActivity.class);
            intent.putExtra("user",u);
            startActivity(intent);
        }

    }
}