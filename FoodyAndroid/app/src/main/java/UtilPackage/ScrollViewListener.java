package UtilPackage;

import com.example.nhox_.foody.CustomView.CustomScrollView;

/**
 * Created by nhox_ on 22/5/2017.
 */

public interface ScrollViewListener {
    void onScrollChanged(CustomScrollView scrollView,
                         int x, int y, int oldx, int oldy);
}
