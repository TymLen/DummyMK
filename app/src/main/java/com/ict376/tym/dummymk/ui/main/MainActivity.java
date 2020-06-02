package com.ict376.tym.dummymk.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ict376.tym.dummymk.R;
import com.ict376.tym.dummymk.ui.main.MenuFrag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MenuFrag.newInstance())
                    .commitNow();
        }
    }
}
