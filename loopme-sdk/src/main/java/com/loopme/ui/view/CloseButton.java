package com.loopme.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.loopme.R;
import com.loopme.common.Utils;

public class CloseButton extends View {
    private static final int EDGING_DP = 2;
    private static final int THICKNESS_DP = 4;
    private static final int OFFSET_DP = 14;
    private static final int CUSTOM_VIEW_SIZE_DP = 16;
    private static final int CLICKABLE_VIEW_SIZE_DP = (int) (CUSTOM_VIEW_SIZE_DP * 3);
    private Paint mPaint = new Paint();

    public CloseButton(Context context) {
        super(context);
    }

    public CloseButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CloseButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int offsetPx = Utils.convertDpToPixel(OFFSET_DP);
        int viewSize = Utils.convertDpToPixel(CUSTOM_VIEW_SIZE_DP + OFFSET_DP);
        drawEdging(canvas, offsetPx, viewSize);
        drawWitheCross(canvas, offsetPx, viewSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizePx = Utils.convertDpToPixel(CLICKABLE_VIEW_SIZE_DP);
        setMeasuredDimension(sizePx, sizePx);
    }

    private void drawEdging(Canvas canvas, int offsetDp, int viewSize) {
        float thickness = Utils.convertDpToPixel(THICKNESS_DP + EDGING_DP);

        mPaint.setStrokeWidth(thickness);
        mPaint.setColor(ResourcesCompat.getColor(this.getResources(), R.color.black, null));
        drawCross(canvas, offsetDp, viewSize, thickness, mPaint);
    }

    private void drawWitheCross(Canvas canvas, int offsetDp, int viewSize) {
        int thickness = Utils.convertDpToPixel(THICKNESS_DP);

        mPaint.setStrokeWidth(thickness);
        mPaint.setColor(ResourcesCompat.getColor(this.getResources(), R.color.white, null));
        drawCross(canvas, offsetDp, viewSize, thickness, mPaint);
    }

    private void drawCross(Canvas canvas, int offsetDp, int viewSize, float thickness, Paint mPaint) {
        canvas.drawLine(offsetDp, offsetDp, viewSize, viewSize, mPaint);
        canvas.drawLine(offsetDp, viewSize, viewSize, offsetDp, mPaint);

        canvas.drawCircle(offsetDp, offsetDp, thickness / 2, mPaint);
        canvas.drawCircle(viewSize, viewSize, thickness / 2, mPaint);
        canvas.drawCircle(offsetDp, viewSize, thickness / 2, mPaint);
        canvas.drawCircle(viewSize, offsetDp, thickness / 2, mPaint);
    }

    public void addInLayout(RelativeLayout parentLayout) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        parentLayout.addView(this, layoutParams);
    }
}
