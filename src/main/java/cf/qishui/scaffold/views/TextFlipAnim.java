package cf.qishui.scaffold.views;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by wangxiao on 2017/12/9.
 * <p>
 * 数字额度变化时候的动画
 */

public class TextFlipAnim {
    private int index = 0;
    private List<Integer> list = new ArrayList<>();

    public void flipNumber(int from, final int to, Context context, final TextView tv, int times) {
        if (from >= to) {
            tv.setText("" + to);
            return;
        }

        int step = (to - from) / times;

        if (step == 0) {
            list.add(to);
        } else {
            for (int element = from; element <= to; element += step) {
                list.add(element);
            }
        }

        int gapTime = 1000 / times;
        if (gapTime == 0) {
            tv.setText("" + to);
            return;
        }

        Observable.interval(gapTime, TimeUnit.MILLISECONDS)
                .take(list.size())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        int value = list.get(index);
                        index++;
                        tv.setText(value + "");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        tv.setText(to + "");
                    }
                });
    }
}
