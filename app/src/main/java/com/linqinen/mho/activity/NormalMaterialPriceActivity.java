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

import com.linqinen.mho.R;
import com.linqinen.mho.adapter.MyRecyclerViewAdapter;
import com.linqinen.mho.adapter.NormalMaterialPriceListAdapter;
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.bean.NormalMaterialPrice;
import com.linqinen.mho.sqlite.normalmaterial.RankDBhelper;
import com.linqinen.mho.tools.PinyinUtils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NormalMaterialPriceActivity extends AppCompatActivity {

    private final String TAG = "NormalMaterialPrice";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private NormalMaterialPriceListAdapter mAdapter;
    private List<NormalMaterialPrice> mListCache = new ArrayList<>();
    private List<NormalMaterialPrice> mList = new ArrayList<>();

    private RankDBhelper mRankDBhelper;
    private String name = "全部";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_material_price);
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

    private void initData() {

        mRankDBhelper = new RankDBhelper(this, mListCache);
        mRankDBhelper.onlyQueryAllData();
        if (mListCache.size() < 1) {
            Log.i(TAG, "initData: ");
            addRecyclerViewData();
            mRankDBhelper.addAllData();
        }

        initFirstSpinner();
        initRecyclerView();

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
                View view = inflater.inflate(R.layout.inflate_addsqlitedata_normal_material_price, null);
                final EditText mEtName = (EditText) view.findViewById(R.id.et_name);
                final EditText mEtNum = (EditText) view.findViewById(R.id.et_num);
                final EditText mEtMinPrice = (EditText) view.findViewById(R.id.et_minPrice);
                final EditText mEtMaxPrice = (EditText) view.findViewById(R.id.et_maxPrice);
                new AlertDialog.Builder(this)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mRankDBhelper.addData(mEtName.getText().toString(), mEtNum.getText().toString(), mEtMinPrice.getText().toString()+"--"+ mEtMaxPrice.getText().toString());
                                mListCache.add(new NormalMaterialPrice(mEtName.getText().toString(),
                                        mEtNum.getText().toString(),mEtMinPrice.getText().toString()+"--"+ mEtMaxPrice.getText().toString(),
                                        1
                                ));
                                mList.clear();
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
                                mRankDBhelper.resetData();
                                mList.clear();
                                mListCache.clear();
                                addRecyclerViewData();
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
        mAdapter = new NormalMaterialPriceListAdapter(this, mList);
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);
        mAdapter.setOnItemLongClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {

            @Override
            public void onItemLongClick(View view2, int position) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.inflate_addsqlitedata_normal_material_price, null);
                final EditText mEtName = (EditText) view.findViewById(R.id.et_name);
                final EditText mEtNum = (EditText) view.findViewById(R.id.et_num);
                final EditText mEtMinPrice = (EditText) view.findViewById(R.id.et_minPrice);
                final EditText mEtMaxPrice = (EditText) view.findViewById(R.id.et_maxPrice);

                final NormalMaterialPrice mNormalMaterialPrice =  mList.get(position);
                mEtName.setText(mNormalMaterialPrice.getName());
                mEtNum.setText(mNormalMaterialPrice.getNum());

                String price[] = mNormalMaterialPrice.getPrice().split("--");
                if(price.length > 0){
                    mEtMinPrice.setText(price[0]);
                }
                if(price.length > 1){
                    mEtMaxPrice.setText(price[1]);
                }

                new AlertDialog.Builder(NormalMaterialPriceActivity.this)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mRankDBhelper.updateData(mNormalMaterialPrice.getName(),mEtName.getText().toString(), mEtNum.getText().toString(),mEtMinPrice.getText().toString()+"--"+ mEtMaxPrice.getText().toString());
                                mList.clear();
                                mListCache.clear();
                                mRankDBhelper.onlyQueryAllData();

                                if(!name.equals("全部")){
                                    reInitRecyclerViewNameData(name);
                                }else{
                                    mList.addAll(mListCache);
                                    mAdapter.notifyDataSetChanged();
                                }
