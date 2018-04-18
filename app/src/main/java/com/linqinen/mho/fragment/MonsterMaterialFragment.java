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
import com.linqinen.mho.adapter.MaterialPriceListAdapter;
import com.linqinen.mho.adapter.MyRecyclerViewAdapter;
import com.linqinen.mho.adapter.ScaleInAnimatorAdapter;
import com.linqinen.mho.bean.MaterialPrice;
import com.linqinen.mho.sqlite.monstermaterial.MonsterMaterialDBhelper;
import com.linqinen.mho.tools.comparator.MonsterMaterialPinyinComparator;
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

import static com.linqinen.mho.fragment.NormalMaterialFragment.ADD_ITEM;
import static com.linqinen.mho.fragment.NormalMaterialFragment.DELETE_ALL_DATA;
import static com.linqinen.mho.fragment.NormalMaterialFragment.FILTRATE_BE_MODIFIED_DATA;

/**
 * Created by lin on 2017/1/4.
 */

public class MonsterMaterialFragment extends BasicFragment {

    private final String TAG = "MonsterMaterial";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.sidebar_A_N)
    SideBar_A_N mSidebarAN;
    @BindView(R.id.sidebar_O_Z)
    SideBar_O_Z mSidebarOZ;
    @BindView(R.id.dialog)
    TextView mDialog;

    private Context mContext;
    private View onCreateView;

    int a;
    String toString = "";
    private MaterialPriceListAdapter mAdapter;
    public List<MaterialPrice> mListCache = new ArrayList<>();
