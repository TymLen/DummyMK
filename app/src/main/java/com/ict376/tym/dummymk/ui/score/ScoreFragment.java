package com.ict376.tym.dummymk.ui.score;

import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.ict376.tym.dummymk.R;
import com.ict376.tym.dummymk.ui.database.ScoreData;
import com.ict376.tym.dummymk.ui.game.CardAdapter;

import java.util.ArrayList;


public class ScoreFragment extends Fragment {

    private ScoreViewModel mViewModel;
    private ListView mScoreList;
    private ArrayList<String> listDates, listScene, listScore, listHero;
    private Button mRefresh;
    private String currScen="All";
    private Spinner mScenSpin;

    public static ScoreFragment newInstance() {
        return new ScoreFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.score_fragment, container, false);
        currScen="All";
        mScoreList = (ListView) v.findViewById(R.id.score_listview);
        mScenSpin = (Spinner) v.findViewById(R.id.score_scenSpin);
        mScenSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currScen = mScenSpin.getSelectedItem().toString();
                getScores(currScen);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mRefresh = (Button) v.findViewById(R.id.score_refresh);
        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScores(currScen);
            }
        });
        getScores(currScen);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ScoreViewModel.class);
        // TODO: Use the ViewModel
    }
    private void getScores(String inScenario){
        Cursor cursor;
        ScoreData db = new ScoreData(getContext());
        listDates = new ArrayList<>();
        listScene = new ArrayList<>();
        listScore = new ArrayList<>();
        listHero = new ArrayList<>();

        if(inScenario.equals("All")){
            cursor = db.getAllScores();
            Log.d("Load", Integer.toString(cursor.getCount()));
        }else{
            cursor = db.getScenarioScores(inScenario);
            Log.d("Load", Integer.toString(cursor.getCount()));
        }
        cursor.moveToFirst();
        try{
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                listDates.add(cursor.getString(1));
                listHero.add(cursor.getString(2));
                listScore.add(cursor.getString(3));
                listScene.add(cursor.getString(4));
            }
        }finally{
            cursor.close();
        }
        for(int i = 0; i< listDates.size(); i++){
            Log.d("Load_Dates", listDates.get(i));
        }
        for(int i = 0; i< listHero.size(); i++){
            Log.d("Load_Hero", listHero.get(i));
        }
        for(int i = 0; i< listScore.size(); i++){
            Log.d("Load_Score", listScore.get(i));
        }
        for(int i = 0; i< listScene.size(); i++){
            Log.d("Load_Scenerio", listScene.get(i));
        }
        ScoreAdapter sAd = new ScoreAdapter(getContext(), listScore, listScene, listDates, listHero);
        mScoreList.setAdapter(sAd);
    }
    public void refreshList(){


    }

}
