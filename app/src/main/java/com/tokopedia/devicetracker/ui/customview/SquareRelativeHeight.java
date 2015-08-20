package com.tokopedia.devicetracker.ui.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by Angga.Prasetiyo on 19/08/2015.
 */
public class SquareRelativeHeight extends RelativeLayout {
    private static final String TAG = SquareRelativeHeight.class.getSimpleName();


    public SquareRelativeHeight(Context context) {
        super(context);
    }

    public SquareRelativeHeight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareRelativeHeight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measured = getMeasuredHeight();
        setMeasuredDimension(measured, measured);
    }
}
