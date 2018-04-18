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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linqinen.mho.MenuActivity;
import com.linqinen.mho.R;
import com.linqinen.mho.bean.MaterialPrice;

import java.util.List;


/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class MaterialPriceListAdapter extends MyRecyclerViewAdapter{

    private final String TAG = "MaterialPriceAdapter";

    private Context mContext;
    private List<MaterialPrice> mList;

    MaterialPriceListAdapter(Context mContext) {
        super(mContext);
    }
    public MaterialPriceListAdapter(Context mContext,List<MaterialPrice> mList) {
        super(mContext);
        this.mContext = mContext;
        this.mList = mList;
    }


    private static class SimpleViewHolder extends RecyclerView.ViewHolder {
         TextView title,name;
         TextView tv_materialName_1,tv_materialName_2,tv_materialName_3,tv_materialName_4,tv_materialName_5,tv_materialName_6,
                 tv_materialName_7,tv_materialName_8,tv_materialName_9,tv_materialName_10,tv_materialName_11,tv_materialName_12;
         TextView tv_materialNum_1,tv_materialNum_2,tv_materialNum_3,tv_materialNum_4,tv_materialNum_5,tv_materialNum_6,
                 tv_materialNum_7,tv_materialNum_8,tv_materialNum_9,tv_materialNum_10,tv_materialNum_11,tv_materialNum_12;
         TextView tv_materialPrice_1,tv_materialPrice_2,tv_materialPrice_3,tv_materialPrice_4,tv_materialPrice_5,
        tv_materialPrice_6,tv_materialPrice_7,tv_materialPrice_8,tv_materialPrice_9,tv_materialPrice_10,tv_materialPrice_11,tv_materialPrice_12;
         TextView tv_special_1,tv_special_2,tv_special_3,tv_special_4,tv_special_5,
        tv_special_6,tv_special_7,tv_special_8,tv_special_9,tv_special_10,tv_special_11,tv_special_12;

        SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_sequenceNumber);
            name = (TextView) view.findViewById(R.id.tv_monsterName);

            tv_materialName_1 = (TextView) view.findViewById(R.id.tv_materialName_1);
            tv_materialName_2 = (TextView) view.findViewById(R.id.tv_materialName_2);
            tv_materialName_3 = (TextView) view.findViewById(R.id.tv_materialName_3);
            tv_materialName_4 = (TextView) view.findViewById(R.id.tv_materialName_4);
            tv_materialName_5 = (TextView) view.findViewById(R.id.tv_materialName_5);
            tv_materialName_6 = (TextView) view.findViewById(R.id.tv_materialName_6);
            tv_materialName_7 = (TextView) view.findViewById(R.id.tv_materialName_7);
            tv_materialName_8 = (TextView) view.findViewById(R.id.tv_materialName_8);
            tv_materialName_9 = (TextView) view.findViewById(R.id.tv_materialName_9);
            tv_materialName_10 = (TextView) view.findViewById(R.id.tv_materialName_10);
            tv_materialName_11 = (TextView) view.findViewById(R.id.tv_materialName_11);
            tv_materialName_12 = (TextView) view.findViewById(R.id.tv_materialName_12);

            tv_materialNum_1 = (TextView) view.findViewById(R.id.tv_materialNum_1);
            tv_materialNum_2 = (TextView) view.findViewById(R.id.tv_materialNum_2);
            tv_materialNum_3 = (TextView) view.findViewById(R.id.tv_materialNum_3);
            tv_materialNum_4 = (TextView) view.findViewById(R.id.tv_materialNum_4);
            tv_materialNum_5 = (TextView) view.findViewById(R.id.tv_materialNum_5);
            tv_materialNum_6 = (TextView) view.findViewById(R.id.tv_materialNum_6);
            tv_materialNum_7 = (TextView) view.findViewById(R.id.tv_materialNum_7);
            tv_materialNum_8 = (TextView) view.findViewById(R.id.tv_materialNum_8);
            tv_materialNum_9 = (TextView) view.findViewById(R.id.tv_materialNum_9);
            tv_materialNum_10 = (TextView) view.findViewById(R.id.tv_materialNum_10);
            tv_materialNum_11 = (TextView) view.findViewById(R.id.tv_materialNum_11);
            tv_materialNum_12 = (TextView) view.findViewById(R.id.tv_materialNum_12);

            tv_materialPrice_1 = (TextView) view.findViewById(R.id.tv_materialPrice_1);
            tv_materialPrice_2 = (TextView) view.findViewById(R.id.tv_materialPrice_2);
            tv_materialPrice_3 = (TextView) view.findViewById(R.id.tv_materialPrice_3);
            tv_materialPrice_4 = (TextView) view.findViewById(R.id.tv_materialPrice_4);
            tv_materialPrice_5 = (TextView) view.findViewById(R.id.tv_materialPrice_5);
            tv_materialPrice_6 = (TextView) view.findViewById(R.id.tv_materialPrice_6);
            tv_materialPrice_7 = (TextView) view.findViewById(R.id.tv_materialPrice_7);
            tv_materialPrice_8 = (TextView) view.findViewById(R.id.tv_materialPrice_8);
            tv_materialPrice_9 = (TextView) view.findViewById(R.id.tv_materialPrice_9);
            tv_materialPrice_10 = (TextView) view.findViewById(R.id.tv_materialPrice_10);
            tv_materialPrice_11 = (TextView) view.findViewById(R.id.tv_materialPrice_11);
            tv_materialPrice_12 = (TextView) view.findViewById(R.id.tv_materialPrice_12);
            
            tv_special_1 = (TextView) view.findViewById(R.id.tv_special_1);
            tv_special_2 = (TextView) view.findViewById(R.id.tv_special_2);
            tv_special_3 = (TextView) view.findViewById(R.id.tv_special_3);
            tv_special_4 = (TextView) view.findViewById(R.id.tv_special_4);
            tv_special_5 = (TextView) view.findViewById(R.id.tv_special_5);
            tv_special_6 = (TextView) view.findViewById(R.id.tv_special_6);
            tv_special_7 = (TextView) view.findViewById(R.id.tv_special_7);
            tv_special_8 = (TextView) view.findViewById(R.id.tv_special_8);
            tv_special_9 = (TextView) view.findViewById(R.id.tv_special_9);
            tv_special_10 = (TextView) view.findViewById(R.id.tv_special_10);
            tv_special_11 = (TextView) view.findViewById(R.id.tv_special_11);
            tv_special_12 = (TextView) view.findViewById(R.id.tv_special_12);

        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_material_price, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        SimpleViewHolder mSimpleViewHolder = (SimpleViewHolder) holder;
        mSimpleViewHolder.title.setText(position + 1 + "");
        mSimpleViewHolder.name.setText(mList.get(position).getName());
        if(mList.get(position).getIsRefresh() == 1){
            mSimpleViewHolder.name.setTextColor(MenuActivity.RED);
        }else{
            mSimpleViewHolder.name.setTextColor(MenuActivity.BLACK);
        }

        mSimpleViewHolder.tv_materialName_1.setText(mList.get(position).getMaterialName_1());
        mSimpleViewHolder.tv_materialName_2.setText(mList.get(position).getMaterialName_2());
        mSimpleViewHolder.tv_materialName_3.setText(mList.get(position).getMaterialName_3());
        mSimpleViewHolder.tv_materialName_4.setText(mList.get(position).getMaterialName_4());
        mSimpleViewHolder.tv_materialName_5.setText(mList.get(position).getMaterialName_5());
        mSimpleViewHolder.tv_materialName_6.setText(mList.get(position).getMaterialName_6());
        mSimpleViewHolder.tv_materialName_7.setText(mList.get(position).getMaterialName_7());
        mSimpleViewHolder.tv_materialName_8.setText(mList.get(position).getMaterialName_8());
        mSimpleViewHolder.tv_materialName_9.setText(mList.get(position).getMaterialName_9());
        mSimpleViewHolder.tv_materialName_10.setText(mList.get(position).getMaterialName_10());
        mSimpleViewHolder.tv_materialName_11.setText(mList.get(position).getMaterialName_11());
        mSimpleViewHolder.tv_materialName_12.setText(mList.get(position).getMaterialName_12());
        
        mSimpleViewHolder.tv_materialNum_1.setText(mList.get(position).getMaterialNum_1());
        mSimpleViewHolder.tv_materialNum_2.setText(mList.get(position).getMaterialNum_2());
        mSimpleViewHolder.tv_materialNum_3.setText(mList.get(position).getMaterialNum_3());
        mSimpleViewHolder.tv_materialNum_4.setText(mList.get(position).getMaterialNum_4());
        mSimpleViewHolder.tv_materialNum_5.setText(mList.get(position).getMaterialNum_5());
        mSimpleViewHolder.tv_materialNum_6.setText(mList.get(position).getMaterialNum_6());
        mSimpleViewHolder.tv_materialNum_7.setText(mList.get(position).getMaterialNum_7());
        mSimpleViewHolder.tv_materialNum_8.setText(mList.get(position).getMaterialNum_8());
        mSimpleViewHolder.tv_materialNum_9.setText(mList.get(position).getMaterialNum_9());
        mSimpleViewHolder.tv_materialNum_10.setText(mList.get(position).getMaterialNum_10());
        mSimpleViewHolder.tv_materialNum_11.setText(mList.get(position).getMaterialNum_11());
        mSimpleViewHolder.tv_materialNum_12.setText(mList.get(position).getMaterialNum_12());
        
        mSimpleViewHolder.tv_materialPrice_1.setText(mList.get(position).getMaterialPrice_1());
        mSimpleViewHolder.tv_materialPrice_2.setText(mList.get(position).getMaterialPrice_2());
        mSimpleViewHolder.tv_materialPrice_3.setText(mList.get(position).getMaterialPrice_3());
        mSimpleViewHolder.tv_materialPrice_4.setText(mList.get(position).getMaterialPrice_4());
        mSimpleViewHolder.tv_materialPrice_5.setText(mList.get(position).getMaterialPrice_5());
        mSimpleViewHolder.tv_materialPrice_6.setText(mList.get(position).getMaterialPrice_6());
        mSimpleViewHolder.tv_materialPrice_7.setText(mList.get(position).getMaterialPrice_7());
        mSimpleViewHolder.tv_materialPrice_8.setText(mList.get(position).getMaterialPrice_8());
        mSimpleViewHolder.tv_materialPrice_9.setText(mList.get(position).getMaterialPrice_9());
        mSimpleViewHolder.tv_materialPrice_10.setText(mList.get(position).getMaterialPrice_10());
        mSimpleViewHolder.tv_materialPrice_11.setText(mList.get(position).getMaterialPrice_11());
        mSimpleViewHolder.tv_materialPrice_12.setText(mList.get(position).getMaterialPrice_12());
        
        mSimpleViewHolder.tv_special_1.setText(mList.get(position).getSpecial_1());
        mSimpleViewHolder.tv_special_2.setText(mList.get(position).getSpecial_2());
        mSimpleViewHolder.tv_special_3.setText(mList.get(position).getSpecial_3());
        mSimpleViewHolder.tv_special_4.setText(mList.get(position).getSpecial_4());
        mSimpleViewHolder.tv_special_5.setText(mList.get(position).getSpecial_5());
        mSimpleViewHolder.tv_special_6.setText(mList.get(position).getSpecial_6());
        mSimpleViewHolder.tv_special_7.setText(mList.get(position).getSpecial_7());
        mSimpleViewHolder.tv_special_8.setText(mList.get(position).getSpecial_8());
        mSimpleViewHolder.tv_special_9.setText(mList.get(position).getSpecial_9());
        mSimpleViewHolder.tv_special_10.setText(mList.get(position).getSpecial_10());
        mSimpleViewHolder.tv_special_11.setText(mList.get(position).getSpecial_11());
        mSimpleViewHolder.tv_special_12.setText(mList.get(position).getSpecial_12());

    }


    @Override
    public int getItemCount() {
        return mList.size();
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
