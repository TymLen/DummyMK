package com.ict376.tym.dummymk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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

import com.ict376.tym.dummymk.ui.game.EoRListener;

import androidx.fragment.app.DialogFragment;

public class EoRDialog extends DialogFragment   {
    private Spinner inMana;
    private Spinner inCard;
    private Button mConfirm, mCancel;
    private EoRListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.eor_dialog, container);
        inMana = (Spinner) view.findViewById(R.id.mana_choice);
        inCard = (Spinner) view.findViewById(R.id.card_choice);
        mConfirm = (Button) view.findViewById(R.id.confirm_button);
        mCancel = (Button) view.findViewById(R.id.cancel_button);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inMana.getSelectedItemPosition()!= 0 && inCard.getSelectedItemPosition() !=0){
                    Intent intent = new Intent();
                    intent.putExtra("mana", inMana.getSelectedItem().toString());
                    intent.putExtra("card", inCard.getSelectedItem().toString());
                    getTargetFragment().onActivityResult(getTargetRequestCode(), 1, intent);
                    getDialog().dismiss();
                }
            }
        });

        getDialog().setTitle("End of Round");
        inMana.requestFocus();
        return view;
    }


}
