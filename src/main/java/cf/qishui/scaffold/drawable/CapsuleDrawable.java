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
 * Created by wangxiao on 2017/8/31.
 * 胶囊形状
 */

public class CapsuleDrawable extends Drawable {
    private float lineWidth;
    private int color;
    private boolean fill;

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bound = getBounds();
        float height = bound.height();
        float radios = height / 2.0f;
        float width = bound.width();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        if (fill) {
            paint.setStyle(Paint.Style.FILL);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(lineWidth);
        }

        Path path = new Path();
        if (fill) {
            path.moveTo(radios, 0);
            path.lineTo(width - radios, 0);
            path.arcTo(new RectF(width - height, 0, width, height), -90, 180);
            path.lineTo(radios, height);
            path.arcTo(new RectF(0, 0, height, height), 90, 180);
        } else {
            path.moveTo(radios, lineWidth);
            path.lineTo(width - radios, lineWidth);
            path.arcTo(new RectF(width - height, lineWidth, width - lineWidth, height - lineWidth), -90, 180);
            path.lineTo(radios, height - lineWidth);
            path.arcTo(new RectF(lineWidth, lineWidth, height - lineWidth, height - lineWidth), 90, 180);
        }

        canvas.drawPath(path, paint);
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
