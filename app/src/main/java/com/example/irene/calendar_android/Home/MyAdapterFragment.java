package com.example.irene.calendar_android.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.irene.calendar_android.Fragments.CalendariFragment;
import com.example.irene.calendar_android.Fragments.EmpresaFragment;



//Adaptador que s'utilitzà per fer el Navigation drawer amb els tabs en el Main Activity
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
        return 1;
    }
}
