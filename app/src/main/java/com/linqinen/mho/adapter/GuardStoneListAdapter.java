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

import com.linqinen.mho.R;
import com.linqinen.mho.bean.GuardStone;

import java.util.List;


/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class GuardStoneListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "AnimationListAdapter";

    private static final int COUNT = 20;

    private final Context mContext;
//    private final List<Integer> mItems;
    private int mCurrentItemId = 0;
    private List<GuardStone> mList;

    public GuardStoneListAdapter(Context context, List<GuardStone> mList) {
        mContext = context;
        this.mList = mList;
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder {
         TextView title,name;
        TextView tv_maxValue_queen,tv_maxValue_dragon,tv_maxValue_hero,tv_maxValue_legend;
        TextView tv_price_queen,tv_price_dragon,tv_price_hero,tv_price_legend;
        TextView tv_precisionCasting_queen,tv_precisionCasting_dragon,tv_precisionCasting_hero,tv_precisionCasting_legend;

        SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_sequenceNumber);
            name = (TextView) view.findViewById(R.id.tv_name);
            tv_maxValue_queen = (TextView) view.findViewById(R.id.tv_maxValue_queen);
            tv_maxValue_dragon = (TextView) view.findViewById(R.id.tv_maxValue_dragon);
            tv_maxValue_hero = (TextView) view.findViewById(R.id.tv_maxValue_hero);
            tv_maxValue_legend = (TextView) view.findViewById(R.id.tv_maxValue_legend);
            tv_price_queen = (TextView) view.findViewById(R.id.tv_price_queen);
            tv_price_dragon = (TextView) view.findViewById(R.id.tv_price_dragon);
            tv_price_hero = (TextView) view.findViewById(R.id.tv_price_hero);
            tv_price_legend = (TextView) view.findViewById(R.id.tv_price_legend);
            tv_precisionCasting_queen = (TextView) view.findViewById(R.id.tv_precisionCasting_queen);
            tv_precisionCasting_dragon = (TextView) view.findViewById(R.id.tv_precisionCasting_dragon);
            tv_precisionCasting_hero = (TextView) view.findViewById(R.id.tv_precisionCasting_hero);
            tv_precisionCasting_legend = (TextView) view.findViewById(R.id.tv_precisionCasting_legend);

        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_guard_stone, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((SimpleViewHolder) holder).title.setText(position + 1 + "");
        ((SimpleViewHolder) holder).name.setText(mList.get(position).getName());
        ((SimpleViewHolder) holder).tv_maxValue_queen.setText(mList.get(position).getValue_queen());
        ((SimpleViewHolder) holder).tv_maxValue_dragon.setText(mList.get(position).getValue_dragon());
        ((SimpleViewHolder) holder).tv_maxValue_hero.setText(mList.get(position).getValue_hero());
        ((SimpleViewHolder) holder).tv_maxValue_legend.setText(mList.get(position).getValue_legend());

        ((SimpleViewHolder) holder).tv_price_queen.setText(mList.get(position).getPrice_queen());
        ((SimpleViewHolder) holder).tv_price_dragon.setText(mList.get(position).getPrice_dragon());
        ((SimpleViewHolder) holder).tv_price_hero.setText(mList.get(position).getPrice_hero());
        ((SimpleViewHolder) holder).tv_price_legend.setText(mList.get(position).getPrice_legend());

        ((SimpleViewHolder) holder).tv_precisionCasting_queen.setText(mList.get(position).getPrecisionCasting_queen());
        ((SimpleViewHolder) holder).tv_precisionCasting_dragon.setText(mList.get(position).getPrecisionCasting_dragon());
        ((SimpleViewHolder) holder).tv_precisionCasting_hero.setText(mList.get(position).getPrecisionCasting_hero());
        ((SimpleViewHolder) holder).tv_precisionCasting_legend.setText(mList.get(position).getPrecisionCasting_legend());

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
