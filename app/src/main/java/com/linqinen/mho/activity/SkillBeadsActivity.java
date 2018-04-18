package com.linqinen.mho.activity;

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
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.adapter.SkillBeadsListAdapter;
import com.linqinen.mho.bean.SkillBeads;
import com.linqinen.mho.tools.PinyinUtils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkillBeadsActivity extends AppCompatActivity {

    private final String TAG = "SkillBeadsActivity";

    @BindView(R.id.recyclerView_SkillBeads)
    RecyclerView mRecyclerView;

    protected SkillBeadsListAdapter mAdapter;
    private List<SkillBeads> mSkillBeadsListCache = new ArrayList<>();
    private List<SkillBeads> mSkillBeadsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_beads);
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
        addSkillBeadsData();
        initFirstSpinner();
        initSecondSpinner();
        initRecyclerView();

    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SkillBeadsListAdapter(this, mSkillBeadsList);
//        mRecyclerView.addItemDecoration(new MyItemDivider(this,R.drawable.ic_launcher));
//        mRecyclerView.setItemAnimator(new SlideScaleInOutRightItemAnimator(mRecyclerView));
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);
//        mRecyclerView.setAdapter(mAdapter);
    }

    private void initFirstSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_first_SkillBeads);

        final List<String> list = new ArrayList<>();
