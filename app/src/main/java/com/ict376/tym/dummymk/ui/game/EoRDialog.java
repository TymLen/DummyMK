package com.ict376.tym.dummymk.ui.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.ict376.tym.dummymk.R;

import androidx.fragment.app.DialogFragment;

public class EoRDialog extends DialogFragment   {
    private Spinner inMana;
    private Spinner inCard;
    private Button mConfirm, mCancel;

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
