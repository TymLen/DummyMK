package com.ict376.tym.dummymk.ui.game;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ict376.tym.dummymk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EndGame_dialog extends DialogFragment {
    private Spinner mInHero, mInScene;
    private EditText mInFame, mInKnow, mInLoot, mInLead, mInCon, mInAdv, mInBeat, mInSS;
    private TextView mTotal;
    private Button mCancel, mSubmit;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable(){
        @Override
        public void run(){
            totalScore();
            handler.postDelayed(this, 1000);
        }
    };


    public EndGame_dialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_end_game_dialog, container, false);
        mInFame = (EditText) v.findViewById(R.id.end_fameEnter);
        mInKnow = (EditText) v.findViewById(R.id.end_knowEnter);
        mInLoot = (EditText) v.findViewById(R.id.end_lootEnter);
        mInLead = (EditText) v.findViewById(R.id.end_leadEnter);
        mInCon = (EditText) v.findViewById(R.id.end_conEnter);
        mInAdv = (EditText) v.findViewById(R.id.end_advEnter);
        mInBeat = (EditText) v.findViewById(R.id.end_beatEnter);
        mInSS = (EditText) v.findViewById(R.id.end_ssEnter);
        mTotal = (TextView) v.findViewById(R.id.end_totalView);
        mCancel = (Button) v.findViewById(R.id.end_canBut);
        mSubmit = (Button) v.findViewById(R.id.end_submit);
        mInHero = (Spinner) v.findViewById(R.id.end_herospin);
        mInScene = (Spinner) v.findViewById(R.id.scenSpin);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        handler.postDelayed(runnable, 1000);


        return v;
    }
    private void totalScore(){
        int total = 0;
        try{
            total += Integer.parseInt(mInFame.getText().toString());
            total += Integer.parseInt(mInKnow.getText().toString());
            total += Integer.parseInt(mInLoot.getText().toString());
            total += Integer.parseInt(mInLead.getText().toString());
            total += Integer.parseInt(mInCon.getText().toString());
            total += Integer.parseInt(mInAdv.getText().toString());
            if(Integer.parseInt(mInBeat.getText().toString()) <0){
                total -=Integer.parseInt(mInBeat.getText().toString());
            }else{
                total += Integer.parseInt(mInBeat.getText().toString());
            }
            total += Integer.parseInt(mInSS.getText().toString());
            mTotal.setText(Integer.toString(total));
        }catch(Exception e){
            Log.d("Crash", "Total");
        }

    }
    private void saveScoreEndGame(){

    }

}
