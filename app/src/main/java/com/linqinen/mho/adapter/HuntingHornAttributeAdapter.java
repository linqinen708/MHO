package com.linqinen.mho.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linqinen.mho.R;
import com.linqinen.mho.bean.HuntingHornAttribute;

import java.util.List;

/**
 * Created by lin on 2016/12/23.
 */

public class HuntingHornAttributeAdapter extends MyRecyclerViewAdapter {

    private List<HuntingHornAttribute> mList;

    private static Context mContext;


    HuntingHornAttributeAdapter(Context mContext) {
        super(mContext);
    }
    public HuntingHornAttributeAdapter(Context mContext, List<HuntingHornAttribute> mList) {
        super(mContext);
        this.mContext = mContext;
        this.mList = mList;
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView title, name, tv_attribute;
        TextView tv_skill_1,tv_skill_2,tv_skill_3,tv_skill_4,tv_skill_5;

        SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_sequenceNumber);
            name = (TextView) view.findViewById(R.id.tv_name);
            tv_attribute = (TextView) view.findViewById(R.id.tv_attribute);
            tv_skill_1 = (TextView) view.findViewById(R.id.tv_skill_1);
            tv_skill_2 = (TextView) view.findViewById(R.id.tv_skill_2);
            tv_skill_3 = (TextView) view.findViewById(R.id.tv_skill_3);
            tv_skill_4 = (TextView) view.findViewById(R.id.tv_skill_4);
            tv_skill_5 = (TextView) view.findViewById(R.id.tv_skill_5);

        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_huntinghornattribute, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        final SimpleViewHolder mSimpleViewHolder = (SimpleViewHolder) holder;

        mSimpleViewHolder.title.setText(position + 1 + "");
        mSimpleViewHolder.name.setText(mList.get(position).getName()+"\t("+mList.get(position).getLevel()+")");
        mSimpleViewHolder.tv_attribute.setText(mList.get(position).getAttribute());
        mSimpleViewHolder.tv_skill_1.setText(mList.get(position).getSkill_1());
        mSimpleViewHolder.tv_skill_2.setText(mList.get(position).getSkill_2());
        mSimpleViewHolder.tv_skill_3.setText(mList.get(position).getSkill_3());
        mSimpleViewHolder.tv_skill_4.setText(mList.get(position).getSkill_4());
        mSimpleViewHolder.tv_skill_5.setText(mList.get(position).getSkill_5());


    }

    

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
