package com.example.cashregisterapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class refund extends AppCompatDialogFragment {

    private EditText refPrice;
    private Spinner spinner;
    String spinselect="Taxable";
    private static final String[] itm = {"Taxable", "Non-Taxable"};

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.refunds, null);
        //date = (TextView) view.findViewById(R.id.dt);

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_dropdown_item_1line,itm);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                spinselect = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        refPrice = (EditText) view.findViewById(R.id.refp11);



        dialog.setView(view)
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String refp =refPrice.getText().toString();
                        double refpd=0.00;

                        if(refp.length()>0){
                            refpd= Double.parseDouble(refp);
                        }

                        if(spinselect.equals("Taxable")){
                            CashHandler.total_taxable-=refpd;
                            CashHandler.total_tax-=0.0825* refpd;
                            Toast.makeText(getContext(),"Refund Sucessful",Toast.LENGTH_SHORT).show();
                        }
                        else if(spinselect.equals("Non-Taxable")){
                            CashHandler.total_nontaxable-=refpd;
                            Toast.makeText(getContext(),"Refund Sucessful",Toast.LENGTH_SHORT).show();
                        }



                    }
                });


        return dialog.create();
    }


}
