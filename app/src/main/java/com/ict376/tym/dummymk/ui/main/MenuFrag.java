package com.ict376.tym.dummymk.ui.main;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ict376.tym.dummymk.R;
import com.ict376.tym.dummymk.ui.database.DummyData;
import com.ict376.tym.dummymk.ui.game.GameContainer;
import com.ict376.tym.dummymk.ui.game.GameHost;
import com.ict376.tym.dummymk.ui.score.ScoreHost;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFrag extends Fragment {
    private Button mNew, mContinue, mScores;

    public MenuFrag() {
        // Required empty public constructor
    }

    public static MenuFrag newInstance(){
        return new MenuFrag();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
        mNew = (Button) v.findViewById(R.id.menu_NewGame);
        mContinue = (Button) v.findViewById(R.id.menu_LoadGame);
        mScores = (Button) v.findViewById(R.id.menu_Scores);
        mNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameContainer.class);
                startActivity(intent);
            }
        });
        DummyData db = new DummyData(getContext());
        Cursor cur = db.loadGame();
        Log.d("Load", Integer.toString(cur.getCount()));
        if(cur.getCount() != 0){
            mContinue.setEnabled(true);
        }else{
            mContinue.setEnabled(false);
            mContinue.setTextColor(getResources().getColor(R.color.black));

        }
        cur.close();
        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DummyData db = new DummyData(getContext());
                Intent intent = new Intent(getActivity(), GameHost.class);
                startActivity(intent);
            }
        });
        mScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScoreHost.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
