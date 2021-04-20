package com.example.cashregisterapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class CashHandler extends AppCompatDialogFragment{

    private EditText billTv;
    private TextView change;
    private TextView totaltv;
    private  Double total;
    public static double total_taxable=0.0;
    public static double total_nontaxable=0.0;
    public static double total_tax=0.0;
    static boolean status= false;


    public CashHandler (Double tot){
        this.total=tot;

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cashhandler, null);
        totaltv= (TextView) view.findViewById(R.id.textView8t);
        change=(TextView) view.findViewById(R.id.change11);
        billTv =(EditText) view.findViewById(R.id.bill11);

        totaltv.setText("Total: $ "+ String.format("%.2f",total));

        billTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String tc = billTv.getText().toString();

                if (tc.length() >0){
                    double bill = Double.parseDouble(tc);
                    if (bill < total) {
                        double val = total - bill;
                        change.setText("Additional: "+ String.format("%.2f", val));
                    }
                    if (bill >= total) {
                        double cnge = bill - total;
                        change.setText("Change: $" + String.format("%.2f", cnge));
                    }

                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //final TextView etName = (EditText) yourCustomView.findViewById(R.id.EditZip);
        dialog.setView(view)
                .setView(view)
                .setPositiveButton("Cash", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        total_taxable +=getTaxableSale();
                        total_nontaxable +=getnonTaxSale();
                        total_tax += getAllTax();


                        MainActivity.items.clear();
                        MainActivity.it.notifyDataSetChanged();
                        MainActivity.totSale.setText("Total : $ 0.00");
                        MainActivity.totTaxx.setText("Total: $0.00");
                    }
                });


        return dialog.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public double getTaxableSale(){
        double taxSale=0;
        for(int i = 0; i< MainActivity.items.size(); i++){
            taxSale+= MainActivity.items.get(i).getTaxable();
        }

        return taxSale;

    }

    public double getnonTaxSale(){
        double nontaxSale=0;
        for(int i = 0; i< MainActivity.items.size(); i++){
            nontaxSale+= MainActivity.items.get(i).getNontax();
        }

        return nontaxSale;

    }

    public double getAllTax(){
        double totTax=0;
        for(int i = 0; i< MainActivity.items.size(); i++){
            totTax += MainActivity.items.get(i).getTax();
        }

        return totTax;
    }

}
