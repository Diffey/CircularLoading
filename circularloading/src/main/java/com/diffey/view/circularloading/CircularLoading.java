package com.diffey.view.circularloading;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by diff on 2016/2/5.
 */
public class CircularLoading extends View {

    private static final int DEF_MIN_RADIUS = 5;
    private static final int DEF_CIIRCULAR_COLOR = Color.BLUE;

    private int maxRadius;
    private int minRadius;
    private int circularColor;

    private int circularX;
    private int circularY;
    private int curRadius;
    private boolean isExpanding = false;
    private Paint mPaint;

    public CircularLoading(Context context) {
        this(context, null);
    }

    public CircularLoading(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        minRadius = DEF_MIN_RADIUS;
        circularColor = DEF_CIIRCULAR_COLOR;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircularLoading);
            circularColor = typedArray.getColor(R.styleable.CircularLoading_cirColor, DEF_CIIRCULAR_COLOR);
            typedArray.recycle();
        }

        mPaint = new Paint();
        mPaint.setColor(circularColor);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int minSide = w > h ? h : w;
        maxRadius = minSide / 2;

        circularX = w / 2;
        circularY = h / 2;

        curRadius = maxRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(circularX, circularY, curRadius, mPaint);

        if (isExpanding) {
            curRadius++;
        } else {
            curRadius--;
        }

        if (curRadius > maxRadius) {
            curRadius = maxRadius;
            isExpanding = false;
        }

        if (curRadius < minRadius) {
            curRadius = minRadius;
            isExpanding = true;
        }
        invalidate();
    }
}
