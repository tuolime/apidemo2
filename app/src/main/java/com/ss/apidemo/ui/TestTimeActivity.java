package com.ss.apidemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.utils.ChjTimer;

public class TestTimeActivity extends AppCompatActivity implements View.OnClickListener, ChjTimer.ChjTimerInter {

    private TextView tiems,timnew;

    private ChjTimer chjTimer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_time);

        tiems = findViewById(R.id.time);

        timnew = findViewById(R.id.timnew);

        findViewById(R.id.but).setOnClickListener(this);

        findViewById(R.id.buts).setOnClickListener(this);

        chjTimer = new ChjTimer(this);

    }

    @Override

    public void onClick(View view) {

        switch (view.getId()){

            case R.id.but:

                tiems.setText("10");

                timnew.setText("正在计时");

                chjTimer.start(10);


                break;

            case R.id.buts:

                chjTimer.stop();

                break;

        }

    }

    @Override

    public void second(int time) {

        tiems.setText(time + "");

    }

    @Override

    public void expire() {

        timnew.setText("计时完成");

    }

    @Override

    public void stop(int time) {

        timnew.setText("计时终止" + time);

    }
}