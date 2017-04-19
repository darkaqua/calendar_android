package com.example.irene.calendar_android.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.irene.calendar_android.Fragments.CalendariFragment;
import com.example.irene.calendar_android.Fragments.EmpresaFragment;

/**
 * Created by Irene on 30/03/2017.
 */

//Adaptador que s'utilitza per fer el Navigation drawer en el Main Activity
public class MyAdapterFragment extends FragmentPagerAdapter {

    public MyAdapterFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new CalendariFragment();
        } else {
            return new EmpresaFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
