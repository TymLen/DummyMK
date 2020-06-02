package com.ict376.tym.dummymk.ui.game;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ict376.tym.dummymk.R;
import com.ict376.tym.dummymk.ui.database.DummyData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GameFrag extends Fragment {

    private GameViewModel mViewModel;
    private static String hero;
    private Map<String, String> mana = new HashMap<>();
    private Stack<Card> Deck = new Stack<>();
    private Stack<Card> actDeck = new Stack<>();
    private int turn = 1;
    private ArrayList<String> history = new ArrayList<>();
    private ListView mHistoryList;
    private TextView decksize;
    private Button mdrawBut, mEoRBut, mEndBut;
    private int round = 1;
    private ArrayList<String> manaList = new ArrayList<>();
    private static int numRounds;
    private TextView red,blue,green,white, mNumRound, mRoundEnd;
    private LinearLayout mBack;
    private int rCard = 4, gCard = 4, wCard = 4, bCard = 4;
    private static int days, nights;
    private static boolean loadgame;

    public static GameFrag newInstance(String dummy, int rounds, int inDays, int inNights) {
        Log.d("Hero", Integer.toString(dummy.length()));
        days = inDays;
        nights = inNights;
        hero = dummy;
        numRounds = rounds;
        return new GameFrag();
    }
    public static GameFrag newInstance(){
        loadgame = true;
        return new GameFrag();
    }
    //(int gMana, int rMana, int bMana, int wMana, int gCard, int rCard, int bCard, int wCard)


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_fragment, container, false);
        int success = 0;
        mdrawBut = (Button) view.findViewById(R.id.drawBut);
        TextView mHero = (TextView) view.findViewById(R.id.heroText);
        mBack = (LinearLayout) view.findViewById(R.id.game);
        red = (TextView) view.findViewById(R.id.redView);
        blue = (TextView) view.findViewById(R.id.blueView);
        green = (TextView) view.findViewById(R.id.greenView);
        white = (TextView) view.findViewById(R.id.whiteView);
        mHistoryList = (ListView) view.findViewById(R.id.historyList);
        decksize = (TextView) view.findViewById(R.id.cardsLeftNum);
        mEoRBut = (Button) view.findViewById(R.id.nextRoundBut);
        mRoundEnd = (TextView) view.findViewById(R.id.endText);
        mNumRound = (TextView) view.findViewById(R.id.roundNum);
        mEoRBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNext();
            }
        });

        if(loadgame){
            success = loadGame();
        }else{
            setMana();
            updateMana();
            createDeck();
        }
        if(success == 0){
            Toast.makeText(getContext(), "Error loading save data", Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
        mRoundEnd.setText(Integer.toString(numRounds));
        mNumRound.setText(Integer.toString(round));
        mHero.setText(hero);

        mdrawBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCard();
            }
        });
        updateHistory("Round "+round+" Start. "+(numRounds-round)+" rounds remaining.","cookie");
        return view;
    }
    private int loadGame(){
        DummyData db = new DummyData(getContext());
        Cursor cursor = db.loadGame();
        cursor.moveToFirst();
        try{
            days = cursor.getInt(cursor.getColumnIndex("DAYS"));
            nights = cursor.getInt(cursor.getColumnIndex("NIGHTS"));
            hero = cursor.getString(cursor.getColumnIndex("HERO"));
            numRounds = days+nights;
            round = cursor.getInt(cursor.getColumnIndex("ROUND"));
            mana.put("Green", cursor.getString(cursor.getColumnIndex("GREENMANA")));
            mana.put("White", cursor.getString(cursor.getColumnIndex("WHITEMANA")));
            mana.put("Blue", cursor.getString(cursor.getColumnIndex("BLUEMANA")));
            mana.put("Red", cursor.getString(cursor.getColumnIndex("REDMANA")));
            for(int i = 0; i< cursor.getInt(cursor.getColumnIndex("REDCARD")); i++){
                addCard("Red");
            }
            for(int i = 0; i< cursor.getInt(cursor.getColumnIndex("GREENCARD")); i++){
                addCard("Green");
            }
            for(int i = 0; i< cursor.getInt(cursor.getColumnIndex("WHITECARD")); i++){
                addCard("White");
            }
            for(int i = 0; i< cursor.getInt(cursor.getColumnIndex("BLUECARD")); i++){
                addCard("Blue");
            }
            return 1;
        }catch(Exception e){
            return 0;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
    }

    private void setMana(){
        mana.put("Green", "0");
        mana.put("White", "0");
        mana.put("Blue", "0");
        mana.put("Red", "0");
        switch(hero){
            case "Goldyx":
                mana.put("Green", "2");
                mana.put("Blue", "1");
                break;
            case "Braevalar":
                mana.put("Blue", "2");
                mana.put("Green", "1");
                break;
            case "Tovak":
                mana.put("Blue", "2");
                mana.put("Red", "1");
                break;
            case "WolfHawk":
                mana.put("White", "2");
                mana.put("Blue", "1");
                break;
            case "Krang":
                mana.put("Red", "2");
                mana.put("Green", "1");
                break;
            case "Norowas":
                mana.put("White", "2");
                mana.put("Green", "1");
                break;
            case "Arythea":
                mana.put("Red", "2");
                mana.put("White", "1");
                break;
        }
    }
    private void createDeck(){
        mdrawBut.setEnabled(true);
        for(int i = 0; i< 4; i++){
            addCard("Red");
            addCard("White");
            addCard("Green");
            addCard("Blue");
        }
        Collections.shuffle(Deck);
        actDeck = (Stack)Deck.clone();
        updateSize();
    }
    private void addCard(String color){
        if(color == "Red"){
            rCard++;
        }else if(color == "Green"){
            gCard++;
        }else if(color == "White"){
            wCard++;
        }else if(color == "Blue"){
            bCard++;
        }

        Card addThis = new Card(color);
        Deck.add(addThis);
    }
    private void drawCard(){
        int cardsPopped = 0;
        if(actDeck.size() == 0){
            updateHistory("Turn "+turn+": AI Calls end of Round", "cookie");
            mdrawBut.setEnabled(false);
            mdrawBut.setTextColor(getResources().getColor(R.color.black));
        }else if(actDeck.size() < 3){
            for(int i = 0; i< actDeck.size()+1; i++){
                actDeck.pop();
                cardsPopped++;
                turn++;
            }
            updateHistory("Turn"+turn+": AI drew "+cardsPopped+" cards and will call end of round next turn", "cookie");
        }else{
            actDeck.pop();
            cardsPopped++;
            actDeck.pop();
            cardsPopped++;
            Card tempCard = actDeck.pop();
            cardsPopped++;
            String cardColor = tempCard.getColor();
            String num = mana.get(tempCard.getColor());
            if(Integer.parseInt(num) != 0){
                for(int i = 0; i < Integer.parseInt(num); i++){
                    if(actDeck.size() != 0) {
                        actDeck.pop();
                        cardsPopped++;
                    }
                }
            }
            if(actDeck.size() == 0){
                updateHistory("Turn "+turn+": AI drew "+cardsPopped+" cards and will call end of round next turn", "cookie");
                turn++;
            }else{
                Log.d("Cards", Integer.toString(cardsPopped));
                updateHistory("Turn "+turn+": Dummy drew "+cardsPopped+" cards", cardColor);
                turn++;
            }
        }
        updateSize();
    }
    private void updateHistory(String update, String manaAdd){

        history.add(update);
        manaList.add(manaAdd);
        CardAdapter cardAdapter = new CardAdapter(getContext(), history, manaList);
        mHistoryList.setAdapter(cardAdapter);
        mHistoryList.setSelection(cardAdapter.getCount() -1);
    }
    private void updateSize(){
        decksize.setText(Integer.toString(actDeck.size()));
    }

    private void startNext(){
        if(numRounds==round){
            Toast.makeText(getContext(), "Out of rounds", Toast.LENGTH_SHORT).show();
        }else{
            FragmentManager fm = getActivity().getSupportFragmentManager();
            EoRDialog dia = new EoRDialog();
            dia.setTargetFragment(this, 1);
            dia.show(fm, "EoR_Dialog");
            turn = 1;
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        String card = data.getStringExtra("card");
        String inMana = data.getStringExtra("mana");
        addCard(card);
        int maNum = Integer.parseInt(mana.get(inMana));
        maNum++;
        mana.put(inMana, Integer.toString(maNum));
        updateMana();
        round++;
        if(round % 2== 0){
            mBack.setBackground((ContextCompat.getDrawable((getContext()), R.drawable.night)));
        }else{
            mBack.setBackground((ContextCompat.getDrawable((getContext()), R.drawable.daytime)));
        }
        history.clear();
        manaList.clear();
        updateHistory("Round "+round+" Start. "+(numRounds-round)+" rounds remaining.", "cookie");
        mNumRound.setText(Integer.toString(round));
        newRound();
        saveGame();
    }

    private void saveGame(){
        //(int gMana, int rMana, int bMana, int wMana, int gCard, int rCard, int bCard, int wCard)
        int gMana = Integer.parseInt(mana.get("Green"));
        int rMana = Integer.parseInt(mana.get("Red"));
        int bMana = Integer.parseInt(mana.get("Blue"));
        int wMana = Integer.parseInt(mana.get("White"));
        DummyData db = new DummyData(getContext());
        int success = db.saveGame(gMana, rMana, bMana, wMana, gCard, rCard, bCard, wCard, days, nights, round, hero);
        if(success == 1){Toast.makeText(getActivity(), "Game Saved.", Toast.LENGTH_LONG).show();}else{
            Toast.makeText(getActivity(), "Game failed to save.", Toast.LENGTH_LONG).show();
        }


    }
    private void newRound(){
        mdrawBut.setEnabled(true);
        mdrawBut.setTextColor(getResources().getColor(R.color.text));
        Collections.shuffle(Deck);
        actDeck.empty();
        actDeck = (Stack)Deck.clone();
        updateSize();
    }
    private void updateMana(){
        red.setText(mana.get("Red"));
        blue.setText(mana.get("Blue"));
        green.setText(mana.get("Green"));
        white.setText(mana.get("White"));
    }
}
