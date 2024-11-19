package com.ss.apidemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ThreadUtils;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.bean.QueueMessage;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.utils.LogUtils;
import com.ss.apidemo.utils.PlayVoiceUtils;
import com.ss.apidemo.utils.ToastUtil;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestDialogActivity extends AppCompatActivity {
    private HintDialog dialog;
    private Queue<Integer> loopDatas = new ConcurrentLinkedQueue<>();//队列
    private int anInt = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            showTips("手具连接失败");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);
        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        anInt++;
                        loopDatas.offer(anInt); //添加数据
                        LogUtils.e("入队列"+anInt);
                    }
                }).start();
            }
        });
        Button add2 = findViewById(R.id.add2);
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        anInt++;
                        loopDatas.offer(10); //添加数据
                        LogUtils.e("入队列"+10);
                    }
                }).start();
            }
        });

        //开启队列监听，队列加入数据就会执行
        startLoopListener();
    }

    public void startLoopListener(){
        ThreadUtils.getIoPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        if (loopDatas.size() > 0){
                            Integer value = loopDatas.poll();
                            if (value != null){
                                LogUtils.e("执行队列-读取到数据="+value);
                            }
                            Thread.sleep(1000);//队列执行间隔
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void selectDialog(View view){
        switch (view.getId()){
            case R.id.bt_1:
                    showTips("短板短路");
                handler.sendMessageDelayed(new Message(),3000);
                break;
            case R.id.bt_2:
                showTips("手具连接失败");
                break;
            case R.id.bt_3:
                showTips("水温高");
                break;
        }
    }
    public void showTips(String hint){
        if (dialog != null) {
            dialog.closeDialog();
            dialog.loadDialog(TestDialogActivity.this, new HintDialog.OnClickIsConfirm() {
                @Override
                public void OnClickIsConfirmListener() {//确定

                }

            }, hint);
        } else {
            dialog = new HintDialog(TestDialogActivity.this);
            dialog.loadDialog(TestDialogActivity.this, new HintDialog.OnClickIsConfirm() {
                @Override
                public void OnClickIsConfirmListener() {//确定
                    handler.sendMessageDelayed(new Message(),3000);
                }

            }, hint);
        }

    }


}