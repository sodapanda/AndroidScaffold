package cf.qishui.scaffold.widgets.flowablebottomlayout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class FlowBottomLayout extends RelativeLayout {
    private View mFlowView;
    private int mFlowDistance;

    public FlowBottomLayout(Context context) {
        this(context, null);
    }

    public FlowBottomLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowBottomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        int measuredHeight = getMeasuredHeight();
        if (measuredHeight < parentHeight) {
            mFlowDistance = parentHeight - measuredHeight;
            setMeasuredDimension(getMeasuredWidth(), parentHeight);
        } else {
            mFlowDistance = 0;
        }

        Log.i("flowview", "onMeasure flow distance " + mFlowDistance);

    }

    public void setFlowView(View flowView) {
        mFlowView = flowView;
    }

    public void logPosition() {
        int[] location = new int[2];
        mFlowView.getLocationInWindow(location);
        Log.i("flowview", "positoin " + location[1]);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.i("flowview", "onlayout ");

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == mFlowView) {
                int parentH = b - t;
                int targetY = parentH - child.getHeight();
                Log.i("flowview", "targety" + targetY);
                child.setY(targetY);
            }
        }
    }

}
