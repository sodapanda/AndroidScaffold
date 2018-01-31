package cf.qishui.scaffold.widgets.progresslayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangxiao on 2017/10/25.
 * <p>
 * 显示加载进度
 */

public class ProgressLayout extends View {
    private Context mContext;
    private Paint paint;

    public ProgressLayout(@NonNull Context context) {
        this(context, null);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int startX = 0;
        int startY = 0;
        int endX = getWidth();
        int endY = 0;
        int startColor = 0x00ffffff;
        int endColor = 0x7fffffff;
        LinearGradient gradient = new LinearGradient(startX,
                startY, endX, endY, startColor, endColor, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        canvas.drawPaint(paint);
    }
}