//                                mAdapter.notifyItemChanged(position);
                            }
                        })
                        .setNeutralButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mRankDBhelper.deleteData(mNormalMaterialPrice.getName());
                                mList.clear();
                                mListCache.clear();
                                mRankDBhelper.onlyQueryAllData();
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

        final String[] mItems = {"全部", "土蜂幼虫", "杀人蜂幼虫", "山青虫", "蓝闪蝶", "红角蝶", "黏着的白蚁",
                "上兽骨", "兽骨[大]", "兽骨[中]", "兽骨[小]", "上龙骨", "龙骨[大]", "龙骨[中]", "龙骨[小]",
                "圆盘石", "辉水晶", "光水晶", "辉龙石", "灵鹤石", "蓝蘑菇", "红蘑菇", "硝化蘑菇", "兴奋蘑菇", "解毒草", "霜降草", "黏着草", "火药草", "大王花", "龟壳果", "生肉"};

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

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.adapters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                name = list.get(position);
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


    private void addRecyclerViewData() {
        mListCache.add(new NormalMaterialPrice("土蜂幼虫",
                "1", "777--898",0
        ));
        mListCache.add(new NormalMaterialPrice("杀人蜂幼虫",
                "1", "98--222",0
        ));
        mListCache.add(new NormalMaterialPrice("山青虫",
                "1", "99--133",0
        ));
        mListCache.add(new NormalMaterialPrice("蓝闪蝶",
                "1", "520--655",0
        ));
        mListCache.add(new NormalMaterialPrice("红角蝶",
                "1", "295--380",0
        ));
        mListCache.add(new NormalMaterialPrice("黏着的白蚁",
                "1", "119--",0
        ));
        mListCache.add(new NormalMaterialPrice("上兽骨",
                "1", "498--698",0
        ));
        mListCache.add(new NormalMaterialPrice("兽骨[大]",
                "1", "455--571",0
        ));
        mListCache.add(new NormalMaterialPrice("兽骨[中]",
                "1", "390--435",0
        ));
        mListCache.add(new NormalMaterialPrice("上龙骨",
                "1", "577--655",0
        ));
        mListCache.add(new NormalMaterialPrice("龙骨[大]",
                "1", "300--398",0
        ));
        mListCache.add(new NormalMaterialPrice("龙骨[中]",
                "1", "--398",0
        ));
        mListCache.add(new NormalMaterialPrice("圆盘石",
                "3", "333",0
        ));
        mListCache.add(new NormalMaterialPrice("辉水晶",
                "1", "332--493",0
        ));
        mListCache.add(new NormalMaterialPrice("光水晶",
                "1", "134--312",0
        ));
        mListCache.add(new NormalMaterialPrice("辉龙石",
                "1", "114--119",0
        ));
        mListCache.add(new NormalMaterialPrice("灵鹤石",
                "1", "104--189",0
        ));
        mListCache.add(new NormalMaterialPrice("蓝蘑菇",
                "50", "1111",0
        ));
        mListCache.add(new NormalMaterialPrice("硝化蘑菇",
                "10", "597--887",0
        ));
        mListCache.add(new NormalMaterialPrice("解毒草",
                "10", "398",0
        ));
        mListCache.add(new NormalMaterialPrice("霜降草",
                "10", "405",0
        ));
        mListCache.add(new NormalMaterialPrice("黏着草",
                "50", "1598-2000",0
        ));
        mListCache.add(new NormalMaterialPrice("大王花",
                "1-50", "120-4998",0
        ));
        mListCache.add(new NormalMaterialPrice("龟壳果",
                "1", "120",0
        ));
        mListCache.add(new NormalMaterialPrice("晶化粉尘",
                "5", "249",0
        ));

        mListCache.add(new NormalMaterialPrice("生肉",
                "10", "998",0
        ));
        mListCache.add(new NormalMaterialPrice("爆裂龙鱼",
                "50", "2398",0
        ));


    }

}
