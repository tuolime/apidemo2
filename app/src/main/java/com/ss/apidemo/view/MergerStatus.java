package com.ss.apidemo.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Toolbar;

import com.ss.apidemo.R;


/**
 * Created by Administrator on 2017/12/14 0014.
 * 兼容沉浸式状态栏
 */

public class MergerStatus extends Toolbar {

    public MergerStatus(Context context) {
        this(context, null);
    }

    public MergerStatus(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MergerStatus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    private void setup() {
        int mCompatPaddingTop = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mCompatPaddingTop = getStatusHeight();
        }
        this.setPadding(getPaddingLeft(), getPaddingTop() + mCompatPaddingTop, getPaddingRight(), getPaddingBottom());
        // 更换Toolbar背景颜色
//        this.setBackgroundColor(SkinUtils.getSkin(getContext()).getPrimaryColor());
        // 左浅蓝右深蓝 渐变
        this.setBackgroundDrawable(getResources().getDrawable(R.drawable.bar_gradient_bg));
    }

    private int getStatusHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    private float px2dp(float pxVal) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }
}
