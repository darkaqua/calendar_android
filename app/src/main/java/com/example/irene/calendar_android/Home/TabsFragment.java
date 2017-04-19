package com.example.irene.calendar_android.Home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.irene.calendar_android.Home.MyAdapterFragment;
import com.example.irene.calendar_android.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabsFragment extends Fragment {

    public  static TabLayout tabLayout;
    public  static ViewPager viewPager;
    //Numeros de pestanyes que tenim
    public  static int int_items= 2;

    public TabsFragment() {
        //Constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tabs,null);
        tabLayout=(TabLayout)v.findViewById(R.id.tabs);
        viewPager=(ViewPager)v.findViewById(R.id.viewpager);
        //set an adpater

        viewPager.setAdapter(new MyAdapterFragment( getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return v;
    }
}
