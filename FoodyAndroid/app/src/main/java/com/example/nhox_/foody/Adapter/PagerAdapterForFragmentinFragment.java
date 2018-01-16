package com.example.nhox_.foody.Adapter;

/**
 * Created by nhox_ on 25/3/2017.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;


import com.example.nhox_.foody.Fragment.MainFragment;

import java.util.List;
/////////////
// input:
// purpose: Cài đặt class PagerAdapterForFragmentinFragment cho viewpager trong main_menu_fragment
// output:
/////////////
public class PagerAdapterForFragmentinFragment extends FragmentPagerAdapter {
    Context ctxt=null;private long baseId = 0;

    private List<Fragment> fragments;
    public PagerAdapterForFragmentinFragment(Context ctxt, FragmentManager mgr,List<Fragment> fragments) {
        super(mgr);
        this.ctxt=ctxt;
        this.fragments = fragments;
    }

    //Trả về fragment tại vị trí position
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    //Đếm số lượng fragment
    @Override
    public int getCount() {
        return this.fragments.size();
    }

    //
    @Override
    public int getItemPosition(Object object) {
        if(object instanceof MainFragment){
            MainFragment f = (MainFragment)object;
            if(f!=null){
                f.update();
            }
        }
        return super.getItemPosition(object);
    }

    //Trả về id của item tại vị trí position
    @Override
    public long getItemId(int position) {
        // give an ID different from position when position has been changed
        return baseId + position;
    }



}