package cf.qishui.scaffold.widgets.TopAlert;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cf.qishui.scaffold.R;
import cf.qishui.scaffold.views.ViewScaffold;

/**
 * Created by wangxiao on 2017/10/11.
 */

public class TopAlertView extends RelativeLayout {
    private Context mContext;

    public TopAlertView(Context context) {
        this(context, null);
    }

    public TopAlertView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopAlertView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void addMsg(String msg) {
        View alertView = createAlertView(msg);
        addView(alertView);
        animAlertView(alertView);
    }

    private View createAlertView(String msg) {
        View alertView = LayoutInflater.from(mContext).inflate(R.layout.scf_top_alert_view, this, false);
        TextView alertTv = alertView.findViewById(R.id.scf_top_alert_text);
        alertTv.setText(msg);
        return alertView;
    }

    private void animAlertView(final View alertView) {
        float heightPx = ViewScaffold.dpToPix(32.0f, mContext);
        ObjectAnimator animIn = ObjectAnimator.ofFloat(alertView, "y", -heightPx, 0);
        ObjectAnimator hold = ObjectAnimator.ofFloat(alertView, "y", 0, 0);
        hold.setDuration(1000);
        ObjectAnimator out = ObjectAnimator.ofFloat(alertView, "y", 0, -heightPx);
        AnimatorSet set = new AnimatorSet();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                removeView(alertView);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        set.playSequentially(animIn, hold, out);
        set.start();
    }
}
