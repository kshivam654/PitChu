package com.app.sharma.pitchu.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.sharma.pitchu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DalilyPopularFragment extends Fragment {


    public static DalilyPopularFragment INSTANCE = null;

    public DalilyPopularFragment() {
        // Required empty public constructor
    }


    public static DalilyPopularFragment getInstance() {

        if (INSTANCE == null)
            INSTANCE = new DalilyPopularFragment();
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dalily_popular, container, false);
    }

}
