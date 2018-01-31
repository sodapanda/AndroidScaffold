package cf.qishui.scaffold.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by wangxiao on 2017/9/4.
 * Bottom bar中间突起来一个半圆的形状
 */

public class LineCircleDrawabel extends Drawable {
    private float mLineWidth;
    private float mTopLineSpace;
    private int mBgColor;
    private int mLineColor;

    public void setmLineWidth(float mLineWidth) {
        this.mLineWidth = mLineWidth;
    }

    public void setmTopLineSpace(float mTopLineSpace) {
        this.mTopLineSpace = mTopLineSpace;
    }

    public void setmBgColor(int mBgColor) {
        this.mBgColor = mBgColor;
    }

    public void setmLineColor(int mLineColor) {
        this.mLineColor = mLineColor;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(mLineColor);
        paint.setStrokeWidth(mLineWidth);

        canvas.drawLine(0, mTopLineSpace, bounds.width(), mTopLineSpace, paint);

        Path path = new Path();
        RectF centerRect = new RectF();
        centerRect.left = (bounds.width() - bounds.height()) / 2.0f + mLineWidth;
        centerRect.top = mLineWidth;
        centerRect.right = bounds.centerX() + bounds.height() / 2.0f - mLineWidth;
        centerRect.bottom = bounds.height() - mLineWidth;
        path.addArc(centerRect, 0, 360);

        canvas.drawPath(path, paint);

        RectF coverRect = new RectF(0, mTopLineSpace + mLineWidth, bounds.width(), bounds.height());
        paint.setColor(mBgColor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(coverRect, paint);

        RectF coverInner = new RectF(centerRect.left + mLineWidth, centerRect.top + mLineWidth, centerRect.right - mLineWidth, centerRect.bottom - mLineWidth);
        canvas.drawArc(coverInner, 0, 360, true, paint);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
