package cf.qishui.scaffold.widgets.bubbletips;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cf.qishui.scaffold.R;

/**
 * Created by wangxiao on 2017/10/30.
 */

public class BubbleTipsView extends RelativeLayout {
    private TextView mTvMsg;
    private Context mContext;
    private String mMsg;
    private View mArrow;

    public BubbleTipsView(Context context) {
        this(context, null);
    }

    public BubbleTipsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleTipsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BubbleTipsView, defStyleAttr, 0);

        mMsg = a.getString(R.styleable.BubbleTipsView_bubble_text);

        a.recycle();

        init(context);
    }

    public void setArrowX(final int x) {
        post(new Runnable() {
            @Override
            public void run() {
                mArrow.setX(x);
            }
        });
    }

    private void init(Context context) {
        mContext = context;

        LayoutInflater.from(mContext).inflate(R.layout.scaffold_bubble_bg, this, true);
        mTvMsg = findViewById(R.id.scaffold_bubble_tv);
        mTvMsg.setText(mMsg);

        mArrow = findViewById(R.id.scaffold_bubble_arrow);
    }

    public void setText(String msg) {
        mTvMsg.setText(msg);
    }
}
