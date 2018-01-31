package cf.qishui.scaffold.widgets.colorshadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import cf.qishui.scaffold.views.ViewScaffold;

/**
 * Created by wangxiao on 2017/11/16.
 */

public class ShadowLayout extends RelativeLayout {
    private Context mContext;
    private View mChildView;
    private RectF mChildBounds;
    private Paint mPaint;

    public ShadowLayout(Context context) {
        this(context, null);
    }

    public ShadowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        setWillNotDraw(false);
        mChildBounds = new RectF();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mChildView = getChildAt(0);
        float x = mChildView.getX();
        float y = mChildView.getY();
        float width = mChildView.getWidth();
        float height = mChildView.getHeight();

        mChildBounds.left = x;
        mChildBounds.top = y;
        mChildBounds.right = x + width;
        mChildBounds.bottom = y + height;

        mPaint.setColor(Color.WHITE);
        mPaint.setShadowLayer(ViewScaffold.dpToPix(10f, mContext), 0, 0, 0xff000000);

        canvas.drawRect(mChildBounds, mPaint);
    }
}
