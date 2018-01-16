package com.example.nhox_.foody.Search_Group;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhox_.foody.R;

/**
 * Created by nhox_ on 13/4/2017.
 */

public class SearchFragment extends Fragment {
    public static SearchFragment instance(){
        return new SearchFragment();
    }
    View view;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);
        context = view.getContext();
        return view;
    }
}
