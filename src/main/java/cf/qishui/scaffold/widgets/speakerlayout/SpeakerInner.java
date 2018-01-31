package cf.qishui.scaffold.widgets.speakerlayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

import cf.qishui.scaffold.R;

/**
 * Created by wangxiao on 2017/9/5.
 * 内部消息展示
 */

public class SpeakerInner extends RelativeLayout {
    private Context mContext;
    public MarqueeView mMarquee;
    public View mCloseBtn;

    public SpeakerInner(Context context) {
        this(context, null);
    }

    public SpeakerInner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpeakerInner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.scaffold_speaker_inner_layout, this, true);

        mMarquee = findViewById(R.id.speaker_marquee);
        mCloseBtn = findViewById(R.id.speaker_close_icon);
    }

    public void setMessages(List<String> messages) {
        mMarquee.setData(messages);
    }

    public void showCloseIcon() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mCloseBtn, "alpha", 0f, 1f);
        animator.setDuration(500);
        animator.start();
    }

    public void hideCloseIcon() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mCloseBtn, "alpha", 1f, 0f);
        animator.setDuration(500);
        animator.start();
    }

}
