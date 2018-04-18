package com.linqinen.mho.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.linqinen.mho.R;
import com.linqinen.mho.adapter.GuardStonePriceListAdapter;
import com.linqinen.mho.adapter.MyRecyclerViewAdapter;
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.bean.GuardStonePrice;
import com.linqinen.mho.sqlite.guardstoneprice.GuardStonePriceDBhelper;
import com.linqinen.mho.tools.comparator.GuardStonePricePinyinComparator;
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

public class GuardStonePriceActivity extends AppCompatActivity {

    private final String TAG = "GuardStonePriceActivity";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.sidebar_A_N)
    SideBar_A_N mSidebarAN;
    @BindView(R.id.sidebar_O_Z)
    SideBar_O_Z mSidebarOZ;
    @BindView(R.id.dialog)
    TextView mDialog;

    private GuardStonePriceListAdapter mAdapter;
    private List<GuardStonePrice> mListCache = new ArrayList<>();
    private List<GuardStonePrice> mList = new ArrayList<>();

    private GuardStonePriceDBhelper mDBhelper;

    private String name = "全部";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_stone_price);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        mDBhelper = new GuardStonePriceDBhelper(this, mListCache);
        mDBhelper.onlyQueryAllData();
        if (mListCache.size() < 1) {
            Log.i(TAG, "initData: ");
            addRecyclerViewData();
            pinYinSort(mListCache);
            mDBhelper.addAllData();
        }else{
            pinYinSort(mListCache);
        }


        initFirstSpinner();
        initSecondSpinner();
        initRecyclerView();

        mSidebarAN.setTextView(mDialog);
        mSidebarOZ.setTextView(mDialog);
        mSidebarAN.setOnTouchingLetterChangedListener(new SideBar_A_N.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) { /* 该字母首次出现的位置*/

                int position = getPositionForSelection(s.charAt(0));
                Log.i(TAG, "onTouchingLetterChanged: position:"+position);
                if (position != -1){

                    if(position > 3){
                        mRecyclerView.scrollToPosition(position+3);
                    }else{
                        mRecyclerView.scrollToPosition(position);
                    }
                }
            }
        });
        mSidebarOZ.setOnTouchingLetterChangedListener(new SideBar_O_Z.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) { /* 该字母首次出现的位置*/

                int position = getPositionForSelection(s.charAt(0));
                Log.i(TAG, "onTouchingLetterChanged: position:"+position);
                if (position != -1){

                    if(position > 3){
                        mRecyclerView.scrollToPosition(position+3);
                    }else{
                        mRecyclerView.scrollToPosition(position);
                    }
                }
            }
        });
    }

    public int getPositionForSelection(int selection) {
        for (int i = 0; i < mList.size(); i++) {
            String Fpinyin = mList.get(i).getFirstPinYin();
            if(Fpinyin != null && Fpinyin.length() > 0 ){
                char first = Fpinyin.toUpperCase().charAt(0);
                if (first == selection) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void pinYinSort(List<GuardStonePrice> mList) {
        for (int i = 0; i < mList.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(mList.get(i).getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            GuardStonePrice mBean = mList.get(i);
            mBean.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                mBean.setFirstPinYin(Fpinyin);
            } else {
                mBean.setFirstPinYin("#");
            }
        }
        Collections.sort(mList, new GuardStonePricePinyinComparator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_normal_material_price, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.addItem:
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.inflate_addsqlitedata_guard_stone_price, null);
                final EditText mEtName = (EditText) view.findViewById(R.id.et_name);
                final EditText et_precisionCasting = (EditText) view.findViewById(R.id.et_precisionCasting);
                final EditText et_auctionPrice = (EditText) view.findViewById(R.id.et_auctionPrice);
                final EditText et_fixedPrice = (EditText) view.findViewById(R.id.et_fixedPrice);
                new AlertDialog.Builder(this)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDBhelper.addData(mEtName.getText().toString(), et_precisionCasting.getText().toString(), et_auctionPrice.getText().toString(), et_fixedPrice.getText().toString());
                                mListCache.add(new GuardStonePrice(mEtName.getText().toString(),et_precisionCasting.getText().toString(), et_auctionPrice.getText().toString(), et_fixedPrice.getText().toString(),1));
                                mList.clear();
                                pinYinSort(mListCache);
                                mList.addAll(mListCache);
                                mAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.deleteAllData:
                new AlertDialog.Builder(this)
                        .setTitle("温馨提示")
                        .setMessage("是否删除所有数据库信息")
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDBhelper.resetData();
                                mList.clear();
                                mListCache.clear();
                                addRecyclerViewData();
                                pinYinSort(mListCache);
                                mList.addAll(mListCache);
                                mAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.filtrateBeModifiedData:
                mList.clear();
                for (int i = 0; i < mListCache.size(); i++) {
                    if(mListCache.get(i).getIsRefresh() == 1){
                        mList.add(mListCache.get(i));
                    }
                }
                mAdapter.notifyDataSetChanged();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GuardStonePriceListAdapter(this, mList);
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);
        mAdapter.setOnItemLongClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {

            @Override
            public void onItemLongClick(View view2, int position) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.inflate_addsqlitedata_guard_stone_price, null);
                final EditText mEtName = (EditText) view.findViewById(R.id.et_name);
                final EditText et_precisionCasting = (EditText) view.findViewById(R.id.et_precisionCasting);
                final EditText et_auctionPrice = (EditText) view.findViewById(R.id.et_auctionPrice);
                final EditText et_fixedPrice = (EditText) view.findViewById(R.id.et_fixedPrice);

                final GuardStonePrice mGuardStonePrice = mList.get(position);
                mEtName.setText(mGuardStonePrice.getName());
                mEtName.setEnabled(false);
                et_precisionCasting.setText(mGuardStonePrice.getPrecisionCasting());
                et_auctionPrice.setText(mGuardStonePrice.getAuctionPrice());
                et_fixedPrice.setText(mGuardStonePrice.getFixedPrice());

                new AlertDialog.Builder(GuardStonePriceActivity.this)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mDBhelper.updateData(mGuardStonePrice.getName(), et_precisionCasting.getText().toString(), et_auctionPrice.getText().toString(), et_fixedPrice.getText().toString());
                                mList.clear();
                                mListCache.clear();
                                mDBhelper.onlyQueryAllData();

                                if (!name.equals("全部")) {
                                    reInitRecyclerViewNameData(name);
                                } else {
                                    mList.addAll(mListCache);
                                    mAdapter.notifyDataSetChanged();
                                }
//                                mAdapter.notifyItemChanged(position);
                            }
                        })
                        .setNeutralButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDBhelper.deleteData(mGuardStonePrice.getName());
                                mList.clear();
                                mListCache.clear();
                                mDBhelper.onlyQueryAllData();
                                mList.addAll(mListCache);
                                mAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });


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
                name = mItems[position];
                Log.i(TAG, "initLeftSpinner: position:" + position);
                if (position == 0) {
                    mList.clear();
                    mList.addAll(mListCache);
                    mAdapter.notifyDataSetChanged();
                } else {
                    reInitRecyclerViewNameData(name);
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
            list.add(Fpinyin + "." + mListCache.get(i).getName());
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
                name = list.get(position);
                Log.i(TAG, "initLeftSpinner: position:" + position);
                if (position == 0) {
                    mList.clear();
                    mList.addAll(mListCache);
                    mAdapter.notifyDataSetChanged();
                } else {
                    reInitRecyclerViewSpecialMaterialNameData(name);
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
        mListCache.add(new GuardStonePrice("攻击",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("攻击",
                "10", "_", "8W",0));
        mListCache.add(new GuardStonePrice("防御",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("达人",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("达人",
                "10", "_", "8W",0));
        mListCache.add(new GuardStonePrice("特殊攻击",
                "6", "4.5W", "6W",0));
        mListCache.add(new GuardStonePrice("食事",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("千里眼",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("观察眼",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("体力",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("回复速度",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("恢复量",
                "5", "_", "9998",0));
        mListCache.add(new GuardStonePrice("恢复量",
                "5", "3", "4.5W",0));
        mListCache.add(new GuardStonePrice("麻痹抵抗",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("气绝抵抗",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("睡眠抵抗",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("毒性抵抗",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("火耐性",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("广域化",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("锋利度",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("加护",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("集中",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("拔刀会心（英雄护石）",
                "3", "_", "13333",0));
        mListCache.add(new GuardStonePrice("重击",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("耐震",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("底力",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("剑术",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("痛击",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("饥渴感",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("体术",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("快速装填",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("高速设置",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("通常弹强化",
                "4", "7350", "6W",0));
        mListCache.add(new GuardStonePrice("贯通弹强化",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("散弹强化",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("强韧",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("眠弹增加",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("听觉保护",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("听觉保护;麻痹抵抗",
                "3;3", "7350", "4W",0));
        mListCache.add(new GuardStonePrice("防御性能",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("回避距离",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("回避距离;毒抗",
                "5;5", "7000", "14.5W",0));
        mListCache.add(new GuardStonePrice("根性",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("斗魂",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("风压抵抗",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("耐力回复",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("耐泥雪",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("会心术",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("速射",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("反动",
                "3", "7350", "6W",0));
        mListCache.add(new GuardStonePrice("KO术",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("倒地之力",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("匠",
                "2", "10W", "14.5W",0));
        mListCache.add(new GuardStonePrice("装填术",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("研磨师",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("恢复量",
                "5", "7000", "6W",0));
        mListCache.add(new GuardStonePrice("笛",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("炮术",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("刺伤",
                "_", "_", "",0));
        mListCache.add(new GuardStonePrice("龙耐性",
                "_", "_", "",0));

    }
}