//    public List<MaterialPrice> mListCursorCache = new ArrayList<>();
    private List<MaterialPrice> mList = new ArrayList<>();

    public MonsterMaterialDBhelper mDBhelper;
    private String name = "全部";

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            MenuActivity.fragmentPosition = MenuActivity.MONSTER_MATERIAL_FRAGMENT;
            Log.i(TAG, "setUserVisibleHint: 看到了");
        } else {
            Log.i(TAG, "setUserVisibleHint: 看不到");
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onCreateView = inflater.inflate(R.layout.fragment_monster_materials, container, false);
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

    /**
     * 初始化数据
     */
    private void initData() {

        mDBhelper = new MonsterMaterialDBhelper(mContext, mListCache);
        mDBhelper.onlyQueryAllData();
        if (mListCache.size() < 1) {
            addRecyclerViewData();
            pinYinSort(mListCache);
            mDBhelper.addAllData();
        } else {
            initDBHelperDataToList();
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

    private void pinYinSort(List<MaterialPrice> mList) {
        for (int i = 0; i < mList.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(mList.get(i).getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            MaterialPrice mBean = mList.get(i);
            mBean.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                mBean.setFirstPinYin(Fpinyin);
            } else {
                mBean.setFirstPinYin("#");
            }
        }
        Collections.sort(mList, new MonsterMaterialPinyinComparator());
    }

    public void setOnOptionsItemSelected(int selecte) {
        if (mAdapter != null) {
            switch (selecte) {
                case ADD_ITEM:
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View view = inflater.inflate(R.layout.inflate_addsqlitedata_monster_material, null);

                    new AlertDialog.Builder(mContext)
                            .setView(view)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final EditText name = addData(view);
                                    mDBhelper.addData(name.getText().toString(), toString);
                                    mListCache.clear();
                                    mDBhelper.onlyQueryAllData();
                                    initDBHelperDataToList();
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

    private EditText addData(View view) {
        final EditText name;
        final EditText tv_materialName_1, tv_materialName_2, tv_materialName_3, tv_materialName_4, tv_materialName_5, tv_materialName_6,
                tv_materialName_7, tv_materialName_8, tv_materialName_9, tv_materialName_10, tv_materialName_11, tv_materialName_12;
        final EditText tv_materialNum_1, tv_materialNum_2, tv_materialNum_3, tv_materialNum_4, tv_materialNum_5, tv_materialNum_6,
                tv_materialNum_7, tv_materialNum_8, tv_materialNum_9, tv_materialNum_10, tv_materialNum_11, tv_materialNum_12;
        final EditText tv_materialPrice_1, tv_materialPrice_2, tv_materialPrice_3, tv_materialPrice_4, tv_materialPrice_5,
                tv_materialPrice_6, tv_materialPrice_7, tv_materialPrice_8, tv_materialPrice_9, tv_materialPrice_10, tv_materialPrice_11, tv_materialPrice_12;
        final EditText tv_special_1, tv_special_2, tv_special_3, tv_special_4, tv_special_5,
                tv_special_6, tv_special_7, tv_special_8, tv_special_9, tv_special_10, tv_special_11, tv_special_12;

        name = (EditText) view.findViewById(R.id.tv_monsterName);

        tv_materialName_1 = (EditText) view.findViewById(R.id.tv_materialName_1);
        tv_materialName_2 = (EditText) view.findViewById(R.id.tv_materialName_2);
        tv_materialName_3 = (EditText) view.findViewById(R.id.tv_materialName_3);
        tv_materialName_4 = (EditText) view.findViewById(R.id.tv_materialName_4);
        tv_materialName_5 = (EditText) view.findViewById(R.id.tv_materialName_5);
        tv_materialName_6 = (EditText) view.findViewById(R.id.tv_materialName_6);
        tv_materialName_7 = (EditText) view.findViewById(R.id.tv_materialName_7);
        tv_materialName_8 = (EditText) view.findViewById(R.id.tv_materialName_8);
        tv_materialName_9 = (EditText) view.findViewById(R.id.tv_materialName_9);
        tv_materialName_10 = (EditText) view.findViewById(R.id.tv_materialName_10);
        tv_materialName_11 = (EditText) view.findViewById(R.id.tv_materialName_11);
        tv_materialName_12 = (EditText) view.findViewById(R.id.tv_materialName_12);

        tv_materialNum_1 = (EditText) view.findViewById(R.id.tv_materialNum_1);
        tv_materialNum_2 = (EditText) view.findViewById(R.id.tv_materialNum_2);
        tv_materialNum_3 = (EditText) view.findViewById(R.id.tv_materialNum_3);
        tv_materialNum_4 = (EditText) view.findViewById(R.id.tv_materialNum_4);
        tv_materialNum_5 = (EditText) view.findViewById(R.id.tv_materialNum_5);
        tv_materialNum_6 = (EditText) view.findViewById(R.id.tv_materialNum_6);
        tv_materialNum_7 = (EditText) view.findViewById(R.id.tv_materialNum_7);
        tv_materialNum_8 = (EditText) view.findViewById(R.id.tv_materialNum_8);
        tv_materialNum_9 = (EditText) view.findViewById(R.id.tv_materialNum_9);
        tv_materialNum_10 = (EditText) view.findViewById(R.id.tv_materialNum_10);
        tv_materialNum_11 = (EditText) view.findViewById(R.id.tv_materialNum_11);
        tv_materialNum_12 = (EditText) view.findViewById(R.id.tv_materialNum_12);

        tv_materialPrice_1 = (EditText) view.findViewById(R.id.tv_materialPrice_1);
        tv_materialPrice_2 = (EditText) view.findViewById(R.id.tv_materialPrice_2);
        tv_materialPrice_3 = (EditText) view.findViewById(R.id.tv_materialPrice_3);
        tv_materialPrice_4 = (EditText) view.findViewById(R.id.tv_materialPrice_4);
        tv_materialPrice_5 = (EditText) view.findViewById(R.id.tv_materialPrice_5);
        tv_materialPrice_6 = (EditText) view.findViewById(R.id.tv_materialPrice_6);
        tv_materialPrice_7 = (EditText) view.findViewById(R.id.tv_materialPrice_7);
        tv_materialPrice_8 = (EditText) view.findViewById(R.id.tv_materialPrice_8);
        tv_materialPrice_9 = (EditText) view.findViewById(R.id.tv_materialPrice_9);
        tv_materialPrice_10 = (EditText) view.findViewById(R.id.tv_materialPrice_10);
        tv_materialPrice_11 = (EditText) view.findViewById(R.id.tv_materialPrice_11);
        tv_materialPrice_12 = (EditText) view.findViewById(R.id.tv_materialPrice_12);

        tv_special_1 = (EditText) view.findViewById(R.id.tv_special_1);
        tv_special_2 = (EditText) view.findViewById(R.id.tv_special_2);
        tv_special_3 = (EditText) view.findViewById(R.id.tv_special_3);
        tv_special_4 = (EditText) view.findViewById(R.id.tv_special_4);
        tv_special_5 = (EditText) view.findViewById(R.id.tv_special_5);
        tv_special_6 = (EditText) view.findViewById(R.id.tv_special_6);
        tv_special_7 = (EditText) view.findViewById(R.id.tv_special_7);
        tv_special_8 = (EditText) view.findViewById(R.id.tv_special_8);
        tv_special_9 = (EditText) view.findViewById(R.id.tv_special_9);
        tv_special_10 = (EditText) view.findViewById(R.id.tv_special_10);
        tv_special_11 = (EditText) view.findViewById(R.id.tv_special_11);
        tv_special_12 = (EditText) view.findViewById(R.id.tv_special_12);

        final EditText mEditText[] = {name, tv_materialName_1, tv_materialNum_1, tv_materialPrice_1, tv_special_1,
                tv_materialName_2, tv_materialNum_2, tv_materialPrice_2, tv_special_2,
                tv_materialName_3, tv_materialNum_3, tv_materialPrice_3, tv_special_3,
                tv_materialName_4, tv_materialNum_4, tv_materialPrice_4, tv_special_4,
                tv_materialName_5, tv_materialNum_5, tv_materialPrice_5, tv_special_5,
                tv_materialName_6, tv_materialNum_6, tv_materialPrice_6, tv_special_6,
                tv_materialName_7, tv_materialNum_7, tv_materialPrice_7, tv_special_7,
                tv_materialName_8, tv_materialNum_8, tv_materialPrice_8, tv_special_8,
                tv_materialName_9, tv_materialNum_9, tv_materialPrice_9, tv_special_9,
                tv_materialName_10, tv_materialNum_10, tv_materialPrice_10, tv_special_10,
                tv_materialName_11, tv_materialNum_11, tv_materialPrice_11, tv_special_11,
                tv_materialName_12, tv_materialNum_12, tv_materialPrice_12, tv_special_12};

        toString = "";

        for (int i = 0; i < mEditText.length; i++) {
            toString = toString + mEditText[i].getText().toString() + ",";
        }

        return name;
    }

    private EditText editData(View view, MaterialPrice mMaterialPrice) {
        final EditText name;
        final EditText tv_materialName_1, tv_materialName_2, tv_materialName_3, tv_materialName_4, tv_materialName_5, tv_materialName_6,
                tv_materialName_7, tv_materialName_8, tv_materialName_9, tv_materialName_10, tv_materialName_11, tv_materialName_12;
        final EditText tv_materialNum_1, tv_materialNum_2, tv_materialNum_3, tv_materialNum_4, tv_materialNum_5, tv_materialNum_6,
                tv_materialNum_7, tv_materialNum_8, tv_materialNum_9, tv_materialNum_10, tv_materialNum_11, tv_materialNum_12;
        final EditText tv_materialPrice_1, tv_materialPrice_2, tv_materialPrice_3, tv_materialPrice_4, tv_materialPrice_5,
                tv_materialPrice_6, tv_materialPrice_7, tv_materialPrice_8, tv_materialPrice_9, tv_materialPrice_10, tv_materialPrice_11, tv_materialPrice_12;
        final EditText tv_special_1, tv_special_2, tv_special_3, tv_special_4, tv_special_5,
                tv_special_6, tv_special_7, tv_special_8, tv_special_9, tv_special_10, tv_special_11, tv_special_12;

        name = (EditText) view.findViewById(R.id.tv_monsterName);

        tv_materialName_1 = (EditText) view.findViewById(R.id.tv_materialName_1);
        tv_materialName_2 = (EditText) view.findViewById(R.id.tv_materialName_2);
        tv_materialName_3 = (EditText) view.findViewById(R.id.tv_materialName_3);
        tv_materialName_4 = (EditText) view.findViewById(R.id.tv_materialName_4);
        tv_materialName_5 = (EditText) view.findViewById(R.id.tv_materialName_5);
        tv_materialName_6 = (EditText) view.findViewById(R.id.tv_materialName_6);
        tv_materialName_7 = (EditText) view.findViewById(R.id.tv_materialName_7);
        tv_materialName_8 = (EditText) view.findViewById(R.id.tv_materialName_8);
        tv_materialName_9 = (EditText) view.findViewById(R.id.tv_materialName_9);
        tv_materialName_10 = (EditText) view.findViewById(R.id.tv_materialName_10);
        tv_materialName_11 = (EditText) view.findViewById(R.id.tv_materialName_11);
        tv_materialName_12 = (EditText) view.findViewById(R.id.tv_materialName_12);

        tv_materialNum_1 = (EditText) view.findViewById(R.id.tv_materialNum_1);
        tv_materialNum_2 = (EditText) view.findViewById(R.id.tv_materialNum_2);
        tv_materialNum_3 = (EditText) view.findViewById(R.id.tv_materialNum_3);
        tv_materialNum_4 = (EditText) view.findViewById(R.id.tv_materialNum_4);
        tv_materialNum_5 = (EditText) view.findViewById(R.id.tv_materialNum_5);
        tv_materialNum_6 = (EditText) view.findViewById(R.id.tv_materialNum_6);
        tv_materialNum_7 = (EditText) view.findViewById(R.id.tv_materialNum_7);
        tv_materialNum_8 = (EditText) view.findViewById(R.id.tv_materialNum_8);
        tv_materialNum_9 = (EditText) view.findViewById(R.id.tv_materialNum_9);
        tv_materialNum_10 = (EditText) view.findViewById(R.id.tv_materialNum_10);
        tv_materialNum_11 = (EditText) view.findViewById(R.id.tv_materialNum_11);
        tv_materialNum_12 = (EditText) view.findViewById(R.id.tv_materialNum_12);

        tv_materialPrice_1 = (EditText) view.findViewById(R.id.tv_materialPrice_1);
        tv_materialPrice_2 = (EditText) view.findViewById(R.id.tv_materialPrice_2);
        tv_materialPrice_3 = (EditText) view.findViewById(R.id.tv_materialPrice_3);
        tv_materialPrice_4 = (EditText) view.findViewById(R.id.tv_materialPrice_4);
        tv_materialPrice_5 = (EditText) view.findViewById(R.id.tv_materialPrice_5);
        tv_materialPrice_6 = (EditText) view.findViewById(R.id.tv_materialPrice_6);
        tv_materialPrice_7 = (EditText) view.findViewById(R.id.tv_materialPrice_7);
        tv_materialPrice_8 = (EditText) view.findViewById(R.id.tv_materialPrice_8);
        tv_materialPrice_9 = (EditText) view.findViewById(R.id.tv_materialPrice_9);
        tv_materialPrice_10 = (EditText) view.findViewById(R.id.tv_materialPrice_10);
        tv_materialPrice_11 = (EditText) view.findViewById(R.id.tv_materialPrice_11);
        tv_materialPrice_12 = (EditText) view.findViewById(R.id.tv_materialPrice_12);

        tv_special_1 = (EditText) view.findViewById(R.id.tv_special_1);
        tv_special_2 = (EditText) view.findViewById(R.id.tv_special_2);
        tv_special_3 = (EditText) view.findViewById(R.id.tv_special_3);
        tv_special_4 = (EditText) view.findViewById(R.id.tv_special_4);
        tv_special_5 = (EditText) view.findViewById(R.id.tv_special_5);
        tv_special_6 = (EditText) view.findViewById(R.id.tv_special_6);
        tv_special_7 = (EditText) view.findViewById(R.id.tv_special_7);
        tv_special_8 = (EditText) view.findViewById(R.id.tv_special_8);
        tv_special_9 = (EditText) view.findViewById(R.id.tv_special_9);
        tv_special_10 = (EditText) view.findViewById(R.id.tv_special_10);
        tv_special_11 = (EditText) view.findViewById(R.id.tv_special_11);
        tv_special_12 = (EditText) view.findViewById(R.id.tv_special_12);

        final EditText mEditText[] = {name, tv_materialName_1, tv_materialNum_1, tv_materialPrice_1, tv_special_1,
                tv_materialName_2, tv_materialNum_2, tv_materialPrice_2, tv_special_2,
                tv_materialName_3, tv_materialNum_3, tv_materialPrice_3, tv_special_3,
                tv_materialName_4, tv_materialNum_4, tv_materialPrice_4, tv_special_4,
                tv_materialName_5, tv_materialNum_5, tv_materialPrice_5, tv_special_5,
                tv_materialName_6, tv_materialNum_6, tv_materialPrice_6, tv_special_6,
                tv_materialName_7, tv_materialNum_7, tv_materialPrice_7, tv_special_7,
                tv_materialName_8, tv_materialNum_8, tv_materialPrice_8, tv_special_8,
                tv_materialName_9, tv_materialNum_9, tv_materialPrice_9, tv_special_9,
                tv_materialName_10, tv_materialNum_10, tv_materialPrice_10, tv_special_10,
                tv_materialName_11, tv_materialNum_11, tv_materialPrice_11, tv_special_11,
                tv_materialName_12, tv_materialNum_12, tv_materialPrice_12, tv_special_12};

        String split[] = mMaterialPrice.toString().split(",");
        Log.i(TAG, "editData: split:" + split.length);
        for (int i = 0; i < mEditText.length; i++) {
            if (i < split.length) {
                mEditText[i].setText(split[i]);
            }
        }

        return name;
    }


    private void initRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MaterialPriceListAdapter(mContext, mList);
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);

        mAdapter.setOnItemLongClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view2, final int position) {

                Log.i(TAG, "onItemLongClick: position" + position);
                Log.i(TAG, "onItemLongClick: position" + mList.get(position).toString());
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View view = inflater.inflate(R.layout.inflate_addsqlitedata_monster_material, null);

                final EditText etName = editData(view, mList.get(position));
                etName.setEnabled(false);
                new AlertDialog.Builder(mContext)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                addData(view);
                                mDBhelper.updateData(etName.getText().toString(), toString);
                                mList.clear();
                                mListCache.clear();
                                mDBhelper.onlyQueryAllData();
                                initDBHelperDataToList();
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
                                mDBhelper.deleteData(etName.getText().toString());
                                mList.clear();
                                mListCache.clear();
                                mDBhelper.onlyQueryAllData();
                                initDBHelperDataToList();
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

//        final String[] mItems = new String[mListCache.size() + 1];
//        mItems[0] = "全部";
//        for (int i = 1; i < mListCache.size() + 1; i++) {
//            mItems[i] = mListCache.get(i - 1).getName();
//        }

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mListCache.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(mListCache.get(i).getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();
            list.add(Fpinyin + "." + mListCache.get(i).getName());
        }

//        /**java中的中文排序逻辑*/
//        Collator coll = Collator.getInstance(Locale.CHINESE);
//        Collections.sort(list, coll);

        list.add(0, "全部");
        Log.i(TAG, "initLeftSpinner: list:" + list.toString());

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                name = list.get(position).substring(2);
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
        Spinner spinner = (Spinner) onCreateView.findViewById(R.id.spinner_second);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mListCache.size(); i++) {
            String materialName[] = {mListCache.get(i).getMaterialName_1(), mListCache.get(i).getMaterialName_2(),
                    mListCache.get(i).getMaterialName_3(), mListCache.get(i).getMaterialName_4(),
                    mListCache.get(i).getMaterialName_5(), mListCache.get(i).getMaterialName_6(),
                    mListCache.get(i).getMaterialName_7(), mListCache.get(i).getMaterialName_8(),
                    mListCache.get(i).getMaterialName_9(), mListCache.get(i).getMaterialName_10(),
                    mListCache.get(i).getMaterialName_11(), mListCache.get(i).getMaterialName_12()
            };
            String material[] = {mListCache.get(i).getSpecial_1(), mListCache.get(i).getSpecial_2(),
                    mListCache.get(i).getSpecial_3(), mListCache.get(i).getSpecial_4(),
                    mListCache.get(i).getSpecial_5(), mListCache.get(i).getSpecial_6(),
                    mListCache.get(i).getSpecial_7(), mListCache.get(i).getSpecial_8(),
                    mListCache.get(i).getSpecial_9(), mListCache.get(i).getSpecial_10(),
                    mListCache.get(i).getSpecial_11(), mListCache.get(i).getSpecial_12()
            };
            for (int j = 0; j < material.length; j++) {
                if (material[j] != null && !material[j].equals("")) {
                    String pinyin = PinyinUtils.getPingYin(materialName[j]);
                    if (pinyin.length() > 0 && !pinyin.equals("_")) {
                        String Fpinyin = pinyin.substring(0, 1).toUpperCase();
                        list.add(Fpinyin + "." + materialName[j]);
                    }
                }
            }
        }

        /**java中的中文排序逻辑*/
        Collator coll = Collator.getInstance(Locale.CHINESE);
        Collections.sort(list, coll);

        list.add(0, "全部");

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                String skillName = mItems[position];
                String skillName = list.get(position);
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

    public void reInitRecyclerViewNameData(String name) {
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {
            if (mListCache.get(i).getName().contains(name)) {
                mList.add(mListCache.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void reInitRecyclerViewSpecialMaterialNameData(String name) {
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {
            String materialName[] = {mListCache.get(i).getMaterialName_1(), mListCache.get(i).getMaterialName_2(),
                    mListCache.get(i).getMaterialName_3(), mListCache.get(i).getMaterialName_4(),
                    mListCache.get(i).getMaterialName_5(), mListCache.get(i).getMaterialName_6(),
                    mListCache.get(i).getMaterialName_7(), mListCache.get(i).getMaterialName_8(),
                    mListCache.get(i).getMaterialName_9(), mListCache.get(i).getMaterialName_10(),
                    mListCache.get(i).getMaterialName_11(), mListCache.get(i).getMaterialName_12()
            };
            for (int j = 0; j < materialName.length; j++) {
                if (!materialName[j].equals("") && name.contains(materialName[j])) {
                    mList.add(mListCache.get(i));
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initDBHelperDataToList() {
        for (int i = 0; i < mListCache.size(); i++) {
            String toString = mListCache.get(i).getToString();
//            Log.i(TAG, "initDBHelperDataToList: toString:"+toString);
            if (toString != null) {
                String split[] = toString.split(",");
//            Log.i(TAG, "initDBHelperDataToList: splitlength:"+split.length);
//            Log.i(TAG, "initDBHelperDataToList: split:"+Arrays.asList(split));
                String name = mListCache.get(i).getName();
                int isRefresh = mListCache.get(i).getIsRefresh();
                mListCache.remove(i);

                a = 0;
                mListCache.add(i, new MaterialPrice(name,
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        ooo(split), ooo(split), ooo(split), ooo(split),
                        isRefresh
                ));
            }

        }
    }

    private String ooo(String split[]) {
        a++;
        if (split.length > a) {
            return split[a];
        } else {
            return "";
        }

    }

    private void addRecyclerViewData() {

        addAncientDragon();
        addBird();
        addCarapace();
        addDragon();
        addPterosaur();
        addTuscampa();
        addOthers();
//        mListCursorCache.addAll(mListCache);
    }

    private void addOthers(){
        mListCache.add(new MaterialPrice("电甲虫",
                "刚尾须", "1", "798--2222", "_",
                "坚尾须", "1", "2998", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("雷狼龙",
                "逆鳞", "1", "385-855", "_",
                "", "1", "", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("恐暴龙",
                "宝玉", "1", "1088", "_",
                "", "1", "", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
    }

    /**
     * 古龙
     */
    private void addAncientDragon() {
        mListCache.add(new MaterialPrice("吞渊龙",
                "古龙之血", "3", "998", "_",
                "古龙之燃血", "1", "198", "_",
                "岩壳", "4", "498", "_",
                "坚岩壳", "3", "498", "_",
                "坚骨甲", "4", "498", "_",
                "骨板甲", "3", "398", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("燎渊龙",
                "龙神玉", "1", "589", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("麒麟",
                "白银的髭", "1", "1555", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("浮岳龙",
                "神龙木", "1", "1555", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("五相荒厄龙",
                "凶眼", "1", "785-995", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
    }

    /**
     * 甲壳
     */
    private void addCarapace() {
        mListCache.add(new MaterialPrice("灰晶蝎",
                "毒刚毛", "1", "998", "_",
                "毒豪毛", "1", "493-1166", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("尾晶蝎",
                "_", "_", "_", "_",
                "毒豪毛", "1", "635-2098", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("鬼狩蛛",
                "复鬼眼", "1", "498", "_",
                "_", "1", "", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));

        mListCache.add(new MaterialPrice("剑豪镰蟹",
                "坚钳", "3", "998", "_",
                "坚脚", "3", "998", "_",
                "坚壳", "3", "998", "_",
                "尖爪", "3", "998", "_",
                "极品金色大真珠", "1", "398-998", "_",
                "金色大真珠", "1", "2555--2777", "_",
                "极上金真珠", "1", "493", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
    }

    /**
     * 飞鸟
     */
    private void addBird() {

        mListCache.add(new MaterialPrice("金眠鸟",
                "稀胃石", "1", "598--3111", "_",
                "粘稠毒液", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("银眠鸟",
                "稀胃石", "1", "798--1798", "_",
                "珍胃石", "1", "687-1500", "_",
                "尖爪", "3", "598", "_",
                "银羽毛", "1", "598", "_",
                "晶莹的眠液", "4", "355", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));

        mListCache.add(new MaterialPrice("独耳黑狼鸟",
                "钩爪", "2;5;10", "498;998;998", "_",
                "厚鳞", "10", "998-1498", "_",
                "重壳", "10", "998", "_",
                "重髭", "1", "1185-1193", "_",
                "刚翼膜", "1", "348-498", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("黑狼鸟",
                "优质鳞", "2", "398", "_",
                "刚翼爪", "3", "498", "_",
                "重髭", "1", "596", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));

        mListCache.add(new MaterialPrice("冰雷鸟",
                "优质皮", "3", "398+1+1;498", "_",
                "刚翼", "3", "598+1+1", "_",
                "亮蓝色黏液", "3", "598;698", "_",
                "湛蓝刚鬓", "3", "598+1+1+1", "_",
                "厚实嗦囊", "1", "1855", "是",
                "极寒嗦囊", "1", "1555", "是",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
    }

    /**
     * 地面上的龙
     */
    private void addDragon() {

        mListCache.add(new MaterialPrice("铠龙",
                "大龙玉", "1", "798-1333", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("荒厄龙",
                "凶眼", "1", "1298", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("熔岩龙",
                "狱岩块", "1", "378", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));

        mListCache.add(new MaterialPrice("砂岩龙",
                "荒泪", "1", "1187-1998", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));

        mListCache.add(new MaterialPrice("晶岩龙",
                "甲壳", "2", "998", "是",
                "头壳", "2", "498", "_",
                "背壳", "2", "498", "_",
                "翼", "3", "898+1", "_",
                "刚翼", "4", "498", "_",
                "靭翼", "2;4", "498+1+1;698", "_",
                "坚甲", "2", "498", "_",
                "坚头壳", "3", "398", "_",
                "重背壳", "2;4", "498;698", "_",
                "重头壳", "2;4", "398-555;698", "_",
                "重甲", "1", "587-698", "_",
                "结晶之泪", "1", "498", "_",
                0
        ));

        mListCache.add(new MaterialPrice("断刃一角龙",
                "坚甲", "1", "588--898", "_",
                "刚翼", "10", "1769", "_",
                "延髓", "5", "1298", "_",
                "坚壳", "3", "598+1+1+1", "_",
                "脉动心脏", "1", "2910", "_",
                "_", "1", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("一角龙",
                "坚甲", "1；2", "498；588--798", "_",
                "刚翼", "4；10", "398；2111", "_",
                "延髓", "3；11", "598；2111", "_",
                "坚壳", "3；10", "698；1998", "_",
                "_", "1", "_", "_",
                "_", "1", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("白一角龙",
                "心脏", "1", "645-787", "_",
                "_", "1", "_", "_",
                "_", "1", "_", "_",
                "_", "_", "_", "_",
                "_", "1", "_", "_",
                "_", "1", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
 mListCache.add(new MaterialPrice("角龙",
                "心脏", "1", "1998", "_",
                "延髓", "1", "1998", "_",
                "_", "1", "_", "_",
                "_", "_", "_", "_",
                "_", "1", "_", "_",
                "_", "1", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("祸星龙",
                "刚翼", "3", "998", "_",
                "尖角", "3", "998", "_",
                "坚壳", "3", "998-1333", "_",
                "灾星龙鳞", "1", "2998", "_",
                "灾星厚龙鳞", "1", "998-1998", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("灭星龙",
                "星辉之血", "1", "1438-1877", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
    }

    /**
     * 飞龙
     */
    private void addPterosaur() {

        mListCache.add(new MaterialPrice("烈焰女王",
                "重壳", "1", "498-655", "_",
                "毒棘", "1", "1248-1395", "_",
                "厚龙鳞", "5", "490", "_",
                "神龙爪", "5", "498", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("苍火龙",
                "逆厚鳞", "1", "3298", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("冰牙龙",
                "瞬间冰冻袋", "1", "850", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));

        mListCache.add(new MaterialPrice("轰龙",
                "颚", "1", "887", "_",
                "大颚", "1", "1185", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("火龙",
                "逆厚鳞", "1", "598", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("雌火龙",
                "优质鳞", "10", "1666", "_",
                "尖爪", "5", "598", "_",
                "刚爪", "10", "998-1998", "_",
                "厚鳞", "2;10", "298;998", "_",
                "大秘棘", "1;5;10", "150;498-598;998", "_",
                "优质棘", "2", "498", "_",
                "毒棘", "1", "898-1555", "_",
                "坚壳", "2", "598-698", "_",
                "重壳", "1;5", "495;985", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));

        mListCache.add(new MaterialPrice("钢龙",
                "逆鳞", "1", "4998", "_",
                "逆厚鳞", "1", "1998", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("红电龙",
                "刚翼", "4", "498", "_",
                "尖牙", "3", "266-398", "_",
                "柔皮", "3", "298-355", "_",
                "超强吸盘", "1", "1198-1555", "_",
                "强光电气袋", "1", "698-798", "_",
                "_", "1", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));


    }

    /**
     * 牙兽
     */
    private void addTuscampa() {
        mListCache.add(new MaterialPrice("剑极狼",
                "凶戾眼", "1", "2998", "_",
                "残暴眼", "1", "1485-2100", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("剑刹狼",
                "修罗眼", "1", "398", "_",
                "_", "1", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("金狮子",
                "黄金的煌毛", "1", "1998-2498", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));

        mListCache.add(new MaterialPrice("雪狮子",
                "刚髭", "1", "1555", "_",
                "刚翼爪", "3", "498", "_",
                "豪髭", "1", "998", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("炎狮子",
                "上头冠", "1", "2998--3998", "_",
                "豪头冠", "1", "1498-3195", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_",
                "_", "_", "_", "_", 0
        ));
        mListCache.add(new MaterialPrice("战鬼河狸兽",
                "利爪", "3", "298", "_",
                "利牙", "5", "398--598", "_",
                "优质皮", "5", "498", "_",
                "坚尾甲", "3", "298-398", "_",
                "尾甲", "1", "298", "_",
                "皮", "1", "300", "_",
                "牙", "2", "798", "_",
                "_", "_", "_", "_",
                "战鬼之眼", "1", "1200", "_",
                "战鬼之血", "1", "498-1700", "_",
                "凶眼", "1", "1998", "_",
                "_", "_", "_", "_", 0
        ));
    }
}
