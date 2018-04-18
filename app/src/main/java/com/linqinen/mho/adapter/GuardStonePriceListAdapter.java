package com.linqinen.mho.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linqinen.mho.MenuActivity;
import com.linqinen.mho.R;
import com.linqinen.mho.bean.GuardStonePrice;

import java.util.List;

/**
 * Created by lin on 2016/12/22.
 */

public class GuardStonePriceListAdapter extends MyRecyclerViewAdapter {

    private Context mContext;
    private List<GuardStonePrice> mList;

    public GuardStonePriceListAdapter(Context mContext) {
        super(mContext);
    }
    public GuardStonePriceListAdapter(Context mContext, List<GuardStonePrice> mList) {
        super(mContext);
        this.mContext = mContext;
        this.mList = mList;
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder{
        TextView title,name,tv_precisionCasting,tv_auctionPrice,tv_fixedPrice;

        private final String TAG = "GuardStonePriceListAdapter";


        SimpleViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.tv_sequenceNumber);
            name = (TextView) view.findViewById(R.id.tv_Name);
            tv_precisionCasting = (TextView) view.findViewById(R.id.tv_precisionCasting);
            tv_auctionPrice = (TextView) view.findViewById(R.id.tv_auctionPrice);
            tv_fixedPrice = (TextView) view.findViewById(R.id.tv_fixedPrice);

        }

    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_guard_stone_price, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        final SimpleViewHolder mSimpleViewHolder = ((SimpleViewHolder) holder);


        mSimpleViewHolder.title.setText(position + 1 + "");
        mSimpleViewHolder.name.setText(mList.get(position).getName());
        if(mList.get(position).getIsRefresh() == 1){
            mSimpleViewHolder.name.setTextColor(MenuActivity.RED);
        }else{
            mSimpleViewHolder.name.setTextColor(MenuActivity.BLACK);
        }
        mSimpleViewHolder.tv_precisionCasting.setText(mList.get(position).getPrecisionCasting());
        mSimpleViewHolder.tv_auctionPrice.setText(mList.get(position).getAuctionPrice());
        mSimpleViewHolder.tv_fixedPrice.setText(mList.get(position).getFixedPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
