package com.linqinen.mho.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linqinen.mho.R;
import com.linqinen.mho.bean.MyGuardStone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lin on 2016/12/22.
 */

public class MyGuardStoneAdapter extends MyRecyclerViewAdapter {

    private Context mContext;
    private List<MyGuardStone> mList;
//    List<Map<String,Integer>> maxPrecisionCastingList;
    Map<String,String> maxPrecisionCastingList;

    public MyGuardStoneAdapter(Context mContext) {
        super(mContext);
    }
    public MyGuardStoneAdapter(Context mContext, List<MyGuardStone> mList) {
        super(mContext);
        this.mContext = mContext;
        this.mList = mList;
        maxPrecisionCasting();
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder{
        TextView title,tv_skillName_1,tv_skillName_2,tv_skillName_3,
                tv_maxPrecisionCasting_1,tv_maxPrecisionCasting_2,tv_maxPrecisionCasting_3,
                tv_floatingPrecisionCasting_1,tv_floatingPrecisionCasting_2,tv_floatingPrecisionCasting_3;

        private final String TAG = MyGuardStoneAdapter.class.getSimpleName();


        SimpleViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.tv_sequenceNumber);
            tv_skillName_1 = (TextView) view.findViewById(R.id.tv_skillName_1);
            tv_skillName_2 = (TextView) view.findViewById(R.id.tv_skillName_2);
            tv_skillName_3 = (TextView) view.findViewById(R.id.tv_skillName_3);

            tv_maxPrecisionCasting_1 = (TextView) view.findViewById(R.id.tv_maxPrecisionCasting_1);
            tv_maxPrecisionCasting_2 = (TextView) view.findViewById(R.id.tv_maxPrecisionCasting_2);
            tv_maxPrecisionCasting_3 = (TextView) view.findViewById(R.id.tv_maxPrecisionCasting_3);

            tv_floatingPrecisionCasting_1 = (TextView) view.findViewById(R.id.tv_floatingPrecisionCasting_1);
            tv_floatingPrecisionCasting_2 = (TextView) view.findViewById(R.id.tv_floatingPrecisionCasting_2);
            tv_floatingPrecisionCasting_3 = (TextView) view.findViewById(R.id.tv_floatingPrecisionCasting_3);

        }

    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_guard_stone, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        final SimpleViewHolder mSimpleViewHolder = ((SimpleViewHolder) holder);


        mSimpleViewHolder.title.setText(position + 1 + "");
        mSimpleViewHolder.tv_skillName_1.setText(mList.get(position).getSkill_1());
        mSimpleViewHolder.tv_skillName_2.setText(mList.get(position).getSkill_2());
        mSimpleViewHolder.tv_skillName_3.setText(mList.get(position).getSkill_3());

//        for (int i = 0; i < maxPrecisionCastingList.size(); i++) {
//            if(){
////                mSimpleViewHolder.tv_skillName_1.setText(maxPrecisionCastingList.get(i));
//
//            }
//            if(maxPrecisionCastingList.get(i).containsKey(mList.get(position).getSkill_2())){
////                mSimpleViewHolder.tv_skillName_2.setText(maxPrecisionCastingList.get(i));
//            }
//            if(maxPrecisionCastingList.get(i).containsKey(mList.get(position).getSkill_3())){
////                mSimpleViewHolder.tv_skillName_3.setText(maxPrecisionCastingList.get(i));
//            }
//
//        }
        mSimpleViewHolder.tv_maxPrecisionCasting_1.setText(maxPrecisionCastingList.get(mList.get(position).getSkill_1()));
        mSimpleViewHolder.tv_maxPrecisionCasting_2.setText(maxPrecisionCastingList.get(mList.get(position).getSkill_2()));
        mSimpleViewHolder.tv_maxPrecisionCasting_3.setText(maxPrecisionCastingList.get(mList.get(position).getSkill_3()));
//        mSimpleViewHolder.tv_maxPrecisionCasting_1.setText(mList.get(position).getMaxPrecisionCasting_1());
//        mSimpleViewHolder.tv_maxPrecisionCasting_2.setText(mList.get(position).getMaxPrecisionCasting_2());
//        mSimpleViewHolder.tv_maxPrecisionCasting_3.setText(mList.get(position).getMaxPrecisionCasting_3());

