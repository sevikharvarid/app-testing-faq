package com.example.appuser;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class TestFragment extends Fragment {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mPager;
    private MyPagerAdapter mAdapter;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;

    private ListView mainListView;
    private FirebaseAuth auth;
    private String desc, tilt, cat;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_test_fragment, container, false);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mPager = view.findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(5);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPager.setCurrentItem(tab.getPosition());
                // String data = tab.getText().toString();
                //Bundle bd1 = new Bundle();
                //Fragment frg = new MyFragment().newInstance();
                //bd1.putString("data", data);
                //frg.setArguments(bd1);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setDynamicFragmentToTabLayout();


        return view;
    }


    private void setDynamicFragmentToTabLayout() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("SpinnerData");
        listener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot item : dataSnapshot.getChildren()) {
                    mTabLayout.addTab(mTabLayout.newTab().setText(item.getValue().toString()));
                    mAdapter = new MyPagerAdapter(getFragmentManager(),
                            mTabLayout.getTabCount());
                    //mDynamicFragmentAdapter.addItem(item.getValue().toString());
                    mAdapter.addFragments(item.getValue().toString());
                    mPager.setAdapter(mAdapter);
                    mPager.setCurrentItem(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public static class MyFragment extends Fragment {


        public MyFragment() {

        }

        public static MyFragment newInstance(String title) {

            MyFragment fragment = new MyFragment();

            Bundle args = new Bundle();
            args.putCharSequence("title", title);
            fragment.setArguments(args);
            return fragment;

            //return MyFragment.newInstance();
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            String titleTab = getTitle();
            Bundle arguments = getArguments();

            //  int pageNumber = arguments.getInt(ARG_PAGE);
            // String text = savedInstanceState.getString("data");
            TextView myText = new TextView(getActivity());
            myText.setText("Hello I am the text inside this Fragment " + titleTab);
            myText.setGravity(Gravity.CENTER);
            return myText;
        }

        public String getTitle() {
            Bundle args = getArguments();
            return (String) args.getCharSequence("title", "NO TITLE FOUND");
        }
    }

}

class MyPagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private String x;
    private final ArrayList<String> mFragmentTitleList = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
       /* Bundle b = new Bundle();
        b.putInt("position", position);
        Fragment frag = TestFragment.MyFragment.newInstance();
        frag.setArguments(b);
        return frag;

        */
        TestFragment.MyFragment myFragment = TestFragment.MyFragment.newInstance(x);
        return myFragment;
    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    //  public void addItem(String title) {
    //     mFragmentTitleList.add(title);
    //    TestFragment.MyFragment.newInstance(mFragmentTitleList);
    // }
    public void addFragments(String title) {
        x = title;
        TestFragment.MyFragment.newInstance(x);
    }

}