package cf.qishui.scaffold.widgets.colorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cf.qishui.scaffold.R;

/**
 * Created by wangxiao on 2017/11/30.
 */

public class ColorView extends View {
    private Context mContext;
    private int color;

    public ColorView(Context context) {
        this(context, null);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColorView, defStyleAttr, 0);

            color = a.getColor(R.styleable.ColorView_color, 0);
            a.recycle();
        }

        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(color);
    }
}
