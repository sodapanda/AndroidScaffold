package cf.qishui.scaffold.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import cf.qishui.scaffold.R;
import cf.qishui.scaffold.drawable.CapsuleDrawable;

/**
 * Created by wangxiao on 2017/10/17.
 */

public class CapsuleView extends RelativeLayout {
    private int bgColor;
    private boolean isFill;
    private float lineWidth;
    private Context mContext;

    public CapsuleView(@NonNull Context context) {
        this(context, null);
    }

    public CapsuleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CapsuleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CapsuleView, defStyleAttr, 0);
            for (int i = 0; i < a.getIndexCount(); i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.CapsuleView_cap_color) {
                    bgColor = a.getColor(attr, 0xffffffff);
                }

                if (attr == R.styleable.CapsuleView_cap_line_width) {
                    lineWidth = a.getDimensionPixelSize(attr, 0);
                }

                if (attr == R.styleable.CapsuleView_cap_fill) {
                    isFill = a.getBoolean(attr, true);
                }
            }
            a.recycle();
        }

        init(context);
    }


    private void init(Context context) {
        if (mContext == null) {
            mContext = context;
        }
        setBackgroundDrawable(null);
        CapsuleDrawable drawable = new CapsuleDrawable();

        drawable.setColor(bgColor);
        drawable.setFill(isFill);
        drawable.setLineWidth(lineWidth);

        setBackgroundDrawable(drawable);
    }

    public void setBgColor(int color) {
        this.bgColor = color;
        init(mContext);
    }

    public void setFill(boolean fill) {
        this.isFill = fill;
        init(mContext);
    }

    public void setLineWidth(int width) {
        this.lineWidth = width;
        init(mContext);
    }
}
