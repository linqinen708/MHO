package com.linqinen.mho.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linqinen.mho.R;
import com.linqinen.mho.adapter.MyGuardStoneAdapter;
import com.linqinen.mho.adapter.MyRecyclerViewAdapter;
import com.linqinen.mho.adapter.RecyclerView_GridView_Adapter;
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.bean.MyGuardStone;
import com.linqinen.mho.tools.PinyinUtils;

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

public class MyGuardStoneActivity extends AppCompatActivity {

    private final String TAG = "MyGuardStoneActivity";


    @BindView(R.id.tv_skillName)
    TextView mTvSkillName;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.recyclerView_gridview)
    RecyclerView mRecyclerViewGridview;
    @BindView(R.id.content_my_guard_stone)
    RelativeLayout mContentMyGuardStone;

    private MyGuardStoneAdapter mAdapter;

    private Context mContext;
    private List<String> mStringList;


    private ArrayList<MyGuardStone> mListCache = new ArrayList<>();
    private ArrayList<MyGuardStone> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_guard_stone);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = this;
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

        addRecyclerViewData();
        initFirstSpinner();
//        initSecondSpinner();
        mList.addAll(mListCache);
        initRecyclerView();
        initRecyclerView_GridView();
    }

    private void initRecyclerView() {
//        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
//        mLinearLayoutManager.scrollToPositionWithOffset();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MyGuardStoneAdapter(mContext, mList);
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);

    }

    private void initRecyclerView_GridView() {
        mRecyclerViewGridview.setLayoutManager(new GridLayoutManager(mContext, 4));
        RecyclerView_GridView_Adapter mGridViewAdapter = new RecyclerView_GridView_Adapter(mContext, mStringList);
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

                    reInitRecyclerViewData(skillName);
                }
