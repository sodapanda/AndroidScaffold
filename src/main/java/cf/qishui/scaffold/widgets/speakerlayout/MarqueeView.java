package cf.qishui.scaffold.widgets.speakerlayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import java.util.List;

import cf.qishui.scaffold.views.ViewScaffold;

/**
 * Created by wangxiao on 2017/9/26.
 * 跑马灯布局
 */

public class MarqueeView extends ViewGroup {
    private Context mContext;
    private TextView mChild;
    private int speed;
    private ObjectAnimator mCurrentAnim;
    private List<String> mData;

    private boolean mStopFlag;

    private int mIndex = -1;

    public MarqueeView(@NonNull Context context) {
        this(context, null);
    }

    public MarqueeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        speed = 70;
    }

    public void setData(List<String> data) {
        mIndex = -1;
        this.mData = data;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //只接受一个view child
        mChild = (TextView) getChildAt(0);

        int widthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int heightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

        mChild.measure(widthSpec, heightSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = mChild.getMeasuredHeight();

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean flat, int l, int t, int r, int b) {
        View child = getChildAt(0);
        child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    //获取下一条消息
    private String getNextMsg() {
        if (mData == null) {
            return "";
        }

        if (mData.size() == 0) {
            return "";
        }

        mIndex = mIndex + 1;
        if (mIndex >= mData.size()) {
            mIndex = 0;
        }

        return mData.get(mIndex);
    }

    //停止动画
    public void stopAnim() {
        if (mCurrentAnim == null) {
            return;
        }

        mStopFlag = true;
        mCurrentAnim.end();
    }

    //开始动画
    public void startAnim() {
        mStopFlag = false;
        String msg = getNextMsg();
        mChild.setText(msg);

        Rect bounds = new Rect();
        Paint textPaint = mChild.getPaint();
        textPaint.getTextBounds(msg, 0, msg.length(), bounds);
        int height = bounds.height();
        int width = bounds.width();

        float speedPx = ViewScaffold.dpToPix(speed, mContext);
        int holderWidth = getWidth();

        mCurrentAnim = ObjectAnimator.ofFloat(mChild, "x", getWidth(), -width);
        mCurrentAnim.setInterpolator(new LinearInterpolator());

        long duration = ((long) ((holderWidth + width) / speedPx)) * 1000L;


        mCurrentAnim.setDuration(duration);
        mCurrentAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (mStopFlag) {
                    return;
                }

                startAnim();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        mCurrentAnim.start();
    }
}
