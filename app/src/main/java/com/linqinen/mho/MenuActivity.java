package com.linqinen.mho;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.linqinen.mho.activity.AssemblingActivity;
import com.linqinen.mho.activity.GuardStoneActivity;
import com.linqinen.mho.activity.GuardStonePriceActivity;
import com.linqinen.mho.activity.HuntingHornActivity;
import com.linqinen.mho.activity.MonstersActivity;
import com.linqinen.mho.activity.MyGuardStoneActivity;
import com.linqinen.mho.adapter.MyPagerAdapter;
import com.linqinen.mho.fragment.MonsterMaterialFragment;
import com.linqinen.mho.fragment.NormalMaterialFragment;
import com.linqinen.mho.fragment.SkillBeadsFragment;
import com.linqinen.mho.fragment.SuitSkillAttributeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.linqinen.mho.R.id.toolbar;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "MenuActivity";

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(toolbar)
    Toolbar mToolbar;

    /**
     * 青色
     */
    public static int CYAN;
    public static int YELLOW, GREEN, RED, GREY, BLACK;

    public static final int NORMAL_MATERIAL_FRAGMENT = 0, MONSTER_MATERIAL_FRAGMENT = 1,
            SUIT_SKILL_ATTRIBUTE_FRAGMENT = 2, SKILL_BEADS = 3;

    public static int fragmentPosition = 0;
    @BindView(R.id.content_menu)
    LinearLayout contentMenu;


    private NormalMaterialFragment mNormalMaterialFragment;
    private MonsterMaterialFragment mMonsterMaterialFragment;
    private SuitSkillAttributeFragment mSuitSkillAttributeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//
