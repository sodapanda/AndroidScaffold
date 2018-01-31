package cf.qishui.scaffold.widgets.clipedview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import cf.qishui.scaffold.R;
import cf.qishui.scaffold.views.ViewScaffold;

/**
 * Created by wangxiao on 2017/9/21.
 * 圆角FrameLayout
 */

public class ClipedView extends FrameLayout {
    private Context mContext;
    private float radius = 8;

    public ClipedView(@NonNull Context context) {
        this(context, null);
    }

    public ClipedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipedView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.scaffold_clipview, defStyleAttr, 0);

            for (int i = 0; i < a.getIndexCount(); i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.scaffold_clipview_corner_radius) {
                    this.radius = a.getDimensionPixelSize(attr, (int) ViewScaffold.dpToPix(4, context));
                }
            }
            a.recycle();
        }

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        setWillNotDraw(false);

//        radius = ViewScaffold.dpToPix(radius, mContext);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rect = new RectF(0, 0, getWidth(), getHeight());

        Path path = new Path();

        path.moveTo(radius, 0);
        path.lineTo(rect.width() - radius, 0);
        path.arcTo(new RectF(rect.width() - radius * 2, 0, rect.width(), radius * 2), -90, 90);
        path.lineTo(rect.width(), rect.height() - radius);
        path.arcTo(new RectF(rect.width() - radius * 2, rect.height() - 2 * radius, rect.width(), rect.height()), 0, 90);
        path.lineTo(radius, rect.height());
        path.arcTo(new RectF(0, rect.height() - radius * 2, radius * 2, rect.height()), 90, 90);
        path.lineTo(0, radius);
        path.arcTo(new RectF(0, 0, 2 * radius, 2 * radius), 180, 90);
        path.close();
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

}
