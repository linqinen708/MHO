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
import android.widget.TextView;

import com.linqinen.mho.R;
import com.linqinen.mho.adapter.GuardStoneListAdapter;
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.bean.GuardStone;
import com.linqinen.mho.tools.comparator.GuardStonePinyinComparator;
import com.linqinen.mho.tools.PinyinUtils;
import com.linqinen.mho.tools.SideBar_A_N;
import com.linqinen.mho.tools.SideBar_O_Z;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuardStoneActivity extends AppCompatActivity {

    private final String TAG = "GuardStoneActivity";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.sidebar_A_N)
    SideBar_A_N mSidebarAN;
    @BindView(R.id.sidebar_O_Z)
    SideBar_O_Z mSidebarOZ;
    @BindView(R.id.dialog)
    TextView mDialog;

    private GuardStoneListAdapter mAdapter;
    private List<GuardStone> mListCache = new ArrayList<>();
    private List<GuardStone> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_stone);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        addRecyclerViewData();
        pinYinSort(mListCache);
        initFirstSpinner();
        initSecondSpinner();
        initRecyclerView();

        mSidebarAN.setTextView(mDialog);
        mSidebarOZ.setTextView(mDialog);
        mSidebarAN.setOnTouchingLetterChangedListener(new SideBar_A_N.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) { /* 该字母首次出现的位置*/

                int position = getPositionForSelection(s.charAt(0));
                if (position != -1)
                    mRecyclerView.scrollToPosition(position);
            }
        });
        mSidebarOZ.setOnTouchingLetterChangedListener(new SideBar_O_Z.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) { /* 该字母首次出现的位置*/

                int position = getPositionForSelection(s.charAt(0));
                if (position != -1)
                    mRecyclerView.scrollToPosition(position);
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

    private void pinYinSort(List<GuardStone> mList) {
        for (int i = 0; i < mList.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(mList.get(i).getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            GuardStone mBean = mList.get(i);
            mBean.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                mBean.setFirstPinYin(Fpinyin);
            } else {
                mBean.setFirstPinYin("#");
            }
        }
        Collections.sort(mList, new GuardStonePinyinComparator());
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new GuardStoneListAdapter(this, mList);
//        mRecyclerView.addItemDecoration(new MyItemDivider(this,R.drawable.ic_launcher));
//        mRecyclerView.setItemAnimator(new SlideScaleInOutRightItemAnimator(mRecyclerView));
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);
//        mRecyclerView.setAdapter(mAdapter);
    }

    private void initFirstSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_first);

        final String[] mItems = new String[mListCache.size() + 1];
        mItems[0] = "全部";
        for (int i = 1; i < mListCache.size() + 1; i++) {
            mItems[i] = mListCache.get(i - 1).getName();
        }
        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String skillName = mItems[position];
                Log.i(TAG, "initLeftSpinner: position:" + position);
                if (position == 0) {
                    mList.clear();
                    mList.addAll(mListCache);
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
        Spinner spinner = (Spinner) findViewById(R.id.spinner_second);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mListCache.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(mListCache.get(i).getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();
            list.add(Fpinyin+"."+mListCache.get(i).getName());
        }

        /**java中的中文排序逻辑*/
        Collator coll = Collator.getInstance(Locale.CHINESE);
        Collections.sort(list, coll);

        list.add(0, "全部");
        Log.i(TAG, "initLeftSpinner: list:" + list.toString());

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
                    mList.clear();
                    mList.addAll(mListCache);
                    mAdapter.notifyDataSetChanged();
                } else {
                    reInitRecyclerViewSpecialMaterialNameData(skillName);
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
            if (mListCache.get(i).getName().equals(name)) {
                mList.add(mListCache.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void reInitRecyclerViewSpecialMaterialNameData(String name) {
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {

            if (name.contains(mListCache.get(i).getName())) {
                mList.add(mListCache.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }


    private void addRecyclerViewData() {
        mListCache.add(new GuardStone("攻击",
                "7","","",
                "10","","",
                "12","","",
                "14","","5-1+3-2"));
        mListCache.add(new GuardStone("防御",
                "7","","",
                "10","","",
                "12","","",
                "10","",""));
        mListCache.add(new GuardStone("达人",
                "7","","",
                "10","","",
                "12","","",
                "14","",""));
        mListCache.add(new GuardStone("特殊攻击",
                "4","","",
                "7","","",
                "7","","",
                "7","",""));
        mListCache.add(new GuardStone("食事",
                "4","","",
                "6","","",
                "6","","",
                "5","",""));
        mListCache.add(new GuardStone("千里眼",
                "10","","",
                "12","","",
                "12","","",
                "14","","7-2+3-3+1+2"));
        mListCache.add(new GuardStone("观察眼",
                "10","","",
                "12","","",
                "12","","",
                "12","",""));
        mListCache.add(new GuardStone("体力",
                "10","","",
                "12","","",
                "12","","",
                "11","","9-3+3"));
        mListCache.add(new GuardStone("回复速度",
                "7","","",
                "10","","",
                "11","","",
                "12","",""));
        mListCache.add(new GuardStone("恢复量",
                "","","",
                "","","",
                "","","",
                "14","",""));
        mListCache.add(new GuardStone("麻痹抵抗",
                "7","","",
                "10","","",
                "0","","",
                "4","目前发现4","估计有10"));
        mListCache.add(new GuardStone("气绝抵抗",
                "10","","",
                "12","","",
                "12","","",
                "14","",""));
        mListCache.add(new GuardStone("睡眠抵抗",
                "7","","",
                "10","","",
                "0","","",
                "10","",""));
        mListCache.add(new GuardStone("毒性抵抗",
                "7","","",
                "10","","",
                "0","","",
                "10","",""));
        mListCache.add(new GuardStone("火耐性",
                "","","",
                "","","",
                "","","",
                "14","","8+1"));
        mListCache.add(new GuardStone("荷尔蒙",
                "4","","",
                "7","","",
                "7","","",
                "8","",""));
        mListCache.add(new GuardStone("广域化",
                "10","","",
                "12","","",
                "12","","",
                "12","","7-1+1"));
        mListCache.add(new GuardStone("锋利度",
                "2","","",
                "5","","",
                "5","","",
                "6","","3+1-2-2"));
        mListCache.add(new GuardStone("加护",
                "7","","",
                "8","","",
                "9","","",
                "9","+2:9555,15555",""));
        mListCache.add(new GuardStone("集中",
                "2","","",
                "5","","",
                "5","","",
                "6","","3+1-2+1-2"));
        mListCache.add(new GuardStone("拔刀会心",
                "0","","",
                "4","","",
                "5","","",
                "6","+3:2W","3+1-1+2"));
        mListCache.add(new GuardStone("重击",
                "2","","",
                "5","","",
                "5","","",
                "6","",""));
        mListCache.add(new GuardStone("耐震",
                "10","","",
                "0","","",
                "0","","",
                "10","","5+1-2+1+1-2,2-1"));
        mListCache.add(new GuardStone("底力",
                "4","","",
                "6","","",
                "6","","",
                "6","",""));
        mListCache.add(new GuardStone("剑术",
                "4","","",
                "4","","",
                "5","","",
                "6","",""));
        mListCache.add(new GuardStone("痛击",
                "4","","",
                "5","","",
                "5","","",
                "6","",""));
        mListCache.add(new GuardStone("饥渴感",
                "9","","",
                "10","","",
                "0","","",
                "10","",""));
        mListCache.add(new GuardStone("体术",
                "6","","",
                "7","","",
                "7","","",
                "6","",""));
        mListCache.add(new GuardStone("快速装填",
                "4","","",
                "7","","",
                "7","","",
                "7","",""));
        mListCache.add(new GuardStone("高速设置",
                "10","","",
                "0","","",
                "0","","",
                "10","",""));
        mListCache.add(new GuardStone("通常弹强化",
                "3","","",
                "6","","",
                "6","","",
                "7","",""));
        mListCache.add(new GuardStone("贯通弹强化",
                "3","","",
                "6","","",
                "6","","",
                "7","",""));
        mListCache.add(new GuardStone("散弹强化",
                "3","","",
                "6","","",
                "6","","",
                "7","",""));
        mListCache.add(new GuardStone("强韧",
                "4","","",
                "7","","",
                "7","","",
                "8","",""));
        mListCache.add(new GuardStone("眠弹增加",
                "4","","",
                "7","","",
                "7","","",
                "7","",""));
        mListCache.add(new GuardStone("听觉保护",
                "2","","",
                "5","","",
                "5","","",
                "6","","2-1+2"));
        mListCache.add(new GuardStone("抗菌",
                "10","","",
                "0","","",
                "0","","",
                "10","",""));
        mListCache.add(new GuardStone("防御性能",
                "4","","",
                "7","","",
                "7","","",
                "8","",""));
        mListCache.add(new GuardStone("回避性能",
                "5","","",
                "7","","",
                "7","","",
                "8","",""));
        mListCache.add(new GuardStone("回避距离",
                "4","","",
                "7","","",
                "7","","",
                "8","",""));
        mListCache.add(new GuardStone("根性",
                "4","","",
                "7","","",
                "7","","",
                "7","",""));
        mListCache.add(new GuardStone("斗魂",
                "4","","",
                "7","","",
                "7","","",
                "7","",""));
        mListCache.add(new GuardStone("风压抵抗",
                "4","","",
                "7","","",
                "7","","",
                "7","",""));
        mListCache.add(new GuardStone("耐力回复",
                "4","","",
                "7","","",
                "8","","",
                "7","",""));
        mListCache.add(new GuardStone("耐泥雪",
                "10","","",
                "0","","",
                "0","","",
                "10","",""));
        mListCache.add(new GuardStone("会心术",
                "0","","",
                "4","","",
                "5","","",
                "6","",""));
        mListCache.add(new GuardStone("速射",
                "0","","",
                "4","","",
                "4","","",
                "5","",""));
        mListCache.add(new GuardStone("反动",
                "2","","",
                "5","","",
                "5","","",
                "5","",""));
        mListCache.add(new GuardStone("KO术",
                "5","","",
                "7","","",
                "7","","",
                "7","",""));
        mListCache.add(new GuardStone("倒地之力",
                "5","","",
                "7","","",
                "7","","",
                "7","",""));
        mListCache.add(new GuardStone("匠",
                "0","","",
                "3","","",
                "3","","",
                "4","",""));
        mListCache.add(new GuardStone("装填术",
                "0","","",
                "4","","",
                "4","","",
                "6","",""));
        mListCache.add(new GuardStone("研磨师",
                "0","","",
                "8","","",
                "8","","",
                "8","",""));
        mListCache.add(new GuardStone("恢复量",
                "6","","",
                "7","","",
                "8","","",
                "7","",""));
        mListCache.add(new GuardStone("笛",
                "8","","",
                "9","","",
                "10","","",
                "0","",""));
        mListCache.add(new GuardStone("炮术",
                "8","","",
                "9","","",
                "10","","",
                "12","",""));
        mListCache.add(new GuardStone("刺伤",
                "7","","",
                "8","","",
                "10","","",
                "10","",""));
        mListCache.add(new GuardStone("龙耐性",
                "10","","",
                "11","","",
                "13","","",
                "14","",""));

    }

}
