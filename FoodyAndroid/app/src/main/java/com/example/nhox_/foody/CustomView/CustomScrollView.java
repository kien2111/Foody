package com.example.nhox_.foody.CustomView;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import UtilPackage.INotifier;
import UtilPackage.ScrollViewListener;

/**
 * Created by nhox_ on 5/4/2017.
 */
/////////////
// input:
// purpose: Cài đặt class CustomScrollView kế thừa từ ScrollView trong mainFragment
// output:
/////////////
public class CustomScrollView extends ScrollView{
    private ScrollViewListener scrollViewListener = null;
    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    ///////////////////
    // input :
    // purpose : gán giá trị để tạo callback function
    // output :
    /////////////////////
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng scroll
    // output :
    /////////////////////
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }


}
