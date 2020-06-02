package com.ict376.tym.dummymk.ui.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ict376.tym.dummymk.R;
import com.ict376.tym.dummymk.ui.main.MainFragment;
import com.ict376.tym.dummymk.ui.main.MenuFrag;

public class GameContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_container);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.game_container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}