//        final String[] mItems = new String[mSkillBeadsListCache.size() + 1];
//        mItems[0] = "全部";
        for (int i = 0; i < mSkillBeadsListCache.size(); i++) {
           String name = mSkillBeadsListCache.get(i).getName();
            String pinyin = PinyinUtils.getPingYin(name);
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();
            list.add(Fpinyin+"."+name);
        }

        /**java中的中文排序逻辑*/
        Collator coll = Collator.getInstance(Locale.CHINESE);
        Collections.sort(list, coll);

        list.add(0,"全部");

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String skillName = mItems[position];
                String skillName = list.get(position);
                Log.i(TAG, "initLeftSpinner: position:" + position);
                if (position == 0) {
                    mSkillBeadsList.clear();
                    mSkillBeadsList.addAll(mSkillBeadsListCache);
                    mAdapter.notifyDataSetChanged();
                } else {
                    reInitRecyclerViewNameData(skillName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void initSecondSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_second_SkillBeads);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mSkillBeadsListCache.size() ; i++) {
            String material[] = {mSkillBeadsListCache.get(i).getTv_materialName_1(),mSkillBeadsListCache.get(i).getTv_materialName_2(),mSkillBeadsListCache.get(i).getTv_materialName_3()};
            for (int j = 0; j < material.length; j++) {
                if(!material[j].equals("") && !list.toString().contains(material[j])){
                    String pinyin = PinyinUtils.getPingYin(material[j]);
                    String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                    list.add(Fpinyin+"."+material[j]);
                }
            }
        }

        /**java中的中文排序逻辑*/
        Collator coll = Collator.getInstance(Locale.CHINESE);
        Collections.sort(list, coll);

        list.add(0,"全部");
        Log.i(TAG, "initLeftSpinner: list:"+list.toString());

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String skillName = mItems[position];
                String skillName = list.get(position);
                Log.i(TAG, "initLeftSpinner: position:" + position);
                if (position == 0) {
                    mSkillBeadsList.clear();
                    mSkillBeadsList.addAll(mSkillBeadsListCache);
                    mAdapter.notifyDataSetChanged();
                } else {
                    reInitRecyclerViewMaterialData(skillName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void reInitRecyclerViewNameData(String name) {
        mSkillBeadsList.clear();
        for (int i = 0; i < mSkillBeadsListCache.size(); i++) {
//            Log.i(TAG, "reInitRecyclerViewNameData: name:"+name);
//            Log.i(TAG, "reInitRecyclerViewNameData: mSkillBeadsList.get(i).getName():"+mSkillBeadsListCache.get(i).getName());
            if (name.contains(mSkillBeadsListCache.get(i).getName())) {
                mSkillBeadsList.add(mSkillBeadsListCache.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }
    private void reInitRecyclerViewMaterialData(String name) {
        mSkillBeadsList.clear();
        for (int i = 0; i < mSkillBeadsListCache.size(); i++) {
            String material[] = {mSkillBeadsListCache.get(i).getTv_materialName_1(),mSkillBeadsListCache.get(i).getTv_materialName_2(),mSkillBeadsListCache.get(i).getTv_materialName_3()};
            for (int j = 0; j < material.length; j++) {
                if(!material[j].equals("") && name.contains(material[j])){
                    mSkillBeadsList.add(mSkillBeadsListCache.get(i));
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    /**千里眼*/
    private void addSkillBeadsData() {
        mSkillBeadsListCache.add(new SkillBeads("千里珠",
                "千里眼", "2", "", "",
                "", "", "","",
                 "星雨蝶体液", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("观察珠",
                "观察眼", "2", "", "",
                "", "", "","",
                 "厚实紫毒舌", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("炮术珠",
                "炮术", "2", "", "",
                "", "", "","",
                 "深紫色毒液", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("鼓笛珠",
                "笛", "2", "", "",
                "", "", "","",
                 "厚重的石壳", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("防音珠",
                "听觉保护", "1", "", "4",
                "加护", "-1", "","-2",
                 "厚重石壳", "", "雌火龙翼膜"));
        mSkillBeadsListCache.add(new SkillBeads("防风珠",
                "风压抵抗", "1", "3", "",
                "气息", "-1", "-1","",
                 "青怪鸟的翼爪", "独耳黑狼鸟的毒棘", ""));
        mSkillBeadsListCache.add(new SkillBeads("广域珠",
                "广域化", "1", "3", "",
                "气息", "-1", "-1","",
                 "异味腺体", "完好骨甲", ""));
        mSkillBeadsListCache.add(new SkillBeads("攻击珠",
                "攻击", "1", "3", "5",
                "防御", "-1", "-1","-2",
                 "怪鸟尾巴", "松动矿石", "恶臭腺体"));
        mSkillBeadsListCache.add(new SkillBeads("防御珠",
                "防御", "1", "3", "5",
                "攻击", "-1", "-1","-2",
                 "怪鸟尾巴", "松动矿石", "恶臭腺体"));
        mSkillBeadsListCache.add(new SkillBeads("达人珠",
                "达人", "1", "3", "5",
                "龙耐性", "-1", "-1","-2",
                 "鬼王之角", "强韧脚蹼", "锋利长趾甲"));
        mSkillBeadsListCache.add(new SkillBeads("特攻珠",
                "特殊攻击", "1", "3", "",
                "攻击", "-1", "-1","",
                 "眠鸟尾巴", "麻痹腺体", ""));
        mSkillBeadsListCache.add(new SkillBeads("重击珠",
                "重击", "1", "", "4",
                "达人", "-1", "","-2",
                 "大野猪王尖獠牙", "", "妖艳的星光"));
        mSkillBeadsListCache.add(new SkillBeads("痛击珠",
                "痛击", "1", "4", "",
                "体力", "-1", "-2","",
                 "沙雷鸟臼齿", "新鲜体液", ""));
        mSkillBeadsListCache.add(new SkillBeads("体力珠",
                "体力", "2", "", "",
                "", "", "","",
                 "潮湿毛皮", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("治愈珠",
                "恢复量", "1", "3", "",
                "回复速度", "-1", "-1","",
                 "异味腺体", "星雨蝶体液", ""));
        mSkillBeadsListCache.add(new SkillBeads("拔刀珠",
                "拔刀术", "1", "3", "",
                "达人", "-1", "-1","",
                 "腐烂的蘑菇", "黑紫色的小鳞", ""));
        mSkillBeadsListCache.add(new SkillBeads("剑术珠",
                "剑术", "1", "", "4",
                "匠", "-1", "","-2",
                 "锋利的镰", "", "剑豪将军角"));
        mSkillBeadsListCache.add(new SkillBeads("KO珠",
                "KO", "1", "", "4",
                "耐力回复", "-1", "","-2",
                 "星雨蝶的甲壳", "", "晶岩龙苔背甲"));
        mSkillBeadsListCache.add(new SkillBeads("底力珠",
                "底力", "1", "3", "",
                "加护", "-1", "-1","",
                 "亮黄的毛", "烈焰的毒液", ""));
        mSkillBeadsListCache.add(new SkillBeads("斗魂珠",
                "斗魂", "1", "3", "",
                "防御性能", "-1", "-1","",
                 "破损的毒腺", "战鬼河狸兽尖爪", ""));
        mSkillBeadsListCache.add(new SkillBeads("看破珠",
                "看破", "1", "3", "",
                "无伤", "-1", "-2","",
                 "猩红之眼", "独耳黑紫色小鳞", ""));
        mSkillBeadsListCache.add(new SkillBeads("背水珠",
                "孤注一掷", "1", "3", "",
                "加护", "-1", "-2","",
                 "深紫色毒液", "变质的体液", ""));
        mSkillBeadsListCache.add(new SkillBeads("倒地珠",
                "倒地之力", "1", "", "",
                "达人", "-1", "","",
                 "雌火龙的毒液", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("刺杀珠",
                "伏击", "1", "", "4",
                "气息", "-1", "","-2",
                 "被消化的铁砂", "", "黑狼鸟的毒棘"));
        mSkillBeadsListCache.add(new SkillBeads("短缩珠",
                "集中", "1", "", "4",
                "体术", "-1", "","-2",
                 "石质的脚趾", "", "一角龙的血液"));
        mSkillBeadsListCache.add(new SkillBeads("匠珠",
                "匠", "1", "", "",
                "锋利度", "-1", "","",
                 "石质的脚趾", "", "一角龙的血液"));
        mSkillBeadsListCache.add(new SkillBeads("斩铁珠",
                "锋利度", "1", "", "4",
                "匠", "-1", "","-2",
                 "红色的鸟龙皮", "", "炽热的真红之角"));
        mSkillBeadsListCache.add(new SkillBeads("铁壁珠",
                "防御性能", "1", "3", "",
                "体术", "-1", "-1","",
                 "完整的大块胶皮", "断刃一角龙的血液", ""));
        mSkillBeadsListCache.add(new SkillBeads("强壁珠",
                "防御强化", "1", "3", "",
                "体术", "-1", "-1","",
                 "完整的大块紫胶皮", "厚重的晶石壳", ""));
        mSkillBeadsListCache.add(new SkillBeads("体术珠",
                "体术", "1", "3", "",
                "耐力回复", "-1", "-1","",
                 "粗糙的毛皮", "炽热的折断真红之角", ""));
        mSkillBeadsListCache.add(new SkillBeads("研磨珠",
                "研磨师", "2", "", "",
                "", "", "","",
                 "盾蟹的腮", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("锁气珠",
                "锁气绝", "1", "", "4",
                "KO术", "-1", "","-2",
                 "白速龙王的尾巴", "", "魅惑色的环状嘴"));
        mSkillBeadsListCache.add(new SkillBeads("装填珠",
                "装填术", "", "1", "",
                "快速装填", "", "-1","",
                 "", "星雨蝶的妖角", ""));
        mSkillBeadsListCache.add(new SkillBeads("强弹珠",
                "通常弹强化", "1", "", "",
                "通常弹增加", "-1", "","",
                 "红速龙王的尾巴", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("贯通珠",
                "贯通弹强化", "1", "", "",
                "贯通弹增加", "-1", "","",
                 "白色的鸟龙皮", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("散弹珠",
                "散弹强化", "1", "", "",
                "散弹增加", "-1", "","",
                 "丑陋的臼齿", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("加弹珠",
                "通常弹增加", "1", "", "",
                "通常弹强化", "-1", "","",
                 "骇人的毒臼齿", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("加贯珠",
                "贯通弹增加", "1", "", "",
                "贯通弹强化", "-1", "","",
                 "镰蟹的储水袋", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("加散珠",
                "散弹增加", "1", "", "",
                "散弹强化", "-1", "","",
                 "骇狩蛛的麻痹液", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("早填珠",
                "快速装填", "1", "", "",
                "通常弹强化", "-1", "","",
                 "岩龙的苔背甲", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("毒瓶珠",
                "毒弹增加", "1", "", "",
                "", "", "","",
                 "厚实的毒舌", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("眠瓶珠",
                "眠弹增加", "1", "", "",
                "", "", "","",
                 "眠鸟的翼羽", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("麻瓶珠",
                "麻弹增加", "1", "", "",
                "", "", "","",
                 "漆黑的趾甲", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("加榴珠",
                "榴弹增加", "1", "", "",
                "快速装填", "-1", "","",
                 "战鬼河狸兽的坚硬皮", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("反动珠",
                "反动", "1", "", "",
                "装填术", "-1", "","",
                 "河狸兽的尾甲", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("速射珠",
                "速射", "1", "", "",
                "反动", "-1", "","",
                 "蓝色的鸟龙皮", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("回避珠",
                "回避性能", "1", "3", "",
                "体力", "-1", "-1","",
                 "星雨蝶的角", "银眠鸟的尾巴", ""));
        mSkillBeadsListCache.add(new SkillBeads("跳跃珠",
                "回避距离", "1", "", "",
                "回避性能", "-1", "-","",
                 "珍珠色的脚趾", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("早食珠",
                "食事", "1", "", "",
                "回复速度", "-1", "-","",
                 "骇狩蛛的妖面壳", "", ""));
        mSkillBeadsListCache.add(new SkillBeads("抗震珠",
                "耐震", "1", "2", "",
                "睡眠抵抗", "-1", "-1","",
                 "蓝速龙王的锐爪", "粗壮的鸟腿骨", ""));
        mSkillBeadsListCache.add(new SkillBeads("耐暑珠",
                "耐暑", "2", "5", "",
                "", "", "","",
                 "沙龙王琼脂", "烈焰的翼膜", ""));
        mSkillBeadsListCache.add(new SkillBeads("耐寒珠",
                "耐寒", "2", "5", "",
                "", "", "","",
                 "冰冷的头骨", "沙雷鸟的臼齿", ""));
        mSkillBeadsListCache.add(new SkillBeads("耐粘珠",
                "耐泥雪", "1", "", "",
                "睡眠抵抗", "-1", "","",
                 "肥沃的泥巴", " ", ""));
    }
}