//                mRecyclerViewGridview.setVisibility(View.GONE);
                hideAnimator(mRecyclerViewGridview);
            }
        });
    }



    private void initFirstSpinner() {
//        Spinner spinner = (Spinner) onCreateView.findViewById(R.id.spinner_first);

        mStringList = new ArrayList<>();
//        final List<String> skillNameList = new ArrayList<>();
        for (int i = 0; i < mListCache.size(); i++) {
            String skillName[] = {mListCache.get(i).getSkill_1(), mListCache.get(i).getSkill_2(), mListCache.get(i).getSkill_3()};
            for (int j = 0; j < skillName.length; j++) {

                String name = skillName[j];

                if (name.length() > 0 && !mStringList.toString().contains(name)) {
                    String pinyin = PinyinUtils.getPingYin(name);
                    String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                    mStringList.add(Fpinyin + "." + name);
                }
            }
        }

        /**java中的中文排序逻辑*/
        Collator coll = Collator.getInstance(Locale.CHINESE);
        Collections.sort(mStringList, coll);

        mStringList.add(0, "全部");

        //android.R.layout.simple_spinner_item为系统默认样式
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, mStringList);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.adapters, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String skillName = mStringList.get(position).substring(2);
////                Log.i(TAG, "onItemSelected: skillName:"+skillName);
//                if (position == 0) {
//                    mList.clear();
//                    mList.addAll(mListCache);
//                    mAdapter.notifyDataSetChanged();
//                } else {
//                    reInitRecyclerViewData(skillName);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }

    private void reInitRecyclerViewData(String skillName) {
//        Log.i(TAG, "reInitRecyclerViewData: skillName:"+skillName);
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {
            if (mListCache.get(i).getSkill_1().length() > 0 && skillName.equals(mListCache.get(i).getSkill_1())) {
//                Log.i(TAG, "reInitRecyclerViewData111: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkill_2().length() > 0 && skillName.equals(mListCache.get(i).getSkill_2())) {
//                Log.i(TAG, "reInitRecyclerViewData222: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkill_3().length() > 0 && skillName.equals(mListCache.get(i).getSkill_3())) {
//                Log.i(TAG, "reInitRecyclerViewData333: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }

        }
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_skillName)
    public void onClick() {
        if(mRecyclerViewGridview.getVisibility() != View.VISIBLE){
//            mRecyclerViewGridview.setVisibility(View.VISIBLE);
            appearAnimator(mRecyclerViewGridview);
            Log.i(TAG, "onClick: ");
        }
    }


    private void addRecyclerViewData() {
//        mListCache.add(new MyGuardStone(
//                "", "","",
//                "", "","",
//                "", "",""
//
//        ));
        mListCache.add(new MyGuardStone(
                "千里眼", "14","13",
                "回复速度", "12","4,6",
                "耐泥雪", "10","5,7"

        ));
        mListCache.add(new MyGuardStone(
                "千里眼", "14","9,11,7",
                "防御", "10","4,2",
                "水耐性", "14","9"

        ));
        mListCache.add(new MyGuardStone(
                "千里眼", "14","10,11",
                "观察眼", "12","",
                "达人", "14",""

        ));
        mListCache.add(new MyGuardStone(
                "千里眼", "14","13,12,11,9,12,9,10,13",
                "毒性抵抗", "10","6,5,6,9",
                "耐泥雪", "10","7,5,9"

        ));
        mListCache.add(new MyGuardStone(
                "千里眼", "14","12,10,13",
                "雷耐性", "14","6,8",
                "集中", "6","5,4,5"

        ));
        mListCache.add(new MyGuardStone(
                "千里眼", "14","9,11,7,11",
                "加护", "9","4,3,6",
                "", "",""

        ));
        mListCache.add(new MyGuardStone(
                "反动", "5","4,5",
                "体力", "11","9,11",
                "荷尔蒙", "","3"

        ));
        mListCache.add(new MyGuardStone(
                "反动", "5","4,5",
                "水耐性", "14","11,9,10,9",
                "根性", "7","6,7"

        ));
        mListCache.add(new MyGuardStone(
                "快速装填", "7","",
                "", "","",
                "", "",""

        ));
        mListCache.add(new MyGuardStone(
                "装填术", "6","",
                "", "","",
                "", "",""

        ));
        mListCache.add(new MyGuardStone(
                "痛击", "6","",
                "", "","",
                "", "",""

        ));
        mListCache.add(new MyGuardStone(
                "通常弹强化", "7","",
                "", "","",
                "", "",""

        ));
        mListCache.add(new MyGuardStone(
                "眠弹追加", "7","5，4，6",
                "广域化", "12","5,6,5",
                "", "",""

        ));
        mListCache.add(new MyGuardStone(
                "回复速度", "12","9，10，11,8,7,8",
                "冰耐性", "14","12,14",
                "气绝抵抗", "14","10,12,9,12,11"

        ));
        mListCache.add(new MyGuardStone(
                "恢复量", "7","6,7",
                "抗菌", "","8,6,8",
                "耐震", "10","5"

        ));
        mListCache.add(new MyGuardStone(
                "荷尔蒙", "8","6,4,6,8",
                "风压抵抗", "7","6,7,6,5",
                "痛击", "6","3"

        ));
        mListCache.add(new MyGuardStone(
                "体力", "","9，11",
                "炮术", "12","6,8",
                "耐力回复", "7","6"

        ));
        mListCache.add(new MyGuardStone(
                "特殊攻击", "7","6,5,6,7",
                "麻痹抵抗", "","2,1,2,4",
                "", "",""

        ));
        mListCache.add(new MyGuardStone(
                "回避性能", "8","8",
                "回复速度", "12","10",
                "", "",""

        ));
        mListCache.add(new MyGuardStone(
                "龙耐性", "14","12,14",
                "耐泥雪", "10","3,5",
                "", "",""

        ));
        mListCache.add(new MyGuardStone(
                "防御", "10","8,6,10",
                "耐泥雪", "10","5,7,5,6,5,6,3,4",
                "锋利度", "6","5,4,6"
        ));
        mListCache.add(new MyGuardStone(
                "广域化", "12","9,8,7,11",
                "观察眼", "12","5",
                "火耐性", "14","7"
        ));
        mListCache.add(new MyGuardStone(
                "广域化", "12","5,7,4,7,3,6,5,7,3,5,7",
                "加护", "9","7,6,9,7,9,8,7,5,9",
                "耐力回复", "7","4,3,4,3,4"
        ));
        mListCache.add(new MyGuardStone(
                "广域化", "12","6,3,7,3,6,7",
                "体力", "12","9,8,9,7,8,7",
                "火耐性", "14","10,8,7,10"
        ));
        mListCache.add(new MyGuardStone(
                "集中", "6","4,3,2,3,4",
                "耐震", "10","6,3,5,4,6,5,6",
                "攻击", "14","3,4,6,4,6,7"
        ));
        mListCache.add(new MyGuardStone(
                "KO术", "7","6,7",
                "麻痹抵抗", "8+","6,7,8,4,5,6,7,4",
                "恢复量", "7","4,3,5,4,3,4"
        ));
        mListCache.add(new MyGuardStone(
                "广域化", "12","10,8,10,9",
                "刺伤", "10","4",
                "水耐性", "14","12"
        ));
    }
}
