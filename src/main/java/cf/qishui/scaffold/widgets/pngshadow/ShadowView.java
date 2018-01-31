package cf.qishui.scaffold.widgets.pngshadow;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cf.qishui.scaffold.R;
import cf.qishui.scaffold.bitmap.BitmapSingle;

/**
 * Created by wangxiao on 2017/11/23.
 */

public class ShadowView extends View {
    private Context mContext;
    private Bitmap maskBitmap;
    private Bitmap origBitmap;
    private Paint mPaint;
    private int color;

    public ShadowView(Context context) {
        this(context, null);
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShadowView, defStyleAttr, 0);

            for (int i = 0; i < a.getIndexCount(); i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.ShadowView_shadow_color) {
                    this.color = a.getColor(attr, 0);
                }
                if (attr == R.styleable.ShadowView_shadow_mask) {
                    int resourceId = a.getResourceId(attr, 0);
                    origBitmap = BitmapSingle.getBitmapById(context, resourceId);
                }

            }

            this.color = a.getColor(R.styleable.ShadowView_shadow_color, 0);
            a.recycle();
        }

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        if (origBitmap != null) {
            try {
                maskBitmap = origBitmap.extractAlpha();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
        this.color = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(this.color);
        if (maskBitmap != null) {
            canvas.drawBitmap(maskBitmap, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
        }
    }
}
