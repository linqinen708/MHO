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
import com.linqinen.mho.adapter.SuitSkillAttributeAdapter;
import com.linqinen.mho.bean.SuitSkillAttribute;
import com.linqinen.mho.tools.PinyinUtils;
import com.linqinen.mho.tools.SideBar_A_N;
import com.linqinen.mho.tools.SideBar_O_Z;
import com.linqinen.mho.tools.comparator.SuitSkillAttributePinyinComparator;

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

public class SuitSkillAttributeFragment extends BasicFragment {

    private final String TAG = "SuitSkillAttribute";

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

    private Context mContext;
    private View onCreateView;

    private SuitSkillAttributeAdapter mAdapter;

    private List<String> mStringList;

    public ArrayList<SuitSkillAttribute> mListCache = new ArrayList<>();
    private ArrayList<SuitSkillAttribute> mList = new ArrayList<>();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            MenuActivity.fragmentPosition = MenuActivity.SUIT_SKILL_ATTRIBUTE_FRAGMENT;
            Log.i(TAG, "setUserVisibleHint: 看到了");
        } else {
            Log.i(TAG, "setUserVisibleHint: 看不到");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onCreateView = inflater.inflate(R.layout.fragment_suit_skill_attribute, container, false);
        ButterKnife.bind(this, onCreateView);
        mContext = getActivity();
        initData();
        return onCreateView;
    }

    /**
     * 初始化数据
     */
    private void initData() {

        addRecyclerViewData();

        initFirstSpinner();
        initSecondSpinner();
        initThirdSpinner();
        initRecyclerView();
        initRecyclerView_GridView();

        mSidebarAN.setTextView(mDialog);
        mSidebarOZ.setTextView(mDialog);
        mSidebarAN.setOnTouchingLetterChangedListener(new SideBar_A_N.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) { /* 该字母首次出现的位置*/

                int position = getPositionForSelection(s.charAt(0));
                if (position != -1)
                    mRecyclerView.scrollToPosition(position + 1);
            }
        });
        mSidebarOZ.setOnTouchingLetterChangedListener(new SideBar_O_Z.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) { /* 该字母首次出现的位置*/

                int position = getPositionForSelection(s.charAt(0));
                if (position != -1)
                    mRecyclerView.scrollToPosition(position + 1);
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

    private void addRecyclerViewData() {
        addSharpness();
        addHearingProtection();
        addArtisan();
        addDefenserPlus();
        addFocus();
        addCritical();
        addClairvoyant();
        addPoisonResistance();
        addSwordsmanship();
        addThump();
        addAttributeAttack();
        addKO();
        addWindResistance();
        addGuard();
        addRegeneration();
        addResistanceSnowMud();
        friendSort(mListCache);
    }

    private void initRecyclerView() {
//        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
//        mLinearLayoutManager.scrollToPositionWithOffset();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SuitSkillAttributeAdapter(mContext, mList);
        Log.i(TAG, "initRecyclerView: mList：" + mList.toString());
        ScaleInAnimatorAdapter scaleInRecyclerViewAnimationAdapter = new ScaleInAnimatorAdapter(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(scaleInRecyclerViewAnimationAdapter);

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

                    reInitRecyclerViewData(skillName);
                }
//                mRecyclerViewGridview.setVisibility(View.GONE);
                hideAnimator(mRecyclerViewGridview);
                Log.i(TAG, "onItemClick: skillName:"+skillName);
            }
        });
    }



    private void initFirstSpinner() {
//        Spinner spinner = (Spinner) onCreateView.findViewById(R.id.spinner_first);

        mStringList = new ArrayList<>();
//        final List<String> skillNameList = new ArrayList<>();
        for (int i = 0; i < mListCache.size(); i++) {
            String skillName[] = {mListCache.get(i).getSkillName_1(), mListCache.get(i).getSkillName_2(), mListCache.get(i).getSkillName_3(), mListCache.get(i).getSkillName_4(), mListCache.get(i).getSkillName_5()};
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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, mStringList);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.adapters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

    private void initSecondSpinner() {
        Spinner spinner = (Spinner) onCreateView.findViewById(R.id.spinner_second);

        final String[] mItems = {"全部", "头1", "头2", "头3", "手1", "手2", "手3", "衣1", "衣2", "衣3", "腰1", "腰2", "腰3", "腿1", "腿2", "腿3"};

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, mItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    mList.clear();
                    mList.addAll(mListCache);
                    mAdapter.notifyDataSetChanged();
                } else {
                    reInitRecyclerViewHoleData(mItems[position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initThirdSpinner() {
        Spinner spinner = (Spinner) onCreateView.findViewById(R.id.spinner_third);

        String[] mItems = new String[mListCache.size() + 1];
        mItems[0] = "全部";
        for (int i = 1; i < mListCache.size() + 1; i++) {
            mItems[i] = mListCache.get(i - 1).getFirstPinYin() + "." + mListCache.get(i - 1).getName();
        }
        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, mItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    mList.clear();
                    mList.addAll(mListCache);
                    mAdapter.notifyDataSetChanged();
                } else {
                    reInitRecyclerViewNameData(mListCache.get(position - 1).getName());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void reInitRecyclerViewHoleData(String NameHole) {
        mList.clear();
        String name[] = {"头", "手", "衣", "腰", "腿",};
        int j = 0;
        for (int i = 0; i < name.length; i++) {
            if (NameHole.contains(name[i])) {
                j = i;
                break;
            }
        }
        for (int i = 0; i < mListCache.size(); i++) {
            String hole[] = {mListCache.get(i).getHead_hole(), mListCache.get(i).getHand_hole(), mListCache.get(i).getClothes_hole(), mListCache.get(i).getWaist_hole(), mListCache.get(i).getFoot_hole()};
            Log.i(TAG, "reInitRecyclerViewHoleData: hole[j]:" + hole[j] + "\tNameHole:" + NameHole);
            if (hole[j].equals(NameHole)) {
                mList.add(mListCache.get(i));
//                Log.i(TAG, "reInitRecyclerViewHoleData: mList" + mList.toString());
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void reInitRecyclerViewNameData(String name) {
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {
            if (mListCache.get(i).getName().equals(name)) {
                mList.add(mListCache.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void reInitRecyclerViewData(String skillName) {
//        Log.i(TAG, "reInitRecyclerViewData: skillName:"+skillName);
        mList.clear();
        for (int i = 0; i < mListCache.size(); i++) {
            if (mListCache.get(i).getSkillName_1().length() > 0 && skillName.equals(mListCache.get(i).getSkillName_1())) {
//                Log.i(TAG, "reInitRecyclerViewData111: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkillName_2().length() > 0 && skillName.equals(mListCache.get(i).getSkillName_2())) {
//                Log.i(TAG, "reInitRecyclerViewData222: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkillName_3().length() > 0 && skillName.equals(mListCache.get(i).getSkillName_3())) {
//                Log.i(TAG, "reInitRecyclerViewData333: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkillName_4().length() > 0 && skillName.equals(mListCache.get(i).getSkillName_4())) {
//                Log.i(TAG, "reInitRecyclerViewData444: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
                continue;
            }
            if (mListCache.get(i).getSkillName_5().length() > 0 && skillName.equals(mListCache.get(i).getSkillName_5())) {
//                Log.i(TAG, "reInitRecyclerViewData5555: "+mListCache.get(i).getName());
                mList.add(mListCache.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void friendSort(List<SuitSkillAttribute> friendList) {
        for (int i = 0; i < friendList.size(); i++) {
            String pinyin = PinyinUtils.getPingYin(friendList.get(i).getName());
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            SuitSkillAttribute friend = friendList.get(i);
            friend.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                friend.setFirstPinYin(Fpinyin);
            } else {
                friend.setFirstPinYin("#");
            }
        }
        Collections.sort(friendList, new SuitSkillAttributePinyinComparator());
    }

    private void aaaaaaa_______aaaaaaa() {
        mListCache.add(new SuitSkillAttribute("黄速龙王", "",
                "麻痹抵抗", "特殊攻击", "体力", "回复速度", "毒抵抗",
                "头2", "_", "_", "_", "_", "-0", 0,
                "手2", "_", "_", "_", "_", "-0", 0,
                "衣0", "_", "_", "_", "_", "-0", 0,
                "腰2", "_", "_", "_", "_", "-0", 0,
                "腿0", "_", "_", "_", "_", "-0", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
    }

    /**
     * 回避性能
     */
    private void addAvoidPerformance() {

    }

    /**
     * 回复速度
     */
    private void addRegeneration() {
        mListCache.add(new SuitSkillAttribute("电龙", "",
                "回复速度", "广域化", "雷属性攻击", "", "气绝抵抗",
                "头1", "5", "2", "2", "_", "-2", 0,
                "手1", "4", "2", "2", "_", "-2", 0,
                "衣1", "2", "1", "2", "_", "-2", 0,
                "腰2", "4", "2", "2", "_", "-2", 0,
                "腿2", "5", "2", "2", "_", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
    }

    /**
     * 耐泥雪
     */
    private void addResistanceSnowMud() {
        mListCache.add(new SuitSkillAttribute("冰牙龙", "115",
                "耐力消耗", "冰属性攻击", "体术", "耐泥雪", "耐力回复",
                "头2", "2", "3", "1", "1", "-3", 0,
                "手0", "2", "5", "4", "3", "-2", 0,
                "衣2", "3", "2", "1", "2", "-2", 0,
                "腰3", "1", "2", "4", "2", "-2", 0,
                "腿3", "2", "3", "2", "2", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
    }

    /**
     * 加护
     */
    private void addGuard() {

        mListCache.add(new SuitSkillAttribute("岩龙", "",
                "睡眠抵抗", "加护", "防御", "防御性能", "麻痹抵抗",
                "头1", "2", "1", "1", "2", "-2", 0,
                "手2", "2", "1", "2", "2", "-2", 0,
                "衣1", "2", "2", "2", "3", "-2", 0,
                "腰1", "2", "2", "1", "1", "-2", 0,
                "腿1", "2", "2", "1", "2", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
        mListCache.add(new SuitSkillAttribute("岩龙(远)", "",
                "睡眠抵抗", "加护", "防御", "散弹强化", "麻痹抵抗",
                "头1", "2", "1", "1", "2", "-2", 0,
                "手2", "2", "1", "2", "2", "-2", 0,
                "衣1", "2", "2", "2", "3", "-2", 0,
                "腰1", "2", "2", "1", "1", "-2", 0,
                "腿1", "2", "2", "1", "2", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));

        mListCache.add(new SuitSkillAttribute("电甲虫", "",
                "加护", "雷属性攻击", "荷尔蒙", "回避性能", "研磨师",
                "头0", "2", "4", "2", "3", "-3", 0,
                "手1", "2", "3", "1", "3", "-2", 0,
                "衣2", "2", "2", "1", "3", "-2", 0,
                "腰3", "2", "2", "2", "2", "-3", 0,
                "腿2", "2", "2", "1", "2", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
        mListCache.add(new SuitSkillAttribute("电甲虫(远)", "",
                "加护", "雷属性攻击", "荷尔蒙", "回避性能", "快速装填",
                "头0", "2", "4", "2", "3", "-3", 0,
                "手1", "2", "3", "1", "3", "-2", 0,
                "衣2", "2", "2", "1", "3", "-2", 0,
                "腰3", "2", "2", "2", "2", "-3", 0,
                "腿2", "2", "2", "1", "2", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
    }

    /**
     * 风压抵抗
     */
    private void addWindResistance() {
        mListCache.add(new SuitSkillAttribute("樱火龙", "",
                "底力", "火属性攻击", "风压抵抗", "飞龙猎人", "",
                "头1", "3", "3", "3", "2", "-0", 0,
                "手3", "3", "1", "4", "_", "-0", 0,
                "衣2", "3", "3", "3", "_", "-0", 0,
                "腰3", "3", "2", "2", "_", "-0", 0,
                "腿1", "3", "3", "3", "2", "-0", 0,

                "上鳞", "坚壳", "上棘", "逆鳞", "爆炎袋", "延髓",
                "8", "12", "_", "_", "_", "_",
                "_", "12", "8", "6", "_", "_",
                "_", "_", "12", "_", "12", "8",
                "12", "8", "_", "_", "_", "_",
                "_", "_", "12", "_", "12", "_"
        ));
    }


    /**
     * KO术
     */
    private void addKO() {
        mListCache.add(new SuitSkillAttribute("鬼狩蛛", "",
                "KO术", "研磨师", "攻击", "气息", "",
                "头0", "2", "2", "_", "-2", "-0", 0,
                "手2", "2", "2", "2", "-2", "-0", 0,
                "衣0", "2", "3", "2", "-2", "-0", 0,
                "腰3", "2", "2", "_", "-2", "-0", 0,
                "腿0", "2", "1", "3", "-2", "-0", 0,

                "尖爪", "坚壳", "浓液", "鬼眼", "刚毛", "韧蛛丝",
                "4", "6", "_", "_", "_", "_",
                "_", "6", "4", "3", "_", "_",
                "_", "_", "6", "6", "4", "_",
                "6", "4", "_", "_", "_", "_",
                "_", "_", "6", "_", "6", "_"
        ));
        mListCache.add(new SuitSkillAttribute("鬼狩蛛(远)", "",
                "KO术", "快速装填", "攻击", "气息", "",
                "头0", "2", "2", "_", "-2", "-0", 0,
                "手2", "2", "2", "2", "-2", "-0", 0,
                "衣0", "2", "3", "2", "-2", "-0", 0,
                "腰3", "2", "2", "_", "-2", "-0", 0,
                "腿0", "2", "1", "3", "-2", "-0", 0,

                "尖爪", "坚壳", "浓液", "鬼眼", "刚毛", "韧蛛丝",
                "4", "6", "_", "_", "_", "_",
                "_", "6", "4", "3", "_", "_",
                "_", "_", "6", "6", "4", "_",
                "6", "4", "_", "_", "_", "_",
                "_", "_", "6", "_", "6", "_"
        ));

        mListCache.add(new SuitSkillAttribute("砂岩龙", "",
                "KO术", "集中", "强韧", "锁气绝", "耐力回复",
                "头3", "2", "2", "2", "_", "-2", 0,
                "手2", "2", "2", "3", "_", "-2", 0,
                "衣0", "3", "2", "4", "3", "-3", 0,
                "腰2", "1", "2", "_", "2", "-3", 0,
                "腿2", "3", "2", "1", "_", "-2", 0,

                "坚头壳", "坚背壳", "沙泪", "坚壳", "优质翼", "坚腹甲",
                "12", "8", "6", "_", "_", "_",
                "12", "_", "_", "8", "_", "_",
                "8", "_", "_", "12", "_", "_",
                "_", "12", "_", "_", "12", "8",
                "_", "12", "_", "_", "12", "_"
        ));

    }

    /**
     * 锋利度
     */
    private void addSharpness() {

        mListCache.add(new SuitSkillAttribute("麒麟", "120",
                "属性攻击", "加护", "耐力消耗", "属性耐性", "体力",
                "头2", "3", "2", "2", "2", "-3", 0,
                "手3", "2", "1", "2", "2", "-1", 0,
                "衣1", "4", "3", "2", "1", "-2", 0,
                "腰2", "3", "2", "2", "3", "-2", 0,
                "腿2", "3", "2", "2", "2", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
        mListCache.add(new SuitSkillAttribute("恐暴龙", "120",
                "匠", "锋利度", "千里眼", "", "饥饿感",
                "头0", "2", "2", "2", "_", "-2", 0,
                "手1", "1", "3", "2", "_", "-2", 0,
                "衣0", "3", "1", "2", "_", "-2", 0,
                "腰0", "2", "2", "2", "_", "-2", 0,
                "腿1", "2", "2", "2", "_", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
        mListCache.add(new SuitSkillAttribute("恐暴龙(远)", "120",
                "装填", "反动", "千里眼", "", "饥饿感",
                "头0", "2", "2", "2", "_", "-2", 0,
                "手1", "1", "3", "2", "_", "-2", 0,
                "衣0", "3", "1", "2", "_", "-2", 0,
                "腰0", "2", "2", "2", "_", "-2", 0,
                "腿1", "2", "2", "2", "_", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
        mListCache.add(new SuitSkillAttribute("炎狮子", "",
                "锋利度", "气绝抵抗", "回避性能", "耐暑", "耐寒",
                "头2", "1", "_", "4", "2", "-2", 0,
                "手2", "1", "3", "2", "2", "-3", 0,
                "衣2", "2", "1", "2", "2", "-2", 0,
                "腰1", "3", "3", "2", "2", "-2", 0,
                "腿2", "3", "3", "3", "2", "-3", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
        mListCache.add(new SuitSkillAttribute("炎狮子(远)", "",
                "快速装填", "气绝抵抗", "回避性能", "耐暑", "耐寒",
                "头2", "1", "_", "4", "2", "-2", 0,
                "手2", "1", "3", "2", "2", "-3", 0,
                "衣2", "2", "1", "2", "2", "-2", 0,
                "腰1", "3", "3", "2", "2", "-2", 0,
                "腿2", "3", "3", "3", "2", "-3", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));

        mListCache.add(new SuitSkillAttribute("雷狼龙", "110",
                "认真", "雷属性攻击", "锋利度", "牙兽猎人", "气息",
                "头2", "2", "3", "3", "2", "-2", 0,
                "手0", "5", "4", "3", "2", "-3", 0,
                "衣3", "1", "2", "1", "2", "-2", 0,
                "腰3", "3", "2", "2", "2", "-2", 0,
                "腿1", "4", "4", "3", "2", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
        mListCache.add(new SuitSkillAttribute("雷狼龙(远)", "110",
                "认真", "雷属性攻击", "集中", "牙兽猎人", "气息",
                "头2", "3", "3", "3", "2", "-2", 0,
                "手0", "4", "4", "5", "2", "-3", 0,
                "衣3", "1", "2", "1", "2", "-2", 0,
                "腰3", "3", "2", "2", "2", "-2", 0,
                "腿1", "4", "4", "4", "2", "-2", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));

        mListCache.add(new SuitSkillAttribute("尾晶蝎", "",
                "锋利度", "防御性能", "攻击", "特殊攻击", "",
                "头1", "1", "2", "1", "-2", "", 1,
                "手2", "1", "3", "2", "-2", "", 0,
                "衣1", "1", "3", "3", "-2", "", 1,
                "腰3", "3", "3", "2", "-2", "", 0,
                "腿2", "1", "2", "2", "-2", "", 1,

                "尖爪", "刚毛", "坚壳", "紫晶", "大颚", "毒刚毛",
                "8", "12", "", "", "", "",
                "", "", "12", "12", "8", "",
                "12", "8", "", "", "", "",
                "", "12", "8", "", "", "6",
                "", "", "12", "12", "", ""));
        mListCache.add(new SuitSkillAttribute("尾晶蝎(远)", "",
                "通常弹强化", "快速装填", "攻击", "特殊攻击", "",
                "头1", "1", "2", "1", "-2", "", 1,
                "手2", "2", "2", "2", "-2", "", 0,
                "衣1", "1", "2", "3", "-2", "", 1,
                "腰3", "3", "2", "2", "-2", "", 0,
                "腿2", "1", "2", "2", "-2", "", 1,

                "尖爪", "刚毛", "坚壳", "紫晶", "大颚", "毒刚毛",
                "8", "12", "", "", "", "",
                "", "", "12", "12", "8", "",
                "12", "8", "", "", "", "",
                "", "12", "8", "", "", "6",
                "", "", "12", "12", "", ""));
        mListCache.add(new SuitSkillAttribute("黑狼鸟", "",
                "锋利度", "达人", "听觉保护", "防御", "",
                "头2", "2", "3", "3", "-2", "", 0,
                "手2", "1", "2", "1", "-2", "", 1,
                "衣1", "2", "2", "1", "-2", "", 1,
                "腰2", "3", "2", "1", "-2", "", 0,
                "腿1", "3", "2", "1", "-2", "", 0,

                "刚翼爪", "优质鳞", "坚壳", "刚髭", "韧翼膜", "地狱耳",
                "", "12", "", "6", "", "",
                "8", "12", "", "", "", "",
                "12", "8", "", "", "", "",
                "", "", "12", "", "12", "8",
                "", "", "12", "", "12", ""));
        mListCache.add(new SuitSkillAttribute("黑狼鸟(远)", "",
                "贯通弹强化", "达人", "听觉保护", "防御", "",
                "头2", "2", "3", "3", "-2", "", 0,
                "手2", "1", "2", "1", "-2", "", 1,
                "衣1", "2", "2", "1", "-2", "", 1,
                "腰2", "3", "2", "1", "-2", "", 0,
                "腿1", "2", "2", "1", "-2", "", 0,

                "刚翼爪", "优质鳞", "坚壳", "刚髭", "韧翼膜", "地狱耳",
                "", "12", "", "6", "", "",
                "8", "12", "", "", "", "",
                "12", "8", "", "", "", "",
                "", "", "12", "", "12", "8",
                "", "", "12", "", "12", ""));

        mListCache.add(new SuitSkillAttribute("葵盾蟹", "100",
                "防御性能", "水耐性", "锋利度", "炮术", "研磨师",
                "头1", "4", "3/4", "2", "2", "-3", 0,
                "手2", "2", "3", "1", "2", "-2", 0,
                "衣2", "2", "2", "3", "2", "-2", 0,
                "腰3", "1", "2", "2", "2", "-2", 0,
                "腿1", "2", "5", "2", "2", "-3", 0,

                "黑壳", "漆黑脚", "极品黑珍珠", "青爪", "黑胸甲", "",
                "18", "12", "9", "", "", "",
                "18", "", "", "12", "", "",
                "12", "", "", "18", "", "",
                "", "18", "9", "", "18", "",
                "", "18", "", "", "18", ""));
        mListCache.add(new SuitSkillAttribute("葵盾蟹(远)", "100",
                "贯通弹强化", "水耐性", "反动", "速射", "快速装填",
                "头1", "2", "4", "3", "2", "-3", 0,
                "手2", "2", "3", "1", "2", "-2", 0,
                "衣2", "2", "2", "3", "2", "-2", 0,
                "腰3", "1", "2", "1", "2", "-2", 0,
                "腿1", "3", "4", "2", "2", "-3", 0,

                "黑壳", "漆黑脚", "极品黑珍珠", "青爪", "黑胸甲", "",
                "18", "12", "9", "", "", "",
                "18", "", "", "12", "", "",
                "12", "", "", "18", "", "",
                "", "18", "9", "", "18", "",
                "", "18", "", "", "18", ""));
        mListCache.add(new SuitSkillAttribute("剑极狼", "",
                "回复速度", "锋利度", "斗魂", "刺伤", "耐力回复",
                "头1", "3", "2", "2", "2", "-2", 0,
                "手3", "3", "1", "1", "2", "-2", 0,
                "衣0", "3", "4", "4", "2", "-3", 0,
                "腰1", "3", "1", "3", "2", "-2", 0,
                "腿0", "3", "2", "5", "2", "-3", 0,

                "刚毛", "上腕甲", "锐牙", "凶戾眼", "尖爪", "獠齿",
                "8", "12", "", "", "", "",
                "12", "8", "", "", "", "",
                "", "12", "8", "6", "", "",
                "", "", "12", "", "12", "",
                "", "", "12", "", "12", "8"));
        mListCache.add(new SuitSkillAttribute("剑极狼(远)", "",
                "回复速度", "反动", "斗魂", "刺伤", "耐力回复",
                "头1", "3", "2", "2", "2", "-2", 0,
                "手3", "3", "1", "1", "2", "-2", 0,
                "衣0", "3", "4", "4", "2", "-3", 0,
                "腰1", "3", "1", "3", "2", "-2", 0,
                "腿0", "3", "2", "5", "2", "-3", 0,

                "刚毛", "上腕甲", "锐牙", "凶戾眼", "尖爪", "獠齿",
                "8", "12", "", "", "", "",
                "12", "8", "", "", "", "",
                "", "12", "8", "6", "", "",
                "", "", "12", "", "12", "",
                "", "", "12", "", "12", "8"));
        mListCache.add(new SuitSkillAttribute("锈钢龙", "100",
                "锋利度", "回避距离", "无伤", "体术", "毒抵抗",
                "头1", "3", "4", "2", "3", "-1", 0,
                "手3", "3", "", "2", "3", "-3", 0,
                "衣1", "4", "2", "2", "2", "-2", 0,
                "腰3", "", "3", "2", "2", "-3", 0,
                "腿3", "", "3", "2", "2", "-1", 0,

                "锈龙鳞", "龙鳞", "龙壳", "逆鳞", "龙爪", "银岭之冠",
                "12", "18", "", "", "", "",
                "18", "12", "", "", "", "",
                "", "18", "12", "9", "", "",
                "", "", "18", "", "18", "",
                "", "", "18", "", "18", "12"));
        mListCache.add(new SuitSkillAttribute("锈钢龙(远)", "100",
                "散弹强化", "回避距离", "无伤", "体术", "毒抵抗",
                "头1", "3", "4", "2", "3", "-1", 0,
                "手3", "4", "", "2", "3", "-3", 0,
                "衣1", "3", "2", "2", "2", "-2", 0,
                "腰3", "", "3", "2", "2", "-3", 0,
                "腿3", "", "3", "2", "2", "-1", 0,

                "锈龙鳞", "龙鳞", "龙壳", "逆鳞", "龙爪", "银岭之冠",
                "12", "18", "", "", "", "",
                "18", "12", "", "", "", "",
                "", "18", "12", "9", "", "",
                "", "", "18", "", "18", "",
                "", "", "18", "", "18", "12"));
    }

    private void addHearingProtection() {
        mListCache.add(new SuitSkillAttribute("独耳黑狼鸟", "",
                "听觉保护", "特殊攻击", "研磨师", "回复速度", "",
                "头1", "2", "2", "2", "-2", "", 0,
                "手2", "3", "1", "2", "-2", "", 1,
                "衣3", "3", "3", "2", "-2", "", 0,
                "腰2", "3", "2", "2", "-2", "", 0,
                "腿0", "2", "2", "2", "-2", "", 0,

                "刚翼爪", "优质鳞", "坚壳", "刚髭", "韧翼膜", "地狱耳",
                "8", "12", "", "", "", "",
                "8", "12", "", "", "", "",
                "", "12", "8", "6", "", "",
                "", "", "12", "", "12", "8",
                "", "", "12", "", "12", ""));
        mListCache.add(new SuitSkillAttribute("独耳黑狼鸟(远)", "",
                "听觉保护", "特殊攻击", "反动", "回复速度", "",
                "头1", "2", "2", "2", "-2", "", 0,
                "手2", "3", "1", "2", "-2", "", 1,
                "衣3", "3", "3", "2", "-2", "", 0,
                "腰2", "3", "2", "2", "-2", "", 0,
                "腿0", "2", "2", "2", "-2", "", 0,

                "刚翼爪", "优质鳞", "坚壳", "刚髭", "韧翼膜", "地狱耳",
                "8", "12", "", "", "", "",
                "8", "12", "", "", "", "",
                "", "12", "8", "6", "", "",
                "", "", "12", "", "12", "8",
                "", "", "12", "", "12", ""));
        mListCache.add(new SuitSkillAttribute("燎渊龙", "90",
                "加护", "痛击", "听觉保护", "飞龙猎人", "",
                "头2", "3", "3", "4", "1", "", 1,
                "手0", "3", "4", "4", "1", "", 0,
                "衣3", "3", "1", "4", "3", "", 1,
                "腰3", "3", "1", "4", "3", "", 1,
                "腿2", "3", "1", "4", "2", "", 1,

                "重骨甲", "重焰壳", "焰尘沙", "龙神玉", "深渊燃血", "重腕甲",
                "12", "18", "", "", "", "",
                "", "18", "12", "9", "", "",
                "18", "12", "", "", "", "",
                "", "", "18", "", "18", "",
                "", "", "18", "", "18", "12"));
        mListCache.add(new SuitSkillAttribute("霞龙", "",
                "听觉保护", "气息", "风压抵抗", "特殊攻击", "耐力回复",
                "头2", "5", "1", "3", "", "-2", 0,
                "手1", "1", "3", "3", "3", "-2", 0,
                "衣1", "2", "4", "3", "3", "-2", 0,
                "腰2", "4", "1", "3", "", "-2", 0,
                "腿2", "4", "1", "3", "", "-2", 0,

                "上皮", "尖爪", "锐牙", "翼膜", "尖角", "长舌",
                "12", "18", "", "", "", "",
                "18", "12", "", "", "", "",
                "", "", "18", "18", "12", "",
                "", "", "18", "18", "", "",
                "", "18", "12", "", "", "9"));
        mListCache.add(new SuitSkillAttribute("苍火龙", "65",
                "听觉保护", "达人", "锋利度", "体力", "",
                "头2", "3", "3", "2", "-2", "", 0,
                "手1", "3", "2", "2", "-2", "", 0,
                "衣1", "3", "4", "2", "-3", "", 0,
                "腰2", "3", "4", "2", "-3", "", 0,
                "腿2", "3", "4", "2", "-2", "", 0,

                "刚翼", "炎袋", "延髓", "上鳞", "坚壳", "逆鳞",
                "12", "12", "8", "", "", "",
                "", "", "", "8", "12", "",
                "", "", "", "12", "8", "",
                "", "", "18", "18", "", "",
                "8", "", "", "", "12", "6"));
        mListCache.add(new SuitSkillAttribute("苍火龙(远)", "65",
                "听觉保护", "达人", "通常弹强化", "体力", "",
                "头2", "3", "3", "2", "-2", "", 0,
                "手1", "3", "2", "2", "-2", "", 0,
                "衣1", "3", "4", "2", "-3", "", 0,
                "腰2", "3", "4", "2", "-3", "", 0,
                "腿2", "3", "4", "2", "-2", "", 0,

                "刚翼", "炎袋", "延髓", "上鳞", "坚壳", "逆鳞",
                "12", "12", "8", "", "", "",
                "", "", "", "8", "12", "",
                "", "", "", "12", "8", "",
                "", "", "18", "18", "", "",
                "8", "", "", "", "12", "6"));
    }

    /**
     * 匠
     */
    private void addArtisan() {
        mListCache.add(new SuitSkillAttribute("剑刹狼", "80",
                "特殊攻击", "麻痹抵抗", "剑术", "匠", "锋利度",
                "头2", "3", "1", "3", "", "-2", 1,
                "手2", "3", "1", "2", "", "-2", 1,
                "衣2", "3", "3", "2", "2", "-3", 0,
                "腰2", "3", "2", "2", "2", "-2", 0,
                "腿2", "3", "3", "1", "1", "-2", 1,

                "豪刚毛", "重腕甲", "重牙", "修罗眼", "刚爪", "剑棘",
                "12", "18", "", "", "", "",
                "18", "12", "", "", "", "",
                "", "18", "12", "9", "", "",
                "", "", "18", "", "18", "12",
                "", "", "18", "", "18", ""));
        mListCache.add(new SuitSkillAttribute("剑刹狼(远)", "80",
                "特殊攻击", "麻痹抵抗", "反动", "装填术", "快速装填",
                "头2", "3", "1", "3", "", "-2", 1,
                "手2", "3", "1", "2", "", "-2", 1,
                "衣2", "3", "3", "2", "2", "-3", 0,
                "腰2", "3", "2", "2", "2", "-2", 0,
                "腿2", "3", "3", "1", "1", "-2", 1,

                "豪刚毛", "重腕甲", "重牙", "修罗眼", "刚爪", "剑棘",
                "12", "18", "", "", "", "",
                "18", "12", "", "", "", "",
                "", "18", "12", "9", "", "",
                "", "", "18", "", "18", "12",
                "", "", "18", "", "18", ""));
        mListCache.add(new SuitSkillAttribute("白一角龙", "70",
                "底力", "匠", "达人", "加护", "",
                "头2", "3", "1", "3", "-2", "", 1,
                "手1", "3", "2", "2", "-2", "", 0,
                "衣1", "3", "2", "3", "-2", "", 0,
                "腰2", "3", "1", "1", "-2", "", 0,
                "腿2", "3", "2", "1", "-2", "", 0,

                "坚壳", "锐牙", "坚甲", "延髓", "坚尾甲", "韧尾",
                "12", "18", "", "", "", "",
                "", "18", "12", "9", "", "",
                "18", "12", "", "", "", "",
                "", "", "18", "", "18", "",
                "", "", "18", "", "18", "12"));
        mListCache.add(new SuitSkillAttribute("白一角龙(远)", "70",
                "底力", "装填术", "达人", "加护", "",
                "头2", "3", "1", "3", "-2", "", 1,
                "手1", "3", "2", "2", "-2", "", 0,
                "衣1", "3", "2", "3", "-2", "", 0,
                "腰2", "3", "1", "1", "-2", "", 0,
                "腿2", "3", "2", "1", "-2", "", 0,

                "坚壳", "锐牙", "坚甲", "延髓", "坚尾甲", "韧尾",
                "12", "18", "", "", "", "",
                "", "18", "12", "9", "", "",
                "18", "12", "", "", "", "",
                "", "", "18", "", "18", "",
                "", "", "18", "", "18", "12"));
        mListCache.add(new SuitSkillAttribute("钢龙", "",
                "匠", "回避性能", "集中", "毒性抵抗", "",
                "头2", "2", "3", "1", "-2", "", 0,
                "手1", "2", "3", "2", "-2", "", 0,
                "衣2", "1", "3", "2", "-2", "", 0,
                "腰2", "1", "3", "3", "-2", "", 0,
                "腿2", "", "3", "2", "-2", "", 0,

                "坚壳", "尖爪", "尖角", "腐朽龙鳞", "钢之上龙鳞", "逆鳞",
                "12", "12", "8", "", "", "",
                "", "", "", "8", "12", "",
                "", "", "12", "8", "", "",
                "8", "", "", "", "12", "6",
                "12", "12", "", "", "", ""));
        mListCache.add(new SuitSkillAttribute("钢龙(远)", "",
                "装填术", "回避性能", "集中", "毒性抵抗", "",
                "头2", "2", "3", "1", "-2", "", 0,
                "手1", "2", "3", "2", "-2", "", 0,
                "衣2", "1", "3", "2", "-2", "", 0,
                "腰2", "1", "3", "3", "-2", "", 0,
                "腿2", "", "3", "2", "-2", "", 0,

                "坚壳", "尖爪", "尖角", "腐朽龙鳞", "钢之上龙鳞", "逆鳞",
                "12", "12", "8", "", "", "",
                "", "", "", "8", "12", "",
                "", "", "12", "8", "", "",
                "8", "", "", "", "12", "6",
                "12", "12", "", "", "", ""));
        mListCache.add(new SuitSkillAttribute("浮岳龙", "105",
                "剑术", "速食", "匠", "麻痹抵抗", "加护",
                "头2", "2", "4", "2", "_", "-3", 0,
                "手2", "2", "1", "2", "3", "-3", 0,
                "衣2", "2", "3", "2", "1", "-3", 0,
                "腰2", "2", "1", "2", "3", "-3", 0,
                "腿2", "2", "1", "2", "3", "-3", 0,

                "体液", "皮", "鳍", "神龙木", "神龙苔", "",
                "12", "18", "", "", "", "",
                "18", "12", "", "", "", "",
                "", "18", "12", "9", "", "",
                "", "", "", "18", "12", "",
                "", "", "18", "", "18", ""));
        mListCache.add(new SuitSkillAttribute("浮岳龙(远)", "105",
                "快速装填", "速食", "装填术", "麻痹抵抗", "加护",
                "头2", "4", "4", "2", "_", "-3", 0,
                "手2", "3", "1", "2", "3", "-3", 0,
                "衣2", "2", "2", "2", "2", "-3", 0,
                "腰2", "3", "2", "2", "2", "-3", 0,
                "腿2", "3", "1", "2", "3", "-3", 0,

                "体液", "皮", "鳍", "神龙木", "神龙苔", "",
                "12", "18", "", "", "", "",
                "18", "12", "", "", "", "",
                "", "18", "12", "9", "", "",
                "", "", "", "18", "12", "",
                "", "", "18", "", "18", ""));
        mListCache.add(new SuitSkillAttribute("将军镰蟹", "",
                "锋利度", "匠", "研磨师", "体力", "",
                "头1", "3", "1", "2", "-3", "", 0,
                "手1", "2", "", "2", "-3", "", 0,
                "衣2", "2", "2", "2", "-3", "", 0,
                "腰2", "3", "1", "2", "-3", "", 0,
                "腿0", "2", "", "2", "-3", "", 0,

                "尖爪", "坚壳", "长脚", "小坚壳", "坚头龙壳", "极上金真珠",
                "4", "6", "", "", "", "",
                "6", "4", "", "", "", "",
                "", "", "6", "6", "4", "",
                "", "6", "4", "", "", "3",
                "", "", "6", "6", "", ""));
        mListCache.add(new SuitSkillAttribute("将军镰蟹(远)", "",
                "贯通弹", "装填术", "快速装填", "体力", "",
                "头1", "3", "1", "2", "-3", "", 0,
                "手1", "2", "", "2", "-3", "", 0,
                "衣2", "2", "2", "2", "-3", "", 0,
                "腰2", "3", "1", "2", "-3", "", 0,
                "腿0", "2", "", "2", "-3", "", 0,

                "尖爪", "坚壳", "长脚", "小坚壳", "坚头龙壳", "极上金真珠",
                "4", "6", "", "", "", "",
                "6", "4", "", "", "", "",
                "", "", "6", "6", "4", "",
                "", "6", "4", "", "", "3",
                "", "", "6", "6", "", ""));
    }

    /**
     * 防御强化
     */
    private void addDefenserPlus() {

        mListCache.add(new SuitSkillAttribute("晶岩龙", "",
                "防御强化", "食事", "耐泥雪", "攻击", "",
                "头2", "3", "2", "2", "-2", "", 0,
                "手1", "2", "2", "2", "-2", "", 0,
                "衣1", "1", "2", "2", "-2", "", 0,
                "腰1", "1", "2", "2", "-2", "", 0,
                "腿3", "3", "2", "2", "-2", "", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));
        mListCache.add(new SuitSkillAttribute("晶岩龙(远)", "",
                "散弹强化", "食事", "耐泥雪", "攻击", "",
                "头2", "3", "2", "2", "-2", "", 0,
                "手1", "2", "2", "2", "-2", "", 0,
                "衣1", "1", "2", "2", "-2", "", 0,
                "腰1", "1", "2", "2", "-2", "", 0,
                "腿3", "3", "2", "2", "-2", "", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));

        mListCache.add(new SuitSkillAttribute("铠龙", "80",
                "防御性能", "体力", "防御强化", "体术", "",
                "头1", "3", "5", "2", "-3", "", 0,
                "手3", "2", "4", "2", "-2", "", 0,
                "衣3", "3", "3", "2", "-2", "", 0,
                "腰1", "3", "2", "2", "-2", "", 0,
                "腿2", "4", "6", "2", "-3", "", 0,

                "重壳", "重头壳", "重背壳", "刚翼", "重腹甲", "",
                "12", "18", "", "", "", "",
                "", "", "18", "18", "12", "",
                "", "18", "12", "", "", "",
                "18", "12", "", "", "", "",
                "", "", "18", "18", "", ""));
        mListCache.add(new SuitSkillAttribute("铠龙(远)", "80",
                "快速装填", "体力", "散弹强化", "体术", "",
                "头1", "3", "5", "2", "-3", "", 0,
                "手3", "2", "4", "2", "-2", "", 0,
                "衣3", "3", "3", "2", "-2", "", 0,
                "腰1", "3", "2", "2", "-2", "", 0,
                "腿2", "4", "6", "2", "-3", "", 0,

                "重壳", "重头壳", "重背壳", "刚翼", "重腹甲", "",
                "12", "18", "", "", "", "",
                "", "", "18", "18", "12", "",
                "", "18", "12", "", "", "",
                "18", "12", "", "", "", "",
                "", "", "18", "18", "", ""));
    }

    /**
     * 剑术
     */
    private void addSwordsmanship() {
        mListCache.add(new SuitSkillAttribute("烈焰女王", "",
                "剑术", "火属性攻击", "重击", "防御", "",
                "头0", "2", "3", "", "-2", "", 0,
                "手0", "2", "3", "", "-1", "", 0,
                "衣3", "1", "3", "2", "-3", "", 0,
                "腰3", "4", "3", "4", "-3", "", 0,
                "腿3", "1", "3", "", "-1", "", 0,

                "刚龙爪", "优质龙鳞", "优质棘", "坚壳", "刚翼", "秘棘",
                "8", "12", "", "", "", "",
                "", "", "12", "12", "8", "",
                "", "12", "8", "", "", "6",
                "12", "8", "", "", "", "",
                "", "", "12", "12", "", ""));
        mListCache.add(new SuitSkillAttribute("烈焰女王(远)", "",
                "伏击", "火属性攻击", "重击", "防御", "",
                "头0", "2", "3", "", "-2", "", 0,
                "手0", "2", "3", "", "-1", "", 0,
                "衣3", "1", "3", "2", "-3", "", 0,
                "腰3", "4", "3", "4", "-3", "", 0,
                "腿3", "1", "3", "", "-1", "", 0,

                "刚龙爪", "优质龙鳞", "优质棘", "坚壳", "刚翼", "秘棘",
                "8", "12", "", "", "", "",
                "", "", "12", "12", "8", "",
                "", "12", "8", "", "", "6",
                "12", "8", "", "", "", "",
                "", "", "12", "12", "", ""));
        mListCache.add(new SuitSkillAttribute("红莲砦蟹", "",
                "剑术", "回复速度", "匠", "火属性攻击", "锋利度",
                "头3", "3", "2", "1", "2", "-2", 0,
                "手0", "1", "2", "_", "2", "-3", 0,
                "衣1", "1", "2", "1", "2", "-3", 0,
                "腰1", "4", "2", "2", "2", "-2", 0,
                "腿1", "1", "2", "2", "2", "-2", 0,

                "", "", "", "", "", "",
                "", "", "", "", "", "",
                "", "", "", "", "", "",
                "", "", "", "", "", "",
                "", "", "", "", "", "",
                "", "", "", "", "", ""));
        mListCache.add(new SuitSkillAttribute("红莲砦蟹(远)", "",
                "痛击", "回复速度", "装填术", "火属性攻击", "快速装填",
                "头3", "3", "2", "1", "2", "-2", 0,
                "手0", "1", "2", "_", "2", "-3", 0,
                "衣1", "1", "2", "1", "2", "-3", 0,
                "腰1", "4", "2", "2", "2", "-2", 0,
                "腿1", "1", "2", "2", "2", "-2", 0,

                "", "", "", "", "", "",
                "", "", "", "", "", "",
                "", "", "", "", "", "",
                "", "", "", "", "", "",
                "", "", "", "", "", "",
                "", "", "", "", "", ""));
        mListCache.add(new SuitSkillAttribute("角龙", "",
                "拔刀会心", "耐震", "耐力回复", "剑术", "研磨师",
                "头2", "3", "2", "2", "", "-2", 0,
                "手1", "3", "2", "2", "", "-2", 0,
                "衣1", "2", "2", "2", "", "-2", 0,
                "腰1", "2", "2", "2", "1", "-2", 0,
                "腿2", "", "2", "2", "4", "-3", 0,

                "坚甲", "坚尾甲", "韧尾", "坚壳", "锐牙", "延髓",
                "12", "12", "8", "", "", "",
                "", "", "", "8", "12", "",
                "", "", "", "12", "8", "",
                "8", "", "", "", "12", "6",
                "12", "12", "", "", "", ""));
        mListCache.add(new SuitSkillAttribute("角龙(远)", "",
                "快速装填", "贯通弹强化", "耐力回复", "反动", "特殊攻击",
                "头2", "3", "2", "2", "", "-2", 0,
                "手1", "3", "2", "2", "", "-2", 0,
                "衣1", "2", "2", "2", "", "-2", 0,
                "腰1", "2", "2", "2", "1", "-2", 0,
                "腿2", "", "2", "2", "4", "-3", 0,

                "坚甲", "坚尾甲", "韧尾", "坚壳", "锐牙", "延髓",
                "12", "12", "8", "", "", "",
                "", "", "", "8", "12", "",
                "", "", "", "12", "8", "",
                "8", "", "", "", "12", "6",
                "12", "12", "", "", "", ""));
    }

    /**
     * 集中
     */
    private void addFocus() {

        mListCache.add(new SuitSkillAttribute("灭星龙", "115",
                "化羽", "蝶舞", "集中", "", "攻击",
                "头2", "2", "2", "2", "_", "-2", 0,
                "手1", "1", "3", "4", "_", "-2", 0,
                "衣1", "3", "2", "4", "_", "-3", 0,
                "腰3", "2", "2", "2", "_", "-2", 0,
                "腿2", "2", "2", "3", "_", "-3", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));

        mListCache.add(new SuitSkillAttribute("金狮子", "85",
                "集中", "回避距离", "斗魂", "龙耐性", "气息",
                "头2", "3", "2", "2", "2", "-3", 0,
                "手3", "1", "2", "3", "2", "-1", 0,
                "衣0", "5", "2", "2", "2", "-4", 0,
                "腰2", "", "2", "2", "2", "-1", 0,
                "腿2", "1", "2", "2", "2", "-1", 0,

                "黑荒毛", "黑荒发", "刚角", "重牙", "刚爪", "",
                "18", "18", "12", "", "", "",
                "", "", "", "12", "18", "",
                "", "", "", "18", "12", "",
                "18", "12", "", "", "", "",
                "18", "18", "", "", "", ""));
        mListCache.add(new SuitSkillAttribute("祸星龙", "",
                "集中", "重击", "伏击", "龙属性攻击", "体力",
                "头0", "1", "2", "3", "", "-3", 0,
                "手1", "2", "2", "1", "", "-3", 0,
                "衣3", "2", "2", "", "2", "-3", 0,
                "腰1", "3", "2", "", "2", "-3", 0,
                "腿3", "2", "2", "3", "2", "-3", 0,

                "妖光的体液", "刚翼", "尖角", "坚壳", "星雨蝶薄妖羽", "灾星龙鳞",
                "8", "12", "12", "", "", "",
                "12", "8", "", "12", "18", "",
                "", "", "12", "12", "8", "",
                "18", "12", "", "", "", "",
                "", "12", "8", "", "", "6"));
        mListCache.add(new SuitSkillAttribute("熔岩龙", "",
                "集中", "根性", "火攻", "耐震", "水耐性",
                "头1", "3", "2", "2", "2", "-3", 0,
                "手1", "3", "2", "2", "", "-3", 0,
                "衣3", "2", "3", "4", "", "-3", 0,
                "腰2", "2", "2", "2", "", "-3", 0,
                "腿2", "2", "2", "1", "", "-3", 0,

                "锐牙", "尾鳍", "上鳍", "上鳞", "坚壳", "",
                "18", "18", "12", "", "", "",
                "", "", "", "12", "8", "",
                "", "", "", "18", "12", "",
                "12", "", "", "", "18", "",
                "18", "18", "", "", "", ""));
    }

    /**
     * 达人
     */
    private void addCritical() {

        mListCache.add(new SuitSkillAttribute("武镰蟹", "110",
                "达人", "痛击", "毒抗", "回避距离", "攻击",
                "头1", "5", "3", "3", "3", "-3", 0,
                "手3", "3", "1", "2", "1", "-1", 0,
                "衣2", "4", "2", "3", "1", "-2", 0,
                "腰1", "5", "2", "4", "3", "-3", 0,
                "腿2", "3", "2", "3", "2", "-3", 0,

                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_",
                "_", "_", "_", "_", "_", "_"
        ));

        mListCache.add(new SuitSkillAttribute("剑豪镰蟹", "",
                "达人", "会心术", "回避距离", "气绝", "",
                "头1", "3", "2", "1", "-3", "", 0,
                "手1", "3", "2", "2", "-3", "", 0,
                "衣1", "3", "2", "1", "-2", "", 0,
                "腰2", "3", "2", "2", "-3", "", 0,
                "腿0", "3", "2", "1", "-2", "", 0,

                "尖爪", "坚壳", "坚脚", "坚钳", "坚龙头壳", "金色大真珠",
                "8", "12", "", "", "", "",
                "", "", "12", "12", "8", "",
                "12", "8", "", "", "", "",
                "", "12", "8", "", "", "6",
                "", "", "12", "12", "", ""));
    }

    /**
     * 重击
     */
    private void addThump() {
        mListCache.add(new SuitSkillAttribute("荒厄龙", "",
                "重击", "耐震", "痛击", "KO术", "耐力回复",
                "头2", "3", "2", "2", "", "-2", 0,
                "手3", "3", "2", "2", "", "-2", 0,
                "衣1", "2", "2", "2", "2", "-2", 0,
                "腰1", "2", "2", "2", "1", "-2", 0,
                "腿0", "3", "2", "1", "", "-2", 0,

                "鳞", "甲壳", "蛮爪", "眼", "剑鳞", "角",
                "8", "12", "", "", "", "",
                "12", "8", "", "", "", "",
                "", "12", "8", "6", "", "",
                "", "", "12", "", "12", "",
                "", "", "12", "", "12", "8"));
    }

    /**
     * 属性攻击
     */
    private void addAttributeAttack() {
        mListCache.add(new SuitSkillAttribute("荒厄龙完全体", "",
                "倒地之力", "耐震", "属性攻击", "锁气绝", "特殊攻击",
                "头1", "3", "2", "3", "2", "-2", 0,
                "手2", "3", "2", "3", "1", "-2", 0,
                "衣1", "2", "2", "3", "2", "-2", 0,
                "腰3", "2", "2", "3", "1", "-2", 0,
                "腿3", "2", "3", "", "2", "-2", 0,

                "刚蛮爪", "刀剑鳞", "刚角", "厚鳞", "重壳", "凶眼",
                "18", "18", "12", "", "", "",
                "", "", "", "12", "18", "",
                "", "", "", "18", "12", "",
                "12", "", "", "", "18", "9",
                "18", "18", "", "", "", ""));

        mListCache.add(new SuitSkillAttribute("黄速龙王", "",
                "麻痹抵抗", "特殊攻击", "体力", "回复速度", "毒抵抗",
                "头2", "2", "2", "_", "_", "-1", 0,
                "手2", "2", "3", "3", "_", "-1", 0,
                "衣0", "2", "_", "_", "3", "-0", 0,
                "腰2", "2", "2", "3", "3", "-0", 0,
                "腿0", "2", "3", "_", "3", "-0", 0,

                "尖爪", "优质鳞", "麻痹尖牙", "优质皮", "大头冠", "韧尾",
                "4", "6", "_", "_", "_", "_",
                "_", "_", "6", "6", "4", "_",
                "6", "4", "_", "_", "_", "_",
                "_", "6", "4", "_", "_", "3",
                "_", "_", "6", "6", "_", "_"
        ));

        mListCache.add(new SuitSkillAttribute("红速龙王", "",
                "毒抵抗", "体力", "特殊攻击", "气绝抵抗", "",
                "头2", "3", "3", "3", "-2", "", 0,
                "手2", "1", "3", "_", "-2", "", 0,
                "衣1", "1", "3", "_", "-2", "", 0,
                "腰2", "3", "3", "3", "-2", "", 1,
                "腿0", "2", "3", "_", "-2", "", 0,

                "优质鳞", "尖牙", "猛毒腺", "尖爪", "优质皮", "大头冠",
                "6", "4", "3", "_", "_", "_",
                "6", "_", "_", "4", "_", "_",
                "4", "_", "_", "6", "_", "_",
                "_", "6", "_", "_", "6", "4",
                "_", "6", "_", "_", "6", "_"
        ));
    }

    /**
     * 千里眼
     */
    private void addClairvoyant() {
        mListCache.add(new SuitSkillAttribute("雌火龙", "",
                "毒性抵抗", "体力", "千里眼", "气绝抵抗", "",
                "头3", "2", "5", "3", "-1", "", 1,
                "手2", "2", "4", "1", "-1", "", 1,
                "衣2", "2", "4", "1", "-1", "", 1,
                "腰3", "2", "5", "2", "-1", "", 0,
                "腿0", "2", "4", "3", "-1", "", 1,

                "优质棘", "坚壳", "刚翼", "尖爪", "优质鳞", "秘棘",
                "12", "12", "8", "", "", "",
                "", "", "", "8", "12", "",
                "", "", "", "12", "8", "",
                "8", "", "", "", "12", "6",
                "12", "12", "", "", "12", ""));
        mListCache.add(new SuitSkillAttribute("轰龙", "",
                "听觉保护", "千里眼", "食事", "防御", "研磨师",
                "头2", "3", "3", "2", "2", "-2", 1,
                "手2", "2", "", "2", "2", "-2", 1,
                "衣1", "3", "2", "2", "3", "-2", 1,
                "腰2", "3", "", "2", "2", "-2", 1,
                "腿1", "4", "3", "2", "3", "-2", 1,

                "坚壳", "锐牙", "颚", "上鳞", "头壳", "尖爪",
                "", "", "", "8", "12", "",
                "8", "", "", "12", "", "",
                "", "12", "", "", "", "12",
                "", "12", "", "", "8", "12",
                "12", "8", "6", "", "", ""));
        mListCache.add(new SuitSkillAttribute("河狸兽", "",
                "千里眼", "食事", "攻击", "火耐性", "",
                "头2", "2", "1", "", "-2", "", 0,
                "手1", "1", "", "3", "-2", "", 0,
                "衣2", "4", "3", "2", "-1", "", 0,
                "腰1", "2", "3", "", "-2", "", 0,
                "腿0", "1", "3", "3", "-1", "", 0,

                "尖爪", "优质皮", "历战的门牙", "尖牙", "优质的毛皮", "尖爪",
                "4", "6", "", "", "", "",
                "6", "4", "", "", "", "",
                "6", "4", "3", "", "", "",
                "4", "", "", "6", "6", "",
                "", "", "", "6", "6", ""));
        mListCache.add(new SuitSkillAttribute("大野猪王", "",
                "研磨师", "体力", "防御", "千里眼", "饥渴感",
                "头1", "3", "2", "2", "2", "-2", 0,
                "手3", "", "", "2", "2", "-1", 0,
                "衣0", "2", "", "2", "", "-2", 0,
                "腰1", "2", "2", "2", "", "-2", 0,
                "腿1", "3", "2", "2", "2", "-1", 0,

                "硬皮", "硬蹄", "曲刚牙", "尖牙", "硬鬓", "",
                "6", "4", "3", "", "", "",
                "6", "", "", "4", "", "",
                "4", "", "", "6", "", "",
                "", "6", "", "", "6", "",
                "", "6", "", "4", "6", ""
        ));
        mListCache.add(new SuitSkillAttribute("大野猪王(远)", "",
                "通常弹强化", "体力", "防御", "千里眼", "饥渴感",
                "头1", "3", "2", "2", "2", "-2", 0,
                "手3", "", "", "2", "2", "-1", 0,
                "衣0", "2", "", "2", "", "-2", 0,
                "腰1", "2", "2", "2", "", "-2", 0,
                "腿1", "3", "2", "2", "2", "-1", 0,

                "硬皮", "硬蹄", "曲刚牙", "尖牙", "硬鬓", "",
                "6", "4", "3", "", "", "",
                "6", "", "", "4", "", "",
                "4", "", "", "6", "", "",
                "", "6", "", "", "6", "",
                "", "6", "", "4", "6", ""
        ));

        mListCache.add(new SuitSkillAttribute("白速龙王", "",
                "耐泥雪", "千里眼", "冰耐性", "防御强化", "火耐性",
                "头1", "2", "3", "2", "_", "-2", 0,
                "手2", "2", "2", "2", "2", "-2", 0,
                "衣1", "2", "2", "2", "_", "-2", 0,
                "腰1", "2", "3", "2", "_", "-2", 0,
                "腿2", "2", "2", "2", "4", "-2", 0,

                "尖爪", "优质鳞", "尖牙", "冻结腺", "大头冠", "优质皮     ",
                "4", "6", "_", "_", "_", "_",
                "_", "_", "6", "6", "4", "_",
                "6", "4", "_", "_", "_", "_",
                "_", "_", "6", "6", "_", "_",
                "_", "6", "4", "_", "_", "3"
        ));
    }

    /**
     * 毒性抵抗
     */
    private void addPoisonResistance() {
        mListCache.add(new SuitSkillAttribute("骇狩蛛", "",
                "毒性抵抗", "麻痹抵抗", "特殊攻击", "睡眠抵抗", "",
                "头3", "3", "2", "2", "-2", "", 0,
                "手0", "3", "2", "2", "-2", "", 0,
                "衣0", "3", "2", "2", "-2", "", 0,
                "腰3", "3", "2", "2", "-2", "", 0,
                "腿0", "3", "2", "2", "-2", "", 0,

                "坚壳", "复眼", "浓液", "尖爪", "韧蛛丝", "刚毛",
                "6", "4", "3", "", "", "",
                "6", "", "", "4", "", "",
                "4", "", "", "6", "", "",
                "", "6", "", "", "4", "6",
                "", "6", "", "", "", "6"));
    }

    @OnClick(R.id.tv_skillName)
    public void onClick() {
        if(mRecyclerViewGridview.getVisibility() != View.VISIBLE){
//            mRecyclerViewGridview.setVisibility(View.VISIBLE);
            appearAnimator(mRecyclerViewGridview);
            Log.i(TAG, "onClick: ");
        }
    }
}
