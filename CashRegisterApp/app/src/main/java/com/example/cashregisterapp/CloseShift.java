package com.example.cashregisterapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CloseShift extends AppCompatDialogFragment {

    private TextView date;
    static TextView txsale;
    static TextView ntxSale;
    static TextView tx;
    static TextView total;

    private String txs;
    private String ntxs;
    private String txg;
    private String totl;


    public CloseShift(String a,String b, String c, String d) {
        this.ntxs=a;
        this.txs=b;
        this.txg=c;
        this.totl=d;


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.report, null);
        date = (TextView) view.findViewById(R.id.dt);
        txsale = (TextView) view.findViewById(R.id.txsale);
        ntxSale = (TextView) view.findViewById(R.id.nontxsale);
        tx=  (TextView) view.findViewById(R.id.taxcol);
        total = (TextView) view.findViewById(R.id.totsale);


        date.setText(java.time.LocalDate.now()+"");
        txsale.setText("Taxable Sales: "+txs);
        ntxSale.setText("Non-Taxable Sales: "+ntxs);
        tx.setText("Tax Collected: "+txg);
        total.setText("Total: "+totl);

        dialog.setView(view)
                .setView(view)
                .setPositiveButton("Close Shift", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                     if(A.items.isEmpty()){
                         CashHandler.total_nontaxable=0.0;
                         CashHandler.total_tax=0.0;
                         CashHandler.total_taxable=0.0;
                         Toast.makeText(getContext(),"Shift Closed !!",Toast.LENGTH_SHORT).show();
                     }

                     else{
                         Toast.makeText(getContext(),"***Finish transaction first***",Toast.LENGTH_SHORT).show();
                     }

                    }
                });


        return dialog.create();
    }



}

