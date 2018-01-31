package cf.qishui.scaffold.widgets.progresslayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import cf.qishui.scaffold.R;

/**
 * Created by wangxiao on 2017/10/25.
 */

public class ProgressIndicator extends FrameLayout {
    private float percentage = 0;
    private Context mContext;
    private ProgressLayout progressLayout;

    private Animation fadeInOutAnim;

    public ProgressIndicator(@NonNull Context context) {
        this(context, null);
    }

    public ProgressIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressIndicator(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.scaffold_progress_layout, this, true);
        progressLayout = findViewById(R.id.scaffold_progress);

        fadeInOutAnim = new AlphaAnimation(0.4f, 1f);
        fadeInOutAnim.setInterpolator(new DecelerateInterpolator());
        fadeInOutAnim.setDuration(600);
        fadeInOutAnim.setRepeatCount(Animation.INFINITE);
        fadeInOutAnim.setRepeatMode(Animation.REVERSE);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = right - left;
        int height = bottom - top;
        //进度指示器右侧
        int indicatorRight = (int) (width * percentage);

        //进度指示器左侧
        int indicatorLeft = indicatorRight - width;

        View child = getChildAt(0);


        child.layout(indicatorLeft, 0, indicatorRight, height);
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;

        if (getWidth() != 0) {
            int width = getWidth();
            int indicatorRight = (int) (getWidth() * percentage);
            int indicatorLeft = indicatorRight - getWidth();

            ObjectAnimator animator = ObjectAnimator.ofFloat(progressLayout, "x", -width, indicatorLeft);
            animator.setInterpolator(new DecelerateInterpolator());
            animator.start();
        }
    }

    public void setFinish() {
        this.percentage = 1.0f;

        int indicatorRight = (int) (getWidth() * percentage);
        int indicatorLeft = indicatorRight - getWidth();

        int currentX = (int) progressLayout.getX();

        ObjectAnimator animator = ObjectAnimator.ofFloat(progressLayout, "x", currentX, indicatorLeft);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ProgressIndicator.this.setVisibility(GONE);
            }
        });
        animator.start();
    }

    public void stopAnim() {
        progressLayout.clearAnimation();
        if (fadeInOutAnim != null) {
            fadeInOutAnim.cancel();
        }
    }

    public void startAnim() {
        fadeInOutAnim.reset();
        progressLayout.startAnimation(fadeInOutAnim);
    }

}
