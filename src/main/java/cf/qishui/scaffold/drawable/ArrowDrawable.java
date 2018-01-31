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
 * Created by wangxiao on 2017/8/11.
 * 带箭头的圆角矩形
 */

public class ArrowDrawable extends Drawable {
    private float radius;
    private float arrowCenterX;
    private float arrowH;
    private float arrowW;
    private int color;

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setArrowCenterX(float arrowCenterX) {
        this.arrowCenterX = arrowCenterX;
    }

    public void setArrowH(float arrowH) {
        this.arrowH = arrowH;
    }

    public void setArrowW(float arrowW) {
        this.arrowW = arrowW;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();

        RectF leftTop = new RectF(0, arrowH, radius * 2, arrowH + radius * 2);
        RectF rightTop = new RectF(width - radius * 2, arrowH, width, arrowH + radius * 2);
        RectF leftBottom = new RectF(0, height - 2 * radius, radius * 2, height);
        RectF rightBottom = new RectF(width - 2 * radius, height - 2 * radius, width, height);

        Path path = new Path();
        path.moveTo(radius, arrowH);
        path.lineTo(arrowCenterX - arrowW / 2, arrowH);
        path.lineTo(arrowCenterX, 0);
        path.lineTo(arrowCenterX + arrowW / 2, arrowH);
        path.lineTo(width - radius, arrowH);
        path.arcTo(rightTop, -90, 90);
        path.lineTo(width, height - radius);
        path.arcTo(rightBottom, 0, 90);
        path.lineTo(radius, height);
        path.arcTo(leftBottom, 90, 90);
        path.lineTo(0, arrowH + radius);
        path.arcTo(leftTop, 180, 90);
        path.close();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawPath(path, paint);
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
