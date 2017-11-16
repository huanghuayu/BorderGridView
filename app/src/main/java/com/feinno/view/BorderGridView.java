package com.feinno.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.GridView;

import com.feinno.border.R;


public class BorderGridView extends GridView {
    private Paint localPaint;
    // 边框线宽
    private static final float BORDER_WIDTH = 10f;
    // 行高
    private float rowHeightSize;

    private int columnWidthSize;
    // 布局宽度
    private int layoutWidth;

    public BorderGridView(Context context) {
        super(context);
        init();
    }

    public BorderGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BorderGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        localPaint = new Paint();
        localPaint.setAntiAlias(true);
        localPaint.setStyle(Paint.Style.FILL);
//        localPaint.setColor(Color.rgb(229, 229, 229));//E5E5E5
        localPaint.setColor(getResources().getColor(R.color.gray));
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int columnCount = getNumColumns();//计算列数
        int childCount = getChildCount();//子view总数
        if (columnCount == 0 || childCount == 0) {
            return;
        }
        int rowCount;
        if (childCount % columnCount == 0) {
            rowCount = childCount / columnCount;
        } else {
            rowCount = childCount / columnCount + 1;
        }
        rowHeightSize = (getHeight() - BORDER_WIDTH * (rowCount + 1)) / rowCount;
        layoutWidth = getWidth();
        /**这种算列宽度的方法，是最左右边上的两条Border不画。如果需要画最左右边上的两条Border，需要再进行修改**/
        columnWidthSize = (int)((layoutWidth - BORDER_WIDTH * (columnCount - 1)) / columnCount);

        // 画行边框
        for (int i = 0; i <= rowCount; i++) {
            float top = i * (BORDER_WIDTH + rowHeightSize);
            float bottom = (i + 1) * BORDER_WIDTH + i * rowHeightSize;
            if (i == rowCount) {
                top = getHeight() - BORDER_WIDTH;
                bottom = getHeight();
            }
            canvas.drawRect(0, top, layoutWidth, bottom, localPaint);
        }

        // 画列边框
        int contentHeight = (int) (rowCount * (rowHeightSize + BORDER_WIDTH));
        /**这种画列边框的方法，是最左右边上的两条Border不画。如果需要画最左右边上的两条Border，需要再进行修改**/
        for (int i = 1; i < columnCount; i++) {
            canvas.drawRect(i * columnWidthSize + BORDER_WIDTH * (i - 1), 0, i * (BORDER_WIDTH + columnWidthSize), contentHeight, localPaint);
        }

    }
}
