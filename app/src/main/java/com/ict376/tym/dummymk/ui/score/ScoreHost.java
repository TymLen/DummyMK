package com.ict376.tym.dummymk.ui.score;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ict376.tym.dummymk.R;

public class ScoreHost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_host_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ScoreFragment.newInstance())
                    .commitNow();
        }
    }
}
