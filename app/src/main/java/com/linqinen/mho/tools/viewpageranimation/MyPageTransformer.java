package com.linqinen.mho.tools.viewpageranimation;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by lin on 2017/1/6.
 */

public class MyPageTransformer implements ViewPager.PageTransformer {

    private final String TAG = "MyPageTransformer";

//    [-∞ , -1)  :表示左边 的View 且已经看不到了
//    [-1 ,   0]  :表示左边的 View ,且可以看见
//    ( 0 ,   1]  :表示右边的VIew , 且可以看见了
//    ( 1 , -∞)  :表示右边的 View 且已经看不见了

    //    a → b
    // -1    0    1
    //       0是最终显示的页面

    private float INIT_SCALE = 1f,MIN_SCALE = 0.5f;

    @Override
    public void transformPage(View page, float position) {

//        Log.i(TAG, "transformPage: position:" + position);
        if (position < -1) {
            Log.i(TAG, "transformPage: 左边 的View 且已经看不到了");
            page.setScaleX(1f);
        }
        else if (position < 0) {
//            Log.i(TAG, "transformPage: 当前视图向左滑动时");
//            page.setBackgroundResource(R.mipmap.ic_launcher);
            page.setScaleX(INIT_SCALE + position);
            page.setScaleY(INIT_SCALE + position);

            Log.i(TAG, "transformPage: 左滑:"+(INIT_SCALE + position));
        }else if(position == 0){
            page.setScaleX(INIT_SCALE);
            page.setScaleY(INIT_SCALE);
            page.setTranslationX(0);
        }
        else if (position <= 1) {
//            Log.i(TAG, "transformPage: 当前视图向右滑动时");
            //position逐渐从0增大到1
//            float scaleFactor = Math.min(MIN_SCALE, 1 - Math.abs(position));
            float rightPosition = 1-position;
            page.setScaleX(rightPosition);
            page.setScaleY(rightPosition);
            if(rightPosition < 0.5){
                rightPosition = 0;
            }
            page.setTranslationX(-rightPosition*600);
            Log.i(TAG, "transformPage: 右滑:"+(1-position));
//            page.setBackgroundResource(R.mipmap.charmlist_appointment_arrow_silver);
        } else {
            Log.i(TAG, "transformPage: 右边的 View 且已经看不见了");
            page.setScaleX(1f);
        }
    }
}
