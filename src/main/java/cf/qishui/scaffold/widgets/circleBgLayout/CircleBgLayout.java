package cf.qishui.scaffold.widgets.circleBgLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by wangxiao on 2017/11/14.
 */

public class CircleBgLayout extends FrameLayout {
    private Context mContext;
    private int mColor = 0xffffffff;

    public CircleBgLayout(@NonNull Context context) {
        this(context, null);
    }

    public CircleBgLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleBgLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        setWillNotDraw(false);
    }

    public void setColor(int color) {
        this.mColor = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        RectF rect = new RectF(0, 0, width, height);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mColor);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawOval(rect, paint);
    }
}
