package com.linqinen.mho.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.linqinen.mho.MenuActivity;
import com.linqinen.mho.R;
import com.linqinen.mho.adapter.MyRecyclerViewAdapter;
import com.linqinen.mho.adapter.NormalMaterialPriceListAdapter;
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.bean.NormalMaterialPrice;
import com.linqinen.mho.sqlite.normalmaterial.RankDBhelper;
import com.linqinen.mho.tools.comparator.NormalMaterialPinyinComparator;
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

/**
 * Created by lin on 2017/1/4.
 */

public class NormalMaterialFragment extends BasicFragment {

    private final String TAG = "NormalMaterialFragment";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.dialog)
    TextView mDialog;
    @BindView(R.id.sidebar_A_N)
    SideBar_A_N mSidebarAN;
    @BindView(R.id.sidebar_O_Z)
    SideBar_O_Z mSidebarOZ;


    private Context mContext;
    private View onCreateView;

    private NormalMaterialPriceListAdapter mAdapter;
    public List<NormalMaterialPrice> mListCache = new ArrayList<>();
    private List<NormalMaterialPrice> mList = new ArrayList<>();

    public RankDBhelper mDBhelper;
    private String name = "全部";

    public static final int ADD_ITEM = 0, DELETE_ALL_DATA = 1, FILTRATE_BE_MODIFIED_DATA = 2;

