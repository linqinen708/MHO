package com.linqinen.mho.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.linqinen.mho.R;
import com.linqinen.mho.bean.SuitSkillAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssemblingActivity extends AppCompatActivity {

    private final String TAG = "AssemblingActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_headSkill_1)
    TextView tvHeadSkill1;
    @BindView(R.id.tv_headSkill_2)
    TextView tvHeadSkill2;
    @BindView(R.id.tv_headSkill_3)
    TextView tvHeadSkill3;
    @BindView(R.id.tv_headSkill_4)
    TextView tvHeadSkill4;
    @BindView(R.id.tv_headSkill_5)
    TextView tvHeadSkill5;
    @BindView(R.id.tv_handSkill_1)
    TextView tvHandSkill1;
    @BindView(R.id.tv_handSkill_2)
    TextView tvHandSkill2;
    @BindView(R.id.tv_handSkill_3)
    TextView tvHandSkill3;
    @BindView(R.id.tv_handSkill_4)
    TextView tvHandSkill4;
    @BindView(R.id.tv_handSkill_5)
    TextView tvHandSkill5;
    @BindView(R.id.tv_clothesSkill_1)
    TextView tvClothesSkill1;
    @BindView(R.id.tv_clothesSkill_2)
    TextView tvClothesSkill2;
    @BindView(R.id.tv_clothesSkill_3)
    TextView tvClothesSkill3;
    @BindView(R.id.tv_clothesSkill_4)
    TextView tvClothesSkill4;
    @BindView(R.id.tv_clothesSkill_5)
    TextView tvClothesSkill5;
    @BindView(R.id.tv_waistSkill_1)
    TextView tvWaistSkill1;
    @BindView(R.id.tv_waistSkill_2)
    TextView tvWaistSkill2;
    @BindView(R.id.tv_waistSkill_3)
    TextView tvWaistSkill3;
    @BindView(R.id.tv_waistSkill_4)
    TextView tvWaistSkill4;
    @BindView(R.id.tv_waistSkill_5)
    TextView tvWaistSkill5;
    @BindView(R.id.tv_footSkill_1)
    TextView tvFootSkill1;
    @BindView(R.id.tv_footSkill_2)
    TextView tvFootSkill2;
    @BindView(R.id.tv_footSkill_3)
    TextView tvFootSkill3;
    @BindView(R.id.tv_footSkill_4)
    TextView tvFootSkill4;
    @BindView(R.id.tv_footSkill_5)
    TextView tvFootSkill5;

    @BindView(R.id.tv_displaySkill_1)
    TextView tvDisplaySkill1;
    @BindView(R.id.tv_displaySkill_2)
    TextView tvDisplaySkill2;
    @BindView(R.id.tv_displaySkill_3)
    TextView tvDisplaySkill3;
    @BindView(R.id.tv_displaySkill_4)
    TextView tvDisplaySkill4;
    //    @BindView(R.id.tv_displaySkill_5)
//    TextView tvDisplaySkill5;
//    @BindView(R.id.tv_displaySkill_6)
//    TextView tvDisplaySkill6;
//    @BindView(R.id.tv_displaySkill_7)
//    TextView tvDisplaySkill7;
//    @BindView(R.id.tv_displaySkill_8)
//    TextView tvDisplaySkill8;
    @BindView(R.id.content_assembling)
    LinearLayout contentAssembling;
    @BindView(R.id.spinner_headSkill)
    Spinner spinnerHeadSkill;
    @BindView(R.id.spinner_handSkill)
    Spinner spinnerHandSkill;
    @BindView(R.id.spinner_clothesSkill)
    Spinner spinnerClothesSkill;
    @BindView(R.id.spinner_waistSkill)
    Spinner spinnerWaistSkill;
    @BindView(R.id.spinner_footSkill)
    Spinner spinnerFootSkill;
    @BindView(R.id.spinner_guardStoneSkill_1)
    Spinner spinnerGuardStoneSkill1;
    @BindView(R.id.spinner_guardStoneSkillNum_1)
    Spinner spinnerGuardStoneSkillNum1;
    @BindView(R.id.spinner_guardStoneSkill_2)
    Spinner spinnerGuardStoneSkill2;
    @BindView(R.id.spinner_guardStoneSkillNum_2)
    Spinner spinnerGuardStoneSkillNum2;
    @BindView(R.id.spinner_guardStoneSkill_3)
    Spinner spinnerGuardStoneSkill3;
    @BindView(R.id.spinner_guardStoneSkillNum_3)
    Spinner spinnerGuardStoneSkillNum3;

    private List<SuitSkillAttribute> mListCache/* = new ArrayList<>()*/;
    private List<SuitSkillAttribute> mList = new ArrayList<>();
    private Map<String, Map<String, Integer>> map = new HashMap<>();

    private Map<String, Integer> displayMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_assembling);
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

        mListCache = getIntent().getParcelableArrayListExtra("mListCache");
