package com.linqinen.mho.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.linqinen.mho.R;
import com.linqinen.mho.adapter.HuntingHornAttributeAdapter;
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.bean.HuntingHornAttribute;
import com.linqinen.mho.tools.PinyinUtils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HuntingHornActivity extends AppCompatActivity {

    private final String TAG = "HuntingHornActivity";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<HuntingHornAttribute> mListCache = new ArrayList<>();
    private List<HuntingHornAttribute> mList = new ArrayList<>();

    private HuntingHornAttributeAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hunting_horn);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mContext = this;
        addRecyclerViewData();

        initFirstSpinner();
        initRecyclerView();


    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new HuntingHornAttributeAdapter(mContext, mList);
        Log.i(TAG, "initRecyclerView: mList：" + mList.toString());
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);

    }


    private void initFirstSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_first);

        final List<String> list = new ArrayList<>();
//        final List<String> skillNameList = new ArrayList<>();
        for (int i = 0; i < mListCache.size(); i++) {
            String skillName[] = {mListCache.get(i).getSkill_1(), mListCache.get(i).getSkill_2(), mListCache.get(i).getSkill_3(), mListCache.get(i).getSkill_4(), mListCache.get(i).getSkill_5()};
            for (int j = 0; j < skillName.length; j++) {

                String name = skillName[j];

                if (!list.toString().contains(name)) {
                    String pinyin = PinyinUtils.getPingYin(name);
                    String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                    list.add(Fpinyin + "." + name);
                }
            }
        }

        /**java中的中文排序逻辑*/
        Collator coll = Collator.getInstance(Locale.CHINESE);
        Collections.sort(list, coll);

        list.add(0, "全部");

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, list);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.adapters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String skillName = list.get(position);
//                Log.i(TAG, "onItemSelected: skillName:"+skillName);
                if (position == 0) {
                    mList.clear();
                    mList.addAll(mListCache);
                    mAdapter.notifyDataSetChanged();
                } else {
                    reInitRecyclerViewData(skillName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void reInitRecyclerViewData(String skillName) {
//        Log.i(TAG, "reInitRecyclerViewData: skillName:"+skillName);
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {
            if (mListCache.get(i).getSkill_1().length() > 0 && skillName.contains(mListCache.get(i).getSkill_1())) {
//                Log.i(TAG, "reInitRecyclerViewData111: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkill_2().length() > 0 && skillName.contains(mListCache.get(i).getSkill_2())) {
//                Log.i(TAG, "reInitRecyclerViewData222: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkill_3().length() > 0 && skillName.contains(mListCache.get(i).getSkill_3())) {
//                Log.i(TAG, "reInitRecyclerViewData333: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkill_4().length() > 0 && skillName.contains(mListCache.get(i).getSkill_4())) {
//                Log.i(TAG, "reInitRecyclerViewData444: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkill_5().length() > 0 && skillName.contains(mListCache.get(i).getSkill_5())) {
//                Log.i(TAG, "reInitRecyclerViewData5555: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private final String SELF_REINFORCEMENT = "自我强化";
    private final String HP_UP_BIG = "体力回复大";
    private final String HP_UP_SMALL = "体力回复大";
    private final String HP_UP_DETOXIFY = "体力回复中/解毒";
    private final String HP_UP_CRIT = "会心率/体力回复小";
    
    private final String DEFENSE_UP_SMALL = "防御力强化小";
    private final String DEFENSE_UP_BIG = "防御力强化大";
    
    private final String ATTACK_UP_SMALL = "攻击力强化小";
    private final String ATTACK_UP_BIG = "攻击力强化大";

    private final String PROTECT_HEARING_SMALL = "听觉保护小";
    private final String PROTECT_HEARING_BIG = "听觉保护大";

    private final String HP_REGENERATION_BIG = "回复速度大";

    private final String ATTRIBUTE_ATTACK = "属性攻击强化";
    private final String ATTRIBUTE_RESISTANCE = "全属性耐性强化";
    private final String ICE_WATER_ATTRIBUTE_RESISTANCE_SMALL = "冰水属性防御强化小";
    private final String DRAGON_ATTRIBUTE_RESISTANCE_BIG = "龙属性防御强化大";
    private final String FIRE_THUNDER_ATTRIBUTE_RESISTANCE_BIG = "火/雷防御强化大";
    private final String CLAIRVOYANT_COLD_ATTACK = "千里眼/寒气无效";
    private final String ANOMALY_INVALID = "全状态异常无效";
    private final String ABNORMAL_ATTACK = "状态异常攻击强化";
    private final String ENDURANCE_DOWN_INVALID_BIG = "耐力减少无效大";
    private final String ENDURANCE_DOWN_INVALID_SMALL = "耐力减少无效小";
    private final String RECOVERY_FRAMES_INVALID = "硬直无效";
    private final String MUD_FROZEN_INVALID = "泥/雪无效";
    private final String WIND_INVALID = "风压完全无效";
    private final String EARTHQUAKE_NUMB_INVALID = "耐震/麻痹无效";
    private final String ADD_TIME = "全旋律效果延长";
    private final String SONIC_BOMB = "高周波";

    private void addRecyclerViewData() {
        mListCache.add(new HuntingHornAttribute("苍蓝之雷鸣(麒麟)", 120, "532，雷134",
                SELF_REINFORCEMENT,
                HP_UP_SMALL,
                ATTRIBUTE_RESISTANCE,
                ATTRIBUTE_ATTACK,
                RECOVERY_FRAMES_INVALID
                ));
        mListCache.add(new HuntingHornAttribute("狂乱靡音(恐暴龙)", 120, "539，会心-7，龙120",
                SELF_REINFORCEMENT,
                ENDURANCE_DOWN_INVALID_SMALL,
                PROTECT_HEARING_BIG,
                ATTACK_UP_BIG,
                ADD_TIME
                ));
        mListCache.add(new HuntingHornAttribute("失落之笛(冰牙龙)", 115, "_，会心15，冰102",
                SELF_REINFORCEMENT,
                MUD_FROZEN_INVALID,
                PROTECT_HEARING_SMALL,
                ATTACK_UP_BIG,
                ADD_TIME
                ));
        mListCache.add(new HuntingHornAttribute("星罗梵音(灭星龙)", 115, "_，会心10，龙105",
                SELF_REINFORCEMENT,
                DEFENSE_UP_SMALL,
                HP_UP_BIG,
                ATTRIBUTE_ATTACK,
                ANOMALY_INVALID
                ));
        mListCache.add(new HuntingHornAttribute("王琴轰雷(雷狼龙)", 110, "_，会心10，雷95",
                SELF_REINFORCEMENT,
                ATTACK_UP_BIG,
                HP_REGENERATION_BIG,
                DEFENSE_UP_BIG,
                ADD_TIME
                ));
        mListCache.add(new HuntingHornAttribute("雅乐鹤翼(武镰蟹)", 110, "_，防20，会心15，毒110",
                SELF_REINFORCEMENT,
                HP_UP_DETOXIFY,
                ABNORMAL_ATTACK,
                ENDURANCE_DOWN_INVALID_BIG,
                ADD_TIME));
        mListCache.add(new HuntingHornAttribute("真振聋扩音鼓(轰龙)", 100, "399，-13",
                SELF_REINFORCEMENT,
                MUD_FROZEN_INVALID,
                PROTECT_HEARING_SMALL,
                ATTACK_UP_BIG,
                RECOVERY_FRAMES_INVALID));
        mListCache.add(new HuntingHornAttribute("洛克库斯II改(砂岩龙)", 100, "401，防御10，会心0",
                SELF_REINFORCEMENT,
                HP_UP_BIG,
                HP_REGENERATION_BIG,
                ANOMALY_INVALID,
                ADD_TIME));
        mListCache.add(new HuntingHornAttribute("彻响三河(葵盾蟹)", 100, "385，-15，水攻86",
                SELF_REINFORCEMENT,
                ENDURANCE_DOWN_INVALID_BIG,
                WIND_INVALID,
                DRAGON_ATTRIBUTE_RESISTANCE_BIG,
                SONIC_BOMB));
        mListCache.add(new HuntingHornAttribute("湍流龙艇极(水龙)", 100, "364，-0，水攻92",
                SELF_REINFORCEMENT,
                ICE_WATER_ATTRIBUTE_RESISTANCE_SMALL,
                ENDURANCE_DOWN_INVALID_BIG,
                ANOMALY_INVALID,
                ABNORMAL_ATTACK));
        mListCache.add(new HuntingHornAttribute("晶格木琴俄里翁(灰晶蝎)", 100, "320，麻100",
                SELF_REINFORCEMENT,
                HP_REGENERATION_BIG,
                ABNORMAL_ATTACK,
                EARTHQUAKE_NUMB_INVALID,
                ATTRIBUTE_RESISTANCE));
        mListCache.add(new HuntingHornAttribute("森罗宝追影峰(剑刹狼)", 100, "333，-10，麻120",
                SELF_REINFORCEMENT,
                HP_UP_BIG,
                HP_REGENERATION_BIG,
                ANOMALY_INVALID,
                ADD_TIME));
        mListCache.add(new HuntingHornAttribute("炫音颂曲纯爱(锈钢龙)", 100, "358，7，冰攻80",
                SELF_REINFORCEMENT,
                ATTACK_UP_SMALL,
                ENDURANCE_DOWN_INVALID_BIG,
                WIND_INVALID,
                ADD_TIME));
        mListCache.add(new HuntingHornAttribute("重铠笛火工(铠龙)", 100, "385，-15，火攻86",
                SELF_REINFORCEMENT,
                CLAIRVOYANT_COLD_ATTACK,
                MUD_FROZEN_INVALID,
                EARTHQUAKE_NUMB_INVALID,
                RECOVERY_FRAMES_INVALID));
        mListCache.add(new HuntingHornAttribute("白垩鼓爪(白一角龙)", 100, "428，-15",
                SELF_REINFORCEMENT,
                DEFENSE_UP_SMALL,
                ATTACK_UP_SMALL,
                PROTECT_HEARING_BIG,
                ADD_TIME));
        mListCache.add(new HuntingHornAttribute("电电太鼓(金狮子)", 100, "364，雷攻81",
                SELF_REINFORCEMENT,
                CLAIRVOYANT_COLD_ATTACK,
                ABNORMAL_ATTACK,
                ANOMALY_INVALID,
                ADD_TIME));
        mListCache.add(new HuntingHornAttribute("漏液弦歌金极(金眠鸟)", 100, "323，眠100",
                SELF_REINFORCEMENT,
                HP_UP_DETOXIFY,
                ENDURANCE_DOWN_INVALID_BIG,
                WIND_INVALID,
                ABNORMAL_ATTACK));
        mListCache.add(new HuntingHornAttribute("魔童鼓兽葬曲(炎狮子)", 100, "344，15，火攻76",
                SELF_REINFORCEMENT,
                FIRE_THUNDER_ATTRIBUTE_RESISTANCE_BIG,
                ATTACK_UP_BIG,
                HP_UP_CRIT,
                ATTRIBUTE_ATTACK));
        mListCache.add(new HuntingHornAttribute("魔童鼓兽葬曲(炎狮子)", 100, "344，15，火攻76",
                SELF_REINFORCEMENT,
                FIRE_THUNDER_ATTRIBUTE_RESISTANCE_BIG,
                ATTACK_UP_BIG,
                HP_UP_CRIT,
                ATTRIBUTE_ATTACK));
        mListCache.add(new HuntingHornAttribute("真亢音笳(独耳黑狼鸟)", 100, "369，-7，火攻82",
                SELF_REINFORCEMENT,
                FIRE_THUNDER_ATTRIBUTE_RESISTANCE_BIG,
                ATTACK_UP_BIG,
                PROTECT_HEARING_SMALL,
                SONIC_BOMB));
        mListCache.add(new HuntingHornAttribute("樱花竖笛极(樱火龙)", 100, "326，毒100",
                SELF_REINFORCEMENT,
                ENDURANCE_DOWN_INVALID_SMALL,
                HP_UP_BIG,
                WIND_INVALID,
                SONIC_BOMB));
        mListCache.add(new HuntingHornAttribute("异虫风笛拉巴伊(霞龙)", 100, "328，-7，毒120",
                SELF_REINFORCEMENT,
                ATTACK_UP_SMALL,
                HP_UP_BIG,
                WIND_INVALID,
                RECOVERY_FRAMES_INVALID));
        mListCache.add(new HuntingHornAttribute("铓罗毁神灭绝(祸星龙)", 100, "353，10，龙攻79",
                SELF_REINFORCEMENT,
                HP_REGENERATION_BIG,
                DRAGON_ATTRIBUTE_RESISTANCE_BIG,
                HP_UP_CRIT,
                ABNORMAL_ATTACK));
        mListCache.add(new HuntingHornAttribute("灾呦之锁(祸星龙)", 100, "380，-13，龙攻84",
                SELF_REINFORCEMENT,
                HP_REGENERATION_BIG,
                PROTECT_HEARING_SMALL,
                ATTRIBUTE_ATTACK,
                ATTRIBUTE_RESISTANCE));

    }

}
