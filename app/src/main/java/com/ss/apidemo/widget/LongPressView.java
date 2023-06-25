package com.ss.apidemo.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import com.ss.apidemo.ui.PassWordActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class LongPressView extends AppCompatButton {

    private float mLastX, mLastY;

    private Runnable mRunnable;

    private boolean isMove;

    private static final float TOUCH_SLOP = 20;
    private Context context;

    public LongPressView(Context context) {

        super(context);

    }

    public LongPressView(final Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        this.context = context;
        mRunnable = new Runnable() {

            @Override

            public void run() {

                performLongClick();

//                Toast.makeText(context, "自定义长按时长5000毫秒", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, PassWordActivity.class));
//                context.startActivity(new Intent(context, HandgearActivity.class));
//                context.startActivity(new Intent(context, UserSettingActivity.class));

            }

        };

    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();

        float y = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                mLastX = x;

                mLastY = y;

                isMove = false;

                /**
                 * 第二个参数就是自定义长按时长

                 */

                postDelayed(mRunnable, 1000);

                break;

            case MotionEvent.ACTION_MOVE:

                if (isMove) {

                    break;

                }

                if (Math.abs((mLastX - event.getX())) > TOUCH_SLOP || Math.abs((mLastY - event.getY())) > TOUCH_SLOP) {

                    isMove = true;

                    removeCallbacks(mRunnable);

                }

                break;

            case MotionEvent.ACTION_UP:

                removeCallbacks(mRunnable);

                break;

        }

        return super.onTouchEvent(event);

    }

}
