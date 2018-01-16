package com.example.nhox_.foody.Activity;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.nhox_.foody.Adapter.BottomBarFragmentAdapter;
import com.example.nhox_.foody.Adapter.PagerAdapterForFragmentinFragment;
import com.example.nhox_.foody.CustomView.NoSwipeViewPager;
import com.example.nhox_.foody.Fragment.Fragment_Kham_Pha;
import com.example.nhox_.foody.Fragment.Main_menu_fragment;
import com.example.nhox_.foody.R;
import com.example.nhox_.foody.Search_Group.SearchFragment;
import com.example.nhox_.foody.User_Group.UserFragment;
import com.example.nhox_.foody.User_Group.User_Store_Local;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DbConnect.DataBaseHelper;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {



    private BottomNavigationViewEx mBottomNav;
    private NoSwipeViewPager mNoSwipeViewPager;
    /*private int mSelectedItem;
    private static final String SELECTED_ITEM = "arg_selected_item";*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDBifNotExist();
        mBottomNav = (BottomNavigationViewEx)findViewById(R.id.bottom_navigation);
        mNoSwipeViewPager = (NoSwipeViewPager)findViewById(R.id.container);
        init();
        mBottomNav.setTextVisibility(false);
        mBottomNav.enableAnimation(false);
        mBottomNav.enableShiftingMode(false);
        mBottomNav.enableItemShiftingMode(false);

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        MenuItem selectedItem;
        selectedItem = mBottomNav.getMenu().getItem(0);

        selectFragment(selectedItem);

    }
    void init(){
        mNoSwipeViewPager.setPagingEnabled(false);
        mNoSwipeViewPager.setOffscreenPageLimit(4);
        BottomBarFragmentAdapter mBottomBarFragmentAdapter = new BottomBarFragmentAdapter(getSupportFragmentManager());
        mBottomBarFragmentAdapter.addFragments(Main_menu_fragment.newInstance());
        mBottomBarFragmentAdapter.addFragments(Fragment_Kham_Pha.newInstance());
        mBottomBarFragmentAdapter.addFragments(SearchFragment.instance());
        mBottomBarFragmentAdapter.addFragments(Fragment_Kham_Pha.newInstance());
        mBottomBarFragmentAdapter.addFragments(UserFragment.instance());
        mNoSwipeViewPager.setAdapter(mBottomBarFragmentAdapter);

    }

    /////////////
    // input: nhận vào một item mà người dùng click
    // purpose: Chọn fragment trên thanh bottombar để hiện lên màn hình
    // output:
    /////////////
    private void selectFragment(MenuItem item) {
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.home:
                mNoSwipeViewPager.setCurrentItem(0,false);
                break;
            case R.id.collection:
                mNoSwipeViewPager.setCurrentItem(1,false);
                break;
            case R.id.search:
                mNoSwipeViewPager.setCurrentItem(2,false);
                break;
            case R.id.notification:
                mNoSwipeViewPager.setCurrentItem(3,false);
                break;
            case R.id.user:
                mNoSwipeViewPager.setCurrentItem(4,false);
                break;
        }
        // update selected item
        //mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(0);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

    }


    private void createDBifNotExist(){
        try{
            DataBaseHelper db = DataBaseHelper.getInstance(this);
            db.isCreatedDatabase();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu, menu);

        return true;
    }





    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {

    }


}
