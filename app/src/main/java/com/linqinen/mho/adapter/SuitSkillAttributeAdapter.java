package com.linqinen.mho.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linqinen.mho.R;
import com.linqinen.mho.bean.SuitSkillAttribute;

import java.util.List;

/**
 * Created by lin on 2016/12/23.
 */

public class SuitSkillAttributeAdapter extends MyRecyclerViewAdapter {

    private List<SuitSkillAttribute> mList;

    private static Context mContext;


    SuitSkillAttributeAdapter(Context mContext) {
        super(mContext);
    }
    public SuitSkillAttributeAdapter(Context mContext, List<SuitSkillAttribute> mList) {
        super(mContext);
        this.mContext = mContext;
        this.mList = mList;
    }



    static class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView title, name, tv_armorLevel;
        TextView tv_head_hole, tv_hand_hole, tv_clothes_hole, tv_waist_hole, tv_foot_hole;
        TextView tv_skill_name_1, tv_skill_name_2, tv_skill_name_3, tv_skill_name_4, tv_skill_name_5;
        TextView tv_head_skill_name_1, tv_head_skill_name_2, tv_head_skill_name_3, tv_head_skill_name_4, tv_head_skill_name_5;
        TextView tv_hand_skill_name_1, tv_hand_skill_name_2, tv_hand_skill_name_3, tv_hand_skill_name_4, tv_hand_skill_name_5;
        TextView tv_clothes_skill_name_1, tv_clothes_skill_name_2, tv_clothes_skill_name_3, tv_clothes_skill_name_4, tv_clothes_skill_name_5;
        TextView tv_waist_skill_name_1, tv_waist_skill_name_2, tv_waist_skill_name_3, tv_waist_skill_name_4, tv_waist_skill_name_5;
        TextView tv_foot_skill_name_1, tv_foot_skill_name_2, tv_foot_skill_name_3, tv_foot_skill_name_4, tv_foot_skill_name_5;

        TextView tv_material_name_1, tv_material_name_2, tv_material_name_3, tv_material_name_4, tv_material_name_5, tv_material_name_6;
        TextView tv_head_material_1, tv_head_material_2, tv_head_material_3, tv_head_material_4, tv_head_material_5, tv_head_material_6;
        TextView tv_hand_material_1, tv_hand_material_2, tv_hand_material_3, tv_hand_material_4, tv_hand_material_5, tv_hand_material_6;
        TextView tv_clothes_material_1, tv_clothes_material_2, tv_clothes_material_3, tv_clothes_material_4, tv_clothes_material_5, tv_clothes_material_6;
        TextView tv_waist_material_1, tv_waist_material_2, tv_waist_material_3, tv_waist_material_4, tv_waist_material_5, tv_waist_material_6;
        TextView tv_foot_material_1, tv_foot_material_2, tv_foot_material_3, tv_foot_material_4, tv_foot_material_5, tv_foot_material_6;

        ImageView btn_material;

        LinearLayout ll_material_adapter, ll_material;



        SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_sequenceNumber);
            name = (TextView) view.findViewById(R.id.tv_name);
            tv_armorLevel = (TextView) view.findViewById(R.id.tv_armorLevel);
            tv_head_hole = (TextView) view.findViewById(R.id.tv_head_hole);
            tv_hand_hole = (TextView) view.findViewById(R.id.tv_hand_hole);
            tv_clothes_hole = (TextView) view.findViewById(R.id.tv_clothes_hole);
            tv_waist_hole = (TextView) view.findViewById(R.id.tv_waist_hole);
            tv_foot_hole = (TextView) view.findViewById(R.id.tv_foot_hole);

            tv_skill_name_1 = (TextView) view.findViewById(R.id.tv_skill_name_1);
            tv_skill_name_2 = (TextView) view.findViewById(R.id.tv_skill_name_2);
            tv_skill_name_3 = (TextView) view.findViewById(R.id.tv_skill_name_3);
            tv_skill_name_4 = (TextView) view.findViewById(R.id.tv_skill_name_4);
            tv_skill_name_5 = (TextView) view.findViewById(R.id.tv_skill_name_5);

            tv_head_skill_name_1 = (TextView) view.findViewById(R.id.tv_head_skill_1);
            tv_head_skill_name_2 = (TextView) view.findViewById(R.id.tv_head_skill_2);
            tv_head_skill_name_3 = (TextView) view.findViewById(R.id.tv_head_skill_3);
            tv_head_skill_name_4 = (TextView) view.findViewById(R.id.tv_head_skill_4);
            tv_head_skill_name_5 = (TextView) view.findViewById(R.id.tv_head_skill_5);

            tv_hand_skill_name_1 = (TextView) view.findViewById(R.id.tv_hand_skill_1);
            tv_hand_skill_name_2 = (TextView) view.findViewById(R.id.tv_hand_skill_2);
            tv_hand_skill_name_3 = (TextView) view.findViewById(R.id.tv_hand_skill_3);
            tv_hand_skill_name_4 = (TextView) view.findViewById(R.id.tv_hand_skill_4);
            tv_hand_skill_name_5 = (TextView) view.findViewById(R.id.tv_hand_skill_5);

            tv_clothes_skill_name_1 = (TextView) view.findViewById(R.id.tv_clothes_skill_1);
            tv_clothes_skill_name_2 = (TextView) view.findViewById(R.id.tv_clothes_skill_2);
            tv_clothes_skill_name_3 = (TextView) view.findViewById(R.id.tv_clothes_skill_3);
            tv_clothes_skill_name_4 = (TextView) view.findViewById(R.id.tv_clothes_skill_4);
            tv_clothes_skill_name_5 = (TextView) view.findViewById(R.id.tv_clothes_skill_5);

            tv_waist_skill_name_1 = (TextView) view.findViewById(R.id.tv_waist_skill_1);
            tv_waist_skill_name_2 = (TextView) view.findViewById(R.id.tv_waist_skill_2);
            tv_waist_skill_name_3 = (TextView) view.findViewById(R.id.tv_waist_skill_3);
            tv_waist_skill_name_4 = (TextView) view.findViewById(R.id.tv_waist_skill_4);
            tv_waist_skill_name_5 = (TextView) view.findViewById(R.id.tv_waist_skill_5);

            tv_foot_skill_name_1 = (TextView) view.findViewById(R.id.tv_foot_skill_1);
            tv_foot_skill_name_2 = (TextView) view.findViewById(R.id.tv_foot_skill_2);
            tv_foot_skill_name_3 = (TextView) view.findViewById(R.id.tv_foot_skill_3);
            tv_foot_skill_name_4 = (TextView) view.findViewById(R.id.tv_foot_skill_4);
            tv_foot_skill_name_5 = (TextView) view.findViewById(R.id.tv_foot_skill_5);

            btn_material = (ImageView) view.findViewById(R.id.btn_material);
            ll_material = (LinearLayout) view.findViewById(R.id.ll_material);

            ll_material_adapter = (LinearLayout) view.findViewById(R.id.ll_material_adapter);

            tv_material_name_1 = (TextView) view.findViewById(R.id.tv_material_name_1);
            tv_material_name_2 = (TextView) view.findViewById(R.id.tv_material_name_2);
            tv_material_name_3 = (TextView) view.findViewById(R.id.tv_material_name_3);
            tv_material_name_4 = (TextView) view.findViewById(R.id.tv_material_name_4);
            tv_material_name_5 = (TextView) view.findViewById(R.id.tv_material_name_5);
            tv_material_name_6 = (TextView) view.findViewById(R.id.tv_material_name_6);

            tv_head_material_1 = (TextView) view.findViewById(R.id.tv_head_material_1);
            tv_head_material_2 = (TextView) view.findViewById(R.id.tv_head_material_2);
            tv_head_material_3 = (TextView) view.findViewById(R.id.tv_head_material_3);
            tv_head_material_4 = (TextView) view.findViewById(R.id.tv_head_material_4);
            tv_head_material_5 = (TextView) view.findViewById(R.id.tv_head_material_5);
            tv_head_material_6 = (TextView) view.findViewById(R.id.tv_head_material_6);

            tv_hand_material_1 = (TextView) view.findViewById(R.id.tv_hand_material_1);
            tv_hand_material_2 = (TextView) view.findViewById(R.id.tv_hand_material_2);
            tv_hand_material_3 = (TextView) view.findViewById(R.id.tv_hand_material_3);
            tv_hand_material_4 = (TextView) view.findViewById(R.id.tv_hand_material_4);
            tv_hand_material_5 = (TextView) view.findViewById(R.id.tv_hand_material_5);
            tv_hand_material_6 = (TextView) view.findViewById(R.id.tv_hand_material_6);

            tv_clothes_material_1 = (TextView) view.findViewById(R.id.tv_clothes_material_1);
            tv_clothes_material_2 = (TextView) view.findViewById(R.id.tv_clothes_material_2);
            tv_clothes_material_3 = (TextView) view.findViewById(R.id.tv_clothes_material_3);
            tv_clothes_material_4 = (TextView) view.findViewById(R.id.tv_clothes_material_4);
            tv_clothes_material_5 = (TextView) view.findViewById(R.id.tv_clothes_material_5);
            tv_clothes_material_6 = (TextView) view.findViewById(R.id.tv_clothes_material_6);

            tv_waist_material_1 = (TextView) view.findViewById(R.id.tv_waist_material_1);
            tv_waist_material_2 = (TextView) view.findViewById(R.id.tv_waist_material_2);
            tv_waist_material_3 = (TextView) view.findViewById(R.id.tv_waist_material_3);
            tv_waist_material_4 = (TextView) view.findViewById(R.id.tv_waist_material_4);
            tv_waist_material_5 = (TextView) view.findViewById(R.id.tv_waist_material_5);
            tv_waist_material_6 = (TextView) view.findViewById(R.id.tv_waist_material_6);

            tv_foot_material_1 = (TextView) view.findViewById(R.id.tv_foot_material_1);
            tv_foot_material_2 = (TextView) view.findViewById(R.id.tv_foot_material_2);
            tv_foot_material_3 = (TextView) view.findViewById(R.id.tv_foot_material_3);
            tv_foot_material_4 = (TextView) view.findViewById(R.id.tv_foot_material_4);
            tv_foot_material_5 = (TextView) view.findViewById(R.id.tv_foot_material_5);
            tv_foot_material_6 = (TextView) view.findViewById(R.id.tv_foot_material_6);

        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.simple_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        final SimpleViewHolder mSimpleViewHolder = (SimpleViewHolder) holder;

        mSimpleViewHolder.ll_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSimpleViewHolder.ll_material_adapter.getVisibility() == View.GONE) {
                    mSimpleViewHolder.btn_material.setRotationX(180);
                    mSimpleViewHolder.ll_material_adapter.setVisibility(View.VISIBLE);
                } else {
                    mSimpleViewHolder.btn_material.setRotationX(0);
                    mSimpleViewHolder.ll_material_adapter.setVisibility(View.GONE);
                }
            }
        });
        
        mSimpleViewHolder.title.setText(position + 1 + "");
        mSimpleViewHolder.name.setText(mList.get(position).getName());
        mSimpleViewHolder.tv_armorLevel.setText(mList.get(position).getArmorLevel());

        mSimpleViewHolder.tv_head_hole.setText(mList.get(position).getHead_hole());
        mSimpleViewHolder.tv_hand_hole.setText(mList.get(position).getHand_hole());
        mSimpleViewHolder.tv_clothes_hole.setText(mList.get(position).getClothes_hole());
        mSimpleViewHolder.tv_waist_hole.setText(mList.get(position).getWaist_hole());
        mSimpleViewHolder.tv_foot_hole.setText(mList.get(position).getFoot_hole());

