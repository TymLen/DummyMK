package com.ict376.tym.dummymk.ui.score;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ict376.tym.dummymk.R;
import com.ict376.tym.dummymk.ui.database.ScoreData;

import java.util.ArrayList;

public class ScoreAdapter extends BaseAdapter {
    private ArrayList<String> listScore, listScene, listDate, listHero;
    Context mContext;

    public int getCount(){return listScore.size();}
    public long getItemId(int position){return position;}
    public Object getItem(int arg0){return null;}
    public ScoreAdapter(Context context, ArrayList<String> inScore, ArrayList<String> inScene, ArrayList<String> inDate, ArrayList<String> inHero){
        mContext = context;
        listScore = inScore;
        listScene = inScene;
        listDate = inDate;
        listHero = inHero;
    }
    @Override
    public View getView(int position, View v, ViewGroup parent){
        final int pos = position;
        LayoutInflater inflater = (LayoutInflater.from(mContext));
        v = inflater.inflate(R.layout.score_row, null);
        final TextView mScore = (TextView) v.findViewById(R.id.score_list_score);
        mScore.setText(listScore.get(pos));
        final TextView mScene = (TextView) v.findViewById(R.id.score_list_scenario);
        mScene.setText(listScene.get(pos));
        final TextView mDate = (TextView) v.findViewById(R.id.score_list_date);
        mDate.setText(listDate.get(pos));
        final Button mDelete = (Button) v.findViewById(R.id.score_list_del);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreData db = new ScoreData(mContext);
                try{
                    int confirm = db.deleteEntry(listDate.get(pos), listHero.get(pos), listScore.get(pos), listScene.get(pos));
                    if(confirm == 0){
                        Toast.makeText(mContext, "Delete entry failed.", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(mContext, "Entry deleted", Toast.LENGTH_LONG).show();

                    }
                }catch(Exception e){
                    Toast.makeText(mContext, "Delete entry failed.", Toast.LENGTH_LONG).show();
                }finally{
                    db.close();
                    updateList();
                }

            }
        });
        final ImageView mHero = (ImageView) v.findViewById(R.id.score_list_hero);
        switch(listHero.get(pos)){
            case("Select"):
                mHero.setImageResource(R.drawable.random);
                break;
            case("Goldyx"):
                mHero.setImageResource(R.drawable.goldyx);
                break;
            case("Braevalar"):
                mHero.setImageResource(R.drawable.braevalar);
                break;
            case("Tovak"):
                mHero.setImageResource(R.drawable.tovak);
                break;
            case("WolfHawk"):
                mHero.setImageResource(R.drawable.wolfhawk);
                break;
            case("Krang"):
                mHero.setImageResource(R.drawable.krang);
                break;
            case("Norowas"):
                mHero.setImageResource(R.drawable.norowas);
                break;
            case("Arythea"):
                mHero.setImageResource(R.drawable.arythea);
                break;
            default:
                mHero.setImageResource(R.drawable.random);
                break;
        }
        return v;
    }
    private void updateList(){
        Log.d("Refresh", "Refreshed");
        this.notifyDataSetChanged();
    }
}
