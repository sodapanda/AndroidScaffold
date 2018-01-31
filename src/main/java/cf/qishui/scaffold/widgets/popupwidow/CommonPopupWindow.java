package cf.qishui.scaffold.widgets.popupwidow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by wangxiao on 2017/10/17.
 */

public class CommonPopupWindow extends PopupWindow {

    public CommonPopupWindow(View contentView, int width, int height) {
        super(contentView, width, height);
        init();
    }

    public CommonPopupWindow(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOutsideTouchable(true);
        setFocusable(true);
        if (Build.VERSION.SDK_INT < 21) {
            setBackgroundDrawable(new BitmapDrawable());
        }
    }
}
