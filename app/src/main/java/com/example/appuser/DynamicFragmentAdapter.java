package com.example.appuser;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class DynamicFragmentAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private String nameCategory;
    private List<String> tabTitles = new ArrayList<>();

    DynamicFragmentAdapter(FragmentManager fm, int NumOfTabs,List<String> tabTitles) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.tabTitles =tabTitles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
     //   Bundle b = new Bundle();
       // b.putInt("position", position);
        //Fragment frag = DynamicFragment.newInstance();
            return DynamicFragment.newInstance(position,getPageTitle(position).toString());

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
