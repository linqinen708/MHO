package com.linqinen.mho.tools.viewpageranimation;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by lin on 2017/1/6.
 */

public class DepthPageTransformer implements ViewPager.PageTransformer {

    private final String TAG = "DepthPageTransformer";

    private static final float MIN_SCALE = 0.3f;//0.75

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);
            Log.i(TAG, "transformPage: position:"+position);//当显示的页面左滑时，position从1到0
            Log.i(TAG, "transformPage: pageWidth * -position:"+pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
