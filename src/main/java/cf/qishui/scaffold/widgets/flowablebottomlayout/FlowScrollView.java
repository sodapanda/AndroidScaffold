package cf.qishui.scaffold.widgets.flowablebottomlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class FlowScrollView extends ScrollView {
    public FlowScrollView(Context context) {
        this(context, null);
    }

    public FlowScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (getChildCount() > 0) {
            View child = getChildAt(0);
            int childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            int childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.UNSPECIFIED);

            child.measure(childWidthSpec, childHeightSpec);
        }

        setMeasuredDimension(width, height);
    }
}