//        addData();
        initFirstSpinner();
        initHandSkillSpinner();
        initClothesSkillSpinner();
        initWaistSkillSpinner();
        initFootSkillSpinner();


    }

    private void calculate() {

        final TextView tv[] = {tvHeadSkill1, tvHeadSkill2, tvHeadSkill3, tvHeadSkill4, tvHeadSkill5,
                tvHandSkill1, tvHandSkill2, tvHandSkill3, tvHandSkill4, tvHandSkill5,
                tvClothesSkill1, tvClothesSkill2, tvClothesSkill3, tvClothesSkill4, tvClothesSkill5,
                tvWaistSkill1, tvWaistSkill2, tvWaistSkill3, tvWaistSkill4, tvWaistSkill5,
                tvFootSkill1, tvFootSkill2, tvFootSkill3, tvFootSkill4, tvFootSkill5};
        displayMap.clear();
        for (int i = 0; i < tv.length; i++) {
            if (tv[i].getText().toString().length() > 2) {
                Log.i(TAG, "initData: tv[i].getText().toString():" + tv[i].getText().toString());
                String a[] = tv[i].getText().toString().split("\\:");
                Log.i(TAG, "initData: " + a[0]);
                Log.i(TAG, "initData: " + a[1]);
                String skillName = a[0];
                int skillNum = 0;
                if (a[1].length() > 1) {
                    skillNum = Integer.parseInt(a[1].trim());
                }
//            Map<String,Integer> map2 = new HashMap<>();
//            map2.put(skillName,skillNum);
//            map.put(s[i],map2);
                Log.i(TAG, "initData: skillName:" + skillName + "\tskillNum:" + skillNum);

//                Log.i(TAG, "initData: displayMap:"+displayMap.values());
                if (displayMap.containsKey(skillName)) {
                    Log.i(TAG, "initData: displayMap.get(skillName):" + displayMap.get(skillName));
                    displayMap.put(skillName, displayMap.get(skillName) + skillNum);
                } else {
                    displayMap.put(skillName, skillNum);
                }
                Log.i(TAG, "initData: displayMap:" + displayMap.toString());
            }
        }
//        TextView tvDisplay[] = {}
//        tvDisplaySkill5.setText(displayMap.values().toString());
        String display[] = displayMap.toString().substring(1, displayMap.toString().length() - 1).split(",");
        String displaySkillName[] = new String[display.length];
        String displaySkillNum[] = new String[display.length];
        for (int i = 0; i < display.length; i++) {
            displaySkillName[i] = display[i].split("=")[0];
            displaySkillNum[i] = display[i].split("=")[1];
        }
        String text = "";
        String text2 = "";
        for (int i = 0; i < displaySkillName.length / 2; i++) {
            text = text + "\n" + displaySkillName[i];
            text2 = text2 + "\n" + displaySkillNum[i];
        }
        tvDisplaySkill1.setText(text);
        tvDisplaySkill2.setText(text2);
        text = "";
        text2 = "";
        for (int i = displaySkillName.length / 2; i < displaySkillName.length; i++) {
            text = text + "\n" + displaySkillName[i];
            text2 = text2 + "\n" + displaySkillNum[i];
        }
        tvDisplaySkill3.setText(text);
        tvDisplaySkill4.setText(text2);


    }


    private void initFirstSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_headSkill);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mListCache.size(); i++) {
            list.add(mListCache.get(i).getName() + "_" + mListCache.get(i).getHead_hole());

        }

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int a = Integer.parseInt(o1.substring(o1.length() - 1, o1.length()));
                int b = Integer.parseInt(o2.substring(o2.length() - 1, o2.length()));

                //a > b 返回1 为 从小到大 ，返回 -1 从大到小

                if (a > b) {
                    return -1;
                } else if (a < b) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i(TAG, "initLeftSpinner: position:" + position);
                for (int i = 0; i < mListCache.size(); i++) {
                    String name = list.get(position).split("_")[0];
                    if (name.equals(mListCache.get(i).getName())) {
                        tvHeadSkill1.setText(mListCache.get(i).getSkillName_1() + ": " + mListCache.get(i).getHeadSkill_1());
                        tvHeadSkill2.setText(mListCache.get(i).getSkillName_2() + ": " + mListCache.get(i).getHeadSkill_2());
                        tvHeadSkill3.setText(mListCache.get(i).getSkillName_3() + ": " + mListCache.get(i).getHeadSkill_3());
                        tvHeadSkill4.setText(mListCache.get(i).getSkillName_4() + ": " + mListCache.get(i).getHeadSkill_4());
                        tvHeadSkill5.setText(mListCache.get(i).getSkillName_5() + ": " + mListCache.get(i).getHeadSkill_5());
                        break;
                    }

                }
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initHandSkillSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_handSkill);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mListCache.size(); i++) {
            list.add(mListCache.get(i).getName() + "_" + mListCache.get(i).getHand_hole());

        }

