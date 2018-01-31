package cf.qishui.scaffold.widgets.colorshadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cf.qishui.scaffold.views.ViewScaffold;

/**
 * Created by wangxiao on 2017/11/2.
 */

public class ShadowColorView extends View {
    private Context mContext;
    private Paint mPaint;
    private int offsetH;
    private int offsetV;
    private int mColor;
    private int mRadios;

    public ShadowColorView(Context context) {
        this(context, null);
    }

    public ShadowColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint);
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public void setRadios(int radios) {
        this.mRadios = radios;
    }

    public void setOffsetV(int offsetV) {
        this.offsetV = offsetV;
    }

    public void setOffsetH(int offsetH) {
        this.offsetH = offsetH;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left = offsetH;
        int top = 0;
        int right = getWidth() - offsetH;
        int bottom = getHeight() - offsetV;

        mPaint.setColor(mColor);
        mPaint.setShadowLayer(mRadios, 0, ViewScaffold.dpToPix(1, mContext), mColor);
        canvas.drawRect(new Rect(left, top, right, bottom), mPaint);
    }
}
