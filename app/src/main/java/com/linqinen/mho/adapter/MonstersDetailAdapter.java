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
import com.linqinen.mho.bean.Monster;

import java.util.List;


/**
 * @author Gabriele Mariotti (gabri.mariotti@gmail.com)
 */
public class MonstersDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "MonstersDetailAdapter";


    private static Context mContext;
//    private final List<Integer> mItems;
    private List<Monster> mList;
    public MonstersDetailAdapter(Context context, List<Monster> mList) {
        mContext = context;
        this.mList = mList;
    }

    private static class SimpleViewHolder extends RecyclerView.ViewHolder {
        TextView title,name;
        TextView tv_body_chop,tv_back_chop,tv_tail_chop,tv_head_chop,tv_neck_chop,tv_foot_chop,tv_hand_chop,tv_wing_chop,tv_abdomen_chop;
        TextView tv_body_strike,tv_back_strike,tv_tail_strike,tv_head_strike,tv_neck_strike,tv_foot_strike,tv_hand_strike,tv_wing_strike,tv_abdomen_strike;
        TextView tv_body_shot,tv_back_shot,tv_tail_shot,tv_head_shot,tv_neck_shot,tv_foot_shot,tv_hand_shot,tv_wing_shot,tv_abdomen_shot;
        TextView tv_body_fire,tv_back_fire,tv_tail_fire,tv_head_fire,tv_neck_fire,tv_foot_fire,tv_hand_fire,tv_wing_fire,tv_abdomen_fire;
        TextView tv_body_water,tv_back_water,tv_tail_water,tv_head_water,tv_neck_water,tv_foot_water,tv_hand_water,tv_wing_water,tv_abdomen_water;
        TextView tv_body_thunder,tv_back_thunder,tv_tail_thunder,tv_head_thunder,tv_neck_thunder,tv_foot_thunder,tv_hand_thunder,tv_wing_thunder,tv_abdomen_thunder;
        TextView tv_body_ice,tv_back_ice,tv_tail_ice,tv_head_ice,tv_neck_ice,tv_foot_ice,tv_hand_ice,tv_wing_ice,tv_abdomen_ice;
        TextView tv_body_dragon,tv_back_dragon,tv_tail_dragon,tv_head_dragon,tv_neck_dragon,tv_foot_dragon,tv_hand_dragon,tv_wing_dragon,tv_abdomen_dragon;
        TextView expire,poison,numb,sleep,holeTrap,numbTrap,flash,sonicBoom,meat,remark;





        SimpleViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.tv_sequenceNumber);
            name = (TextView) view.findViewById(R.id.tv_name);

            tv_body_chop = (TextView) view.findViewById(R.id.tv_body_chop);
            tv_back_chop = (TextView) view.findViewById(R.id.tv_back_chop);
            tv_tail_chop = (TextView) view.findViewById(R.id.tv_tail_chop);
            tv_head_chop = (TextView) view.findViewById(R.id.tv_head_chop);
            tv_neck_chop = (TextView) view.findViewById(R.id.tv_neck_chop);
            tv_foot_chop = (TextView) view.findViewById(R.id.tv_foot_chop);
            tv_hand_chop = (TextView) view.findViewById(R.id.tv_hand_chop);
            tv_wing_chop = (TextView) view.findViewById(R.id.tv_wing_chop);
            tv_abdomen_chop = (TextView) view.findViewById(R.id.tv_abdomen_chop);
            
            tv_body_strike = (TextView) view.findViewById(R.id.tv_body_strike);
            tv_back_strike = (TextView) view.findViewById(R.id.tv_back_strike);
            tv_tail_strike = (TextView) view.findViewById(R.id.tv_tail_strike);
            tv_head_strike = (TextView) view.findViewById(R.id.tv_head_strike);
            tv_neck_strike = (TextView) view.findViewById(R.id.tv_neck_strike);
            tv_foot_strike = (TextView) view.findViewById(R.id.tv_foot_strike);
            tv_hand_strike = (TextView) view.findViewById(R.id.tv_hand_strike);
            tv_wing_strike = (TextView) view.findViewById(R.id.tv_wing_strike);
            tv_abdomen_strike = (TextView) view.findViewById(R.id.tv_abdomen_strike);
            
            tv_body_shot = (TextView) view.findViewById(R.id.tv_body_shot);
            tv_back_shot = (TextView) view.findViewById(R.id.tv_back_shot);
            tv_tail_shot = (TextView) view.findViewById(R.id.tv_tail_shot);
            tv_head_shot = (TextView) view.findViewById(R.id.tv_head_shot);
            tv_neck_shot = (TextView) view.findViewById(R.id.tv_neck_shot);
            tv_foot_shot = (TextView) view.findViewById(R.id.tv_foot_shot);
            tv_hand_shot = (TextView) view.findViewById(R.id.tv_hand_shot);
            tv_wing_shot = (TextView) view.findViewById(R.id.tv_wing_shot);
            tv_abdomen_shot = (TextView) view.findViewById(R.id.tv_abdomen_shot);
            
            tv_body_ice = (TextView) view.findViewById(R.id.tv_body_ice);
            tv_back_ice = (TextView) view.findViewById(R.id.tv_back_ice);
            tv_tail_ice = (TextView) view.findViewById(R.id.tv_tail_ice);
            tv_head_ice = (TextView) view.findViewById(R.id.tv_head_ice);
            tv_neck_ice = (TextView) view.findViewById(R.id.tv_neck_ice);
            tv_foot_ice = (TextView) view.findViewById(R.id.tv_foot_ice);
            tv_hand_ice = (TextView) view.findViewById(R.id.tv_hand_ice);
            tv_wing_ice = (TextView) view.findViewById(R.id.tv_wing_ice);
            tv_abdomen_ice = (TextView) view.findViewById(R.id.tv_abdomen_ice);
            
            tv_body_water = (TextView) view.findViewById(R.id.tv_body_water);
            tv_back_water = (TextView) view.findViewById(R.id.tv_back_water);
            tv_tail_water = (TextView) view.findViewById(R.id.tv_tail_water);
            tv_head_water = (TextView) view.findViewById(R.id.tv_head_water);
            tv_neck_water = (TextView) view.findViewById(R.id.tv_neck_water);
            tv_foot_water = (TextView) view.findViewById(R.id.tv_foot_water);
            tv_hand_water = (TextView) view.findViewById(R.id.tv_hand_water);
            tv_wing_water = (TextView) view.findViewById(R.id.tv_wing_water);
            tv_abdomen_water = (TextView) view.findViewById(R.id.tv_abdomen_water);
            
            tv_body_thunder = (TextView) view.findViewById(R.id.tv_body_thunder);
            tv_back_thunder = (TextView) view.findViewById(R.id.tv_back_thunder);
            tv_tail_thunder = (TextView) view.findViewById(R.id.tv_tail_thunder);
            tv_head_thunder = (TextView) view.findViewById(R.id.tv_head_thunder);
            tv_neck_thunder = (TextView) view.findViewById(R.id.tv_neck_thunder);
            tv_foot_thunder = (TextView) view.findViewById(R.id.tv_foot_thunder);
            tv_hand_thunder = (TextView) view.findViewById(R.id.tv_hand_thunder);
            tv_wing_thunder = (TextView) view.findViewById(R.id.tv_wing_thunder);
            tv_abdomen_thunder = (TextView) view.findViewById(R.id.tv_abdomen_thunder);
            
            tv_body_fire = (TextView) view.findViewById(R.id.tv_body_fire);
            tv_back_fire = (TextView) view.findViewById(R.id.tv_back_fire);
            tv_tail_fire = (TextView) view.findViewById(R.id.tv_tail_fire);
            tv_head_fire = (TextView) view.findViewById(R.id.tv_head_fire);
            tv_neck_fire = (TextView) view.findViewById(R.id.tv_neck_fire);
            tv_foot_fire = (TextView) view.findViewById(R.id.tv_foot_fire);
            tv_hand_fire = (TextView) view.findViewById(R.id.tv_hand_fire);
            tv_wing_fire = (TextView) view.findViewById(R.id.tv_wing_fire);
            tv_abdomen_fire = (TextView) view.findViewById(R.id.tv_abdomen_fire);
            
            tv_body_dragon = (TextView) view.findViewById(R.id.tv_body_dragon);
            tv_back_dragon = (TextView) view.findViewById(R.id.tv_back_dragon);
            tv_tail_dragon = (TextView) view.findViewById(R.id.tv_tail_dragon);
            tv_head_dragon = (TextView) view.findViewById(R.id.tv_head_dragon);
            tv_neck_dragon = (TextView) view.findViewById(R.id.tv_neck_dragon);
            tv_foot_dragon = (TextView) view.findViewById(R.id.tv_foot_dragon);
            tv_hand_dragon = (TextView) view.findViewById(R.id.tv_hand_dragon);
            tv_wing_dragon = (TextView) view.findViewById(R.id.tv_wing_dragon);
            tv_abdomen_dragon = (TextView) view.findViewById(R.id.tv_abdomen_dragon);
            
            expire = (TextView) view.findViewById(R.id.expire);
            poison = (TextView) view.findViewById(R.id.poison);
            numb = (TextView) view.findViewById(R.id.numb);
            sleep = (TextView) view.findViewById(R.id.sleep);
            holeTrap = (TextView) view.findViewById(R.id.holeTrap);
            numbTrap = (TextView) view.findViewById(R.id.numbTrap);
            flash = (TextView) view.findViewById(R.id.flash);
            sonicBoom = (TextView) view.findViewById(R.id.sonicBoom);
            meat = (TextView) view.findViewById(R.id.meat);

            remark = (TextView) view.findViewById(R.id.remark);

        }
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_monster_detail, parent, false);
        return new SimpleViewHolder(view);
    }

    private void setColor(TextView tv){

        if(tv.getText().equals("极佳")){
            tv.setTextColor(MenuActivity.RED);
        }else if(tv.getText().equals("有效")){
            tv.setTextColor(MenuActivity.GREEN);
        }else if(tv.getText().equals("一般")){
            tv.setTextColor(MenuActivity.YELLOW);
        }else{
            tv.setTextColor(MenuActivity.GREY);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        SimpleViewHolder svHolder = (SimpleViewHolder)holder;

        svHolder.title.setText(position + 1 + "");
        svHolder.name.setText(mList.get(position).getName());

        svHolder.tv_body_chop.setText(mList.get(position).getBody_chop());
        svHolder.tv_back_chop.setText(mList.get(position).getBack_chop());
        svHolder.tv_tail_chop.setText(mList.get(position).getTail_chop());
        svHolder.tv_head_chop.setText(mList.get(position).getHead_chop());
        svHolder.tv_neck_chop.setText(mList.get(position).getNeck_chop());
        svHolder.tv_foot_chop.setText(mList.get(position).getFoot_chop());
        svHolder.tv_hand_chop.setText(mList.get(position).getHand_chop());
        svHolder.tv_wing_chop.setText(mList.get(position).getWing_chop());
        svHolder.tv_abdomen_chop.setText(mList.get(position).getAbdomen_chop());
        
        svHolder.tv_body_strike.setText(mList.get(position).getBody_strike());
        svHolder.tv_back_strike.setText(mList.get(position).getBack_strike());
        svHolder.tv_tail_strike.setText(mList.get(position).getTail_strike());
        svHolder.tv_head_strike.setText(mList.get(position).getHead_strike());
        svHolder.tv_neck_strike.setText(mList.get(position).getNeck_strike());
        svHolder.tv_foot_strike.setText(mList.get(position).getFoot_strike());
        svHolder.tv_hand_strike.setText(mList.get(position).getHand_strike());
        svHolder.tv_wing_strike.setText(mList.get(position).getWing_strike());
        svHolder.tv_abdomen_strike.setText(mList.get(position).getAbdomen_strike());
        
        svHolder.tv_body_shot.setText(mList.get(position).getBody_shot());
        svHolder.tv_back_shot.setText(mList.get(position).getBack_shot());
        svHolder.tv_tail_shot.setText(mList.get(position).getTail_shot());
        svHolder.tv_head_shot.setText(mList.get(position).getHead_shot());
        svHolder.tv_neck_shot.setText(mList.get(position).getNeck_shot());
        svHolder.tv_foot_shot.setText(mList.get(position).getFoot_shot());
        svHolder.tv_hand_shot.setText(mList.get(position).getHand_shot());
        svHolder.tv_wing_shot.setText(mList.get(position).getWing_shot());
        svHolder.tv_abdomen_shot.setText(mList.get(position).getAbdomen_shot());
        
        svHolder.tv_body_fire.setText(mList.get(position).getBody_fire());
        svHolder.tv_back_fire.setText(mList.get(position).getBack_fire());
        svHolder.tv_tail_fire.setText(mList.get(position).getTail_fire());
        svHolder.tv_head_fire.setText(mList.get(position).getHead_fire());
        svHolder.tv_neck_fire.setText(mList.get(position).getNeck_fire());
        svHolder.tv_foot_fire.setText(mList.get(position).getFoot_fire());
        svHolder.tv_hand_fire.setText(mList.get(position).getHand_fire());
        svHolder.tv_wing_fire.setText(mList.get(position).getWing_fire());
        svHolder.tv_abdomen_fire.setText(mList.get(position).getAbdomen_fire());
        
        svHolder.tv_body_water.setText(mList.get(position).getBody_water());
        svHolder.tv_back_water.setText(mList.get(position).getBack_water());
        svHolder.tv_tail_water.setText(mList.get(position).getTail_water());
        svHolder.tv_head_water.setText(mList.get(position).getHead_water());
        svHolder.tv_neck_water.setText(mList.get(position).getNeck_water());
        svHolder.tv_foot_water.setText(mList.get(position).getFoot_water());
        svHolder.tv_hand_water.setText(mList.get(position).getHand_water());
        svHolder.tv_wing_water.setText(mList.get(position).getWing_water());
        svHolder.tv_abdomen_water.setText(mList.get(position).getAbdomen_water());
        
        svHolder.tv_body_ice.setText(mList.get(position).getBody_ice());
        svHolder.tv_back_ice.setText(mList.get(position).getBack_ice());
        svHolder.tv_tail_ice.setText(mList.get(position).getTail_ice());
        svHolder.tv_head_ice.setText(mList.get(position).getHead_ice());
        svHolder.tv_neck_ice.setText(mList.get(position).getNeck_ice());
        svHolder.tv_foot_ice.setText(mList.get(position).getFoot_ice());
        svHolder.tv_hand_ice.setText(mList.get(position).getHand_ice());
        svHolder.tv_wing_ice.setText(mList.get(position).getWing_ice());
        svHolder.tv_abdomen_ice.setText(mList.get(position).getAbdomen_ice());
        
        svHolder.tv_body_thunder.setText(mList.get(position).getBody_thunder());
        svHolder.tv_back_thunder.setText(mList.get(position).getBack_thunder());
        svHolder.tv_tail_thunder.setText(mList.get(position).getTail_thunder());
        svHolder.tv_head_thunder.setText(mList.get(position).getHead_thunder());
        svHolder.tv_neck_thunder.setText(mList.get(position).getNeck_thunder());
        svHolder.tv_foot_thunder.setText(mList.get(position).getFoot_thunder());
        svHolder.tv_hand_thunder.setText(mList.get(position).getHand_thunder());
        svHolder.tv_wing_thunder.setText(mList.get(position).getWing_thunder());
        svHolder.tv_abdomen_thunder.setText(mList.get(position).getAbdomen_thunder());
        
        svHolder.tv_body_dragon.setText(mList.get(position).getBody_dragon());
        svHolder.tv_back_dragon.setText(mList.get(position).getBack_dragon());
        svHolder.tv_tail_dragon.setText(mList.get(position).getTail_dragon());
        svHolder.tv_head_dragon.setText(mList.get(position).getHead_dragon());
        svHolder.tv_neck_dragon.setText(mList.get(position).getNeck_dragon());
        svHolder.tv_foot_dragon.setText(mList.get(position).getFoot_dragon());
        svHolder.tv_hand_dragon.setText(mList.get(position).getHand_dragon());
        svHolder.tv_wing_dragon.setText(mList.get(position).getWing_dragon());
        svHolder.tv_abdomen_dragon.setText(mList.get(position).getAbdomen_dragon());


        svHolder.expire.setText(mList.get(position).getExpire());
        svHolder.poison.setText(mList.get(position).getPoison());
        svHolder.numb.setText(mList.get(position).getNumb());
        svHolder.sleep.setText(mList.get(position).getSleep());
        svHolder.holeTrap.setText(mList.get(position).getHoleTrap());
        svHolder.numbTrap.setText(mList.get(position).getNumbTrap());
        svHolder.flash.setText(mList.get(position).getFlash());
        svHolder.sonicBoom.setText(mList.get(position).getSonicBoom());
        svHolder.meat.setText(mList.get(position).getMeat());

        svHolder.remark.setText(mList.get(position).getRemark());


        TextView mTV[] = { svHolder.tv_body_chop,svHolder.tv_back_chop,svHolder.tv_tail_chop,svHolder.tv_head_chop,svHolder.tv_neck_chop,svHolder.tv_foot_chop,svHolder.tv_hand_chop,svHolder.tv_wing_chop,svHolder.tv_abdomen_chop,
                svHolder.tv_body_strike,svHolder.tv_back_strike,svHolder.tv_tail_strike,svHolder.tv_head_strike,svHolder.tv_neck_strike,svHolder.tv_foot_strike,svHolder.tv_hand_strike,svHolder.tv_wing_strike,svHolder.tv_abdomen_strike,
                svHolder.tv_body_shot,svHolder.tv_back_shot,svHolder.tv_tail_shot,svHolder.tv_head_shot,svHolder.tv_neck_shot,svHolder.tv_foot_shot,svHolder.tv_hand_shot,svHolder.tv_wing_shot,svHolder.tv_abdomen_shot,
                svHolder.tv_body_fire,svHolder.tv_back_fire,svHolder.tv_tail_fire,svHolder.tv_head_fire,svHolder.tv_neck_fire,svHolder.tv_foot_fire,svHolder.tv_hand_fire,svHolder.tv_wing_fire,svHolder.tv_abdomen_fire,
                svHolder.tv_body_water,svHolder.tv_back_water,svHolder.tv_tail_water,svHolder.tv_head_water,svHolder.tv_neck_water,svHolder.tv_foot_water,svHolder.tv_hand_water,svHolder.tv_wing_water,svHolder.tv_abdomen_water,
                svHolder.tv_body_thunder,svHolder.tv_back_thunder,svHolder.tv_tail_thunder,svHolder.tv_head_thunder,svHolder.tv_neck_thunder,svHolder.tv_foot_thunder,svHolder.tv_hand_thunder,svHolder.tv_wing_thunder,svHolder.tv_abdomen_thunder,
                svHolder.tv_body_ice,svHolder.tv_back_ice,svHolder.tv_tail_ice,svHolder.tv_head_ice,svHolder.tv_neck_ice,svHolder.tv_foot_ice,svHolder.tv_hand_ice,svHolder.tv_wing_ice,svHolder.tv_abdomen_ice,
                svHolder.tv_body_dragon,svHolder.tv_back_dragon,svHolder.tv_tail_dragon,svHolder.tv_head_dragon,svHolder.tv_neck_dragon,svHolder.tv_foot_dragon,svHolder.tv_hand_dragon,svHolder.tv_wing_dragon,svHolder.tv_abdomen_dragon,
                svHolder.expire,svHolder.poison,svHolder.numb,svHolder.sleep,svHolder.holeTrap,svHolder.numbTrap,svHolder.flash,svHolder.sonicBoom,svHolder.meat};

        for (int i = 0; i < mTV.length; i++) {
            setColor(mTV[i]);
        }
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
