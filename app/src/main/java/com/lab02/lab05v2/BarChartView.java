package com.lab02.lab05v2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;

public class BarChartView extends View {
    Paint barPainter, axisPainter, guidePainter, xLabelPainter, yLabelPainter;
    float padding, yLabelWidth, xLabelWidth;
    private Context mContext;
    private BarData[] mDataArray;

    public BarChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mContext = context;
        init();
    }

    private void init() {
        barPainter = new Paint();
        barPainter.setStyle(Paint.Style.FILL);
        barPainter.setColor(Color.BLUE);

        axisPainter = new Paint();
        axisPainter.setStyle(Paint.Style.STROKE);
        axisPainter.setColor(Color.BLACK);
        axisPainter.setStrokeWidth(3f);

        guidePainter = new Paint();
        guidePainter.setStyle(Paint.Style.STROKE);
        guidePainter.setColor(Color.GRAY);
        guidePainter.setStrokeWidth(3f);

        xLabelPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
        xLabelPainter.setColor(Color.BLACK);
        xLabelPainter.setTextSize(30f);
        xLabelPainter.setTextAlign(Paint.Align.RIGHT);

        yLabelPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
        yLabelPainter.setColor(Color.BLACK);
        yLabelPainter.setTextSize(30f);
        yLabelPainter.setTextAlign(Paint.Align.RIGHT);
    }

    public void setYAxisData(BarData[] barData) {
        mDataArray = barData;

        int aux = 0;
        String label = "";

        for(int i = 0; i < mDataArray.length; i++){
            int nameLength = mDataArray[i].getXAxisName().length();

            if(nameLength > aux) {
                aux = nameLength;
                label = mDataArray[i].getXAxisName();
            }
        }

        xLabelWidth = xLabelPainter.measureText(label);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int width = getWidth();

        float gridBottom = height - padding - xLabelWidth;
        float gridRight = width - padding;
        drawGuides(canvas, gridBottom, gridRight);

        float gridTopLeft = padding + yLabelWidth;
        drawAxis(canvas, gridTopLeft, gridBottom, gridRight);
        drawBars(canvas, height, gridTopLeft, gridBottom, gridRight);
    }

    private void drawBars(Canvas canvas, float canvasHeight, float gridTopLeft, float gridBottom, float gridRight) {
        float space = 60f;
        float totalSpace = space * mDataArray.length;
        float width = (gridRight - gridTopLeft - totalSpace) / mDataArray.length;
        float left = gridTopLeft + 10f;
        float right = left + width;
        float height = canvasHeight - 2 * padding - xLabelWidth;

        for (BarData s : mDataArray){
            float top = padding + height * (1f - s.getValue());
            canvas.drawRect(left + 80f, top, right, gridBottom, barPainter);

            canvas.rotate(-90f);
            canvas.drawText(s.getXAxisName(), -height - padding - 10f, left + (width + 2 * space) / 2, xLabelPainter);
            canvas.rotate(90f);

            left = right + space;
            right = left + width;
        }
    }

    private void drawAxis(Canvas canvas, float gridTopLeft, float gridBottom, float gridRight) {
        canvas.drawLine(gridTopLeft, gridBottom, gridTopLeft, padding, axisPainter);
        canvas.drawLine(gridTopLeft, gridBottom, gridRight, gridBottom, axisPainter);
    }

    private void drawGuides(Canvas canvas, float gridBottom, float gridRight) {
        float spacing = (gridBottom - padding) / 10f;
        float y;
        for (int i = 0; i < 10; i++) {
            String label = Integer.toString(100 - i * 10);
            float width = yLabelPainter.measureText(label);

            if(yLabelWidth < width)
                yLabelWidth = width;

            Rect bound = new Rect();
            yLabelPainter.getTextBounds(label, 0, label.length(), bound);

            y = padding + i * spacing;
            canvas.drawLine(padding + yLabelWidth, y, gridRight, y, guidePainter);
            canvas.drawText(label, padding + yLabelWidth, y + bound.height() / 2, yLabelPainter);
        }
    }
}
