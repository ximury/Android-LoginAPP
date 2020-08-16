package com.example.loginapp.first_try;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginapp.R;

public class infoActivity extends AppCompatActivity {
    Button button;
    TextView tv1,tv2,tv3;
    String sex,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tv1=(TextView)findViewById(R.id.info_username);
        tv2=(TextView)findViewById(R.id.info_password);
        tv3=(TextView)findViewById(R.id.info_sex);

        Intent getData=getIntent();
        User user = (User)getData.getSerializableExtra("user");
        tv1.setText("Your username is " +user.getUsername());
        tv2.setText("Your sex is "+user.getSex());
        tv3.setText("Your password is "+user.getPassword());
    }
}