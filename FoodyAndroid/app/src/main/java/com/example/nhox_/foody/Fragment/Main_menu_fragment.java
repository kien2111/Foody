package com.example.nhox_.foody.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nhox_.foody.Adapter.PagerAdapterForFragmentinFragment;
import com.example.nhox_.foody.CustomView.CustomBottomSheetDialogFragment;
import com.example.nhox_.foody.R;

import java.util.List;
import java.util.Vector;

import UtilPackage.INotifier;


/**
 * Created by nhox_ on 24/3/2017.
 */

public class Main_menu_fragment extends Fragment implements ViewPager.OnPageChangeListener, INotifier {

    ViewPager mViewPager;
    //PagerAdapter mPagerAdapter;
    //private int mColor;
    View view;
    public static View viewofparent;
    View viewofactionbar;
    //private View mContent;
    //private TextView mTextView;

    //parent view
    //FrameLayout parentlayout;

    /////////////
    // input:
    // purpose: Tạo ra fragment Main_menu_fragment
    // output: Trả về đối tượng Main_menu_fragment đã được khởi tạo
    /////////////
    public static Fragment newInstance() {
        Fragment frag = new Main_menu_fragment();
        return frag;
    }


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.main_menu_fragment, container, false);
        viewofparent = view;
        Toolbar myToolbar = (Toolbar) view.findViewById(R.id.main_menu_toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        if(myToolbar!=null)System.out.println("ton tai toolbar");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(myToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        myToolbar.setContentInsetsAbsolute(0,0);
        Button btn = (Button)view.findViewById(R.id.icon_foody_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentKhamPha("KhamPha");
            }
        });
        Button plusbtn = (Button)view.findViewById(R.id.plusButton);
        final BottomSheetDialogFragment bottomSheetDialogFragment = new CustomBottomSheetDialogFragment();

        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialogFragment.show(getFragmentManager(), bottomSheetDialogFragment.getTag());
                //show it


            }
        });
        viewofactionbar= view;
        mViewPager=(ViewPager)view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(buildAdapter());
        this.mViewPager.setOnPageChangeListener(this);
        changebutton(0);




        handleMenuEvent();
        return view;
    }








    /////////////
    // input: Chuỗi tag đã gán cho mỗi fragment để xác định fragment
    // purpose: Ẩn hiện các fragment
    // output:
    /////////////
    private void replaceFragmentKhamPha(String tag){
        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if(fragment!=null){
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for(int i =0;i<fragmentManager.getFragments().size();i++){
                fragmentTransaction.hide(fragmentManager.getFragments().get(i));
            }
            fragmentTransaction.show(fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    /////////////
    // input: Vị trí fragment trong viewpager đang hiện lên màn hình
    // purpose: Đổi màu các button ăn gì, ở đâu
    // output:
    /////////////
    private void changebutton(int position) {
        Button l = (Button)viewofactionbar.findViewById(R.id.OdauButton);
        Button r = (Button)viewofactionbar.findViewById(R.id.AngiButton);
        if(position==0){
            r.setTextColor(getResources().getColor(R.color.backgroundoftab));
            l.setTextColor(Color.BLACK);
            l.setBackgroundResource(R.drawable.shape_button);
            r.setBackgroundResource(R.drawable.button_selected_right);
        }else{
            l.setTextColor(getResources().getColor(R.color.backgroundoftab));
            r.setTextColor(Color.BLACK);
            l.setBackgroundResource(R.drawable.button_selected);
            r.setBackgroundResource(R.drawable.shape_button_right);

        }
    }


    /////////////
    // input:
    // purpose: Xử lý sự kiện khi người dùng nhấn hai button ăn gì ở đâu
    // output:
    /////////////
    public void handleMenuEvent(){
        Button odau = (Button)viewofactionbar.findViewById(R.id.OdauButton);
        Button angi = (Button)viewofactionbar.findViewById(R.id.AngiButton);
        odau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
                changebutton(0);
            }
        });
        angi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
                changebutton(1);
            }
        });
    }




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);

        }
    }






    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        //inflater.inflate(R.menu.menu,menu);

        super.onCreateOptionsMenu(menu,inflater);
    }

    ///////////////////
    // input : Danh sách các thành phố , Class
    // purpose : Khởi tạo Dialog listview
    // output : ListviewDialogFragment
    /////////////////////
    private PagerAdapterForFragmentinFragment buildAdapter() {
        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(MainFragment.newInstance());
        fragments.add(MainFragment1.newInstance());
        return(new PagerAdapterForFragmentinFragment(getActivity(), getChildFragmentManager(),fragments));
    }



    private void replaceFragment(String tag){
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if(fragment!=null){
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.viewPager, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            System.out.println("replace fragment");
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    ///////////////////
    // input :
    // purpose : Tạo hiệu ứng thay đổi các button
    // output :
    /////////////////////
    @Override
    public void onPageSelected(int position) {
        changebutton(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    ///////////////////
    // input :
    // purpose : Thông báo sự thay đổi cấu trúc của Adapter
    // output :
    /////////////////////
    @Override
    public void notify(Object data) {
        mViewPager.getAdapter().notifyDataSetChanged();
    }


}




