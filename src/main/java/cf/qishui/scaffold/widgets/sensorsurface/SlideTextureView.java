package cf.qishui.scaffold.widgets.sensorsurface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangxiao on 2017/11/15.
 * 重力滚动的View
 */

public class SlideTextureView extends TextureView {
    private Context mContext;
    private volatile boolean mStopFlag;
    private Paint mPaint;
    private float currentG;
    private Thread animThread;
    private Matrix matrix;
    private Bitmap mBitmap;
    private volatile boolean mIsStillMod;

    private int fps = 60;
    private float bounce = 0.3f;

    private OnClickListener mOnClickListener;

    public SlideTextureView(Context context) {
        this(context, null);
    }

    public SlideTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        matrix = new Matrix();

        setOpaque(false);
        setSurfaceTextureListener(new SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                mStopFlag = false;
                startAnimThread();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                mStopFlag = true;
                try {
                    animThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    public void setStill(boolean still) {
        this.mIsStillMod = still;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            if (x < oldRect.right && x > oldRect.left) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(this);
                    return true;
                }
            }
        }
        return false;
    }

    public void setIconBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }

    public void setClickIconListener(OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    public void pause() {
        mStopFlag = true;

        if (animThread == null) {
            return;
        }

        try {
            animThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        mStopFlag = false;
        startAnimThread();
    }

    private void startAnimThread() {
        animThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!mStopFlag) {
                    RectF rectF = getRectF();
                    if (rectF == null) {
                        continue;
                    }
                    Canvas canvas = lockCanvas();
                    if (canvas == null) {
                        break;
                    }
                    canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
                    if (mBitmap != null) {
                        Matrix matrix = getMatrixByRect(rectF);
                        canvas.drawBitmap(mBitmap, matrix, mPaint);
                    }
                    unlockCanvasAndPost(canvas);
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000 / fps);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        animThread.start();
    }

    public void setG(float g) {
        this.currentG = g;
    }

    private float cV = 0;
    private RectF oldRect = new RectF(0, 0, 0, 0);

    private RectF getRectF() {
        int holderWidth = getWidth();
        int holderHeight = getHeight();

        if (mIsStillMod) {
            oldRect.left = holderWidth / 2.0f - holderHeight / 2.0f;
            oldRect.right = oldRect.left + holderHeight;
            oldRect.bottom = holderHeight;
            return oldRect;
        }

        currentG = currentG * 0.05f;

        float acceleration = mToPix(-currentG);
        float interval = 1.0f / fps;
        float deltaX = cV * interval + 1 / 2 * (acceleration * interval * interval);

        float targetLeft = oldRect.left + deltaX;
        float targetRight = oldRect.left + holderHeight;

        if (targetLeft < 0 || targetRight > holderWidth) {
            if (targetLeft < 0 && cV < 0) {
                cV = -cV * bounce;
            }

            if (targetRight > holderWidth && cV > 0) {
                cV = -cV * bounce;
            }
            deltaX = cV * interval + 1 / 2 * (acceleration * interval * interval);
        }
        cV = cV + acceleration * interval;

        oldRect.left = oldRect.left + deltaX;
        oldRect.right = oldRect.left + holderHeight;
        oldRect.top = 0;
        oldRect.bottom = holderHeight;

        return oldRect;
    }

    private Matrix getMatrixByRect(RectF rectF) {
        float left = rectF.left;

        float bitmapHeight = (float) (mBitmap.getHeight());
        float scaleRate = (float) getHeight() / bitmapHeight;

        float degree = (float) (180f * left / ((getHeight() / 2.0f) * Math.PI));

        matrix.reset();
        matrix.setScale(scaleRate, scaleRate);
        matrix.postRotate(degree, getHeight() / 2.0f, getHeight() / 2.0f);
        matrix.postTranslate(left, 0);
        return matrix;
    }

    private float mToPix(float m) {
        float ppi = mContext.getResources().getDisplayMetrics().densityDpi;
        return m * (ppi / 0.025f);
    }

}
