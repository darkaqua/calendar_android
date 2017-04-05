package com.example.irene.calendar_android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static com.example.irene.calendar_android.TabsFragment.int_items;

/**
 * Created by Irene on 30/03/2017.
 */

public class MyAdapterFragment extends FragmentPagerAdapter {


    public MyAdapterFragment(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CalendariFragment();
            case 1:
                return new EmpresaFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Calendari";
            case 1:
                return "Empreses";


        }

        return null;
    }






   /* public MyAdapterFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TabsFragment().newInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public  CharSequence getPageTitle (int position){
        switch (position){
            case 0:
                return "Calendari";
            case 1:
                return "Empreses";



        }
        return super.getPageTitle(position);
    }*/
}
