package cf.qishui.scaffold.widgets.verticaltextswitcher;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cf.qishui.scaffold.R;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wangxiao on 2017/11/3.
 * <p>
 * 上下轮播消息
 */

public class VerticalTextSwitcher extends TextSwitcher {
    private Context mContext;
    private List<TextSwitcherBean> mMsgList;
    private int mCurrentIndex = -1;
    private Subscription subscription;
    private List<TextSwitcherBean> fixedList = new ArrayList<>();
    private List<TextSwitcherBean> fullList;
    private onItemClickListener onItemClickListener;

    public VerticalTextSwitcher(Context context) {
        super(context);
        init(context);
    }

    public VerticalTextSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        setFactory(mFactory);
        Animation in = AnimationUtils.loadAnimation(mContext, R.anim.qs_slide_in_down);
        Animation out = AnimationUtils.loadAnimation(mContext, R.anim.qs_slide_out_top);

        setInAnimation(in);
        setOutAnimation(out);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex >= 0 && mCurrentIndex < fullList.size()) {
                    TextSwitcherBean bean = fullList.get(mCurrentIndex);
                    if (onItemClickListener != null) {
                        onItemClickListener.clickItem(mCurrentIndex, bean);
                    }
                }
            }
        });
    }

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    public boolean shouldHide() {
        return (fixedList == null || fixedList.size() == 0) && (mMsgList == null || mMsgList.size() == 0);
    }

    public void setMsgList(List<TextSwitcherBean> msgList) {
        mMsgList = msgList;
    }

    public void insertMsg(TextSwitcherBean msg) {
        if (msg == null) {
            return;
        }
        for (TextSwitcherBean thisBean : fixedList) {
            if (thisBean.msg.equals(msg.msg)) {
                return;
            }
        }
        fixedList.add(0, msg);
    }

    public void removeMsg(String msg) {
        if (msg == null) {
            return;
        }

        if (fixedList == null) {
            return;
        }

        int indexToDelete = -1;
        for (int i = 0; i < fixedList.size(); i++) {
            String str = fixedList.get(i).msg;
            if (str.equals(msg)) {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete >= 0) {
            fixedList.remove(indexToDelete);
        }
    }

    public void start() {
        if (mMsgList == null && fixedList.size() == 0) {
            return;
        }

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

        fullList = new ArrayList<>();
        fullList.addAll(fixedList);
        if (mMsgList != null) {
            fullList.addAll(mMsgList);
        }

        if (fullList.size() == 1) {
            mCurrentIndex++;
            setText(fullList.get(0).msg);
        } else {
            subscription = Observable.interval(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            String msg = getNextStr(fullList);
                            if (msg == null) {
                                return;
                            }

                            setText(msg);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }, new Action0() {
                        @Override
                        public void call() {
                        }
                    });

        }
    }

    private String getNextStr(List<TextSwitcherBean> list) {
        if (list == null) {
            return null;
        }

        int nextIndex = mCurrentIndex + 1;

        if (nextIndex < 0 || nextIndex >= list.size()) {
            nextIndex = 0;
        }

        mCurrentIndex = nextIndex;
        return list.get(mCurrentIndex).msg;
    }

    public void shutdown() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private ViewSwitcher.ViewFactory mFactory = new ViewSwitcher.ViewFactory() {

        @Override
        public View makeView() {

            // Create a new TextView
            TextView t = new TextView(mContext);
            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            t.setTextAppearance(mContext, R.style.jd_broadcast_text_style);
            return t;
        }
    };

    public interface onItemClickListener {
        void clickItem(int index, TextSwitcherBean bean);
    }
}
