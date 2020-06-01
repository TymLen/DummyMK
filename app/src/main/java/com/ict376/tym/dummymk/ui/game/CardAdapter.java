package com.ict376.tym.dummymk.ui.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ict376.tym.dummymk.R;

import java.util.ArrayList;

public class CardAdapter extends BaseAdapter {
    private ArrayList<String> hist;
    private ArrayList<String> icon;
    Context mContext;

    public int getCount(){return hist.size();}
    public long getItemId(int position){return position;}
    public Object getItem(int arg0){return null;}
    public CardAdapter(Context context, ArrayList<String> inHist, ArrayList<String> inMana){
        mContext = context;
        hist = inHist;
        icon = inMana;
    }
    @Override
    public View getView(int position, View v, ViewGroup parent){
        final int pos = position;
        LayoutInflater inflater;
        inflater = (LayoutInflater.from(mContext));
        v = inflater.inflate(R.layout.history_row, null);
        final ImageView mManaView = v.findViewById(R.id.manaIcon);
        final TextView mHistText = v.findViewById(R.id.histText);
        mHistText.setText(hist.get(position));
        if(icon.get(position).equals("cookie")){
            mManaView.setImageResource(R.drawable.cookie);
        }else if(icon.get(position).equals("Red")){
            mManaView.setImageResource(R.drawable.red_mana);
        }else if(icon.get(position).equals("Blue")){
            mManaView.setImageResource(R.drawable.blue_mana);
        }else if(icon.get(position).equals("Green")){
            mManaView.setImageResource(R.drawable.green_mana);
        }else if(icon.get(position).equals("White")){
            mManaView.setImageResource(R.drawable.white_mana);
        }else{
            mManaView.setImageResource(R.drawable.cookie);
        }

        return v;
    }
}
