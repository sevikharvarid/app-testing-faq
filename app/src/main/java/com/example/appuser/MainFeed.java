package com.example.appuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainFeed extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private ImageView profileIcon;

    //    initializing the fragments
    private HomeFragment2 homeFragment;
    private ProfileFragment profileFragment;
    private AskFragment askFragment;
    private SettingsFragment settingsFragment;
    private FeedbackFragment feedbackFragment;
    private TestFragment testFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_feed);

        homeFragment = new HomeFragment2();
        testFragment = new TestFragment();
        askFragment = new AskFragment();
        settingsFragment = new SettingsFragment();
        feedbackFragment = new FeedbackFragment();
        profileFragment = new ProfileFragment();
//        profileIcon = findViewById(R.id.profileIcon);
        bottomNavigationView = findViewById(R.id.mainNavBar);
        frameLayout = findViewById(R.id.navFrame);
//        Intent i = new Intent(MainFeed.this,HomeActivity.class);
        //      startActivity(i);
        //changeFragment(homeFragment);
        changeFragment(testFragment);

//        profileIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i=new Intent(MainFeed.this,personalprofile.class);
//                startActivity(i);
//            }
//        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navBtnHome:
                       // changeFragment(homeFragment);
                        changeFragment(testFragment);
                        //Intent i = new Intent(MainFeed.this,HomeActivity.class);
                        //startActivity(i);
                        return true;
                    case R.id.navBtnAsk:
                        changeFragment(askFragment);
                        return true;
                    case R.id.navBtnContact:
                        Intent contactUsIntent = new Intent(MainFeed.this, addCategory.class);
                        startActivity(contactUsIntent);
                        return true;
                    case R.id.navBtnProfile:
                        Intent ix = new Intent(MainFeed.this, personalprofile.class);
                        startActivity(ix);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        changeFragment(testFragment);
    }

    public void ChangeToHome() {
        changeFragment(testFragment);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
    }

    private void changeFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.navFrame, fragment);
        fragmentTransaction.commit();

    }

}
