package cf.qishui.scaffold.widgets.speakerlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ConvertUtils;

import java.util.List;

import cf.qishui.scaffold.R;
import cf.qishui.scaffold.drawable.CapsuleDrawable;

/**
 * Created by wangxiao on 2017/9/5.
 */

public class SpeakerLayout extends RelativeLayout {
    private Context mContext;
    private SpeakerInner inner;
    private boolean isOpen;
    private float openWidth;
    private float closeWidth;
    private SpeakerListener listener;
    private OnClickListener mClick;

    private boolean inAnimation = false;

    public SpeakerLayout(Context context) {
        this(context, null);
    }

    public SpeakerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpeakerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setListener(SpeakerListener listener) {
        this.listener = listener;
    }

    public void setSpeakerClick(OnClickListener listener) {
        mClick = listener;
    }

    private void init(Context context) {
        mContext = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.scaffold_speaker_layout, this, true);

        inner = rootView.findViewById(R.id.jd_speaker_inner_layout);
        CapsuleDrawable capsuleDrawable = new CapsuleDrawable();
        capsuleDrawable.setColor(0x33404858);
        capsuleDrawable.setFill(true);
        inner.setBackgroundDrawable(capsuleDrawable);

        View icon = findViewById(R.id.icon);
        icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inAnimation) {
                    return;
                }
                if (mClick != null) {
                    mClick.onClick(view);
                }
                if (!isOpen) {
                    openBar(false);
                } else {
                    closeBar();
                }
            }
        });

        inner.mCloseBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inAnimation) {
                    return;
                }

                if (!isOpen) {
                    return;
                }

                closeBar();
            }
        });

    }

    private void openBar(boolean wait) {
        if (inAnimation) {
            return;
        }
        if (!isOpen) {
//            closeWidth = inner.getWidth();
//            openWidth = getWidth();//0
            int waitTime = 0;
            if (wait) {
                waitTime = 300;
            }
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    ResizeWidthAnimation openAnimation = new ResizeWidthAnimation(inner, (int) openWidth);
                    openAnimation.setInterpolator(new DecelerateInterpolator());
                    openAnimation.setDuration(500);
                    inner.startAnimation(openAnimation);
                    inner.showCloseIcon();

                    openAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            inAnimation = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            inAnimation = false;
                            isOpen = true;

                            inner.mMarquee.startAnim();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    if (listener != null) {
                        listener.open();
                    }
                }
            }, waitTime);

        }
    }

    public void closeBar() {
        if (inAnimation) {
            return;
        }

        if (!isOpen) {
            return;
        }

        ResizeWidthAnimation closeAnimation = new ResizeWidthAnimation(inner, (int) closeWidth);
        closeAnimation.setInterpolator(new DecelerateInterpolator());
        closeAnimation.setDuration(500);
        inner.startAnimation(closeAnimation);
        inner.hideCloseIcon();

        closeAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                inAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                inAnimation = false;
                isOpen = false;

                inner.mMarquee.stopAnim();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        if (listener != null) {
            listener.close();
        }
    }

    public void setMessages(List<String> list) {
        if (list != null && list.size() > 0) {
            inner.setMessages(list);

            openBar(true);
        }
    }

    public void clearMsg() {
        inner.setMessages(null);
    }

    public interface SpeakerListener {
        void open();

        void close();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        openWidth = r - l;

        closeWidth = ConvertUtils.dp2px(32);
    }
}
