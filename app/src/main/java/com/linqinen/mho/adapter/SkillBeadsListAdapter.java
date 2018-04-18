/*
 * ******************************************************************************
 *   Copyright (c) 2014 Gabriele Mariotti.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */
package com.linqinen.mho.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linqinen.mho.R;
import com.linqinen.mho.bean.SkillBeads;

import java.util.List;


/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class SkillBeadsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "AnimationListAdapter";


    private final Context mContext;
    private List<SkillBeads> mSkillBeadsList;

    public SkillBeadsListAdapter(Context context, List mSkillBeadsList) {
        mContext = context;
        this.mSkillBeadsList = mSkillBeadsList;
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder {
         TextView title,name;
         TextView tv_positiveName,tv_negativeName;
         TextView tv_positiveAttribute_1,tv_positiveAttribute_2,tv_positiveAttribute_3;
         TextView tv_negativeAttribute_1,tv_negativeAttribute_2,tv_negativeAttribute_3;
         TextView tv_materialName_1,tv_materialName_2,tv_materialName_3;

        SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_sequenceNumber);
            name = (TextView) view.findViewById(R.id.tv_name);

            tv_positiveName = (TextView) view.findViewById(R.id.tv_positiveName);
            tv_positiveAttribute_1 = (TextView) view.findViewById(R.id.tv_positiveAttribute_1);
            tv_positiveAttribute_2 = (TextView) view.findViewById(R.id.tv_positiveAttribute_2);
            tv_positiveAttribute_3 = (TextView) view.findViewById(R.id.tv_positiveAttribute_3);

            tv_negativeName = (TextView) view.findViewById(R.id.tv_negativeName);
            tv_negativeAttribute_1 = (TextView) view.findViewById(R.id.tv_negativeAttribute_1);
            tv_negativeAttribute_2 = (TextView) view.findViewById(R.id.tv_negativeAttribute_2);
            tv_negativeAttribute_3 = (TextView) view.findViewById(R.id.tv_negativeAttribute_3);

            tv_materialName_1 = (TextView) view.findViewById(R.id.tv_materialName_1);
            tv_materialName_2 = (TextView) view.findViewById(R.id.tv_materialName_2);
            tv_materialName_3 = (TextView) view.findViewById(R.id.tv_materialName_3);

        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: viewType:"+viewType);
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_skillbeads, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((SimpleViewHolder) holder).title.setText(position + 1 + "");
        ((SimpleViewHolder) holder).name.setText(mSkillBeadsList.get(position).getName());

        ((SimpleViewHolder) holder).tv_positiveName.setText(mSkillBeadsList.get(position).getPositiveName());
        ((SimpleViewHolder) holder).tv_positiveAttribute_1.setText(mSkillBeadsList.get(position).getPositiveAttribute_1());
        ((SimpleViewHolder) holder).tv_positiveAttribute_2.setText(mSkillBeadsList.get(position).getPositiveAttribute_2());
        ((SimpleViewHolder) holder).tv_positiveAttribute_3.setText(mSkillBeadsList.get(position).getPositiveAttribute_3());

        ((SimpleViewHolder) holder).tv_negativeName.setText(mSkillBeadsList.get(position).getNegativeName());
        ((SimpleViewHolder) holder).tv_negativeAttribute_1.setText(mSkillBeadsList.get(position).getTv_negativeAttribute_1());
        ((SimpleViewHolder) holder).tv_negativeAttribute_2.setText(mSkillBeadsList.get(position).getTv_negativeAttribute_2());
        ((SimpleViewHolder) holder).tv_negativeAttribute_3.setText(mSkillBeadsList.get(position).getTv_negativeAttribute_3());

        ((SimpleViewHolder) holder).tv_materialName_1.setText(mSkillBeadsList.get(position).getTv_materialName_1());
        ((SimpleViewHolder) holder).tv_materialName_2.setText(mSkillBeadsList.get(position).getTv_materialName_2());
        ((SimpleViewHolder) holder).tv_materialName_3.setText(mSkillBeadsList.get(position).getTv_materialName_3());

    }

//    private void addItem(int position) {
//        final int id = mCurrentItemId++;
//        mItems.add(position, id);
//        notifyItemInserted(position);
//    }
//
//    public void removeItem(int position) {
//        mItems.remove(position);
//        notifyItemRemoved(position);
//    }

    @Override
    public int getItemCount() {
        return mSkillBeadsList.size();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

}