//    public static NormalMaterialFragment newInstance(GuardStonePriceDBhelper mDBhelper) {
//        NormalMaterialFragment newFragment = new NormalMaterialFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("11",1);
//        newFragment.setArguments(bundle);
//        return newFragment;
//
//    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            MenuActivity.fragmentPosition = MenuActivity.NORMAL_MATERIAL_FRAGMENT;
            Log.i(TAG, "setUserVisibleHint: 看到了");
        } else {
            Log.i(TAG, "setUserVisibleHint: 看不到");
        }
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle args = getArguments();
//        if (args != null) {
//            mDBhelper = args.getParcelable("");
//        }
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onCreateView = inflater.inflate(R.layout.fragment_normal_materials, container, false);
        ButterKnife.bind(this, onCreateView);
        mContext = getActivity();
        initData();
        return onCreateView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDBhelper.closeSQLiteDatabase();
    }

    private void initData() {

        mDBhelper = new RankDBhelper(mContext, mListCache);
        mDBhelper.onlyQueryAllData();
        if (mListCache.size() < 1) {
            Log.i(TAG, "initData: ");
            addRecyclerViewData();
            pinYinSort(mListCache);
            mDBhelper.addAllData();
        } else {
            pinYinSort(mListCache);
        }

        initFirstSpinner();
        initRecyclerView();

        mSidebarAN.setTextView(mDialog);
        mSidebarOZ.setTextView(mDialog);
        mSidebarAN.setOnTouchingLetterChangedListener(new SideBar_A_N.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) { /* 该字母首次出现的位置*/

                int position = getPositionForSelection(s.charAt(0));
                Log.i(TAG, "onTouchingLetterChanged: position:" + position);
                if (position != -1) {

                    if (position > 3) {
                        mRecyclerView.scrollToPosition(position + 3);
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
                Log.i(TAG, "onTouchingLetterChanged: position:" + position);
                if (position != -1) {

                    if (position > 3) {
                        mRecyclerView.scrollToPosition(position + 3);
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
            if (Fpinyin != null && Fpinyin.length() > 0) {
                char first = Fpinyin.toUpperCase().charAt(0);
                if (first == selection) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void pinYinSort(List<NormalMaterialPrice> mList) {
        for (int i = 0; i < mList.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(mList.get(i).getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            NormalMaterialPrice mBean = mList.get(i);
            mBean.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                mBean.setFirstPinYin(Fpinyin);
            } else {
                mBean.setFirstPinYin("#");
            }
        }
        Collections.sort(mList, new NormalMaterialPinyinComparator());
    }

    public void setOnOptionsItemSelected(int selecte) {
        if (mAdapter != null) {
            switch (selecte) {
                case ADD_ITEM:
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = inflater.inflate(R.layout.inflate_addsqlitedata_normal_material_price, null);
                    final EditText mEtName = (EditText) view.findViewById(R.id.et_name);
                    final EditText mEtNum = (EditText) view.findViewById(R.id.et_num);
                    final EditText mEtMinPrice = (EditText) view.findViewById(R.id.et_minPrice);
                    final EditText mEtMaxPrice = (EditText) view.findViewById(R.id.et_maxPrice);
                    new AlertDialog.Builder(mContext)
                            .setView(view)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mDBhelper.addData(mEtName.getText().toString(), mEtNum.getText().toString(), mEtMinPrice.getText().toString() + "--" + mEtMaxPrice.getText().toString());
                                    mListCache.add(new NormalMaterialPrice(mEtName.getText().toString(),
                                            mEtNum.getText().toString(), mEtMinPrice.getText().toString() + "--" + mEtMaxPrice.getText().toString(),
                                            1
                                    ));
                                    mList.clear();
                                    pinYinSort(mListCache);
                                    mList.addAll(mListCache);
                                    mAdapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                    break;
                case DELETE_ALL_DATA:
                    new AlertDialog.Builder(mContext)
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
                case FILTRATE_BE_MODIFIED_DATA:
                    mList.clear();
                    for (int i = 0; i < mListCache.size(); i++) {
                        if (mListCache.get(i).getIsRefresh() == 1) {
                            mList.add(mListCache.get(i));
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                    break;

                default:
                    break;
            }
        }
    }

    private void initRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new NormalMaterialPriceListAdapter(mContext, mList);
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);
        mAdapter.setOnItemLongClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {

            @Override
            public void onItemLongClick(View view2, int position) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.inflate_addsqlitedata_normal_material_price, null);
                final EditText mEtName = (EditText) view.findViewById(R.id.et_name);
                final EditText mEtNum = (EditText) view.findViewById(R.id.et_num);
                final EditText mEtMinPrice = (EditText) view.findViewById(R.id.et_minPrice);
                final EditText mEtMaxPrice = (EditText) view.findViewById(R.id.et_maxPrice);

                final NormalMaterialPrice mNormalMaterialPrice = mList.get(position);
                mEtName.setText(mNormalMaterialPrice.getName());
                mEtNum.setText(mNormalMaterialPrice.getNum());

                String price[] = mNormalMaterialPrice.getPrice().split("--");
                if (price.length > 0) {
                    mEtMinPrice.setText(price[0]);
                }
                if (price.length > 1) {
                    mEtMaxPrice.setText(price[1]);
                }

                new AlertDialog.Builder(mContext)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mDBhelper.updateData(mNormalMaterialPrice.getName(), mEtName.getText().toString(), mEtNum.getText().toString(), mEtMinPrice.getText().toString() + "--" + mEtMaxPrice.getText().toString());
                                mList.clear();
                                mListCache.clear();
                                mDBhelper.onlyQueryAllData();
                                pinYinSort(mListCache);
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
                                mDBhelper.deleteData(mNormalMaterialPrice.getName());
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
        Spinner spinner = (Spinner) onCreateView.findViewById(R.id.spinner_first);

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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, list);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.adapters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                name = list.get(position).substring(2);
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

    public void reInitRecyclerViewNameData(String name) {
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {
//            Log.i(TAG, "reInitRecyclerViewNameData: name:"+name);
//            Log.i(TAG, "reInitRecyclerViewNameData: mList.get(i).getName():"+mListCache.get(i).getName());
            if (mListCache.get(i).getName().contains(name)) {
                mList.add(mListCache.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }


    private void addRecyclerViewData() {
        mListCache.add(new NormalMaterialPrice("土蜂幼虫",
                "1", "98--198", 0
        ));
        mListCache.add(new NormalMaterialPrice("杀人蜂幼虫",
                "1", "98--222", 0
        ));
        mListCache.add(new NormalMaterialPrice("山青虫",
                "1", "99--133", 0
        ));
        mListCache.add(new NormalMaterialPrice("蓝闪蝶",
                "1", "520--655", 0
        ));
        mListCache.add(new NormalMaterialPrice("红角蝶",
                "1", "98--198", 0
        ));
        mListCache.add(new NormalMaterialPrice("黏着的白蚁",
                "1", "119--", 0
        ));
        mListCache.add(new NormalMaterialPrice("上兽骨",
                "1", "498--698", 0
        ));
        mListCache.add(new NormalMaterialPrice("兽骨[大]",
                "1", "455--571", 0
        ));
        mListCache.add(new NormalMaterialPrice("兽骨[中]",
                "1", "390--435", 0
        ));
        mListCache.add(new NormalMaterialPrice("上龙骨",
                "1", "577--655", 0
        ));
        mListCache.add(new NormalMaterialPrice("龙骨[大]",
                "1", "300--398", 0
        ));
        mListCache.add(new NormalMaterialPrice("龙骨[中]",
                "1", "--398", 0
        ));
        mListCache.add(new NormalMaterialPrice("圆盘石",
                "3", "333", 0
        ));
        mListCache.add(new NormalMaterialPrice("辉水晶",
                "1", "332-493", 0
        ));
        mListCache.add(new NormalMaterialPrice("光水晶",
                "1", "134-312", 0
        ));
        mListCache.add(new NormalMaterialPrice("辉龙石",
                "1；10", "114-119；498-598", 0
        ));
        mListCache.add(new NormalMaterialPrice("灵鹤石",
                "1", "96--189", 0
        ));
        mListCache.add(new NormalMaterialPrice("蓝蘑菇",
                "50", "748-1111", 0
        ));
        mListCache.add(new NormalMaterialPrice("硝化蘑菇",
                "10", "748--1555", 0
        ));
        mListCache.add(new NormalMaterialPrice("解毒草",
                "10", "398", 0
        ));
        mListCache.add(new NormalMaterialPrice("睡眠草",
                "50", "1888", 0
        ));
        mListCache.add(new NormalMaterialPrice("霜降草",
                "10", "405", 0
        ));
        mListCache.add(new NormalMaterialPrice("黏着草",
                "50", "2000", 0
        ));
        mListCache.add(new NormalMaterialPrice("大王花",
                "50", "2398-2888", 0
        ));
        mListCache.add(new NormalMaterialPrice("龟壳果",
                "1", "120", 0
        ));
        mListCache.add(new NormalMaterialPrice("晶化粉尘",
                "5", "544-698", 0
        ));

        mListCache.add(new NormalMaterialPrice("生肉",
                "10", "998", 0
        ));
        mListCache.add(new NormalMaterialPrice("蜂蜜",
                "50", "998", 0
        ));
        mListCache.add(new NormalMaterialPrice("爆裂龙鱼",
                "50", "2398", 0
        ));
        mListCache.add(new NormalMaterialPrice("沙丁鱼",
                "10", "1099", 0
        ));


    }

}
