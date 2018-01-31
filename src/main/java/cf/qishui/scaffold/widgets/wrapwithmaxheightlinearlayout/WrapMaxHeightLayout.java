package cf.qishui.scaffold.widgets.wrapwithmaxheightlinearlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import cf.qishui.scaffold.R;

/**
 * Created by wangxiao on 2017/11/21.
 */

public class WrapMaxHeightLayout extends ScrollView {
    private int mMaxHeight;

    public WrapMaxHeightLayout(Context context) {
        this(context, null);
    }

    public WrapMaxHeightLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapMaxHeightLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WrapMaxHeightLayout, defStyleAttr, 0);

        mMaxHeight = a.getDimensionPixelSize(R.styleable.WrapMaxHeightLayout_max_height, 0);

        a.recycle();
        init(context);
    }

    private void init(Context context) {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        View child = getChildAt(0);

        int width = getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        if (childHeight <= mMaxHeight) {
            setMeasuredDimension(width, childHeight);
        } else {
            setMeasuredDimension(width, mMaxHeight);
        }
    }
}
