package com.ss.apidemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.ss.api.HardwareCtrl;
import com.ss.apidemo.R;

public class BrightnessActivity extends AppCompatActivity implements View.OnClickListener {

    private Button confirmButton;
    private SeekBar brightnessBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        brightnessBar = findViewById(R.id.brightnessBar);
        brightnessBar.setMax(255);
        confirmButton = findViewById(R.id.confirm);
        confirmButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm:
                HardwareCtrl.setBrightness(brightnessBar.getProgress());
                break;
        }
    }
}