        mSimpleViewHolder.tv_floatingPrecisionCasting_1.setText(mList.get(position).getFloatingPrecisionCasting_1());
        mSimpleViewHolder.tv_floatingPrecisionCasting_2.setText(mList.get(position).getFloatingPrecisionCasting_2());
        mSimpleViewHolder.tv_floatingPrecisionCasting_3.setText(mList.get(position).getFloatingPrecisionCasting_3());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

//    private void maxPrecisionCasting(){
//        maxPrecisionCastingList = new ArrayList<>();
//
//        maxPrecisionCastingList.add("千里眼14");
//        maxPrecisionCastingList.add("达人14");
//        maxPrecisionCastingList.add("攻击14");
//        maxPrecisionCastingList.add("回复速度12");
//        maxPrecisionCastingList.add("观察眼12");
//        maxPrecisionCastingList.add("广域化12");
//        maxPrecisionCastingList.add("炮术12");
//        maxPrecisionCastingList.add("体力11");
//        maxPrecisionCastingList.add("耐泥雪10");
//        maxPrecisionCastingList.add("刺伤10");
//        maxPrecisionCastingList.add("耐震10");
//        maxPrecisionCastingList.add("防御10");
//        maxPrecisionCastingList.add("加护9");
//        maxPrecisionCastingList.add("根性7");
//        maxPrecisionCastingList.add("KO术7");
//        maxPrecisionCastingList.add("锋利度6");
//        maxPrecisionCastingList.add("通常弹强化7");
//        maxPrecisionCastingList.add("眠弹追加7");
//        maxPrecisionCastingList.add("快速装填7");
//        maxPrecisionCastingList.add("耐力回复7");
//        maxPrecisionCastingList.add("恢复量7");
//        maxPrecisionCastingList.add("装填术6");
//        maxPrecisionCastingList.add("集中6");
//        maxPrecisionCastingList.add("反动5");
//
//    }
    private void maxPrecisionCasting(){
        maxPrecisionCastingList = new HashMap<>();

        maxPrecisionCastingList.put("","");
        maxPrecisionCastingList.put("气绝抵抗","14");
        maxPrecisionCastingList.put("千里眼","14");
        maxPrecisionCastingList.put("水耐性","14");
        maxPrecisionCastingList.put("雷耐性","14");
        maxPrecisionCastingList.put("水耐性","14");
        maxPrecisionCastingList.put("冰耐性","14");
        maxPrecisionCastingList.put("火耐性","14");
        maxPrecisionCastingList.put("龙耐性","14");
        maxPrecisionCastingList.put("达人","14");
        maxPrecisionCastingList.put("攻击","14");
        maxPrecisionCastingList.put("回复速度","12");
        maxPrecisionCastingList.put("观察眼","12");
        maxPrecisionCastingList.put("广域化","12");
        maxPrecisionCastingList.put("炮术","12");
        maxPrecisionCastingList.put("体力","11");
        maxPrecisionCastingList.put("毒性抵抗","10");
        maxPrecisionCastingList.put("麻痹抵抗","8+");
        maxPrecisionCastingList.put("耐泥雪","10");
        maxPrecisionCastingList.put("刺伤","10");
        maxPrecisionCastingList.put("耐震","10");
        maxPrecisionCastingList.put("防御","10");
        maxPrecisionCastingList.put("加护","9");
        maxPrecisionCastingList.put("抗菌","8+");
        maxPrecisionCastingList.put("回避性能","8");
        maxPrecisionCastingList.put("荷尔蒙","8");
        maxPrecisionCastingList.put("通常弹强化","7");
        maxPrecisionCastingList.put("风压抵抗","7");
        maxPrecisionCastingList.put("特殊攻击","7");
        maxPrecisionCastingList.put("眠弹追加","7");
        maxPrecisionCastingList.put("快速装填","7");
        maxPrecisionCastingList.put("耐力回复","7");
        maxPrecisionCastingList.put("恢复量","7");
        maxPrecisionCastingList.put("根性","7");
        maxPrecisionCastingList.put("KO术","7");
        maxPrecisionCastingList.put("锋利度","6");
        maxPrecisionCastingList.put("装填术","6");
        maxPrecisionCastingList.put("痛击","6");
        maxPrecisionCastingList.put("集中","6");
        maxPrecisionCastingList.put("反动","5");

    }

}
