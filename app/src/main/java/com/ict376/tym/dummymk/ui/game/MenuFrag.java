package com.ict376.tym.dummymk.ui.game;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ict376.tym.dummymk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFrag extends Fragment {

    public MenuFrag() {
        // Required empty public constructor
    }

    public MenuFrag newInstance(){
        return new MenuFrag();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}
