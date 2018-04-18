package com.linqinen.mho.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linqinen.mho.R;

import java.util.List;

/**
 * Created by lin on 2017/1/22.
 */

public class RecyclerView_GridView_Adapter extends MyRecyclerViewAdapter {

    private final String TAG = "RecyclerView_GridView_Adapter";

    private  Context mContext;

    private List<String> mList;

    RecyclerView_GridView_Adapter(Context mContext) {
        super(mContext);
    }
    public RecyclerView_GridView_Adapter(Context mContext,List<String> mList) {
        super(mContext);
        this.mContext = mContext;
        this.mList = mList;
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        SimpleViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.inflate_gridview, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final SimpleViewHolder mSimpleViewHolder = (SimpleViewHolder) holder;

        mSimpleViewHolder.name.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private static final int DURATION = 400;
    private static final int TRANSLATION_DISTANCE_X = 400;
    private static final int TRANSLATION_DISTANCE_Y = 650;

    public static void appearAnimator(final View view) {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", -TRANSLATION_DISTANCE_X, 0);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", -TRANSLATION_DISTANCE_Y, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1f);

        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(DURATION);
        mAnimatorSet.playTogether(scaleX, scaleY, translationX, translationY);
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setVisibility(View.VISIBLE);
            }
        });
        mAnimatorSet.start();
    }

    public static void hideAnimator(final View view) {

        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", 0, -TRANSLATION_DISTANCE_X);
        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", 0, -TRANSLATION_DISTANCE_Y);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1, 0);

        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(DURATION);//飞机起飞时间
        mAnimatorSet.playTogether(scaleX, scaleY, translationX, translationY);
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });
        mAnimatorSet.start();
    }
}
