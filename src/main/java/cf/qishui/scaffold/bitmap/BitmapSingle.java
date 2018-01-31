package cf.qishui.scaffold.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangxiao on 2017/12/1.
 */

public class BitmapSingle {
    private static Map<Integer, Bitmap> bitmapMap = new HashMap<>();
    private static Map<Config, Bitmap> bitmapConfigMap = new HashMap<>();

    public static Bitmap getBitmapById(Context context, int resourceId) {
        Bitmap bitmap = bitmapMap.get(resourceId);
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
            if (bitmap != null) {
                bitmapMap.put(resourceId, bitmap);
            }
        }

        return bitmap;
    }

    public static Bitmap getBitmapByConfig(Context context, Config config) {
        Bitmap bitmap = bitmapConfigMap.get(config);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(config.width, config.height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);

            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(config.color);

            canvas.drawRect(0, 0, config.width, config.height, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawRoundRect(new RectF(0, 0, config.width, config.height), config.radius, config.radius, paint);
            bitmapConfigMap.put(config, bitmap);
        }

        return bitmap;
    }


}
