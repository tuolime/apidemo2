package com.ss.apidemo.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.ss.apidemo.R;


/**
 * Created by guo on 2018/11/1.
 */

public class CallDialog extends BaseCustomDialog {
    private CallDialog dialog;
    String title;
    OnClickIsConfirm listener;
    OnClickIsCancel listeners;

    public CallDialog(Activity context) {
        super(context);
    }


    @Override
    protected void setContentView() {
        this.setContentView(R.layout.dialog_call);
        TextView cancel = findViewById(R.id.cancel);
        TextView send = findViewById(R.id.send);
        TextView tv_content = findViewById(R.id.tv_content);
        tv_content.setText(title);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnClickIsConfirmListener();
                dismiss();
            }
        });
    }
    public void loadDialog(Activity activity,OnClickIsConfirm listener, String title) {
        dialog = new CallDialog(activity);
        dialog.listener=listener;
        dialog.title=title;
        dialog.show();
    }
    public  interface OnClickIsConfirm{
        public void OnClickIsConfirmListener();
    }
    public  interface OnClickIsCancel{
        public void OnClickIsCancelListener();
    }
}