//        fab.setAlpha(0.5f);
        ActionBarDrawerToggle
                toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        initData();

    }

    private long exitTime = 0;


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i(TAG, "onCreateOptionsMenu: fragmentPosition:" + fragmentPosition);
        if (fragmentPosition == NORMAL_MATERIAL_FRAGMENT) {
            if (menu.size() == 0) {
                getMenuInflater().inflate(R.menu.menu_normal_material_price, menu);
                initSearchView(menu);
            }
            return true;
        } else if (fragmentPosition == MONSTER_MATERIAL_FRAGMENT) {
            if (menu.size() == 0) {
                getMenuInflater().inflate(R.menu.menu_normal_material_price, menu);
                initSearchView(menu);
            }
            return true;
        } else {
            menu.clear();
            return false;
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.addItem:
                if (fragmentPosition == NORMAL_MATERIAL_FRAGMENT) {
                    mNormalMaterialFragment.setOnOptionsItemSelected(NormalMaterialFragment.ADD_ITEM);
                } else if (fragmentPosition == MONSTER_MATERIAL_FRAGMENT) {
                    mMonsterMaterialFragment.setOnOptionsItemSelected(NormalMaterialFragment.ADD_ITEM);

                }
                break;
            case R.id.deleteAllData:
                if (fragmentPosition == NORMAL_MATERIAL_FRAGMENT) {
                    mNormalMaterialFragment.setOnOptionsItemSelected(NormalMaterialFragment.DELETE_ALL_DATA);
                } else if (fragmentPosition == MONSTER_MATERIAL_FRAGMENT) {
                    mMonsterMaterialFragment.setOnOptionsItemSelected(NormalMaterialFragment.DELETE_ALL_DATA);

                }

                break;
            case R.id.filtrateBeModifiedData:
                if (fragmentPosition == NORMAL_MATERIAL_FRAGMENT) {
                    mNormalMaterialFragment.setOnOptionsItemSelected(NormalMaterialFragment.FILTRATE_BE_MODIFIED_DATA);
                } else if (fragmentPosition == MONSTER_MATERIAL_FRAGMENT) {
                    mMonsterMaterialFragment.setOnOptionsItemSelected(NormalMaterialFragment.FILTRATE_BE_MODIFIED_DATA);

                }
                break;
            case R.id.search:
                Log.i(TAG, "onOptionsItemSelected: search");
                Log.i(TAG, "initSearchView: mMonsterMaterialFragment:" + mMonsterMaterialFragment);
                Log.i(TAG, "initSearchView: mDBhelper:" + mMonsterMaterialFragment.mDBhelper);
//                Cursor cursor = null;
//                if (fragmentPosition == NORMAL_MATERIAL_FRAGMENT) {
//                    if(mNormalMaterialFragment.mDBhelper != null){
//                        cursor = mNormalMaterialFragment.mDBhelper.cursorQuery();
//                        Log.i(TAG, "initSearchView: cursor.getColumnCount():"+cursor.getColumnCount());
//                        Log.i(TAG, "initSearchView: cursor.getCount():"+cursor.getCount());
//
//                    }
//                } else if (fragmentPosition == MONSTER_MATERIAL_FRAGMENT) {
//                    if(mMonsterMaterialFragment.mDBhelper != null){
//                        cursor = mMonsterMaterialFragment.mDBhelper.cursorQuery();
//                        Log.i(TAG, "initSearchView: cursor.getColumnCount():"+cursor.getColumnCount());
//                        Log.i(TAG, "initSearchView: cursor.getCount():"+cursor.getCount());
//
//                    }
//                }
//                if(cursor != null){
//                    SimpleCursorAdapter mCursorAdapter = new SimpleCursorAdapter(this,R.layout.inflate_cursor,cursor,new String[] { "name" }, new int[] { R.id.tv_cursor_content });
//                    Log.i(TAG, "onOptionsItemSelected: mCursorAdapter.getCount():"+mCursorAdapter.getCount());
//
////        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.mytextview, cursor, new String[] { "tb_name" }, new int[] { R.id.textview });
//                    SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//
//                    searchView.setSuggestionsAdapter(mCursorAdapter);
//                }
                break;

            default:
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void initSearchView(Menu menu) {

//                final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
//                final EditText mEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

//                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//                searchView.setSearchableInfo(searchManager)来跳转到搜索结果的activity。
//                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));来跳转到搜索结果的activity。

//                searchView.setIconifiedByDefault(true);//设置展开后图标的样式,这里只有两种,false图标在搜索框外,true在搜索框内
//                searchView.onActionViewExpanded();// 写上此句后searchView初始是可以点击输入的状态，如果不写，那么就需要点击下放大镜，才能出现输入框,也就是设置为ToolBar的ActionView，默认展开
//                searchView.requestFocus();//输入焦点
//                searchView.setSubmitButtonEnabled(true);//添加提交按钮，监听在OnQueryTextListener的onQueryTextSubmit响应
//                searchView.setFocusable(true);//将控件设置成可获取焦点状态,默认是无法获取焦点的,只有设置成true,才能获取控件的点击事件
//                searchView.setIconified(false);//输入框内icon不显示
//                searchView.requestFocusFromTouch();//模拟焦点点击事件
//
//                searchView.setFocusable(false);//禁止弹出输入法，在某些情况下有需要
//                searchView.clearFocus();//禁止弹出输入法，在某些情况下有需要
//                searchView.setQueryHint("搜索");
//                final MenuItem searchMenuItem = menu.findItem(R.id.search);

//                final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        SearchView.SearchAutoComplete
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                Log.i(TAG, "onSuggestionSelect: position:" + position);
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Log.i(TAG, "onSuggestionClick: position:" + position);
                if (fragmentPosition == NORMAL_MATERIAL_FRAGMENT) {
                    Log.i(TAG, "onSuggestionClick: " + mNormalMaterialFragment.mListCache.get(position).getName());

                } else if (fragmentPosition == MONSTER_MATERIAL_FRAGMENT) {
                    Log.i(TAG, "onSuggestionClick: " + mMonsterMaterialFragment.mListCache.get(position).getName());
                }
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "onQueryTextSubmit: query:" + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "onQueryTextChange: newText:" + newText);
                if (fragmentPosition == NORMAL_MATERIAL_FRAGMENT) {
                    mNormalMaterialFragment.reInitRecyclerViewNameData(newText);

                } else if (fragmentPosition == MONSTER_MATERIAL_FRAGMENT) {
                    mMonsterMaterialFragment.reInitRecyclerViewNameData(newText);
                }
                return false;
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.skillBeadsActivity) {
            startActivity(new Intent(this, SkillBeadsActivity.class));
        } else if (id == R.id.materialPriceActivity) {
//            startActivity(new Intent(this, MaterialPriceActivity.class));
        } else if (id == R.id.normalMaterialPriceActivity) {
//            startActivity(new Intent(this, NormalMaterialPriceActivity.class));

        } else */
        if (id == R.id.assemblingActivity) {
            Intent intent = new Intent(this, AssemblingActivity.class);
            intent.putParcelableArrayListExtra("mListCache", mSuitSkillAttributeFragment.mListCache);
            startActivity(intent);
        } else if (id == R.id.guardStoneActivity) {
            startActivity(new Intent(this, GuardStoneActivity.class));
        } else if (id == R.id.huntingHornActivity) {
            startActivity(new Intent(this, HuntingHornActivity.class));
        } else if (id == R.id.guardStonePriceActivity) {
            startActivity(new Intent(this, GuardStonePriceActivity.class));
        } else if (id == R.id.monsterActivity) {
            startActivity(new Intent(this, MonstersActivity.class));
        } else if (id == R.id.myGuardStoneActivity) {
            startActivity(new Intent(this, MyGuardStoneActivity.class));
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        initColor();
        initViewPager();

    }

    private void initViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        mNormalMaterialFragment = new NormalMaterialFragment();
        mMonsterMaterialFragment = new MonsterMaterialFragment();
        mSuitSkillAttributeFragment = new SuitSkillAttributeFragment();

        adapter.addFragment(mNormalMaterialFragment, "普通材料");
        adapter.addFragment(mMonsterMaterialFragment, "怪物材料");
        adapter.addFragment(mSuitSkillAttributeFragment, "装备技能");
        adapter.addFragment(new SkillBeadsFragment(), "技能珠");
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
//        mViewPager.setPageTransformer(true, new DepthPageTransformer());
//        mViewPager.setPageTransformer(true, new MyPageTransformer());
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case NORMAL_MATERIAL_FRAGMENT:
                        onCreateOptionsMenu(mToolbar.getMenu());
                        break;
                    case MONSTER_MATERIAL_FRAGMENT:
                        onCreateOptionsMenu(mToolbar.getMenu());
                        break;
                    case SUIT_SKILL_ATTRIBUTE_FRAGMENT:
                    case SKILL_BEADS:
                        onCreateOptionsMenu(mToolbar.getMenu());
//                        mToolbar.getMenu().clear();
                        break;
                    default:
                        break;
                }
                super.onPageSelected(position);
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);

//        ViewTreeObserver vto = mTabLayout.getViewTreeObserver();
//        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            public boolean onPreDraw() {
//                //暂时注释
////                tabLayoutHeight = mTabLayout.getMeasuredHeight();
//                return true;
//            }
//        });
    }

    private void initColor() {
        GREEN = ContextCompat.getColor(this, R.color.green_6D9249);
        YELLOW = ContextCompat.getColor(this, R.color.yellow_FFBD00);
        RED = ContextCompat.getColor(this, R.color.red_C71420);
        GREY = ContextCompat.getColor(this, R.color.grey_5a5a5a);
        CYAN = ContextCompat.getColor(this, R.color.green_59d1bf);
        BLACK = ContextCompat.getColor(this, R.color.black_2f2f2f);
    }


}
