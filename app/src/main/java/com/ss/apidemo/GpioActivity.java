package com.ss.apidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.ss.api.HardwareCtrl;

import java.io.File;
import java.io.IOException;


public class GpioActivity extends AppCompatActivity {

    private Switch relaySwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpio);
        relaySwitch = findViewById(R.id.relaySwitch);
        relaySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    sendRelaySignal(isChecked);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void sendRelaySignal(boolean up) throws Exception {
        HardwareCtrl.exportGpio(92);
        HardwareCtrl.ctrlGpio(92, "out", up ? 1 : 0);
    }

    public static boolean getRelayValue() throws Exception {
        boolean relay = false;
        HardwareCtrl.exportGpio(92);
        String singal = HardwareCtrl.readFromDevice(new File("/sys/class/gpio/gpio92/value"));
        relay = TextUtils.equals(singal, "1");
        return relay;
    }
}