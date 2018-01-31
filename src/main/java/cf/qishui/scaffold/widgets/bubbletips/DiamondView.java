package cf.qishui.scaffold.widgets.bubbletips;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangxiao on 2017/10/30.
 */

public class DiamondView extends View {
    private Context mContext;

    public DiamondView(Context context) {
        this(context, null);
    }

    public DiamondView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiamondView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xff1a97ff);

        Path path = new Path();
        Rect bounds = new Rect(0, 0, getWidth(), getHeight());
        path.moveTo(bounds.width() / 2.0f, 0);
        path.lineTo(bounds.width(), bounds.height() / 2.0f);
        path.lineTo(bounds.width() / 2.0f, bounds.height());
        path.lineTo(0, bounds.height() / 2.0f);
        path.lineTo(bounds.width() / 2.0f, 0);
        path.close();

        canvas.drawPath(path,paint);
    }
}
