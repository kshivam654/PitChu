package com.app.sharma.pitchu.Adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.sharma.pitchu.Fragment.CategoryFragment;
import com.app.sharma.pitchu.Fragment.DalilyPopularFragment;
import com.app.sharma.pitchu.Fragment.RecentsFragment;

/**
 * Created by Sharma's on 3/24/2018.
 */

public class MyFragmentAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public MyFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return CategoryFragment.getInstance();
        else if (position == 1)
            return DalilyPopularFragment.getInstance();
        else if (position == 2)
            return RecentsFragment.getInstance();
        else
            return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Category";

            case 1:
                return "Daliy Popular";

            case 2:
                return "Recents";

        }
        return "";
    }
}
