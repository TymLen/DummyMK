package com.ict376.tym.dummymk.ui.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.ict376.tym.dummymk.R;
import com.ict376.tym.dummymk.ui.game.GameFrag;

public class GameHost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_host_activity);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String hero = extras.getString("name");
            int days = extras.getInt("days");
            int nights = extras.getInt("nights");
            GameFrag _gameFrag = GameFrag.newInstance(hero, days+nights, days, nights);
            FragmentManager fragman = getSupportFragmentManager();
            FragmentTransaction fragTran = fragman.beginTransaction();
            fragTran.replace(R.id.container, _gameFrag);
            fragTran.commit();
        }
        else{
            GameFrag _gameFrag = GameFrag.newInstance();
            FragmentManager fragman = getSupportFragmentManager();
            FragmentTransaction fragTran = fragman.beginTransaction();
            fragTran.replace(R.id.container, _gameFrag);
            fragTran.commit();
        }

    }
}
