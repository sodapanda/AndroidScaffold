package cf.qishui.scaffold.widgets.clipedview;

/**
 * Created by wangxiao on 2017/11/30.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import cf.qishui.scaffold.R;
import cf.qishui.scaffold.bitmap.BitmapSingle;
import cf.qishui.scaffold.bitmap.Config;

public class RoundedCornerLayout extends FrameLayout {
    private Bitmap maskBitmap;
    private Paint paint;
    private float cornerRadius;
    private int color;
    private Context mContext;

    private Rect bounds = new Rect();
    private Rect newBounds = new Rect();

    public RoundedCornerLayout(Context context) {
        this(context, null);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundedCornerLayout, defStyle, 0);
            cornerRadius = a.getDimensionPixelSize(R.styleable.RoundedCornerLayout_radios, 0);
            color = a.getColor(R.styleable.RoundedCornerLayout_round_color, 0);
            a.recycle();
        }

        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mContext = context;
        setWillNotDraw(false);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        newBounds.set(0, 0, right - left, bottom - top);

        if (!newBounds.equals(bounds)) {
            bounds.set(newBounds);

            Config config = new Config();
            config.color = color;
            config.width = bounds.width();
            config.height = bounds.height();
            config.radius = cornerRadius;
            maskBitmap = BitmapSingle.getBitmapByConfig(mContext, config);
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (maskBitmap != null) {
            canvas.drawBitmap(maskBitmap, 0f, 0f, paint);
        }
    }
}

