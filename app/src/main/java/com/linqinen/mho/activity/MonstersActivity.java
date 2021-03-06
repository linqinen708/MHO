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
import com.linqinen.mho.adapter.MonstersDetailAdapter;
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.bean.Monster;
import com.linqinen.mho.tools.PinyinUtils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MonstersActivity extends AppCompatActivity {

    private final String TAG = "MonstersActivity";
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private MonstersDetailAdapter mAdapter;
    private List<Monster> mListCache = new ArrayList<>();
    private List<Monster> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monsters);
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
        initFirstSpinner();
        initSecondSpinner();
        initRecyclerView();

    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MonstersDetailAdapter(this, mList);
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

//    String body_dragon;
//    String back_dragon;
//    String tail_dragon;
//    String head_dragon;
//    String neck_dragon;
//    String foot_dragon;
//    String hand_dragon;
//    String wing_dragon;
//    String abdomen_dragon;

    private void addRecyclerViewData() {

//        mListCache.add(new Monster("",
//                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
//                "——", "——", "——","——", "——", "——","——", "——", "——",
//                "——", "——", "——","——", "——", "——","——", "——", "——",
//                "——", "——", "——","——", "——", "——","——", "——", "——",
//
//                "——", "——", "——","——", "——", "——","——", "——", "——",//火
//                "——", "——", "——","——", "——", "——","——", "——", "——",//水
//                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
//                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
//                "——", "——", "——","——", "——", "——","——", "——", "——",//龙
//
//                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
//                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
//                ""
//                ));
        mListCache.add(new Monster("一角龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "极佳","——", "有效", "——","——", "极佳", "有效",
                "——", "——", "极佳","——", "极佳", "有效","——", "有效", "有效",
                "——", "——", "极佳","——", "极佳", "有效","——", "极佳", "有效",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//水
                "——", "——", "——","——", "极佳", "——","——", "极佳", "极佳",//雷
                "——", "——", "——","极佳", "——", "极佳","——", "——", "——",//冰
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","有效","无效",
                "发怒后音爆无效"
                ));
        mListCache.add(new Monster("红电龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "有效", "——", "——","有效", "极佳", "——","——", "——", "——",
                "有效", "——", "——","极佳", "有效", "——","——", "——", "——",
                "——", "——", "——","极佳", "有效", "——","——", "——", "——",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "极佳", "——", "——","极佳", "极佳", "——","——", "——", "——",//水
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","无效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("黑狼鸟",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "极佳", "——", "——","极佳", "——", "——","——", "——", "——",

                "一般", "较差", "较差","无效", "无效", "无效","无效", "无效", "无效",//火
                "极佳", "极佳", "极佳","极佳", "——", "——","——", "——", "——",//水
                "无效", "较差", "较差","无效", "无效", "无效","无效", "无效", "无效",//雷
                "极佳", "——", "——","——", "——", "——","——", "——", "——",//冰
                "极佳", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","无效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("砦蟹",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","——", "——", "有效","——", "——", "——",
                "——", "——", "——","——", "——", "有效","——", "——", "——",
                "——", "——", "——","——", "——", "有效","——", "——", "——",

                "——", "——", "——","——", "——", "有效","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "极佳", "——", "——","——", "——", "极佳","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "无效","无效","无效","无效","无效","无效","无效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("雄火龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "有效","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",

                "无效", "一般", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "有效", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "有效", "——","——", "——", "——","——", "极佳", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","有效",
                ""
                ));
        mListCache.add(new Monster("剑极狼",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "有效","有效", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "无效", "无效", "无效","较差", "无效", "无效","无效", "无效", "无效",//水
                "——", "——", "有效","极佳", "——", "——","——", "——", "——",//雷
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                "愤怒后落穴无效"
                ));
        mListCache.add(new Monster("轰龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "有效", "——","极佳", "有效", "——","——", "——", "——",
                "——", "——", "——","极佳", "有效", "有效","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "一般",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "有效", "——","极佳", "——", "——","——", "——", "有效",//雷
                "——", "——", "无效","——", "——", "无效","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","有效",
                ""
                ));
        mListCache.add(new Monster("烈焰女王",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "极佳", "——","——", "——", "——","——", "——", "——",
                "——", "有效", "——","——", "——", "——","——", "——", "——",
                "——", "有效", "——","——", "——", "——","——", "——", "——",

                "无效", "较差", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "有效", "有效","极佳", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("银眠鸟",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","极佳", "——", "——","——", "——", "——",

                "有效", "——", "——","——", "——", "——","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("冰雷鸟",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "有效", "——", "——","有效", "有效", "——","——", "——", "——",
                "有效", "——", "极佳","极佳", "极佳", "——","——", "——", "——",
                "有效", "——", "——","极佳", "有效", "——","——", "——", "——",

                "——", "——", "——","极佳", "极佳", "——","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "极佳", "——", "——","——", "极佳", "——","——", "有效", "——",//雷
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//冰
                "无效", "无效", "无效","无效", "一般", "无效","无效", "无效", "无效",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("白速龙王",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "极佳", "——", "极佳","极佳", "——", "极佳","——", "——", "——",
                "极佳", "——", "极佳","极佳", "——", "极佳","——", "——", "——",
                "有效", "——", "有效","有效", "——", "有效","——", "——", "——",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "较差", "——", "较差","较差", "——", "较差","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","无效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("角龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "极佳","——", "有效", "——","——", "——", "——",
                "——", "——", "有效","——", "极佳", "——","——", "有效", "有效",
                "——", "——", "有效","——", "有效", "——","——", "有效", "有效",

                "无效", "较差", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "极佳", "——","极佳", "——", "——","——", "有效", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","有效","无效",
                ""
                ));
        mListCache.add(new Monster("雌火龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",

                "无效", "较差", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "有效", "有效","极佳", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","有效",
                ""
                ));
        mListCache.add(new Monster("电龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "有效", "——", "——","有效", "极佳", "——","——", "——", "——",
                "有效", "——", "——","极佳", "有效", "——","——", "——", "——",
                "——", "——", "——","极佳", "有效", "——","——", "——", "——",

                "极佳", "——", "——","极佳", "极佳", "——","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","无效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("桃毛兽王",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","极佳", "——", "有效","有效", "——", "——",

                "——", "——", "——","有效", "——", "——","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","有效",
                "肉类有效"
                ));
        mListCache.add(new Monster("毒怪鸟",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "极佳", "——", "极佳","——", "有效", "——","——", "有效", "——",
                "——", "——", "极佳","极佳", "——", "——","——", "——", "——",
                "——", "——", "极佳","极佳", "有效", "——","——", "——", "——",

                "——", "——", "极佳","极佳", "极佳", "——","——", "极佳", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","无效","无效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("鬼狩蛛",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "——", "——", "——","极佳", "——", "——","极佳", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","无效","有效","无效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("大怪鸟",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "极佳", "——", "有效","有效", "有效", "——","——", "极佳", "——",
                "有效", "有效", "——","极佳", "极佳", "——","——", "极佳", "——",
                "有效", "——", "——","极佳", "有效", "——","——", "——", "——",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "——", "——", "——","极佳", "极佳", "——","——", "——", "——",//水
                "——", "——", "——","极佳", "极佳", "——","——", "——", "——",//雷
                "——", "——", "——","极佳", "极佳", "——","——", "——", "——",//冰
                "无效", "无效", "无效","无效", "一般", "无效","无效", "无效", "无效",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","有效","无效",
                "愤怒后音爆无效"
                ));
        mListCache.add(new Monster("河狸兽",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "有效", "——", "——","极佳", "——", "——","——", "——", "——",
                "有效", "——", "——","极佳", "——", "——","——", "——", "——",
                "有效", "——", "——","极佳", "——", "——","——", "——", "——",

                "——", "——", "——","有效", "——", "——","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("钢龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "有效", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "有效","——", "——", "——","——", "——", "——",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "极佳","极佳", "——", "——","——", "——", "——",//雷
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//冰
                "——", "——", "极佳","极佳", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","无效","无效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("灰晶蝎",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","——", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "——", "——", "较差","——", "——", "——","——", "——", "——",//火
                "——", "——", "较差","——", "——", "——","——", "——", "——",//水
                "——", "——", "较差","——", "——", "——","——", "——", "——",//雷
                "——", "——", "较差","——", "——", "——","——", "——", "——",//冰
                "——", "——", "较差","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","无效","无效","无效","无效","无效","无效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("金眠鸟",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("樱火龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","有效", "有效", "——","——", "——", "有效",
                "——", "——", "——","有效", "——", "——","——", "——", "有效",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "无效", "较差", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "——","极佳", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","有效",
                ""
                ));
        mListCache.add(new Monster("苍火龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "有效","——", "——", "——",

                "无效", "有效", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","有效",
                ""
                ));
        mListCache.add(new Monster("电甲虫",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","有效", "——", "——","——", "——", "极佳",
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "有效",

                "——", "——", "——","——", "——", "——","——", "——", "极佳",//火
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//水
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//雷
                "——", "——", "——","极佳", "——", "——","——", "极佳", "——",//冰
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","无效","有效","无效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("砂岩龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","——", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("霞龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "极佳", "——", "——","极佳", "——", "——","——", "——", "——",//火
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "较差", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//冰
                "——", "——", "——","极佳", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","无效","无效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("熔岩龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "有效","——", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "有效", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "——","极佳", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","有效","无效",
                ""
        ));
        mListCache.add(new Monster("白一角龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "极佳","——", "——", "——","——", "极佳", "——",
                "——", "——", "极佳","——", "极佳", "——","——", "——", "——",
                "——", "——", "极佳","——", "极佳", "——","——", "极佳", "——",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "无效", "无效", "无效","无效", "无效", "无效","无效", "较差", "无效",//水
                "——", "——", "——","——", "极佳", "——","——", "极佳", "极佳",//雷
                "——", "——", "——","极佳", "——", "极佳","——", "极佳", "——",//冰
                "无效", "无效", "无效","无效", "无效", "无效","无效", "较差", "无效",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","有效","无效",
                "发怒后音爆无效"
                ));
        mListCache.add(new Monster("炎狮子",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","极佳", "——", "有效","有效", "——", "——",
                "——", "——", "——","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","极佳", "——", "有效","有效", "——", "——",

                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "——","有效", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("剑刹狼",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "有效","——", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "无效", "无效", "较差","较差", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "有效","极佳", "——", "——","——", "——", "——",//水
                "无效", "无效", "无效","较差", "无效", "无效","无效", "无效", "无效",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","——", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("铠龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","——", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "一般", "极佳", "极佳","一般", "极佳", "有效","——", "极佳", "极佳",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","——", "——", "——",//冰
                "——", "——", "——","极佳", "——", "——","——", "——", "极佳",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("金狮子",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "有效","有效", "——", "——","——", "——", "——",
                "——", "——", "有效","极佳", "——", "——","——", "——", "——",
                "——", "——", "——","有效", "——", "——","——", "——", "——",

                "无效", "无效", "无效","较差", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "——","——", "——", "——","——", "——", "——",//水
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//雷
                "——", "——", "——","极佳", "——", "——","——", "——", "——",//冰
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//龙

                //气绝，  毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆，  肉
                "有效","有效","有效","有效","有效","有效","有效","无效","无效",
                ""
                ));
        mListCache.add(new Monster("燎渊龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "有效","——", "——", "——","——", "——", "极佳",
                "——", "有效", "——","极佳", "——", "——","——", "——", "——",
                "有效", "——", "——","——", "——", "——","——", "——", "——",

                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//火
                "极佳", "——", "——","——", "——", "——","——", "——", "极佳",//水
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//雷
                "——", "极佳", "——","极佳", "——", "——","——", "——", "——",//冰
                "极佳", "——", "——","有效", "——", "——","——", "——", "——",//龙

                //气绝，  毒，   麻，   眠，     落穴， 麻痹， 闪光，  音爆，    肉
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",
                ""
                ));
        mListCache.add(new Monster("锈钢龙",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","有效", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "无效", "无效", "无效","较差", "无效", "无效","无效", "无效", "无效",//火
                "——", "——", "有效","极佳", "——", "——","——", "——", "——",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//冰
                "——", "——", "有效","极佳", "——", "——","——", "——", "——",//龙

                //气绝，毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆， 肉
                "有效","有效","有效","有效","无效","无效","有效","无效","无效",
                "防御状态吃音爆"
                ));
        mListCache.add(new Monster("葵盾蟹",
                //身体， 背部， 尾巴，  头部，  颈部，  腿部， 手部，  双翼，  腹部
                "——", "——", "——","——", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",
                "——", "——", "——","——", "——", "——","——", "——", "——",

                "——", "——", "——","——", "——", "——","——", "——", "——",//火
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//水
                "——", "——", "——","——", "——", "——","——", "——", "——",//雷
                "——", "——", "——","——", "——", "——","有效", "——", "——",//冰
                "无效", "无效", "无效","无效", "无效", "无效","无效", "无效", "无效",//龙

                //气绝，毒，   麻，   眠，  落穴， 麻痹， 闪光， 音爆， 肉
                "有效","有效","有效","有效","无效","有效","无效","无效","无效",
                "防御状态吃音爆"
                ));


    }
}
