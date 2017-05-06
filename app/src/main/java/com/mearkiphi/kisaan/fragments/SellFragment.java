package com.mearkiphi.kisaan.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mearkiphi.kisaan.R;


/**
 * Created by anuragmaravi on 05/04/17.
 */

public class SellFragment extends Fragment {

    private View rootView;

    public SellFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cards, container, false);

        return rootView;
    }


}
