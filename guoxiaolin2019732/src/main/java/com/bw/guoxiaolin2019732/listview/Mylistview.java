package com.bw.guoxiaolin2019732.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * <p>文件描述：<p>
 * <p>作者：xiaolin<p>
 * <p>创建时间:2019/7/3<p>
 * <p>更改时间:2019/7/3<p>
 * <p>版本号：1<p>
 */
public class Mylistview  extends ListView {
    public Mylistview(Context context) {
        super(context);
    }

    public Mylistview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Mylistview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      heightMeasureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
