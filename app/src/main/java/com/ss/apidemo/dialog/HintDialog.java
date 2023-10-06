package com.ss.apidemo.dialog;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.utils.PlayVoiceUtils;


/**
 * 提示用户
 */

public class HintDialog extends BaseCustomDialog {
    private HintDialog dialog;
    String content;
    OnClickIsConfirm listener;

    public HintDialog(Activity context) {
        super(context);
    }


    @Override
    protected void setContentView() {
        this.setContentView(R.layout.dialog_hint);
        TextView send = findViewById(R.id.send);
        TextView tv_content = findViewById(R.id.tv_content);
        tv_content.setText(content);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);

                listener.OnClickIsConfirmListener();
                dismiss();
            }
        });
    }

    public void loadDialog(Activity activity, OnClickIsConfirm listener, String content) {
        dialog = new HintDialog(activity);
        dialog.setCancelable(false);
        dialog.listener = listener;
        dialog.content = content;
        if (!activity.isFinishing()){//context即为本界面的Activity
            dialog.show();
        }

    }

    public void closeDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public interface OnClickIsConfirm {
        public void OnClickIsConfirmListener();
    }
}