//        Log.i(TAG, "initHandSkillSpinner: list:"+list.toString());
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
//                if(o1 != null && o1.length() > 1 && o2 != null && o2.length() > 1){
//                }
                int a = Integer.parseInt(o1.substring(o1.length() - 1, o1.length()));
                int b = Integer.parseInt(o2.substring(o2.length() - 1, o2.length()));
                if (a > b) {
                    return -1;
                } else if (a < b) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i(TAG, "initLeftSpinner: position:" + position);
                for (int i = 0; i < mListCache.size(); i++) {
                    String name = list.get(position).split("_")[0];
                    if (name.equals(mListCache.get(i).getName())) {
                        tvHandSkill1.setText(mListCache.get(i).getSkillName_1() + ": " + mListCache.get(i).getHandSkill_1());
                        tvHandSkill2.setText(mListCache.get(i).getSkillName_2() + ": " + mListCache.get(i).getHandSkill_2());
                        tvHandSkill3.setText(mListCache.get(i).getSkillName_3() + ": " + mListCache.get(i).getHandSkill_3());
                        tvHandSkill4.setText(mListCache.get(i).getSkillName_4() + ": " + mListCache.get(i).getHandSkill_4());
                        tvHandSkill5.setText(mListCache.get(i).getSkillName_5() + ": " + mListCache.get(i).getHandSkill_5());
                        break;
                    }
                }
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initClothesSkillSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_clothesSkill);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mListCache.size(); i++) {
            list.add(mListCache.get(i).getName() + "_" + mListCache.get(i).getClothes_hole());

        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int a = Integer.parseInt(o1.substring(o1.length() - 1, o1.length()));
                int b = Integer.parseInt(o2.substring(o2.length() - 1, o2.length()));
                if (a > b) {
                    return -1;
                } else if (a < b) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i(TAG, "initLeftSpinner: position:" + position);
                for (int i = 0; i < mListCache.size(); i++) {
                    String name = list.get(position).split("_")[0];
                    if (name.equals(mListCache.get(i).getName())) {
                        tvClothesSkill1.setText(mListCache.get(i).getSkillName_1() + ": " + mListCache.get(i).getClothesSkill_1());
                        tvClothesSkill2.setText(mListCache.get(i).getSkillName_2() + ": " + mListCache.get(i).getClothesSkill_2());
                        tvClothesSkill3.setText(mListCache.get(i).getSkillName_3() + ": " + mListCache.get(i).getClothesSkill_3());
                        tvClothesSkill4.setText(mListCache.get(i).getSkillName_4() + ": " + mListCache.get(i).getClothesSkill_4());
                        tvClothesSkill5.setText(mListCache.get(i).getSkillName_5() + ": " + mListCache.get(i).getClothesSkill_5());
                        break;
                    }

                }
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initWaistSkillSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_waistSkill);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mListCache.size(); i++) {
            list.add(mListCache.get(i).getName() + "_" + mListCache.get(i).getWaist_hole());

        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int a = Integer.parseInt(o1.substring(o1.length() - 1, o1.length()));
                int b = Integer.parseInt(o2.substring(o2.length() - 1, o2.length()));
                if (a > b) {
                    return -1;
                } else if (a < b) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i(TAG, "initLeftSpinner: position:" + position);
                for (int i = 0; i < mListCache.size(); i++) {
                    String name = list.get(position).split("_")[0];
                    if (name.equals(mListCache.get(i).getName())) {
                        tvWaistSkill1.setText(mListCache.get(i).getSkillName_1() + ": " + mListCache.get(i).getWaistSkill_1());
                        tvWaistSkill2.setText(mListCache.get(i).getSkillName_2() + ": " + mListCache.get(i).getWaistSkill_2());
                        tvWaistSkill3.setText(mListCache.get(i).getSkillName_3() + ": " + mListCache.get(i).getWaistSkill_3());
                        tvWaistSkill4.setText(mListCache.get(i).getSkillName_4() + ": " + mListCache.get(i).getWaistSkill_4());
                        tvWaistSkill5.setText(mListCache.get(i).getSkillName_5() + ": " + mListCache.get(i).getWaistSkill_5());
                        break;
                    }

                }
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initFootSkillSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner_footSkill);

        final List<String> list = new ArrayList<>();

        for (int i = 0; i < mListCache.size(); i++) {
            list.add(mListCache.get(i).getName() + "_" + mListCache.get(i).getFoot_hole());

        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int a = Integer.parseInt(o1.substring(o1.length() - 1, o1.length()));
                int b = Integer.parseInt(o2.substring(o2.length() - 1, o2.length()));
                if (a > b) {
                    return -1;
                } else if (a < b) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        //android.R.layout.simple_spinner_item为系统默认样式
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Log.i(TAG, "initLeftSpinner: position:" + position);
                for (int i = 0; i < mListCache.size(); i++) {
                    String name = list.get(position).split("_")[0];
                    if (name.equals(mListCache.get(i).getName())) {
                        tvFootSkill1.setText(mListCache.get(i).getSkillName_1() + ": " + mListCache.get(i).getFootSkill_1());
                        tvFootSkill2.setText(mListCache.get(i).getSkillName_2() + ": " + mListCache.get(i).getFootSkill_2());
                        tvFootSkill3.setText(mListCache.get(i).getSkillName_3() + ": " + mListCache.get(i).getFootSkill_3());
                        tvFootSkill4.setText(mListCache.get(i).getSkillName_4() + ": " + mListCache.get(i).getFootSkill_4());
                        tvFootSkill5.setText(mListCache.get(i).getSkillName_5() + ": " + mListCache.get(i).getFootSkill_5());
                        break;
                    }
                }
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
