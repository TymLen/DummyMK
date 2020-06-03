package com.ict376.tym.dummymk.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ict376.tym.dummymk.R;
import com.ict376.tym.dummymk.ui.game.GameHost;

import java.util.ArrayList;
import java.util.Random;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    String hero = "none";
    ArrayList<String> history = new ArrayList<>();
    ListView mHistoryList;
    TextView hSelect;
    Spinner daySpin, nightSpin;
    String[] rngHero = {"Goldyx","WolfHawk", "Tovak", "Norowas", "Krang", "Arythea", "Braevalar"};
    Button mGold, mWolf, mTovak, mNoro, mKrang, mAry, mBrae, mRan;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        Button mstartBut = (Button) view.findViewById(R.id.startBut);
        hSelect = (TextView) view.findViewById(R.id.heroConfirm);
        daySpin = (Spinner) view.findViewById(R.id.daySpinner);
        nightSpin = (Spinner) view.findViewById(R.id.nightSpinner);
        mstartBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean err = false;
                Intent intent = new Intent(getActivity(), GameHost.class);
                int days = Integer.parseInt(daySpin.getSelectedItem().toString());
                int nights = Integer.parseInt(nightSpin.getSelectedItem().toString());
                if(hero.equals("none")){
                    Toast.makeText(getContext(), "Select an AI opponent", Toast.LENGTH_SHORT).show();
                    err=true;
                }else{
                    intent.putExtra("name", hero);
                }
                if(days == 0){
                    Toast.makeText(getContext(), "Days can't be 0", Toast.LENGTH_SHORT).show();
                    err = true;
                }else{
                    intent.putExtra("days", days);
                }
                if(nights == 0){
                    Toast.makeText(getContext(), "Nights can't be 0", Toast.LENGTH_SHORT).show();
                    err = true;
                }else {
                    intent.putExtra("nights", nights);
                }if(days < nights){
                    Toast.makeText(getContext(), "Days can't be shorter than nights.", Toast.LENGTH_SHORT).show();
                    err = true;
                }
                if(!err){
                    startActivity(intent);
                }
            }
        });

        mGold = (Button) view.findViewById(R.id.goldBut);
        mGold.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                hero = "Goldyx";
                hSelect.setText(hero);
                clearSelected();
                mGold.setTextColor(getResources().getColor(R.color.red));

            }
        });
        mWolf = (Button) view.findViewById(R.id.wolfBut);
        mWolf.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                hero = "WolfHawk";
                hSelect.setText(hero);
                clearSelected();
                mWolf.setTextColor(getResources().getColor(R.color.red));

            }
        });
        mTovak = (Button) view.findViewById(R.id.tovakBut);
        mTovak.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                hero = "Tovak";
                hSelect.setText(hero);
                clearSelected();
                mTovak.setTextColor(getResources().getColor(R.color.red));

            }
        });
        mNoro = (Button) view.findViewById(R.id.noroBut);
        mNoro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                hero = "Norowas";
                hSelect.setText(hero);
                clearSelected();
                mNoro.setTextColor(getResources().getColor(R.color.red));

            }
        });
        mKrang = (Button) view.findViewById(R.id.krangBut);
        mKrang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                hero = "Krang";
                hSelect.setText(hero);
                clearSelected();
                mKrang.setTextColor(getResources().getColor(R.color.red));

            }
        });
        mAry = (Button) view.findViewById(R.id.aryBut);
        mAry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                hero = "Arythea";
                hSelect.setText(hero);
                clearSelected();
                mAry.setTextColor(getResources().getColor(R.color.red));

            }
        });
        mBrae = (Button) view.findViewById(R.id.braeBut);
        mBrae.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                hero = "Braevalar";
                hSelect.setText(hero);
                clearSelected();
                mBrae.setTextColor(getResources().getColor(R.color.red));

            }
        });
        mRan = (Button) view.findViewById(R.id.randomBut);
        mRan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Random rand = new Random();
                hero = rngHero[rand.nextInt(rngHero.length-1)];
                hSelect.setText("Random");
                clearSelected();
                mRan.setTextColor(getResources().getColor(R.color.red));

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }
    private void clearSelected(){
        mGold.setTextColor(getResources().getColor(R.color.text));
        mWolf.setTextColor(getResources().getColor(R.color.text));
        mTovak.setTextColor(getResources().getColor(R.color.text));
        mNoro.setTextColor(getResources().getColor(R.color.text));
        mKrang.setTextColor(getResources().getColor(R.color.text));
        mAry.setTextColor(getResources().getColor(R.color.text));
        mBrae.setTextColor(getResources().getColor(R.color.text));
        mRan.setTextColor(getResources().getColor(R.color.text));
    }

}
