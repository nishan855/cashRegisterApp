package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class allItems extends AppCompatDialogFragment {

   private EditText et;
   private EditText prc;
    private EditText qntt;
   private DialogListen listener;
   private String pressed;
   public allItems (String btn){
       this.pressed=btn;
   }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_main, null);

        //final TextView etName = (EditText) yourCustomView.findViewById(R.id.EditZip);
        dialog.setView(view)
                .setTitle("Insert Item")
                .setView(view)
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String item= et.getText().toString();
                        String price= prc.getText().toString();
                        String quant= qntt.getText().toString();

                        int quan=0;

                        if(quant.length()>0){
                        quan= Integer.parseInt(quant);
                        }


                        if(price.length()==0){
                            price="0.00";
                        }
                        double pr= Double.parseDouble(price);
                        String detPrice = String.format("%.2f",pr);
                        if(quan != 1){
                            detPrice = quan+"x "+ detPrice;
                        }
                        double tax= 0;
                        double taxable=0.0;
                        double nontax=0.0;

                        double total=quan*pr;
                        //calculating tax value

                        if (pressed.equals("tax")||pressed.equals("beer")||pressed.equals("cigg"))
                        {   taxable=total;
                            tax= 0.0825 * total;
                            total +=tax;
                            detPrice =detPrice+" T";
                        }
                        else {
                            nontax=total;
                        }

                        listener.applyTexts(item,detPrice,tax,total,taxable,nontax);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

        prc= (EditText) view.findViewById(R.id.refp11);
        qntt= (EditText) view.findViewById(R.id.quant);
        et=(EditText) view.findViewById(R.id.editTextTextPersonName2);

        return dialog.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListen) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

public interface DialogListen {
    void applyTexts(String item, String price, double tax, double total,double taxable, double nontax);
}
}