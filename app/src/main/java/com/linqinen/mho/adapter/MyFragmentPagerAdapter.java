package com.linqinen.mho.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentLists = new ArrayList<>();


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentLists) {
        super(fm);
        this.fragmentLists = fragmentLists;
    }

    public void addFragment(Fragment fragment) {
        fragmentLists.add(fragment);
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragmentLists.get(arg0);
    }

    @Override
    public int getCount() {
        return fragmentLists.size();
    }





}
