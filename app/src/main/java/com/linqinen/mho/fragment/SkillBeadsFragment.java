package com.linqinen.mho.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.linqinen.mho.MenuActivity;
import com.linqinen.mho.R;
import com.linqinen.mho.adapter.MyRecyclerViewAdapter;
import com.linqinen.mho.adapter.RecyclerView_GridView_Adapter;
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.adapter.SkillBeadsListAdapter;
import com.linqinen.mho.bean.SkillBeads;
import com.linqinen.mho.tools.PinyinUtils;
import com.linqinen.mho.tools.SideBar_A_N;
import com.linqinen.mho.tools.SideBar_O_Z;
import com.linqinen.mho.tools.comparator.SkillBeadsPinyinComparator;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.linqinen.mho.adapter.RecyclerView_GridView_Adapter.appearAnimator;
import static com.linqinen.mho.adapter.RecyclerView_GridView_Adapter.hideAnimator;

/**
 * Created by lin on 2017/1/4.
 */

public class SkillBeadsFragment extends BasicFragment  {

    private final String TAG = "SkillBeadsFragment";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.sidebar_A_N)
    SideBar_A_N mSidebarAN;
    @BindView(R.id.sidebar_O_Z)
    SideBar_O_Z mSidebarOZ;
    @BindView(R.id.dialog)
    TextView mDialog;
    @BindView(R.id.recyclerView_gridview)
    RecyclerView mRecyclerViewGridview;
    @BindView(R.id.tv_skillName)
    TextView mTvSkillName;

    private List<String> mStringList;

    private Context mContext;
    private View onCreateView;

    protected SkillBeadsListAdapter mAdapter;
    private List<SkillBeads> mListCache = new ArrayList<>();
    private List<SkillBeads> mList = new ArrayList<>();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            MenuActivity.fragmentPosition = MenuActivity.SKILL_BEADS;
            Log.i(TAG, "setUserVisibleHint: 看到了");
        } else {
            Log.i(TAG, "setUserVisibleHint: 看不到");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onCreateView = inflater.inflate(R.layout.fragment_skill_beads, container, false);
        ButterKnife.bind(this, onCreateView);
        mContext = getActivity();
        initData();
        return onCreateView;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        addSkillBeadsData();
        pinYinSort(mListCache);
        initFirstSpinner();
        initSecondSpinner();
        initRecyclerView();
        initRecyclerView_GridView();
        mSidebarAN.setTextView(mDialog);
        mSidebarOZ.setTextView(mDialog);
        mSidebarAN.setOnTouchingLetterChangedListener(new SideBar_A_N.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) { /* 该字母首次出现的位置*/

                int position = getPositionForSelection(s.charAt(0));
                if (position != -1) {
                    if (position > 3) {
                        mRecyclerView.scrollToPosition(position + 1);
                    } else {
                        mRecyclerView.scrollToPosition(position);
                    }
                }
            }
        });
        mSidebarOZ.setOnTouchingLetterChangedListener(new SideBar_O_Z.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) { /* 该字母首次出现的位置*/

                int position = getPositionForSelection(s.charAt(0));
                if (position != -1) {
                    if (position > 3) {
                        mRecyclerView.scrollToPosition(position + 1);
                    } else {
                        mRecyclerView.scrollToPosition(position);
                    }
                }
            }
        });

    }

    public int getPositionForSelection(int selection) {
        for (int i = 0; i < mList.size(); i++) {
            String Fpinyin = mList.get(i).getFirstPinYin();
            if(Fpinyin != null && Fpinyin.length() > 0){
                char first = Fpinyin.toUpperCase().charAt(0);
                if (first == selection) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void pinYinSort(List<SkillBeads> mList) {
        for (int i = 0; i < mList.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(mList.get(i).getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            SkillBeads mBean = mList.get(i);
            mBean.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                mBean.setFirstPinYin(Fpinyin);
            } else {
                mBean.setFirstPinYin("#");
            }
        }
        Collections.sort(mList, new SkillBeadsPinyinComparator());
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SkillBeadsListAdapter(mContext, mList);
//        mRecyclerView.addItemDecoration(new MyItemDivider(this,R.drawable.ic_launcher));
//        mRecyclerView.setItemAnimator(new SlideScaleInOutRightItemAnimator(mRecyclerView));
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);
//        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRecyclerView_GridView() {
        mRecyclerViewGridview.setLayoutManager(new GridLayoutManager(mContext, 4));
        RecyclerView_GridView_Adapter mGridViewAdapter = new RecyclerView_GridView_Adapter(mContext, mStringList);
        Log.i(TAG, "initRecyclerView: mList：" + mList.toString());
        mRecyclerViewGridview.setAdapter(mGridViewAdapter);
        mGridViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mTvSkillName.setText(mStringList.get(position));
                String skillName = mStringList.get(position).substring(2);
                if (position == 0) {
                    mList.clear();
                    mList.addAll(mListCache);
                    mAdapter.notifyDataSetChanged();
                } else {
                    reInitRecyclerViewNameData(skillName);
                }
//                mRecyclerViewGridview.setVisibility(View.GONE);
                hideAnimator(mRecyclerViewGridview);
                Log.i(TAG, "onItemClick: skillName:"+skillName);
            }
        });
    }

    @OnClick(R.id.tv_skillName)
    public void onClick() {
        if(mRecyclerViewGridview.getVisibility() != View.VISIBLE){
//            mRecyclerViewGridview.setVisibility(View.VISIBLE);
            appearAnimator(mRecyclerViewGridview);
            Log.i(TAG, "onClick: ");
        }
    }

    private void initFirstSpinner() {
//        Spinner spinner = (Spinner) onCreateView.findViewById(R.id.spinner_first_SkillBeads);
        mStringList = new ArrayList<>();
//        final String[] mItems = new String[mListCache.size() + 1];
//        mItems[0] = "全部";
        for (int i = 0; i < mListCache.size(); i++) {
            String name = mListCache.get(i).getName();
            String pinyin = PinyinUtils.getPingYin(name);
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();
            mStringList.add(Fpinyin+"."+name);
        }

        /**java中的中文排序逻辑*/
        Collator coll = Collator.getInstance(Locale.CHINESE);
        Collections.sort(mStringList, coll);

        mStringList.add(0,"全部");

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, mStringList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
////                String skillName = mItems[position];
//                String skillName = list.get(position);
//                Log.i(TAG, "initLeftSpinner: position:" + position);
//                if (position == 0) {
//                    mList.clear();
//                    mList.addAll(mListCache);
//                    mAdapter.notifyDataSetChanged();
//                } else {
//                    reInitRecyclerViewNameData(skillName);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }
    private void initSecondSpinner() {
        Spinner spinner = (Spinner) onCreateView.findViewById(R.id.spinner_second_SkillBeads);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mListCache.size() ; i++) {
            String material[] = {mListCache.get(i).getTv_materialName_1(), mListCache.get(i).getTv_materialName_2(), mListCache.get(i).getTv_materialName_3()};
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item,list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String skillName = mItems[position];
                String skillName = list.get(position);
                Log.i(TAG, "initLeftSpinner: position:" + position);
                if (position == 0) {
                    mList.clear();
                    mList.addAll(mListCache);
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
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {
//            Log.i(TAG, "reInitRecyclerViewNameData: name:"+name);
//            Log.i(TAG, "reInitRecyclerViewNameData: mList.get(i).getName():"+mListCache.get(i).getName());
            if (name.contains(mListCache.get(i).getName())) {
                mList.add(mListCache.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }
    private void reInitRecyclerViewMaterialData(String name) {
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {
            String material[] = {mListCache.get(i).getTv_materialName_1(), mListCache.get(i).getTv_materialName_2(), mListCache.get(i).getTv_materialName_3()};
            for (int j = 0; j < material.length; j++) {
                if(!material[j].equals("") && name.contains(material[j])){
                    mList.add(mListCache.get(i));
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    /**千里眼*/
    private void addSkillBeadsData() {
        mListCache.add(new SkillBeads("飞龙珠",
                "飞龙猎人", "1", "", "",
                "加护", "-1", "","",
                "沙尘晶*2", "", ""));
        mListCache.add(new SkillBeads("古龙珠",
                "古龙猎人", "1", "", "",
                "加护", "-1", "","",
                "钢龙的血*2", "", ""));
        mListCache.add(new SkillBeads("鸟龙珠",
                "鸟龙猎人", "1", "3", "",
                "加护", "-1", "-1","",
                "独耳黑狼鸟的毒棘*2", "淡金色的羽绒*2", ""));
        mListCache.add(new SkillBeads("牙兽珠",
                "牙兽猎人", "1", "3", "",
                "加护", "-1", "-1","",
                "战鬼河狸兽尖爪*2", "剑极狼的凶眼*2", ""));
        mListCache.add(new SkillBeads("甲壳珠",
                "甲壳猎人", "1", "3", "",
                "加护", "-1", "-1","",
                "新鲜的体液*2", "红色的体液*2", ""));
        mListCache.add(new SkillBeads("鱼龙珠",
                "鱼龙猎人", "1", "3", "",
                "加护", "-1", "-1","",
                "坚硬的沙鳞*2", "水龙的毒腺*2", ""));
        mListCache.add(new SkillBeads("耐火珠",
                "火耐性", "1", "制火珠+2", "",
                "水耐性", "-1", "","",
                "粗糙的翼膜*1", "", ""));
        mListCache.add(new SkillBeads("制火珠",
                "火耐性", "2", "", "",
                "", "", "","",
                "赤红的小鳞*2", "", ""));
        mListCache.add(new SkillBeads("耐水珠",
                "水耐性", "1", "制水珠+2", "",
                "雷耐性", "-1", "","",
                "破损的腺体*2", "", ""));
        mListCache.add(new SkillBeads("制水珠",
                "水耐性", "2", "", "",
                "", "", "","",
                "不明的体液*2", "", ""));
        mListCache.add(new SkillBeads("耐雷珠",
                "雷耐性", "1", "制雷珠+2", "",
                "冰耐性", "-1", "","",
                "珍珠色的翼膜*2", "", ""));
        mListCache.add(new SkillBeads("制雷珠",
                "雷耐性", "2", "", "",
                "", "", "","",
                "高级珍珠色的翼膜*2", "", ""));
        mListCache.add(new SkillBeads("耐冰珠",
                "冰耐性", "1", "制冰珠+2", "",
                "火耐性", "-1", "","",
                "冰雪覆盖的毛皮*2", "", ""));
        mListCache.add(new SkillBeads("制冰珠",
                "冰耐性", "2", "", "",
                "", "", "","",
                "冰雪覆盖的厚毛皮*2", "", ""));
        mListCache.add(new SkillBeads("耐龙珠",
                "龙耐性", "1", "", "",
                "毒抵抗", "-1", "","",
                "星雨蝶的虫腹*2", "", ""));

        mListCache.add(new SkillBeads("火炎珠",
                "火属性攻击", "1", "3", "",
                "水属性攻击", "-1", "-1","",
                "怪鸟的翼爪", "青绿色的韧皮", ""));
        mListCache.add(new SkillBeads("流水珠",
                "水属性攻击", "1", "3", "",
                "雷属性攻击", "-1", "-1","",
                "盾蟹的储水袋", "破损的电气袋", ""));
        mListCache.add(new SkillBeads("雷光珠",
                "雷属性攻击", "1", "3", "",
                "冰属性攻击", "-1", "-1","",
                "破损的电气袋", "珍珠色的环状嘴", ""));
        mListCache.add(new SkillBeads("冰结珠",
                "冰属性攻击", "1", "3", "",
                "火属性攻击", "-1", "-1","",
                "冰冷的爪子", "雪狮子的白毛", ""));
        mListCache.add(new SkillBeads("破龙珠",
                "龙属性攻击", "1", "3", "",
                "特殊攻击", "-1", "-1","",
                "摇曳的星光", "剑豪镰蟹的储水袋", ""));
        mListCache.add(new SkillBeads("吸引珠",
                "荷尔蒙", "1", "3", "",
                "攻击", "-1", "-2","",
                "肥美的猪肉*2", "银色的绒毛*1", ""));
        mListCache.add(new SkillBeads("千里珠",
                "千里眼", "2", "", "",
                "", "", "","",
                "星雨蝶体液", "", ""));
        mListCache.add(new SkillBeads("观察珠",
                "观察眼", "2", "", "",
                "", "", "","",
                "厚实紫毒舌", "", ""));
        mListCache.add(new SkillBeads("炮术珠",
                "炮术", "2", "", "",
                "", "", "","",
                "深紫色毒液", "", ""));
        mListCache.add(new SkillBeads("鼓笛珠",
                "笛", "2", "", "",
                "", "", "","",
                "厚重的石壳", "", ""));
        mListCache.add(new SkillBeads("加护珠",
                "加护", "1", "", "",
                "", "", "","",
                "肥美的蟹膏", "", ""));
        mListCache.add(new SkillBeads("早复珠",
                "回复速度", "1", "3", "",
                "食事", "-1", "-1","",
                "长满霉斑的骨头*2", "沙化的骨甲*1", ""));
        mListCache.add(new SkillBeads("根性珠",
                "根性", "1", "3", "",
                "药王", "-1", "-1","",
                "发黄的臭牙*2", "翠绿色的胶皮*1", ""));

        mListCache.add(new SkillBeads("防音珠",
                "听觉保护", "1", "", "4",
                "加护", "-1", "","-2",
                "厚重石壳", "", "雌火龙翼膜"));
        mListCache.add(new SkillBeads("防风珠",
                "风压抵抗", "1", "3", "",
                "气息", "-1", "-1","",
                "青怪鸟的翼爪", "独耳黑狼鸟的毒棘", ""));
        mListCache.add(new SkillBeads("广域珠",
                "广域化", "1", "3", "",
                "气息", "-1", "-1","",
                "异味腺体", "完好骨甲", ""));
        mListCache.add(new SkillBeads("攻击珠",
                "攻击", "1", "3", "5",
                "防御", "-1", "-1","-2",
                "怪鸟尾巴", "松动矿石", "恶臭腺体"));
        mListCache.add(new SkillBeads("防御珠",
                "防御", "1", "3", "5",
                "攻击", "-1", "-1","-2",
                "怪鸟尾巴", "松动矿石", "恶臭腺体"));
        mListCache.add(new SkillBeads("达人珠",
                "达人", "1", "3", "5",
                "龙耐性", "-1", "-1","-2",
                "鬼王之角", "强韧脚蹼", "锋利长趾甲"));
        mListCache.add(new SkillBeads("特攻珠",
                "特殊攻击", "1", "3", "",
                "攻击", "-1", "-1","",
                "眠鸟尾巴", "麻痹腺体", ""));
        mListCache.add(new SkillBeads("重击珠",
                "重击", "1", "", "4",
                "达人", "-1", "","-2",
                "大野猪王尖獠牙", "", "妖艳的星光"));
        mListCache.add(new SkillBeads("痛击珠",
                "痛击", "1", "4", "",
                "体力", "-1", "-2","",
                "沙雷鸟臼齿", "新鲜体液", ""));
        mListCache.add(new SkillBeads("体力珠",
                "体力", "2", "", "",
                "", "", "","",
                "潮湿毛皮", "", ""));
        mListCache.add(new SkillBeads("治愈珠",
                "恢复量", "1", "3", "",
                "回复速度", "-1", "-1","",
                "异味腺体", "星雨蝶体液", ""));
        mListCache.add(new SkillBeads("拔刀珠",
                "拔刀术", "1", "3", "",
                "达人", "-1", "-1","",
                "腐烂的蘑菇", "黑紫色的小鳞", ""));
        mListCache.add(new SkillBeads("剑术珠",
                "剑术", "1", "", "4",
                "匠", "-1", "","-2",
                "锋利的镰", "", "剑豪将军角"));
        mListCache.add(new SkillBeads("KO珠",
                "KO", "1", "", "4",
                "耐力回复", "-1", "","-2",
                "星雨蝶的甲壳", "", "晶岩龙苔背甲"));
        mListCache.add(new SkillBeads("底力珠",
                "底力", "1", "3", "",
                "加护", "-1", "-1","",
                "亮黄的毛", "烈焰的毒液", ""));
        mListCache.add(new SkillBeads("斗魂珠",
                "斗魂", "1", "3", "",
                "防御性能", "-1", "-1","",
                "破损的毒腺", "战鬼河狸兽尖爪", ""));
        mListCache.add(new SkillBeads("看破珠",
                "看破", "1", "3", "",
                "无伤", "-1", "-2","",
                "猩红之眼", "独耳黑紫色小鳞", ""));
        mListCache.add(new SkillBeads("背水珠",
                "孤注一掷", "1", "3", "",
                "加护", "-1", "-2","",
                "深紫色毒液", "变质的体液", ""));
        mListCache.add(new SkillBeads("倒地珠",
                "倒地之力", "1", "", "",
                "达人", "-1", "","",
                "雌火龙的毒液", "", ""));
        mListCache.add(new SkillBeads("刺杀珠",
                "伏击", "1", "", "4",
                "气息", "-1", "","-2",
                "被消化的铁砂", "", "黑狼鸟的毒棘"));
        mListCache.add(new SkillBeads("短缩珠",
                "集中", "1", "", "4",
                "体术", "-1", "","-2",
                "石质的脚趾", "", "一角龙的血液"));
        mListCache.add(new SkillBeads("匠珠",
                "匠", "1", "", "",
                "锋利度", "-1", "","",
                "石质的脚趾", "", "一角龙的血液"));
        mListCache.add(new SkillBeads("斩铁珠",
                "锋利度", "1", "", "4",
                "匠", "-1", "","-2",
                "红色的鸟龙皮", "", "炽热的真红之角"));
        mListCache.add(new SkillBeads("铁壁珠",
                "防御性能", "1", "3", "",
                "体术", "-1", "-1","",
                "完整的大块胶皮", "断刃一角龙的血液", ""));
        mListCache.add(new SkillBeads("强壁珠",
                "防御强化", "1", "3", "",
                "体术", "-1", "-1","",
                "完整的大块紫胶皮", "厚重的晶石壳", ""));
        mListCache.add(new SkillBeads("体术珠",
                "体术", "1", "3", "",
                "耐力回复", "-1", "-1","",
                "粗糙的毛皮", "炽热的折断真红之角", ""));
        mListCache.add(new SkillBeads("研磨珠",
                "研磨师", "2", "", "",
                "", "", "","",
                "盾蟹的腮", "", ""));
        mListCache.add(new SkillBeads("陷阱珠",
                "高速设置", "2", "", "",
                "", "", "","",
                "短小的喙*1", "", ""));
        mListCache.add(new SkillBeads("忍脚珠",
                "气息", "2", "", "",
                "", "", "","",
                "恶臭的粪便*1", "", ""));
        mListCache.add(new SkillBeads("抗棘珠",
                "刺伤", "2", "", "",
                "", "", "","",
                "剑极狼的黑毛*2", "", ""));
        mListCache.add(new SkillBeads("草药珠",
                "药王", "1", "名药珠+2", "",
                "防御", "-1", "","",
                "肥美的蟹黄*2", "", ""));
        mListCache.add(new SkillBeads("名药珠",
                "药王", "2", "", "",
                "", "", "","",
                "蓝速龙王的尾巴*2", "", ""));
        mListCache.add(new SkillBeads("锁气珠",
                "锁气绝", "1", "", "4",
                "KO术", "-1", "","-2",
                "白速龙王的尾巴", "", "魅惑色的环状嘴"));
        mListCache.add(new SkillBeads("装填珠",
                "装填术", "", "1", "",
                "快速装填", "", "-1","",
                "", "星雨蝶的妖角", ""));
        mListCache.add(new SkillBeads("强弹珠",
                "通常弹强化", "1", "", "",
                "通常弹增加", "-1", "","",
                "红速龙王的尾巴", "", ""));
        mListCache.add(new SkillBeads("贯通珠",
                "贯通弹强化", "1", "", "",
                "贯通弹增加", "-1", "","",
                "白色的鸟龙皮", "", ""));
        mListCache.add(new SkillBeads("散弹珠",
                "散弹强化", "1", "", "",
                "散弹增加", "-1", "","",
                "丑陋的臼齿", "", ""));
        mListCache.add(new SkillBeads("加弹珠",
                "通常弹增加", "1", "", "",
                "通常弹强化", "-1", "","",
                "骇人的毒臼齿", "", ""));
        mListCache.add(new SkillBeads("加贯珠",
                "贯通弹增加", "1", "", "",
                "贯通弹强化", "-1", "","",
                "镰蟹的储水袋", "", ""));
        mListCache.add(new SkillBeads("加散珠",
                "散弹增加", "1", "", "",
                "散弹强化", "-1", "","",
                "骇狩蛛的麻痹液", "", ""));
        mListCache.add(new SkillBeads("早填珠",
                "快速装填", "1", "", "",
                "通常弹强化", "-1", "","",
                "岩龙的苔背甲", "", ""));
        mListCache.add(new SkillBeads("早气珠",
                "耐力回复", "1", "3", "",
                "回避性能", "-1", "-1","",
                "将军之角*2", "黯淡的黏液*1", ""));
        mListCache.add(new SkillBeads("无食珠",
                "饥饿感", "2", "5", "",
                "吃货", "", "-1","",
                "黄绿色的鸟龙皮*2", "骇狩蛛的毒液*1", ""));
        mListCache.add(new SkillBeads("毒瓶珠",
                "毒弹增加", "1", "", "",
                "", "", "","",
                "厚实的毒舌", "", ""));
        mListCache.add(new SkillBeads("眠瓶珠",
                "眠弹增加", "1", "", "",
                "", "", "","",
                "眠鸟的翼羽", "", ""));
        mListCache.add(new SkillBeads("麻瓶珠",
                "麻弹增加", "1", "", "",
                "", "", "","",
                "漆黑的趾甲", "", ""));
        mListCache.add(new SkillBeads("加榴珠",
                "榴弹增加", "1", "", "",
                "快速装填", "-1", "","",
                "战鬼河狸兽的坚硬皮", "", ""));
        mListCache.add(new SkillBeads("反动珠",
                "反动", "1", "", "",
                "装填术", "-1", "","",
                "河狸兽的尾甲", "", ""));
        mListCache.add(new SkillBeads("速射珠",
                "速射", "1", "", "",
                "反动", "-1", "","",
                "蓝色的鸟龙皮", "", ""));
        mListCache.add(new SkillBeads("回避珠",
                "回避性能", "1", "3", "",
                "体力", "-1", "-1","",
                "星雨蝶的角", "银眠鸟的尾巴", ""));
        mListCache.add(new SkillBeads("跳跃珠",
                "回避距离", "1", "", "",
                "回避性能", "-1", "-","",
                "珍珠色的脚趾", "", ""));
        mListCache.add(new SkillBeads("早食珠",
                "食事", "1", "", "",
                "回复速度", "-1", "-","",
                "骇狩蛛的妖面壳", "", ""));
        mListCache.add(new SkillBeads("食汉珠",
                "吃货", "2", "", "",
                "", "", "-","",
                "硬化的脚蹼*1", "", ""));
        mListCache.add(new SkillBeads("抗震珠",
                "耐震", "2", "", "",
                "睡眠抵抗", "-1", "","",
                "粗壮的鸟腿骨", "", ""));
        mListCache.add(new SkillBeads("耐震珠",
                "耐震", "1", "抗震珠+2", "",
                "睡眠抵抗", "-1", "","",
                "蓝速龙王的锐爪", "", ""));
        mListCache.add(new SkillBeads("耐暑珠",
                "耐暑", "2", "5", "",
                "", "", "","",
                "沙龙王琼脂", "烈焰的翼膜", ""));
        mListCache.add(new SkillBeads("耐寒珠",
                "耐寒", "2", "5", "",
                "", "", "","",
                "冰冷的头骨", "沙雷鸟的臼齿", ""));
        mListCache.add(new SkillBeads("耐粘珠",
                "耐泥雪", "1", "", "",
                "睡眠抵抗", "-1", "","",
                "肥沃的泥巴", " ", ""));
    }
}
