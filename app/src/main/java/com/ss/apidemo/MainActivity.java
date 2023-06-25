package com.ss.apidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button rs485_rs232Btn;
    private Button relayBtn;
    private Button brightBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
//        rs485_rs232Btn = findViewById(R.id.rs485_rs232);
//        rs485_rs232Btn.setOnClickListener(this);
//
//        brightBtn = findViewById(R.id.brightness);
//        brightBtn.setOnClickListener(this);
//
//        relayBtn = findViewById(R.id.relay);
//        relayBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.rs485_rs232:
//                Intent intent2 = new Intent(this, Rs485AndRs232Activity.class);
//                startActivity(intent2);
//                break;
//
//            case R.id.relay:
//                Intent intent6 = new Intent(this, GpioActivity.class);
//                startActivity(intent6);
//                break;
//            case R.id.brightness:
//                Intent intent7 = new Intent(this, BrightnessActivity.class);
//                startActivity(intent7);
//                break;
//        }
    }
}