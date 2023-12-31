package com.ss.apidemo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.SeekBar;

/*******
 * VerticalSeekBar使用说明
 * 1.SeekBar不能替换成AppCompatSeekBar,防止报错可以添加@SuppressLint("AppCompatCustomView")注解
 * 2.关于方向，这里提供逆时针90度和顺时针90度的示例
 * 3.逆时针90度 onDraw--> c.rotate(-90);c.translate(-getHeight(), 0);
 * 4.顺时针90度 onDraw--> c.rotate(+90);c.translate(0, -getWidth());
 */
@SuppressLint("AppCompatCustomView")
public class VerticalSeekBar2 extends SeekBar {
    private boolean mIsDragging;
    private float mTouchDownY;
    private int mScaledTouchSlop;
    private boolean isInScrollingContainer = false;

    public interface OnSeekBarChangeListener
    {
        void onProgressChanged(VerticalSeekBar2 VerticalBar, int progress, boolean fromUser);
        void onStartTrackingTouch(VerticalSeekBar2 VerticalBar);
        void onStopTrackingTouch(VerticalSeekBar2 VerticalBar);
    }

    private OnSeekBarChangeListener mOnSeekBarChangeListener;

    public void setOnSeekBarChangeListener(OnSeekBarChangeListener l)
    {
        mOnSeekBarChangeListener = l;
    }

    public boolean isInScrollingContainer()
    {
        return isInScrollingContainer;
    }

    public void setInScrollingContainer(boolean isInScrollingContainer)
    {
        this.isInScrollingContainer = isInScrollingContainer;
    }

    /**
     * On touch, this offset plus the scaled value from the position of the
     * touch will form the progress value. Usually 0.
     */
    float mTouchProgressOffset;

    public VerticalSeekBar2(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public VerticalSeekBar2(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public VerticalSeekBar2(Context context)
    {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {

        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec,
                                          int heightMeasureSpec)
    {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override
    protected synchronized void onDraw(Canvas canvas)
    {
//        canvas.rotate(90);
//        canvas.translate(0, -getWidth());
        canvas.rotate(-90);
        canvas.translate(-getHeight(), 0);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (!isEnabled())
        {
            return false;
        }

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (isInScrollingContainer())
                {

                    mTouchDownY = event.getY();
                }
                else
                {
                    setPressed(true);
                    invalidate();
                    onStartTrackingTouch();
                    trackTouchEvent(event);
                    attemptClaimDrag();
                    onSizeChanged(getWidth(), getHeight(), 0, 0);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mIsDragging)
                {
                    trackTouchEvent(event);

                }
                else
                {
                    final float y = event.getY();
                    if (Math.abs(y - mTouchDownY) > mScaledTouchSlop)
                    {
                        setPressed(true);

                        invalidate();
                        onStartTrackingTouch();
                        trackTouchEvent(event);
                        attemptClaimDrag();

                    }
                }
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;

            case MotionEvent.ACTION_UP:
                if (mIsDragging)
                {
                    trackTouchEvent(event);
                    onStopTrackingTouch();
                    setPressed(false);

                }
                else
                {
                    // Touch up when we never crossed the touch slop threshold
                    // should
                    // be interpreted as a tap-seek to that location.
                    onStartTrackingTouch();
                    trackTouchEvent(event);
                    onStopTrackingTouch();

                }
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                invalidate();
                break;
        }
        return true;

    }

    private void trackTouchEvent(MotionEvent event)
    {
        final int height = getHeight();
        final int top = getPaddingTop();
        final int bottom = getPaddingBottom();
        final int available = height - top - bottom;

        int y = (int) event.getY();

        float scale;
        float progress = 0;

        if (y > height - bottom)
        {
            scale = 0.0f;
        }
        else if (y < top)
        {
            scale = 1.0f;
        }
        else
        {
            scale = (float) (y) / (float) available;
            progress = mTouchProgressOffset;
        }

        final int max = getMax();
//        progress += scale * max;
        progress += max-(scale * max);

        setProgress((int) progress);

    }

    /**
     * This is called when the user has started touching this widget.
     */
    public void onStartTrackingTouch()
    {
        mIsDragging = true;
        if (mOnSeekBarChangeListener != null) {
            mOnSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    /**
     * This is called when the user either releases his touch or the touch is
     * canceled.
     */
    public void onStopTrackingTouch()
    {
        mIsDragging = false;
        if (mOnSeekBarChangeListener != null) {
            mOnSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    private void attemptClaimDrag()
    {
        ViewParent p = getParent();
        if (p != null)
        {
            p.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override
    public synchronized void setProgress(int progress)
    {
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
        if (mOnSeekBarChangeListener != null) {
            mOnSeekBarChangeListener.onProgressChanged(this, getProgress(), isPressed());
        }
    }

}
