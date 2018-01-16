package com.example.nhox_.foody.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nhox_.foody.R;

/**
 * Created by nhox_ on 6/4/2017.
 */

public class Fragment_Kham_Pha extends Fragment {





    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_kham_roi_pha,container,false);
        setHasOptionsMenu(true);
        Toolbar myToolbar = (Toolbar) view.findViewById(R.id.khampha_fragment_toolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(myToolbar);
        if(myToolbar!=null)System.out.println("ton tai toolbar");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(myToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        myToolbar.setContentInsetsAbsolute(0,0);

        Button back_button = (Button)view.findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentKhamPha("home");
            }
        });


        return view;

    }

    /////////////
    // input:
    // purpose: Tạo ra fragment khám phá
    // output: Trả về đối tượng Fragment_Kham_Pha đã được khởi tạo
    /////////////
    public static Fragment_Kham_Pha newInstance() {
        Bundle args = new Bundle();
        Fragment_Kham_Pha fragment = new Fragment_Kham_Pha();
        fragment.setArguments(args);
        return fragment;
    }

    /////////////
    // input: Chuỗi tag đã gán cho fragment để xác định fragment nào cần hiện lên màn hình
    // purpose: Ẩn hiện fragment khi người dùng yêu cầu
    // output:
    /////////////
    private void replaceFragmentKhamPha(String tag){
        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if(fragment!=null){
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            for(int i=0;i<fragmentManager.getFragments().size();i++){
                fragmentTransaction.hide(fragmentManager.getFragments().get(i));
            }
            fragmentTransaction.show(fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
        }
    }



}