//        if(MenuActivity.isDisplayMine){
//            if (mList.get(position).getIsPossessHead() == 1) {
//                mSimpleViewHolder.tv_head_hole.setTextColor(MenuActivity.CYAN);
//            }else{
//                mSimpleViewHolder.tv_head_hole.setTextColor(MenuActivity.BLACK);
//            }
//            if (mList.get(position).getIsPossessHand() == 1) {
//                mSimpleViewHolder.tv_hand_hole.setTextColor(MenuActivity.CYAN);
//            }else{
//                mSimpleViewHolder.tv_hand_hole.setTextColor(MenuActivity.BLACK);
//
//            }
//            if (mList.get(position).getIsPossessClothes() == 1) {
//                mSimpleViewHolder.tv_clothes_hole.setTextColor(MenuActivity.CYAN);
//            }else{
//                mSimpleViewHolder.tv_clothes_hole.setTextColor(MenuActivity.BLACK);
//
//            }
//            if (mList.get(position).getIsPossessWaist() == 1) {
//                mSimpleViewHolder.tv_waist_hole.setTextColor(MenuActivity.CYAN);
//            }else{
//                mSimpleViewHolder.tv_waist_hole.setTextColor(MenuActivity.BLACK);
//
//            }
//            if (mList.get(position).getIsPossessFoot() == 1) {
//                mSimpleViewHolder.tv_foot_hole.setTextColor(MenuActivity.CYAN);
//            }else{
//                mSimpleViewHolder.tv_foot_hole.setTextColor(MenuActivity.BLACK);
//
//            }
//        }
        mSimpleViewHolder.tv_skill_name_1.setText(mList.get(position).getSkillName_1());
        mSimpleViewHolder.tv_skill_name_2.setText(mList.get(position).getSkillName_2());
        mSimpleViewHolder.tv_skill_name_3.setText(mList.get(position).getSkillName_3());
        mSimpleViewHolder.tv_skill_name_4.setText(mList.get(position).getSkillName_4());
        mSimpleViewHolder.tv_skill_name_5.setText(mList.get(position).getSkillName_5());

        mSimpleViewHolder.tv_head_skill_name_1.setText(mList.get(position).getHeadSkill_1());
        mSimpleViewHolder.tv_head_skill_name_2.setText(mList.get(position).getHeadSkill_2());
        mSimpleViewHolder.tv_head_skill_name_3.setText(mList.get(position).getHeadSkill_3());
        mSimpleViewHolder.tv_head_skill_name_4.setText(mList.get(position).getHeadSkill_4());
        mSimpleViewHolder.tv_head_skill_name_5.setText(mList.get(position).getHeadSkill_5());

        mSimpleViewHolder.tv_hand_skill_name_1.setText(mList.get(position).getHandSkill_1());
        mSimpleViewHolder.tv_hand_skill_name_2.setText(mList.get(position).getHandSkill_2());
        mSimpleViewHolder.tv_hand_skill_name_3.setText(mList.get(position).getHandSkill_3());
        mSimpleViewHolder.tv_hand_skill_name_4.setText(mList.get(position).getHandSkill_4());
        mSimpleViewHolder.tv_hand_skill_name_5.setText(mList.get(position).getHandSkill_5());

        mSimpleViewHolder.tv_clothes_skill_name_1.setText(mList.get(position).getClothesSkill_1());
        mSimpleViewHolder.tv_clothes_skill_name_2.setText(mList.get(position).getClothesSkill_2());
        mSimpleViewHolder.tv_clothes_skill_name_3.setText(mList.get(position).getClothesSkill_3());
        mSimpleViewHolder.tv_clothes_skill_name_4.setText(mList.get(position).getClothesSkill_4());
        mSimpleViewHolder.tv_clothes_skill_name_5.setText(mList.get(position).getClothesSkill_5());

        mSimpleViewHolder.tv_waist_skill_name_1.setText(mList.get(position).getWaistSkill_1());
        mSimpleViewHolder.tv_waist_skill_name_2.setText(mList.get(position).getWaistSkill_2());
        mSimpleViewHolder.tv_waist_skill_name_3.setText(mList.get(position).getWaistSkill_3());
        mSimpleViewHolder.tv_waist_skill_name_4.setText(mList.get(position).getWaistSkill_4());
        mSimpleViewHolder.tv_waist_skill_name_5.setText(mList.get(position).getWaistSkill_5());

        mSimpleViewHolder.tv_foot_skill_name_1.setText(mList.get(position).getFootSkill_1());
        mSimpleViewHolder.tv_foot_skill_name_2.setText(mList.get(position).getFootSkill_2());
        mSimpleViewHolder.tv_foot_skill_name_3.setText(mList.get(position).getFootSkill_3());
        mSimpleViewHolder.tv_foot_skill_name_4.setText(mList.get(position).getFootSkill_4());
        mSimpleViewHolder.tv_foot_skill_name_5.setText(mList.get(position).getFootSkill_5());

        mSimpleViewHolder.tv_material_name_1.setText(mList.get(position).getMaterialName_1());
        mSimpleViewHolder.tv_material_name_2.setText(mList.get(position).getMaterialName_2());
        mSimpleViewHolder.tv_material_name_3.setText(mList.get(position).getMaterialName_3());
        mSimpleViewHolder.tv_material_name_4.setText(mList.get(position).getMaterialName_4());
        mSimpleViewHolder.tv_material_name_5.setText(mList.get(position).getMaterialName_5());
        mSimpleViewHolder.tv_material_name_6.setText(mList.get(position).getMaterialName_6());

        mSimpleViewHolder.tv_head_material_1.setText(mList.get(position).getHeadMaterial_1());
        mSimpleViewHolder.tv_head_material_2.setText(mList.get(position).getHeadMaterial_2());
        mSimpleViewHolder.tv_head_material_3.setText(mList.get(position).getHeadMaterial_3());
        mSimpleViewHolder.tv_head_material_4.setText(mList.get(position).getHeadMaterial_4());
        mSimpleViewHolder.tv_head_material_5.setText(mList.get(position).getHeadMaterial_5());
        mSimpleViewHolder.tv_head_material_6.setText(mList.get(position).getHeadMaterial_6());

        mSimpleViewHolder.tv_hand_material_1.setText(mList.get(position).getHandMaterial_1());
        mSimpleViewHolder.tv_hand_material_2.setText(mList.get(position).getHandMaterial_2());
        mSimpleViewHolder.tv_hand_material_3.setText(mList.get(position).getHandMaterial_3());
        mSimpleViewHolder.tv_hand_material_4.setText(mList.get(position).getHandMaterial_4());
        mSimpleViewHolder.tv_hand_material_5.setText(mList.get(position).getHandMaterial_5());
        mSimpleViewHolder.tv_hand_material_6.setText(mList.get(position).getHandMaterial_6());

        mSimpleViewHolder.tv_clothes_material_1.setText(mList.get(position).getClothesMaterial_1());
        mSimpleViewHolder.tv_clothes_material_2.setText(mList.get(position).getClothesMaterial_2());
        mSimpleViewHolder.tv_clothes_material_3.setText(mList.get(position).getClothesMaterial_3());
        mSimpleViewHolder.tv_clothes_material_4.setText(mList.get(position).getClothesMaterial_4());
        mSimpleViewHolder.tv_clothes_material_5.setText(mList.get(position).getClothesMaterial_5());
        mSimpleViewHolder.tv_clothes_material_6.setText(mList.get(position).getClothesMaterial_6());

        mSimpleViewHolder.tv_waist_material_1.setText(mList.get(position).getWaistMaterial_1());
        mSimpleViewHolder.tv_waist_material_2.setText(mList.get(position).getWaistMaterial_2());
        mSimpleViewHolder.tv_waist_material_3.setText(mList.get(position).getWaistMaterial_3());
        mSimpleViewHolder.tv_waist_material_4.setText(mList.get(position).getWaistMaterial_4());
        mSimpleViewHolder.tv_waist_material_5.setText(mList.get(position).getWaistMaterial_5());
        mSimpleViewHolder.tv_waist_material_6.setText(mList.get(position).getWaistMaterial_6());

        mSimpleViewHolder.tv_foot_material_1.setText(mList.get(position).getFootMaterial_1());
        mSimpleViewHolder.tv_foot_material_2.setText(mList.get(position).getFootMaterial_2());
        mSimpleViewHolder.tv_foot_material_3.setText(mList.get(position).getFootMaterial_3());
        mSimpleViewHolder.tv_foot_material_4.setText(mList.get(position).getFootMaterial_4());
        mSimpleViewHolder.tv_foot_material_5.setText(mList.get(position).getFootMaterial_5());
        mSimpleViewHolder.tv_foot_material_6.setText(mList.get(position).getFootMaterial_6());

    }

    

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
