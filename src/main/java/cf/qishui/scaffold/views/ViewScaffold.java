package cf.qishui.scaffold.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by wangxiao on 2017/7/17.
 */

public class ViewScaffold {
    public static float dpToPix(float dpValue, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
                context.getResources().getDisplayMetrics());
    }

    public void setViewBgGradient(View view, String startColor, String endColor) {
        if (TextUtils.isEmpty(startColor) || TextUtils.isEmpty(endColor)) {
            return;
        }
        try {
            if (!startColor.startsWith("#")) {
                startColor = "#" + startColor;
            }
            if (!endColor.startsWith("#")) {
                endColor = "#" + endColor;
            }
            int start = Color.parseColor(startColor);
            int end = Color.parseColor(endColor);
            GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{start, end});
            view.setBackgroundDrawable(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int stringToColor(String colorStr) {
        int color = Color.BLACK;
        try {
            if (!colorStr.startsWith("#")) {
                colorStr = "#" + colorStr;
            }

            color = Color.parseColor(colorStr);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return color;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
