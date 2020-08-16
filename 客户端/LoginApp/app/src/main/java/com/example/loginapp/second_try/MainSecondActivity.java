package com.example.loginapp.second_try;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginapp.R;

public class MainSecondActivity extends AppCompatActivity {

    private Button mBtnGet,mBtnGet2;
    private Button mBtnClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_second);

        mBtnGet=findViewById(R.id.mBtnGet);
        mBtnGet2=findViewById(R.id.mBtnGet2);
        mBtnClient=findViewById(R.id.mBtnClient);
        OnClick onClick=new OnClick();
        mBtnGet.setOnClickListener(onClick);
        mBtnGet2.setOnClickListener(onClick);
        mBtnClient.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener{

        Intent intent=null;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.mBtnGet:
                    intent=new Intent(MainSecondActivity.this, HttpUrlConnection.class);
                    break;
                case R.id.mBtnGet2:
                    intent=new Intent(MainSecondActivity.this, HttpClientActivity.class);
                    break;
                case R.id.mBtnClient:
                    intent=new Intent(MainSecondActivity.this, ClientActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
